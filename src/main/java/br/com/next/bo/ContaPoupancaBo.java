package br.com.next.bo;

import br.com.next.bo.exceptions.SaldoInsuficienteException;
import br.com.next.models.entities.ContaPoupanca;

public class ContaPoupancaBo {

	public boolean verificarSaldo(double saque, ContaPoupanca con) {
		if(con.getSaldo() < (saque+5.6)) {
			throw new SaldoInsuficienteException("Saldo insuficiente!");
		}
		
		return true;
	}
	
	public ContaPoupanca debitarSaldo(double saque, ContaPoupanca con) {
		if(verificarSaldo(saque, con)) {
			con.setSaldo(con.getSaldo() - saque);
		}
		return con;
	}

	public ContaPoupanca depositarSaldo(double dep, ContaPoupanca con) {
		con.setSaldo(con.getSaldo() + dep);
		
		return con;
	}
}
