package com.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);
		
		}catch(DataIntegrityViolationException e){
			throw new ObjetoNaoEncontradoException("Não é possível excluir categoria que possui produtos.");
		}
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
}
