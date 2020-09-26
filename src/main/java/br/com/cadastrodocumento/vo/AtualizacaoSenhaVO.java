package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtualizacaoSenhaVO {
		
	@NotBlank
	@Length(min = 4, message = "A senha deve ter mais de 4 digitos")
	private String senha;
	
	@NotBlank
	@Length(min = 4, message = "A nova senha precisa ter mais de 4 digitos")
	private String novaSenha;
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
}
