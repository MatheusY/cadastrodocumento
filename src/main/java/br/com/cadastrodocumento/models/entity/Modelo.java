package br.com.cadastrodocumento.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "modelo", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_uf", "id_tipo_documento", "ano" }) })
public class Modelo {
	
	
	public Modelo() {
		
	}

	public Modelo(Long id, Uf uf, TipoDocumento tipoDocumento, Integer ano) {
		this.id = id;
		this.uf = uf;
		this.tipoDocumento = tipoDocumento;
		this.ano = ano;
	}

	public Modelo(Long id, Uf uf, TipoDocumento tipoDocumento, Integer ano, byte[] documento) {
		this.id = id;
		this.uf = uf;
		this.tipoDocumento = tipoDocumento;
		this.ano = ano;
		this.documento = documento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_UF", referencedColumnName = "ID")
	private Uf uf;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID")
	private TipoDocumento tipoDocumento;

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

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
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
