package com.cursomc.domain.enuns;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"), 
	PESSOAJURIDICA(2, "Pesso Jurídica");
	
	private int id;
	private String descricao;
	
	private TipoCliente(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(TipoCliente tp : TipoCliente.values()) {
			if(cod.equals(tp.getId())) {
				return tp;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
