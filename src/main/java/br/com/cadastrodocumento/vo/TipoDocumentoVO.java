package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotNull;

public class TipoDocumentoVO {

	private Short id;
	
	@NotNull
	private String nome;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
