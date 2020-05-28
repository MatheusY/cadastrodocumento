package br.com.cadastrodocumento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController extends AbstractController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
//	public Long save(@RequestBody @Valid UsuarioVO usuarioVO) {
//		return usuarioService.salvar(convertVOToEntity(usuarioVO, Usuario.class)).getId();
//}
	public Long save(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha,
			@RequestParam("email") String email) {
		Usuario u = new Usuario();
		u.setUsuario(usuario);
		u.setSenha(senha);
		u.setEmail(email);
		return usuarioService.salvar(u).getId();
	}

}
