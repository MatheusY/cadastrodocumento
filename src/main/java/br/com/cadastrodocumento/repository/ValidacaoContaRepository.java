package br.com.cadastrodocumento.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.cadastrodocumento.models.entity.ValidacaoConta;

public interface ValidacaoContaRepository extends CrudRepository<ValidacaoConta, Long>{

	Optional<ValidacaoConta> findByLink(String link);

}
