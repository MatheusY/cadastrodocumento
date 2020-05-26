package br.com.cadastrodocumento.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.service.ModeloService;

@RestController
@RequestMapping("/imagem")
public class ImagemController {
	
	@Autowired
	private ModeloService modeloService;
	
	@PostMapping("/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void save(@PathVariable("id") Long id, InputStream imagem) throws AbstractException {
		try {
			modeloService.salvarDocumento(id, imagem);
		}catch (ResponseStatusException e) {
			modeloService.deletar(id);
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, InputStream imagem) throws AbstractException {
		modeloService.salvarDocumento(id, imagem);
	}
}
