package br.com.next.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "TB_CONTA")
public class Conta {
	
	@Id
	@Column(name = "NUMERO_CONTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTA_SEQ")
	@SequenceGenerator(name = "CONTA_SEQ", initialValue = 1, allocationSize = 1)
	private int numeroConta;
	
	@Min(0)
	@Column(name = "SALDO")
	private double saldo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", nullable = false)
	private Cliente cliente;
	
	public Conta() {
		super();
	}

	public Conta(double saldo, Cliente cliente) {
		super();
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
