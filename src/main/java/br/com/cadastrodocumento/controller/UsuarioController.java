package br.com.cadastrodocumento.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.UsuarioDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.service.UsuarioService;
import br.com.cadastrodocumento.vo.UsuarioVO;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController extends AbstractController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Long save(@RequestBody @Valid UsuarioVO usuarioVO) {
		return usuarioService.salvar(convertVOToEntity(usuarioVO, Usuario.class)).getId();
	}
//	public Long save(@RequestParam("usuario") String usuario, @RequestParam("senha") String senha,
//			@RequestParam("email") String email) {
//		Usuario u = new Usuario();
//		u.setUsuario(usuario);
//		u.setSenha(senha);
//		u.setEmail(email);
//		return usuarioService.salvar(u).getId();
//	}	

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public UsuarioDTO getPerfil(Principal principal) throws AbstractException {
		return convertToDTO(usuarioService.findByUsuario(principal.getName()), UsuarioDTO.class);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UsuarioDTO findById(@PathVariable("id") Long id, Principal principal) throws AbstractException {
		return convertToDTO(usuarioService.findById(id, principal.getName()), UsuarioDTO.class);
	}

}
