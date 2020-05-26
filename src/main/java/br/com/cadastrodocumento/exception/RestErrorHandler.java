package br.com.cadastrodocumento.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AbstractException.class)
	public ResponseEntity<Erro> processValidationError(AbstractException e) {
		Erro exception = new Erro();
		exception.setMessage(e.getMessage());
		return new ResponseEntity<>(exception, e.getStatus());
	}
}
