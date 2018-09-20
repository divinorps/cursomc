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
			
	public Categoria find(Integer id) throws ObjetoNaoEncontradoException {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " +	id + ", Tipo: " + Categoria.class.getSimpleName())); 
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repository.save(categoria);
	}
}
