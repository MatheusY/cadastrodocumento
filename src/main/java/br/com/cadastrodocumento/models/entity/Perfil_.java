package br.com.cadastrodocumento.models.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Perfil.class)
public abstract class Perfil_ {

	public static volatile SingularAttribute<Perfil, Short> id;
	public static volatile SingularAttribute<Perfil, String> descricao;
	
}
