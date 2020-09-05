package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class ModeloVO {

	@NotNull
	private UfVO uf;
	
	@NotNull
	private TipoDocumentoIdVO tipoDocumento;
	
	@NotNull
	private Integer ano;
	
	public UfVO getUf() {
		return uf;
	}

	public void setUf(UfVO uf) {
		this.uf = uf;
	}

	public TipoDocumentoIdVO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoIdVO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
}
