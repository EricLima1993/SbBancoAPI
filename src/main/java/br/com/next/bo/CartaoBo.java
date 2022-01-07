package br.com.next.bo;

import java.util.Random;

import br.com.next.bo.exceptions.SaldoInsuficienteException;
import br.com.next.models.entities.CartaoCredito;
import br.com.next.models.entities.ContaCorrente;

public class CartaoBo {
	
	private final int mastercard = 5051;
	private final int visa = 4041;
	private final int americanExpress = 3031;
	
	public CartaoCredito verificar(CartaoCredito obj,String bandeira) {
		if(bandeira.toUpperCase().equals("MASTERCARD")) {
			obj.setNumero(criacaoCartaoMaster(obj));
			obj.setNumero(digitosFinais(obj));
			obj.setCodigoSeguranca(gerarCodigo());
			
		}else if(bandeira.toUpperCase().equals("VISA")) {
			obj.setNumero(criacaoCartaoVisa(obj));
			obj.setNumero(digitosFinais(obj));
			obj.setCodigoSeguranca(gerarCodigo());
		}else {
			obj.setNumero(criacaoCartaoAmericanExpress(obj));
			obj.setNumero(digitosFinais(obj));
			obj.setCodigoSeguranca(gerarCodigo());
		}
		
		return obj;
	}

	public String criacaoCartaoMaster(CartaoCredito obj) {
		String nmrcartao = "0123456789";
		Random rng = new Random();

		char[] text = new char[8];
		for (int i = 0; i < 8; i++) {
			text[i] = nmrcartao.charAt(rng.nextInt(nmrcartao.length()));
		}
		
		return mastercard + (new String(text));
	}

	public String criacaoCartaoVisa(CartaoCredito obj) {
		String nmrcartao = "0123456789";
		Random rng = new Random();

		char[] text = new char[8];
		for (int i = 0; i < 8; i++) {
			text[i] = nmrcartao.charAt(rng.nextInt(nmrcartao.length()));
		}
		
		return visa + (new String(text));
	}

	public String criacaoCartaoAmericanExpress(CartaoCredito obj) {
		String nmrcartao = "0123456789";
		Random rng = new Random();

		char[] text = new char[8];
		for (int i = 0; i < 8; i++) {
			text[i] = nmrcartao.charAt(rng.nextInt(nmrcartao.length()));
		}
		
		return americanExpress + (new String(text));
	}
	
	public String gerarCodigo() {
		String numCode;
		Random rng = new Random();
		int num = rng.nextInt(1000);
		
		if(num <= 9) {
			numCode = "00"+num;
		}else if(num <= 99) {
			numCode = "0"+num;
		}else {
			numCode = String.valueOf(num);
		}
		
		return numCode;
	}
	
	public String digitosFinais(CartaoCredito obj) {
		String numFinal;
		int num = obj.getId();
		
		if(num <= 9) {
			numFinal = "000"+num;
		}else if(num <= 99) {
			numFinal = "00"+num;
		}else if(num <= 999){
			numFinal = "0"+num;
		}else {
			numFinal = String.valueOf(num);
		}
		
		return obj.getNumero()+numFinal;
	}

	public CartaoCredito debitarCredito(CartaoCredito obj, double valor) {
		if(verificarCredito(valor, obj)) {
			obj.setLimite(obj.getLimite() - valor);
		}
		return obj;
	}
	
	public boolean verificarCredito(double valor, CartaoCredito cartao) {
		if(cartao.getLimite() < valor) {
			throw new SaldoInsuficienteException("Credito insuficiente!");
		}
		
		return true;
	}

	public CartaoCredito pagarCredito(CartaoCredito cartao, double valor) {
		if(verificarDivida(valor, cartao)) {
			cartao.setLimite(cartao.getLimite() + valor);
		}

		return cartao;
	}

	private boolean verificarDivida(double valor, CartaoCredito cartao) {
		if((cartao.getLimiteMax()-cartao.getLimite()) < valor) {
			throw new SaldoInsuficienteException("sua fatura é menor!");
		}
		return true;
	}
}
