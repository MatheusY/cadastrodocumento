package br.com.cadastrodocumento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.UfDTO;
import br.com.cadastrodocumento.service.UfService;

@RestController
@RequestMapping("/uf")
public class UfController extends AbstractController{

	@Autowired
	private UfService ufService;
	
	@GetMapping("/all")
	public List<UfDTO> getUfs(){
		return convertToDTO(ufService.listAll(), UfDTO.class);
	}
}
