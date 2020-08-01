package br.com.cadastrodocumento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastrodocumento.models.entity.Uf;
import br.com.cadastrodocumento.repository.UfRepository;

@Service
public class UfService {
	
	@Autowired
	private UfRepository ufRepository;

	public Iterable<Uf> listAll() {
		return ufRepository.findByOrderBySiglaAsc();
	}
}
