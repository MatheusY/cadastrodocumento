package br.com.cadastrodocumento.models.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Modelo.class)
public abstract class Modelo_ {

	public static volatile SingularAttribute<Modelo, Long> id;
	public static volatile SingularAttribute<Modelo, TipoDocumento> tipoDocumento;
	public static volatile SingularAttribute<Modelo, Uf> uf;
	public static volatile SingularAttribute<Modelo, Integer> ano;
	
}
