package br.com.ada.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.GetUsuarioDTO;
import br.com.ada.entidade.Usuario;
import br.com.ada.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    // INJETANDO A DEPENDENCIA
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Integer criarNovoUsuario(CadastroDTO dto) {
        String senhaCriptografada = criptografarSenha(dto.getSenha());

        Usuario novoUsuario = new Usuario(null,
                dto.getNome(),
                dto.getIdade(),
                dto.getEmail(),
                senhaCriptografada,
                dto.getCpf());

        novoUsuario.setDataCadastro(LocalDate.now());

        Usuario usuarioSalvo = repository.save(novoUsuario);

        return usuarioSalvo.getId();
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

        // gerenciar data ultima atualização


        Usuario usuarioEncontrado = repository.findById(id)
                .orElseThrow();

        if (dto.getCpf() != null) {
            usuarioEncontrado.setCpf(dto.getCpf());
        }

        if (dto.getEmail() != null) {
            usuarioEncontrado.setEmail(dto.getEmail());
        }

        if (dto.getNome() != null) {
            usuarioEncontrado.setNome(dto.getNome());
        }

        if (dto.getIdade() != usuarioEncontrado.getIdade()) {
            usuarioEncontrado.setIdade(dto.getIdade());
        }

        if (dto.getSenha() != null) {
            String senhaCriptografada = criptografarSenha(dto.getSenha());
            usuarioEncontrado.setSenha(senhaCriptografada);
        }

        repository.save(usuarioEncontrado);
    }

    private String criptografarSenha(String senha) {
        return BCrypt.withDefaults()
                .hashToString(12, senha.toCharArray());
    }
}
