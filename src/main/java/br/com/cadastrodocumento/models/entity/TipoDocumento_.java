package br.com.cadastrodocumento.models.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TipoDocumento.class)
public abstract class TipoDocumento_ {

	public static volatile SingularAttribute<TipoDocumento, Short> id;
	public static volatile SingularAttribute<TipoDocumento, String> nome;
	
}
