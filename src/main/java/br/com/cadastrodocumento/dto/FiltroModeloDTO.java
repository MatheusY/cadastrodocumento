package br.com.cadastrodocumento.dto;

public class FiltroModeloDTO {

	private Short uf;
	
	private Short tipoDocumento;
	
	private Integer ano;

	public Short getUf() {
		return uf;
	}

	public void setUf(Short uf) {
		this.uf = uf;
	}

	public Short getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Short tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
}
