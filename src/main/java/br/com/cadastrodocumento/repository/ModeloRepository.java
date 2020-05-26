package br.com.cadastrodocumento.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cadastrodocumento.models.entity.Modelo;

public interface ModeloRepository extends CrudRepository<Modelo, Long>, ModeloRepositoryCustom {

	@Modifying
	@Transactional
	@Query("UPDATE Modelo SET documento = :documento WHERE id = :id")
	int atualizarDocumento(@Param("id") Long id, @Param("documento") byte[] documento);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Modelo modelo where modelo.id = :id")
	int delete(@Param("id") Long id);



}
