package br.com.next.bo.exceptions;

public class SaldoInsuficienteException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String msg) {
		super(msg);
	}
	
	public SaldoInsuficienteException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
