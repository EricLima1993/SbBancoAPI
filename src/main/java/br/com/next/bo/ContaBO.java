package br.com.next.bo;

import br.com.next.bo.exceptions.SaldoInsuficienteException;
import br.com.next.models.entities.ContaCorrente;

public class ContaBO {
	
	public boolean verificarSaldo(double saque, ContaCorrente con) {
		if(con.getSaldo() < saque) {
			throw new SaldoInsuficienteException("Saldo insuficiente!");
		}
		
		return true;
	}
	
	public ContaCorrente debitarSaldo(double saque, ContaCorrente con) {
		if(verificarSaldo(saque, con)) {
			con.setSaldo(con.getSaldo() - saque);
		}
		return con;
	}

	public ContaCorrente depositarSaldo(double dep, ContaCorrente con) {
		con.setSaldo(con.getSaldo() + dep);
		
		return con;
	}
}
