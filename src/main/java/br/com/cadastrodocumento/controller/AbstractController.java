package br.com.cadastrodocumento.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class AbstractController {

	@Autowired
	protected ModelMapper modelMapper;

	protected <D, T> D convertVOToEntity(final T voClass, final Class<D> model) {
		return voClass != null ? modelMapper.map(voClass, model) : null;
	}
	
	protected <D, T> List<D> convertToDTO(final Iterable<T> models, final Class<D> dtoClass) {
		List<D> dtos = new ArrayList<>();
		for (T model : models) {
			dtos.add(modelMapper.map(model, dtoClass));
		}

		return dtos;
	}
	
	protected <D, T> Page<D> convertToDTO(final Page<T> page, final Class<D> dtoClass) {
		return new PageImpl<>(convertToDTO(page.getContent(), dtoClass), page.getPageable(), page.getTotalElements());
	}

	protected <D, T> D convertToDTO(final T model, final Class<D> dtoClass) {
		return model != null ? modelMapper.map(model, dtoClass) : null;
	}
}
