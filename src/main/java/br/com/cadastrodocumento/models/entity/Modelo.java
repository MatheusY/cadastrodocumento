package br.com.cadastrodocumento.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.cadastrodocumento.models.enumeration.TipoDocumentoEnum;

@Entity
@Table(name = "modelo", uniqueConstraints = { @UniqueConstraint(columnNames = { "uf", "tipo_documento", "ano" }) })
public class Modelo {
	
	
	public Modelo() {
		
	}

	public Modelo(Long id, String uf, TipoDocumentoEnum tipoDocumento, Integer ano) {
		this.id = id;
		this.uf = uf;
		this.tipoDocumento = tipoDocumento;
		this.ano = ano;
	}

	public Modelo(Long id, String uf, TipoDocumentoEnum tipoDocumento, Integer ano, byte[] documento) {
		this.id = id;
		this.uf = uf;
		this.tipoDocumento = tipoDocumento;
		this.ano = ano;
		this.documento = documento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uf", nullable = false)
	private String uf;

	@Column(name = "tipo_documento", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDocumentoEnum tipoDocumento;

	@Column(name = "ano", nullable = false)
	private Integer ano;

	@Lob
	@Column(name = "documento")
	private byte[] documento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
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
