package br.com.cadastrodocumento.service;

import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.models.entity.Usuario;
import br.com.cadastrodocumento.models.enumeration.PerfilEnum;
import br.com.cadastrodocumento.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	  private static final String UNICODE_FORMAT = "UTF8";
	    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	    private KeySpec ks;
	    private SecretKeyFactory skf;
	    private Cipher cipher;
	    byte[] arrayBytes;
	    private String myEncryptionKey;
	    private String myEncryptionScheme;
	    SecretKey key;
	    
	    public UsuarioService() throws Exception{
	    	myEncryptionKey = "OmaeWaMouShindeiruNaniAA";
	        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
	        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
	        ks = new DESedeKeySpec(arrayBytes);
	        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
	        cipher = Cipher.getInstance(myEncryptionScheme);
	        key = skf.generateSecret(ks);
	    }
	
	public Usuario salvar(Usuario usuario) {
		usuario.setDataCadastro(LocalDate.now());
		
		String hash = encrypt(usuario.getSenha());
		usuario.setSenha(hash);
		usuario.setPerfil(PerfilEnum.ADMIN);
		
		return usuarioRepository.save(usuario);
	}

	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsuario(username);
	}
	
	public String login(String usuario, String senha) {
		String senhaHash =  encrypt(senha);
		Optional<Usuario> user = usuarioRepository.findByUsuarioAndSenha(usuario, senhaHash);
		if(user.isPresent()) {
			String token = UUID.randomUUID().toString();
			Usuario u = user.get();
			u.setToken(token);
			usuarioRepository.save(u);
			return token;
		}
		return null;
	}
	
	public Optional<User> findByToken(String token){
		Optional<Usuario> oUsuario = usuarioRepository.findByToken(token);
		if(oUsuario.isPresent()) {
			Usuario usuario = oUsuario.get();
			User user = new User(usuario.getUsuario(), usuario.getSenha(), true, true, true, true, AuthorityUtils.createAuthorityList(usuario.getPerfil().toString()));
			return Optional.of(user);
		}
		return Optional.empty();
	}
	
    private String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }


    private String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
	
	
}
