package br.com.cadastrodocumento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.PerfilDTO;
import br.com.cadastrodocumento.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController extends AbstractController{

	@Autowired
	private PerfilService perfilService;
	
	@GetMapping("/all")
	public List<PerfilDTO> getPerfis(){
		return convertToDTO(perfilService.listAll(), PerfilDTO.class);
	}
}
