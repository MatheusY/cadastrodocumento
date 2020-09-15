package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UsuarioVO {

	@NotBlank
	@Length(min = 4, max = 20)
	private String usuario;
	
	@NotNull
	private String email;
	
	@NotBlank
	@Length(min = 4, message = "A senha deve ter mais de 4 digitos")
	private String senha;

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
