package br.com.cadastrodocumento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastrodocumento.dto.TipoDocumentoDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.TipoDocumento;
import br.com.cadastrodocumento.service.TipoDocumentoService;
import br.com.cadastrodocumento.vo.TipoDocumentoVO;

@RestController
@RequestMapping("/tipo-documento")
public class TipoDocumentoController extends AbstractController {
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	@GetMapping("/all")
	public List<TipoDocumentoDTO> findById(@PathVariable(value = "id") Long id) throws AbstractException {
		return convertToDTO(tipoDocumentoService.listarTodos(), TipoDocumentoDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Short save( @RequestBody @Valid TipoDocumentoVO tipoDocumentoVO) {
		return tipoDocumentoService.salvar(convertVOToEntity(tipoDocumentoVO, TipoDocumento.class)).getId();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Short id, @RequestBody @Valid TipoDocumentoVO tipoDocumentoVO) throws AbstractException {
		TipoDocumento tipoDocumento = convertVOToEntity(tipoDocumentoVO, TipoDocumento.class);
		tipoDocumento.setId(id);
		tipoDocumentoService.atualizar(tipoDocumento);
	}
	
}
