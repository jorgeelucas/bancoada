package br.com.ada.repository;

import br.com.ada.entidade.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


    // findAllBy... nome, email
    List<Usuario> findAllByNome(String nome);

    Optional<Usuario> findByNomeAndEmail(String nome, String email);

    //GreateThan
    //LessThan

    List<Usuario> findByDataCadastroIsAfter(LocalDateTime ponto);
    List<Usuario> findByDataCadastroBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Usuario> findAllByNomeContainingIgnoreCase(String filtro);

}
