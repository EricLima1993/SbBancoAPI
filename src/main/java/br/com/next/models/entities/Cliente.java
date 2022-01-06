package br.com.next.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
	@SequenceGenerator(name = "CLIENTE_SEQ", initialValue = 1, allocationSize = 1)
	private int id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "SENHA")
	private String senha;
	
	@Column(name = "TIPO_CONTA")
	private String tipo;
	
	@JsonManagedReference
	@OneToOne
	@JoinTable(name = "CLIENTE_CONTA",
		joinColumns = @JoinColumn(name = "ID"),
		inverseJoinColumns = @JoinColumn(name = "NUMERO_CONTA")
	)
	private ContaCorrente conta;
	
	@JsonManagedReference
	@OneToOne
	@JoinTable(name = "CLIENTE_ENDERECO",
		joinColumns = @JoinColumn(name = "ID"),
		inverseJoinColumns = @JoinColumn(name = "ID_ENDERECO")
	)
	private Endereco endereco; 
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nome, String cpf, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;

	}

	public Cliente(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ContaCorrente getConta() {
		return conta;
	}

	public void setConta(ContaCorrente conta) {
		this.conta = conta;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
