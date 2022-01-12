package br.com.next.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.next.bo.ContaBo;
import br.com.next.models.entities.Cliente;
import br.com.next.models.entities.ContaCorrente;
import br.com.next.models.repositories.ContaRepository;
import br.com.next.services.exceptions.DataIntegrityException;
import br.com.next.services.exceptions.ObjectNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository cr;
	private ContaBo cb = new ContaBo();
	@Autowired
	private ClienteService clis;

	public ContaCorrente buscar(Integer id) {
		Optional<ContaCorrente> obj = cr.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + ContaCorrente.class.getName()));
	}

	public ContaCorrente inserir(ContaCorrente obj) {
		return cr.save(obj);
	}

	public void deletar(Integer id) {
		buscar(id);
		try {
			cr.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possivel excluir um conta que sem excluir o cadastro do cliente primeiro");
		}
	}

	public ContaCorrente sacar(int numCon, String senha, double saque) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaCorrente con = buscar(numCon);
			cb.debitarSaldo(saque, con);
			return cr.save(con);
		}
	}
	
	public ContaCorrente usarDebito(int numCon, String senha, double saque) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaCorrente con = buscar(numCon);
			cb.debitarSaldo(saque, con);
			return cr.save(con);
		}
	}

	public ContaCorrente depositar(int numCon, double dep) {
		ContaCorrente con = buscar(numCon);
		cb.depositarSaldo(dep, con);
		return cr.save(con);

	}

	public ContaCorrente transferir(int numCon, String senha, int numConT, double vTrans) {
		if (clis.verificarSenha(senha)) {
			throw new ObjectNotFoundException("Senha invalida!");
		} else {
			ContaCorrente con = buscar(numCon);
			ContaCorrente conT = buscar(numConT);
			cb.debitarSaldo(vTrans, con);
			cb.depositarSaldo(vTrans, conT);
			cr.save(conT);
			return cr.save(con);
		}
	}
}
