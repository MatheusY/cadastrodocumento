package br.com.cadastrodocumento.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.service.UsuarioService;
import br.com.cadastrodocumento.vo.UsuarioVO;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public String login(@RequestBody UsuarioVO usuario){
		String token = usuarioService.login(usuario.getUsuario(), usuario.getSenha());
		if(Objects.isNull(token)){
			return "Usuário/Senha inválido!";
		}
		return token;
	}
}
