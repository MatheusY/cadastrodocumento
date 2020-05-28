package br.com.cadastrodocumento.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public String login(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha) {
		String token = usuarioService.login(usuario, senha);
		if(Objects.isNull(token)){
			return "Usuário/Senha inválido!";
		}
		return token;
	}
}
