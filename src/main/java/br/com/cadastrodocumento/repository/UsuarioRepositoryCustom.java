package br.com.cadastrodocumento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.vo.FiltroUsuarioVO;

public interface UsuarioRepositoryCustom {

	Page<Usuario> filtro(FiltroUsuarioVO filtro, Pageable pageable) throws AbstractException;
}
