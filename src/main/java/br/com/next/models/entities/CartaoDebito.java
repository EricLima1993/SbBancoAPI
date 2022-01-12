package br.com.next.models.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.next.models.Cartao;

@Entity
@Table(name = "TB_CARTAO_DEBITO")
public class CartaoDebito extends Cartao implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonBackReference
	@OneToOne(mappedBy = "cardDeb")
	private Cliente cliente;
	
	@Id
	@Column(name = "ID_CARTAO_D")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTAO_DEB_SEQ")
	@SequenceGenerator(name = "CARTAO_DEB_SEQ", initialValue = 1, allocationSize = 1)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "NUMERO_CARTAO")
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@Column(name = "NOME_CARTAO")
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	
	@Column(name = "VALIDADE")
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	@Column(name = "CODIGO_SEGURANCA")
	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	@Column(name = "SENHA_CARTAO")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
