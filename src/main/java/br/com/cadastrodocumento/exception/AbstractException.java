package br.com.cadastrodocumento.exception;

import org.springframework.http.HttpStatus;

public class AbstractException extends Exception {
	 
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
    private String message;
    
    public AbstractException(final String message, final HttpStatus status) {
    	
		this.message = message;
		this.status = status;
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
 
}