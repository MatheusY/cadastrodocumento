package br.com.cadastrodocumento.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.FiltroModeloDTO;
import br.com.cadastrodocumento.dto.ModeloDTO;
import br.com.cadastrodocumento.dto.ModeloParcialDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Modelo;
import br.com.cadastrodocumento.service.ModeloService;
import br.com.cadastrodocumento.vo.ModeloVO;

@RestController
@RequestMapping("/modelo")
public class ModeloController extends AbstractController {
	
	@Autowired
	private ModeloService modeloService;

	@GetMapping
	public Page<ModeloParcialDTO> getDocumentos(FiltroModeloDTO filtro, Pageable pageable) throws AbstractException{
		return convertToDTO(modeloService.filtraModelos(filtro, pageable), ModeloParcialDTO.class);
	}
	
	@GetMapping("/{id}")
	public ModeloDTO findById(@PathVariable(value = "id") Long id) throws AbstractException {
		return convertToDTO(modeloService.buscaPorId(id), ModeloDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Long save( @RequestBody @Valid ModeloVO modeloVO) {
		return modeloService.salvar(convertVOToEntity(modeloVO, Modelo.class)).getId();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, @RequestBody @Valid ModeloVO modeloVO) throws AbstractException {
		Modelo modelo = convertVOToEntity(modeloVO, Modelo.class);
		modelo.setId(id);
		modeloService.atualizar(modelo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) throws AbstractException {
		modeloService.deletar(id);
	}
	
	
}
