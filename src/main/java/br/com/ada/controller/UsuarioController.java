package br.com.ada.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.GetUsuarioDTO;
import br.com.ada.entidade.Usuario;
import br.com.ada.repository.UsuarioRepository;
import br.com.ada.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    //INVERSAO DE CONTROLE
    /**
     * CRIAR METODO ALTERAR USUARIO
     * CRIAR METODO DELETAR USUARIO POR ID
     *
     * O PROJETO TEM QUE RODAR NA PORTA 8081
     */

    private UsuarioService service;

    // INJETANDO A DEPENDENCIA
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // CONTROLLER
    // SERVICE
    // REPOSITORY


    // metodo http
    // metodo java

    // insert into USUARIO(nome, idade) values ('jorge', 28)
    // create, update, delete, findById

    // buscar usuario por id
    @GetMapping("/{id}")
    public GetUsuarioDTO buscarUsuario(@PathVariable int id) {
        GetUsuarioDTO usuarioEncontrado = service.buscarUsuarioPorId(id);

        return usuarioEncontrado;
    }

    // cadastrar novo usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer cadastrarNovo(@RequestBody CadastroDTO dto) {
        Integer novoId = service.criarNovoUsuario(dto);

        return novoId;
    }

    // buscar todos os usuários
    @GetMapping
    public List<GetUsuarioDTO> obterTodos() {
        List<GetUsuarioDTO> getUsuarioDTOS = service.obterTodos();

        return getUsuarioDTOS;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable int id, @RequestBody CadastroDTO dto) {
        service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
    }

}
