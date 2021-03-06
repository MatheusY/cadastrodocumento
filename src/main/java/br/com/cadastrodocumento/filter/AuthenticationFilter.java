package br.com.cadastrodocumento.filter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private static final String TOKEN_NÃO_ENVIADO = "Token não enviado!";
	private static final String AUTHORIZATION = "AUTHORIZATION";
	
	public AuthenticationFilter(final RequestMatcher requiresAuth) {
		super(requiresAuth);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		Optional.ofNullable(request.getHeader(AUTHORIZATION));
		String token = request.getHeader(AUTHORIZATION);
		
		if (Objects.isNull(token)) {
			throw new AuthenticationCredentialsNotFoundException(TOKEN_NÃO_ENVIADO);
		}
		token = StringUtils.removeStart(token, "Bearer").trim();
		Authentication reqAuth = new UsernamePasswordAuthenticationToken(token, token);
		
		return getAuthenticationManager().authenticate(reqAuth);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}
	
	

}
