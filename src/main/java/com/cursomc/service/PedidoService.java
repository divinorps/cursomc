package com.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Pedido;
import com.cursomc.exception.ObjetoNaoEncontradoException;
import com.cursomc.reposotiries.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
			
	public Pedido find(Integer id) throws ObjetoNaoEncontradoException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " +	id + ", Tipo: " + Pedido.class.getSimpleName())); 
	}
}
