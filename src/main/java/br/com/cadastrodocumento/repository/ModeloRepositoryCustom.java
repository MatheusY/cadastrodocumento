package br.com.cadastrodocumento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cadastrodocumento.dto.FiltroModeloDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Modelo;

public interface ModeloRepositoryCustom {

	Page<Modelo> filtro(FiltroModeloDTO filtro, Pageable pageable) throws AbstractException;
}
