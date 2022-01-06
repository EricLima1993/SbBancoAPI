package br.com.next.bo;

import br.com.next.bo.exceptions.SaldoInsuficienteException;
import br.com.next.models.entities.Conta;

public class ContaBO {
	
	public boolean verificarSaldo(double saque, Conta con) {
		if(con.getSaldo() < saque) {
			throw new SaldoInsuficienteException("Saldo insuficiente!");
		}
		
		return true;
	}
	
	public Conta debitarSaldo(double saque, Conta con) {
		if(verificarSaldo(saque, con)) {
			con.setSaldo(con.getSaldo() - saque);
		}
		return con;
	}

	public Conta depositarSaldo(double dep, Conta con) {
		con.setSaldo(con.getSaldo() + dep);
		
		return con;
	}
}
