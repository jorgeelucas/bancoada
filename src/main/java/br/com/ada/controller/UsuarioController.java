package br.com.ada.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.GetUsuarioDTO;
import br.com.ada.entidade.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    /**
     * CRIAR METODO ALTERAR USUARIO
     * CRIAR METODO DELETAR USUARIO POR ID
     *
     * O PROJETO TEM QUE RODAR NA PORTA 8081
     */


    // metodo http
    // metodo java

    private static final List<Usuario> usuarios = new ArrayList<>();

    // buscar usuario por id
    @GetMapping("/{id}")
    public GetUsuarioDTO buscarUsuario(@PathVariable int id) {
        Usuario usuarioEncontrado = usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);

        return new GetUsuarioDTO(usuarioEncontrado);
    }

    // cadastrar novo usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer cadastrarNovo(@RequestBody CadastroDTO dto) {
        // adicionar mais um no id
        Integer novoId = null;

        // obter ultimo ID
        if (usuarios.size() > 0) {
            Integer id = usuarios.get(usuarios.size() - 1).getId();
            novoId = ++id;
        } else {
            novoId = 1;
        }

        String senhaCriptografada = BCrypt.withDefaults()
                .hashToString(12, dto.getSenha().toCharArray());

        Usuario novoUsuario = new Usuario(novoId,
                dto.getNome(),
                dto.getIdade(),
                dto.getEmail(),
                senhaCriptografada,
                dto.getCpf());

        usuarios.add(novoUsuario);
        return novoId;
    }

    // buscar todos os usuários
    @GetMapping
    public List<GetUsuarioDTO> obterTodos() {
        return usuarios.stream()
                .map(GetUsuarioDTO::new)
                .toList();
    }

    @GetMapping("/verify/{id}")
    public Usuario getUsuarioPelaSenha(@PathVariable int id, @RequestBody String senha) {

        Usuario usuarioEncontrado = usuarios.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst()
                .orElse(null);


        BCrypt.Result verify = BCrypt.verifyer().verify(senha.toCharArray(), usuarioEncontrado.getSenha());
        if (verify.verified) {
            return usuarioEncontrado;
        } else {
            return null;
        }
    }

    // alterar usuário
    // - podem usar o dto de cadastro
    // - localhost:8081/usuario/1
    // - PATH
    // - {email: novo-email@jorge.com}
    // - status response: 200 OK

    // deletar usuário por id
    // - DELETE
    // - localhost:8081/usuarios/1
    // - status response: 204 (no content)

}
