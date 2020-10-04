package br.com.cadastrodocumento.vo;

public class FiltroUsuarioVO {
	
	private String usuario;
	
	private String email;

	private Short perfil;
	
	private boolean ativo;
	
	private boolean inativo;

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

	public Short getPerfil() {
		return perfil;
	}

	public void setPerfil(Short perfil) {
		this.perfil = perfil;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	
}
