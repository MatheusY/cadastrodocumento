package br.com.cadastrodocumento.models.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	public static final Perfil ADMINISTRADOR = new Perfil((short) 1);
	public static final Perfil EDITOR = new Perfil((short) 2);
	public static final Perfil VISUALIZADOR = new Perfil((short) 3);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = "descricao")
	private String descricao;
	
	public Perfil() {
		
	}

	public Perfil(Short id) {
		this.id = id;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
