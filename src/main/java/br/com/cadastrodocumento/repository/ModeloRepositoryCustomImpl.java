package br.com.cadastrodocumento.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import br.com.cadastrodocumento.dto.FiltroModeloDTO;
import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Modelo;
import br.com.cadastrodocumento.models.entity.Modelo_;
import br.com.cadastrodocumento.models.enumeration.TipoDocumentoEnum;

public class ModeloRepositoryCustomImpl implements ModeloRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Modelo> filtro(FiltroModeloDTO filtro, Pageable pageable) throws AbstractException{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Modelo> criteriaQuery = criteriaBuilder.createQuery(Modelo.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		CriteriaQuery<Modelo> multiselect = criteriaQuery.multiselect(root.get(Modelo_.id), root.get(Modelo_.uf),
				root.get(Modelo_.tipoDocumento), root.get(Modelo_.ano));
			List<Predicate> predicates = loadPredicates(filtro, criteriaBuilder, root);

		CriteriaQuery<Modelo> select = multiselect.where(predicates.toArray(new Predicate[predicates.size()]));
		Long count = count(filtro);
		if (count == 0) {
			return new PageImpl<>(Collections.emptyList(), pageable, count);
		}

		TypedQuery<Modelo> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<Modelo>(typedQuery.getResultList(), pageable, count);
	}

	private Long count(FiltroModeloDTO filtro) throws AbstractException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Modelo> root = criteriaQuery.from(Modelo.class);
		List<Predicate> predicates = loadPredicates(filtro, criteriaBuilder, root);
		CriteriaQuery<Long> count = criteriaQuery.select(criteriaBuilder.count(root.get(Modelo_.id)));
		count.where(predicates.toArray(new Predicate[predicates.size()]));

		return entityManager.createQuery(count).getSingleResult();
	}
	
	private List<Predicate> loadPredicates(FiltroModeloDTO filtro, CriteriaBuilder criteriaBuilder, Root<Modelo> root) throws AbstractException{
		List<Predicate> predicates = new ArrayList<>();
		
		if(Objects.nonNull(filtro)) {
			if (Objects.nonNull(filtro.getAno())) {
				predicates.add(criteriaBuilder.equal(root.get(Modelo_.ano), filtro.getAno()));
			}
			
			if (Objects.nonNull(filtro.getUf())) {
				predicates.add(criteriaBuilder.equal(root.get(Modelo_.uf), filtro.getUf()));
			}
			
			if (Objects.nonNull(filtro.getTipoDocumento())) {
				try {
				predicates.add(criteriaBuilder.equal(root.get(Modelo_.tipoDocumento), TipoDocumentoEnum.valueOf(filtro.getTipoDocumento())));
				}catch (IllegalArgumentException e) {
					throw new AbstractException("Tipo de documento inválido!", HttpStatus.BAD_REQUEST);
				}
			}
		}

		return predicates;
	}

}
