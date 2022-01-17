package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.next.bo.ContaPoupancaBo;
import br.com.next.models.entities.ContaPoupanca;
import br.com.next.models.repositories.ContaPoupancaRepository;
import br.com.next.services.exceptions.DataIntegrityException;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class ContaPoupancaService {
	
	@Autowired
	private ContaPoupancaRepository cpr;
	@Autowired
	private ClienteService clis;
	private ContaPoupancaBo cpb = new ContaPoupancaBo();
	
	public ContaPoupanca buscar(Integer id) {
		Optional<ContaPoupanca> obj = cpr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaPoupanca.class.getName()));
	}

	public ContaPoupanca inserir(ContaPoupanca obj) {
		return cpr.save(obj);
	}

	public void deletar(Integer id) {
		buscar(id);
		try {
			cpr.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel excluir um conta que sem excluir o cadastro do cliente primeiro");
		}
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
}