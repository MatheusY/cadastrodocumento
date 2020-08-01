package br.com.cadastrodocumento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.TipoDocumento;
import br.com.cadastrodocumento.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
	
	private static final String TIPO_DOCUMENTO_NAO_ENCONTRADO = "Tipo de documento não encontrado!";
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	public Iterable<TipoDocumento> listarTodos(){
		return tipoDocumentoRepository.findAll();
	}

	public TipoDocumento salvar(TipoDocumento tipo) {
		return tipoDocumentoRepository.save(tipo);
	}
	
	public void atualizar(TipoDocumento tipo) throws AbstractException {
		if (tipoDocumentoRepository.existsById(tipo.getId())) {
			tipoDocumentoRepository.save(tipo);
		} else {
			throw new AbstractException(TIPO_DOCUMENTO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}
}
