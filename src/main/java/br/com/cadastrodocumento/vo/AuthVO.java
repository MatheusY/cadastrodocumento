package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthVO {
	
	private Long id;

	@NotBlank
	@Length(min = 4, max = 20)
	private String usuario;
	
	@NotNull
	private String email;
	
	@NotBlank
	@Length(min = 4, message = "A senha deve ter mais de 4 digitos")
	private String senha;
	
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
