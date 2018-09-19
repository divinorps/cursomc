package com.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Cliente;
import com.cursomc.exception.ObjetoNaoEncontradoException;
import com.cursomc.reposotiries.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
			
	public Cliente find(Integer id) throws ObjetoNaoEncontradoException {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " +	id + ", Tipo: " + Cliente.class.getSimpleName())); 
	}
}
