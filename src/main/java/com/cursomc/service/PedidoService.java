package com.cursomc.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.ItemPedido;
import com.cursomc.domain.PagamentoComBoleto;
import com.cursomc.domain.Pedido;
import com.cursomc.domain.enuns.EstadoPagamento;
import com.cursomc.exception.ObjetoNaoEncontradoException;
import com.cursomc.reposotiries.ItemPedidoRepository;
import com.cursomc.reposotiries.PagamentoRepository;
import com.cursomc.reposotiries.PedidoRepository;
import com.cursomc.reposotiries.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired 
	private BoletoService boletoService;
			
	public Pedido find(Integer id) throws ObjetoNaoEncontradoException {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjetoNaoEncontradoException(
				"Objeto não encontrado! Id: " +	id + ", Tipo: " + Pedido.class.getSimpleName())); 
	}

	@Transactional
	public @Valid Pedido insert(@Valid Pedido objPedido) {
		objPedido.setId(null);
		objPedido.setInstante(new Date());
		objPedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		objPedido.getPagamento().setPedido(objPedido);
		if(objPedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto)objPedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, objPedido.getInstante());
		}
		objPedido = pedidoRepository.save(objPedido);
		pagamentoRepository.save(objPedido.getPagamento());
		
		for(ItemPedido ip : objPedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findById(ip.getProduto().getId()).orElse(ip.getProduto()).getPreco());
			ip.setPedido(objPedido);
		}
		itemPedidoRepository.saveAll(objPedido.getItens());
		return objPedido;
	}
}
