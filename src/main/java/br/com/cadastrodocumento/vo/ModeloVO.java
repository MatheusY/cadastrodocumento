package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotNull;

public class ModeloVO {

	@NotNull
	private String uf;
	
	@NotNull
	private String tipoDocumento;
	
	@NotNull
	private Integer ano;
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
