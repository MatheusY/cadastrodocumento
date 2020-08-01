package br.com.cadastrodocumento.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastrodocumento.models.entity.Uf;

public interface UfRepository extends CrudRepository<Uf, Short>{

	Iterable<Uf> findByOrderBySiglaAsc();

}
