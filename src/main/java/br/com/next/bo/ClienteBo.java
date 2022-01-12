package br.com.next.bo;

import br.com.next.enums.TipoCliente;

public  class ClienteBo {

	public static TipoCliente verificarTipo(double saldo) {
		if(saldo <= 5000) {
			return TipoCliente.COMUM;
		}else if(saldo < 15000) {
			return TipoCliente.SUPER;
		}else {
			return TipoCliente.PREMIUM;
		}
	}
}
