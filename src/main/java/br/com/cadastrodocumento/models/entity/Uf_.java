package br.com.cadastrodocumento.models.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Uf.class)
public abstract class Uf_ {

	public static volatile SingularAttribute<Uf, Short> id;
	public static volatile SingularAttribute<Uf, String> sigla;
	public static volatile SingularAttribute<Uf, String> nome;
	
}
