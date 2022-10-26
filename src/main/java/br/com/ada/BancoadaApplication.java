package br.com.ada;

import br.com.ada.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoadaApplication {

	@Autowired
	UsuarioRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BancoadaApplication.class, args);
	}
}
