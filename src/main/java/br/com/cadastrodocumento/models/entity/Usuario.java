package br.com.cadastrodocumento.models.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuario", uniqueConstraints = {
		@UniqueConstraint(columnNames = "usuario", name = "uk_usuario_01"),
		@UniqueConstraint(columnNames = "email", name = "uk_usuario_02")})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "usuario", nullable = false)
	private String usuario;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "senha_hash", nullable = false)
	private String senha;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDate dataCadastro;
	
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
	
	@Column(name = "email_validado", nullable = false)
	private Boolean emailValidado;
	
	@Column(name = "key_email", nullable = false)
	private Long keyEmail;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID")
	private Perfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getEmailValidado() {
		return emailValidado;
	}

	public void setEmailValidado(Boolean emailValidado) {
		this.emailValidado = emailValidado;
	}

	public Long getKeyEmail() {
		return keyEmail;
	}

	public void setKeyEmail(Long keyEmail) {
		this.keyEmail = keyEmail;
	}

	public boolean eAdmin() {
		return Perfil.ADMINISTRADOR.getId().equals(this.perfil.getId());
	}
	
}
