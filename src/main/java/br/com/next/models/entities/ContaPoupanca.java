package br.com.next.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.next.models.Conta;

@Entity
@Table(name = "TB_CONTA_POUPANCA")
public class ContaPoupanca extends Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Min(0)
	@Column(name = "SALDO")
	private double saldo;
	
	@JsonBackReference
	@OneToOne(mappedBy = "contaP")
	private Cliente cliente;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	@Id
	@Column(name = "NUMERO_CONTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTA_P_SEQ")
	@SequenceGenerator(name = "CONTA_P_SEQ", initialValue = 1, allocationSize = 1)
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
}
