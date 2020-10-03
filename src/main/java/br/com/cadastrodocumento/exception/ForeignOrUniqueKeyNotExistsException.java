package br.com.cadastrodocumento.exception;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ForeignOrUniqueKeyNotExistsException extends AbstractException{

	private static final long serialVersionUID = -4371245485096895833L;
	
	static final Pattern PATTERN_REGEX = Pattern.compile("(U|F)K_[A-Z]*_[0-9]{2}");

	public ForeignOrUniqueKeyNotExistsException(DataAccessException e, Map<String, String> messages) {
		super(getMessage(e, messages), HttpStatus.BAD_REQUEST);
	}

	private static String getMessage(DataAccessException e, Map<String, String> messages) {
		if (e instanceof DataIntegrityViolationException) {
			String constraintName =  extractConstraintNames(e.getRootCause().toString().toUpperCase(), PATTERN_REGEX);
			
			if (StringUtils.isNotBlank(constraintName)) {
				return messages.get(constraintName);
			}			
		}
		
		return null;
	}

	private static String extractConstraintNames(String mensagem, Pattern pattern) {
		Matcher m = pattern.matcher(mensagem);
		
		String match = "";
		while(m.find()) {
			match = m.group();
		}
		
		return match;
	}


	
}
