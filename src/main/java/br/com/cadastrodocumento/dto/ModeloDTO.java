package br.com.cadastrodocumento.dto;

public class ModeloDTO {

	private Long id;

	private UfDTO uf;
	
	private TipoDocumentoDTO tipoDocumento;
	
	private Integer ano;
	
	private byte[] documento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UfDTO getUf() {
		return uf;
	}

	public void setUf(UfDTO uf) {
		this.uf = uf;
	}

	public TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}
	
}
