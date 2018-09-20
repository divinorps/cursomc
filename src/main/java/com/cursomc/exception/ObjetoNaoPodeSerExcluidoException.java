package com.cursomc.exception;

public class ObjetoNaoPodeSerExcluidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoPodeSerExcluidoException(String mensagem) {
		super(mensagem);
	}
	
	public ObjetoNaoPodeSerExcluidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
