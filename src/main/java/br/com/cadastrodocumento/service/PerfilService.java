package br.com.cadastrodocumento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.models.entity.Perfil;
import br.com.cadastrodocumento.repository.PerfilRepository;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository perfilRepository;

	public Iterable<Perfil> listAll() {
		return perfilRepository.findAll();
	}
}
