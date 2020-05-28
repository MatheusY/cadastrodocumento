package br.com.cadastrodocumento.vo;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioVO {

	@NotBlank(message = "É necessário preencher usuário")
	@Length(min = 4, max = 20)
	private String usuario;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Length(min = 4)
	private String senha;
}
