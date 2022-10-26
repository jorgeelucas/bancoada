package br.com.ada.controller;

import br.com.ada.dto.CadastroDTO;
import br.com.ada.dto.CadastroResponseDTO;
import br.com.ada.dto.GetUsuarioDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    // INJETANDO A DEPENDENCIA
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public GetUsuarioDTO buscarUsuario(@PathVariable int id) {
        GetUsuarioDTO usuarioEncontrado = service.buscarUsuarioPorId(id);

        return usuarioEncontrado;
    }

    @GetMapping("/buscar")
    public GetUsuarioDTO buscarUsuarioPorNome(
            @RequestParam("nome") String nome,
            @RequestParam("email") String email) {
        return service.buscarPorNomeEEmail(nome, email);
    }

    @GetMapping("/buscar/datas")
    public List<GetUsuarioDTO> buscarUsuarioPorNomeDatas() {
        return service.buscarDatas();
    }

    @GetMapping("/nome-filtro/{filtro}")
    public List<GetUsuarioDTO> buscarUsuarioPorNomeDatas(@PathVariable String filtro) {
        return service.buscarUsuarioFiltro(filtro);
    }

    // cadastrar novo usuário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CadastroResponseDTO cadastrarNovo(@RequestBody CadastroDTO dto) {
        return service.criarNovoUsuario(dto);
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
    public ResponseEntity<GetUsuarioDTO> alterar(@PathVariable int id, @RequestBody CadastroDTO dto) {
        service.alterar(id, dto);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .body(service.buscarUsuarioPorId(id));
    }

}
