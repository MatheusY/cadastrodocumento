package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotNull;

public class PerfilVO {

	@NotNull
	private Short id;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}
}
