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

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.models.entity.Perfil_;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.models.entity.Usuario_;
import br.com.cadastrodocumento.vo.FiltroUsuarioVO;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Usuario> filtro(FiltroUsuarioVO filtro, Pageable pageable) throws AbstractException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		List<Predicate> predicates = loadPredicates(filtro, criteriaBuilder, root);

		CriteriaQuery<Usuario> select = criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		Long count = count(filtro);
		if (count == 0) {
			return new PageImpl<>(Collections.emptyList(), pageable, count);
		}
		select.orderBy(criteriaBuilder.asc(root.get(Usuario_.usuario)));

		TypedQuery<Usuario> typedQuery = entityManager.createQuery(select);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());

		return new PageImpl<Usuario>(typedQuery.getResultList(), pageable, count);
	}

	private Long count(FiltroUsuarioVO filtro) throws AbstractException {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		List<Predicate> predicates = loadPredicates(filtro, criteriaBuilder, root);
		CriteriaQuery<Long> count = criteriaQuery.select(criteriaBuilder.count(root.get(Usuario_.id)));
		count.where(predicates.toArray(new Predicate[predicates.size()]));

		return entityManager.createQuery(count).getSingleResult();
	}

	private List<Predicate> loadPredicates(FiltroUsuarioVO filtro, CriteriaBuilder criteriaBuilder, Root<Usuario> root)
			throws AbstractException {
		List<Predicate> predicates = new ArrayList<>();

		if (Objects.nonNull(filtro)) {
			if (Objects.nonNull(filtro.getUsuario())) {
				predicates.add(criteriaBuilder.like(root.get(Usuario_.usuario), "%" + filtro.getUsuario() + "%"));
			}

			if (Objects.nonNull(filtro.getEmail())) {
				predicates.add(criteriaBuilder.like(root.get(Usuario_.email), "%" + filtro.getEmail() + "%"));
			}

			if (Objects.nonNull(filtro.getPerfil())) {
				predicates.add(
						criteriaBuilder.equal(root.get(Usuario_.perfil).get(Perfil_.id), filtro.getPerfil()));
			}
			
		
			if(filtro.getAtivo() && !filtro.getInativo()) {
				predicates.add(criteriaBuilder.equal(root.get(Usuario_.ativo), Boolean.TRUE));
			} else if(!filtro.getAtivo() && filtro.getInativo()) {
				predicates.add(criteriaBuilder.equal(root.get(Usuario_.ativo), Boolean.FALSE));
			}
			
			predicates.add(criteriaBuilder.equal(root.get(Usuario_.emailValidado), Boolean.TRUE));
		}

		return predicates;
	}

}
