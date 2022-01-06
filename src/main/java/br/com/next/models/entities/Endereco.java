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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
	@SequenceGenerator(name = "ENDERECO_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "ID_ENDERECO")
	private int id;
	
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "NUMERO")
	private int numero;
	
	@Column(name = "UF")
	private String uf;
	
	@JsonBackReference
	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
