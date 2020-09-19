package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.cadastrodocumento.models.enumeration.PerfilEnum;

public class UsuarioVO {
	
	private Long id;

	@NotBlank
	@Length(min = 4, max = 20)
	private String usuario;
	
	@NotNull
	private String email;
	
	private String perfil;
	
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
}
