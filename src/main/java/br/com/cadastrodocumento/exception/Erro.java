package br.com.cadastrodocumento.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Erro {
	private LocalDateTime timestamp = LocalDateTime.now();

	private String message;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<FieldError> fieldErros;
	
	public Erro() {
		
	}
	
	public Erro(LocalDateTime timestamp, String message, List<FieldError> fieldErros) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.fieldErros = fieldErros;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<FieldError> getFieldErros() {
		return fieldErros;
	}
	
	public void setFieldErros(List<FieldError> fieldErros) {
		this.fieldErros = fieldErros;
	}
	
}
