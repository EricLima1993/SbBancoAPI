package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.next.bo.ContaBo;
import br.com.next.bo.ContaPoupancaBo;
import br.com.next.models.entities.ContaCorrente;
import br.com.next.models.entities.ContaPoupanca;
import br.com.next.models.repositories.ContaPoupancaRepository;
import br.com.next.models.repositories.ContaRepository;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class ContaPoupancaService {
	
	@Autowired
	private ContaPoupancaRepository cpr;
	@Autowired
	private ClienteService clis;
	@Autowired
	private ContaRepository cr;
	private ContaPoupancaBo cpb = new ContaPoupancaBo();
	private ContaBo cb = new ContaBo();
	
	public ContaPoupanca buscar(Integer id) {
		Optional<ContaPoupanca> obj = cpr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaPoupanca.class.getName()));
	}
	
	public ContaCorrente buscarCorrente(Integer id) {
		Optional<ContaCorrente> obj = cr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaCorrente.class.getName()));
	}

	public ContaPoupanca inserir(ContaPoupanca obj) {
		return cpr.save(obj);
	}

	public ContaPoupanca sacar(int numCon, String senha, double saque) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaPoupanca con = buscar(numCon);
			cpb.debitarSaldo(saque, con);
			return cpr.save(con);
		}
	}

	public ContaPoupanca depositar(int numCon, double dep) {
		ContaPoupanca con = buscar(numCon);
		cpb.depositarSaldo(dep, con);
		return cpr.save(con);

	}

	public ContaPoupanca transferir(int numCon, String senha, int numConT, double vTrans) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaPoupanca con = buscar(numCon);
			ContaPoupanca conT = buscar(numConT);
			cpb.debitarSaldo((vTrans+5.6), con);
			cpb.depositarSaldo(vTrans, conT);
			cpr.save(conT);
			return cpr.save(con);
		}
	}
	
	public ContaPoupanca transferirCorrente(int numCon, String senha, int numConT, double vTrans) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaPoupanca con = buscar(numCon);
			ContaCorrente conT = buscarCorrente(numConT);
			cpb.debitarSaldo((vTrans+5.6), con);
			cb.depositarSaldo(vTrans, conT);
			atualizarCorrente(conT);
			return cpr.save(con);
		}
	}
	
	public void deletar(Integer id) { 
		cpr.deleteById(id);
	}
	
	public ContaPoupanca atualizar(ContaPoupanca obj) {
		buscar(obj.getNumeroConta());
		return cpr.save(obj);
	}
	
	public ContaCorrente atualizarCorrente(ContaCorrente obj) {
		buscar(obj.getNumeroConta());
		return cr.save(obj);
	}
}
