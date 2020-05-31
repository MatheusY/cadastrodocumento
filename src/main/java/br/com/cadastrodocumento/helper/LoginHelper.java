package br.com.cadastrodocumento.helper;

import java.security.Key;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.cadastrodocumento.models.entity.EncryptConfig;
import br.com.cadastrodocumento.models.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class LoginHelper {
	public static final String UNICODE_FORMAT = "UTF8";
	public static final String SECRET_KEY = "OmaeWaMouShindeiruNaniAA";

	public static String encrypt(EncryptConfig config, String unencryptedString) {
		String encryptedString = null;
		try {
			config.getCipher().init(Cipher.ENCRYPT_MODE, config.getKey());
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = config.getCipher().doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public static String createJWT(Usuario usuario, String subject, long ttlMillis) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder()
				.setId(usuario.getId().toString())
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(usuario.getUsuario())
				.claim("usuario", usuario.getUsuario())
				.claim("email", usuario.getEmail())
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}

	public static Claims decodeJWT(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY)).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}
}
