package com.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.exception.ObjetoNaoEncontradoException;
import com.cursomc.reposotiries.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElse(null); 
	}
	
	public Categoria buscar(Integer id) throws ObjetoNaoEncontradoException {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " +	id + ", Tipo: " + Categoria.class.getSimpleName())); 
	}
}
