package br.com.cadastrodocumento.exception;

import java.time.LocalDateTime;

public class Erro {
	private LocalDateTime timestamp = LocalDateTime.now();

	private String message;
	
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
	
	
}
