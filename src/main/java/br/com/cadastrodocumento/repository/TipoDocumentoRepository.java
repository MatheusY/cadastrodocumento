package br.com.cadastrodocumento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastrodocumento.models.entity.TipoDocumento;

public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento, Short>{

}
