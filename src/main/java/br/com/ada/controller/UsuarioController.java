package br.com.ada.controller;

import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.GetUsuarioDTO;
import br.com.ada.entidade.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    /**
     * CRIAR PROJETO WEB SIMPLES COM SPRING PARA USUARIOS
     * IMPORTAR PROJETO NA IDE
     * CRIAR A ROTA DE CADASTRAR E BUSCAR POR ID
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

        String senhaCriptografada = new StringBuilder(dto.getSenha())
                .reverse()
                .toString();

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

    // alterar usuário

    // deletar usuário

}
