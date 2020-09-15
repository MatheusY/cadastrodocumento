package br.com.cadastrodocumento.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.exception.AbstractException;
import br.com.cadastrodocumento.helper.LoginHelper;
import br.com.cadastrodocumento.models.entity.EncryptConfig;
import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.models.enumeration.PerfilEnum;
import br.com.cadastrodocumento.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

@Service
public class UsuarioService {

	private static final String USUÁRIO_SEM_PERMISSÃO = "Usuário sem permissão";


	@Autowired
	private UsuarioRepository usuarioRepository;
	

	   	private EncryptConfig config;
	   	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	   	private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado!";
	    
	    public UsuarioService() throws Exception{
	    	config = new EncryptConfig();
	    	config.setMyEncryptionKey(LoginHelper.SECRET_KEY);
	    	config.setMyEncryptionScheme(DESEDE_ENCRYPTION_SCHEME);
	        config.setArrayBytes(config.getMyEncryptionKey().getBytes(LoginHelper.UNICODE_FORMAT));
	        config.setKs(new DESedeKeySpec(config.getArrayBytes()));
	        config.setSkf(SecretKeyFactory.getInstance(config.getMyEncryptionScheme()));
	        config.setCipher(Cipher.getInstance(config.getMyEncryptionScheme()));
	        config.setKey(config.getSkf().generateSecret(config.getKs()));
	    }
	
	public Usuario salvar(Usuario usuario) {
		usuario.setDataCadastro(LocalDate.now());
		
		String hash = LoginHelper.encrypt(config, usuario.getSenha());
		usuario.setSenha(hash);
		usuario.setPerfil(PerfilEnum.VISUALIZADOR);
		
		return usuarioRepository.save(usuario);
	}

	
	public String login(String usuario, String senha) throws AbstractException {
		String senhaHash =  LoginHelper.encrypt(config, senha);
		Optional<Usuario> user = usuarioRepository.findByUsuarioAndSenha(usuario, senhaHash);
		if(user.isPresent()) {
			Usuario u = user.get();
			String token = LoginHelper.createJWT(u, "subject", 86400000);
			return token;
		}
		throw new AbstractException("Usuário ou senha inválido!", HttpStatus.BAD_REQUEST);
	}
	
	public Optional<User> findByToken(String token){
		Claims claim = decodeJWT(token);
		String nomeUsuario = (String) claim.get("usuario");
		Optional<Usuario> oUsuario = usuarioRepository.findByUsuario(nomeUsuario);
		if(oUsuario.isPresent()) {
			Usuario usuario = oUsuario.get();
			User user = new User(usuario.getUsuario(), usuario.getSenha(), true, true, true, true, AuthorityUtils.createAuthorityList(usuario.getPerfil().toString()));
			return Optional.of(user);
		}
		return Optional.empty();
	}
	
	public static Claims decodeJWT(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(LoginHelper.SECRET_KEY))
					.parseClaimsJws(token).getBody();
			return claims;
			
		}catch (ExpiredJwtException e) {
			 throw new UsernameNotFoundException("Usuário deslogado, sessão inválida!");
		}
	}

	public Usuario findByUsuario(String name) throws AbstractException {
		return usuarioRepository.findByUsuario(name).orElseThrow(() -> new AbstractException(USUARIO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND));
	}

	public Usuario findById(Long id, String nomeUsuario) throws AbstractException {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new AbstractException(USUARIO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND));
		if(!nomeUsuario.equals(usuario.getUsuario())) {
			Usuario usuarioLogado = findByUsuario(nomeUsuario);
			if(!PerfilEnum.ADMIN.equals(usuarioLogado.getPerfil())) {
				throw new AbstractException(USUÁRIO_SEM_PERMISSÃO, HttpStatus.FORBIDDEN);
			}
		}
		return usuario;
	}
	
	
}
