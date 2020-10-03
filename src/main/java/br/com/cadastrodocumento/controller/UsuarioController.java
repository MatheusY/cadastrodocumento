package br.com.cadastrodocumento.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.TokenDTO;
import br.com.cadastrodocumento.dto.UsuarioDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.service.UsuarioService;
import br.com.cadastrodocumento.vo.AtualizacaoSenhaVO;
import br.com.cadastrodocumento.vo.FiltroUsuarioVO;
import br.com.cadastrodocumento.vo.UsuarioVO;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController extends AbstractController {

	@Autowired
	private UsuarioService usuarioService;

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public TokenDTO update(@PathVariable("id") Long id, @RequestBody @Valid UsuarioVO usuarioVO, Principal principal) throws AbstractException {
		Usuario usuario = convertVOToEntity(usuarioVO, Usuario.class);
		usuario.setId(id);
		String token = usuarioService.update(usuario, principal.getName());
		return new TokenDTO(token);
	}
	
	@PatchMapping("/trocar-senha")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid AtualizacaoSenhaVO novaSenhaVO, Principal principal) throws AbstractException{
		usuarioService.atualizarSenha(novaSenhaVO.getSenha(), novaSenhaVO.getNovaSenha(), findByUsuario(principal.getName()));
	}

	@GetMapping("/perfil")
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
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Page<UsuarioDTO> findByFiltro(FiltroUsuarioVO filtro, Pageable page, Principal principal) throws AbstractException{
		return convertToDTO(usuarioService.findByFiltro(filtro, page, findByUsuario(principal.getName())), UsuarioDTO.class);
	}

}
