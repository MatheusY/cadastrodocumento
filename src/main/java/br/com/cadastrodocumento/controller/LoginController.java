package br.com.cadastrodocumento.controller;

import javax.validation.Valid;import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.service.UsuarioService;
import br.com.cadastrodocumento.vo.AuthVO;

@RestController
@RequestMapping("/auth")
public class LoginController extends AbstractController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String login(@RequestBody AuthVO usuario) throws AbstractException{
		String token = usuarioService.login(usuario.getUsuario(), usuario.getSenha());
		return token;
	}
	
	@PatchMapping("/reset-senha")
	public void resetSenha(@RequestBody String email) throws AbstractException {
		usuarioService.resetSenha(email);
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Long save(@RequestBody @Valid AuthVO usuarioVO) {
		return usuarioService.salvar(convertVOToEntity(usuarioVO, Usuario.class)).getId();
	}
	
	@PatchMapping("/validar-email/{id}")
	public void validar(@PathVariable("id") Long id, @RequestParam Long key) throws AbstractException{
		usuarioService.validarEmail(id, key);
	}
}
