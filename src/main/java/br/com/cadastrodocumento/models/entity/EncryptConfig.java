package br.com.cadastrodocumento.models.entity;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

public class EncryptConfig {
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	private byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	private SecretKey key;

	public KeySpec getKs() {
		return ks;
	}

	public void setKs(KeySpec ks) {
		this.ks = ks;
	}

	public SecretKeyFactory getSkf() {
		return skf;
	}

	public void setSkf(SecretKeyFactory skf) {
		this.skf = skf;
	}

	public Cipher getCipher() {
		return cipher;
	}

	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}

	public byte[] getArrayBytes() {
		return arrayBytes;
	}

	public void setArrayBytes(byte[] arrayBytes) {
		this.arrayBytes = arrayBytes;
	}

	public String getMyEncryptionKey() {
		return myEncryptionKey;
	}

	public void setMyEncryptionKey(String myEncryptionKey) {
		this.myEncryptionKey = myEncryptionKey;
	}

	public String getMyEncryptionScheme() {
		return myEncryptionScheme;
	}

	public void setMyEncryptionScheme(String myEncryptionScheme) {
		this.myEncryptionScheme = myEncryptionScheme;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}

}
