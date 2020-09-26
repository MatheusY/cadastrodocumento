package br.com.cadastrodocumento.models.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> usuario;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, Perfil> perfil;
	
}
