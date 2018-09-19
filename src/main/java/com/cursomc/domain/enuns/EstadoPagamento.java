package com.cursomc.domain.enuns;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int id;
	private String descricao;
	
	private EstadoPagamento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(EstadoPagamento ep : EstadoPagamento.values()) {
			if(cod.equals(ep.getId())) {
				return ep;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
