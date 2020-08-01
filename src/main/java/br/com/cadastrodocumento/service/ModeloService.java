package br.com.cadastrodocumento.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.dto.FiltroModeloDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Modelo;
import br.com.cadastrodocumento.models.entity.TipoDocumento;
import br.com.cadastrodocumento.repository.ModeloRepository;
import br.com.cadastrodocumento.repository.TipoDocumentoRepository;

@Service
public class ModeloService {

	private static final String MODELO_NÃO_ENCONTRADO = "Modelo não encontrado!";
	
	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	public Page<Modelo> filtraModelos(FiltroModeloDTO filtro, Pageable pageable) throws AbstractException {
			return modeloRepository.filtro(filtro, pageable);
	}

	public Modelo buscaPorId(Long id) throws AbstractException{
		return modeloRepository.findById(id).orElseThrow(() -> new AbstractException(MODELO_NÃO_ENCONTRADO, HttpStatus.NOT_FOUND));
	}

	public Modelo salvar(Modelo modelo) {
		TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(modelo.getTipoDocumento().getId()).get();
		modelo.setTipoDocumento(tipoDocumento);
		return modeloRepository.save(modelo);
	}

	public void salvarDocumento(Long id, InputStream imagem) throws AbstractException {
		try {
			if (modeloRepository.atualizarDocumento(id, imagem.readAllBytes()) == 0) {
				throw new AbstractException(MODELO_NÃO_ENCONTRADO, HttpStatus.NOT_FOUND);
			}
		} catch (IOException e) {
			throw new AbstractException("Problema na imagem", HttpStatus.BAD_REQUEST);
		}

	}

	public void deletar(Long id) throws AbstractException {
		if (modeloRepository.delete(id) == 0) {
			throw new AbstractException(MODELO_NÃO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

	public void atualizar(Modelo modelo) throws AbstractException {
		if (modeloRepository.existsById(modelo.getId())) {
			modeloRepository.save(modelo);
		} else {
			throw new AbstractException(MODELO_NÃO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
	}

}
