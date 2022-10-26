package br.com.ada.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.CadastroResponseDTO;
import br.com.ada.dto.GetUsuarioDTO;
import br.com.ada.entidade.ContaCorrente;
import br.com.ada.entidade.Usuario;
import br.com.ada.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    // INJETANDO A DEPENDENCIA
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public CadastroResponseDTO criarNovoUsuario(CadastroDTO dto) {
        String senhaCriptografada = criptografarSenha(dto.getSenha());

        // 30 para expirar
        LocalDateTime localDateTime = LocalDateTime.now()
                .plusDays(30);

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setSaldo(dto.getContaCorrente().getSaldo());

        Usuario novoUsuario = new Usuario(null,
                dto.getNome(),
                dto.getIdade(),
                dto.getEmail(),
                senhaCriptografada,
                dto.getCpf(),
                contaCorrente,
                LocalDateTime.now(),
                System.currentTimeMillis(),
                localDateTime);

        Usuario usuarioSalvo = repository.save(novoUsuario);

        return new CadastroResponseDTO(usuarioSalvo);
    }

    public GetUsuarioDTO buscarUsuarioPorId(int id) {
        Usuario usuarioEncontrado = repository.findById(id)
                .orElseThrow();

        return new GetUsuarioDTO(usuarioEncontrado);
    }

    public List<GetUsuarioDTO> obterTodos() {
        Iterable<Usuario> usuarios = repository.findAll();

        List<GetUsuarioDTO> listaDeRetorno = new ArrayList<>();

        usuarios.forEach(usuario -> listaDeRetorno.add(new GetUsuarioDTO(usuario)));

        return listaDeRetorno;
    }

    public void deletar(int id) {
        repository.deleteById(id);
    }

    public void alterar(int id, CadastroDTO dto) {

        Usuario usuarioEncontrado = repository.findById(id)
                .orElseThrow();

        String cpfAlterado = usuarioEncontrado.getCpf();
        String emailAlterado = usuarioEncontrado.getEmail();
        String nomeAlterado = usuarioEncontrado.getNome();
        int idadeAlterada = usuarioEncontrado.getIdade();
        String senhaAlterada = usuarioEncontrado.getSenha();

        // MAPSTRUCT

        if (dto.getCpf() != null) {
            cpfAlterado = dto.getCpf();
        }

        if (dto.getEmail() != null) {
            emailAlterado = dto.getEmail();
        }

        if (dto.getNome() != null) {
            nomeAlterado = dto.getNome();
        }

        if (dto.getIdade() != idadeAlterada) {
            idadeAlterada = dto.getIdade();
        }

        if (dto.getSenha() != null) {
            // criptografar senha
            if (!BCrypt.verifyer()
                    .verify(dto.getSenha().toCharArray(), usuarioEncontrado.getSenha())
                    .verified) {
                String senhaCriptografada = criptografarSenha(dto.getSenha());
                senhaAlterada = senhaCriptografada;
            }
        }

        Usuario usuarioAlterado = new Usuario(usuarioEncontrado.getId(),
                nomeAlterado, idadeAlterada, emailAlterado,
                senhaAlterada, cpfAlterado, null, usuarioEncontrado.getDataCadastro(),
                System.currentTimeMillis(), usuarioEncontrado.getDataExpiracaoDaConta());

        repository.save(usuarioAlterado);
    }

    private String criptografarSenha(String senha) {
        return BCrypt.withDefaults()
                .hashToString(12, senha.toCharArray());
    }

    public List<GetUsuarioDTO> buscarUsuarioPorNome(String nome) {
        return repository.findAllByNome(nome).stream()
                .map(GetUsuarioDTO::new)
                .toList();
    }

    public GetUsuarioDTO buscarPorNomeEEmail(String nome, String email) {
        return repository.findByNomeAndEmail(nome, email)
                .map(GetUsuarioDTO::new)
                .orElse(null);
    }

    public List<GetUsuarioDTO> buscarUsuarioFiltro(String filtro) {
        return repository.findAllByNomeContainingIgnoreCase(filtro)
                .stream()
                .map(GetUsuarioDTO::new)
                .toList();
    }

    public List<GetUsuarioDTO> buscarDatas() {

        // 18/10/2022 00h00
        // 18/10/2022 21h05

        LocalDateTime anteontem = LocalDateTime.now()
                .minusDays(2)
                .truncatedTo(ChronoUnit.DAYS);

        LocalDateTime ontem = LocalDateTime
                .now()
                .withHour(9)
                .withMinute(00);

        return repository.findByDataCadastroBetween(anteontem, ontem)
                .stream()
                .map(GetUsuarioDTO::new)
                .toList();
    }
}
