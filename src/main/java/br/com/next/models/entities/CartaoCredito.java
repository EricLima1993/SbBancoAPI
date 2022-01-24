package br.com.next.models.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.data.repository.NoRepositoryBean;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.next.models.Cartao;

@Entity
@Table(name = "TB_CARTAO_CREDITO")
public class CartaoCredito extends Cartao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Min(1)
	@Column(name = "LIMITE_MAX")
	private double limiteMax;
	
	@Min(0)
	@Column(name = "LIMITE")
	private double limite;
	
	private Date dataApolice;
	
	private int anosContratacaoApolice;
	
	private boolean seguroMorte;
	
	private boolean seguroInvalidez;
	
	private boolean seguroDesemprego;
	
	@JsonBackReference
	@OneToOne(mappedBy = "cartoes")
	private Cliente cliente;

	public double getLimiteMax() {
		return limiteMax;
	}

	public void setLimiteMax(double limiteMax) {
		this.limiteMax = limiteMax;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
	
	@Id
	@Column(name = "ID_CARTAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARTAO_SEQ")
	@SequenceGenerator(name = "CARTAO_SEQ", initialValue = 1, allocationSize = 1)
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Column(name = "SENHA_CARTAO")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Date getDataApolice() {
		return dataApolice;
	}

	public void setDataApolice(Date dataApolice) {
		this.dataApolice = dataApolice;
	}

	public int getAnosContratacaoApolice() {
		return anosContratacaoApolice;
	}

	public void setAnosContratacaoApolice(int anosContratacaoApolice) {
		this.anosContratacaoApolice = anosContratacaoApolice;
	}

	public boolean isSeguroMorte() {
		return seguroMorte;
	}

	public void setSeguroMorte(boolean seguroMorte) {
		this.seguroMorte = seguroMorte;
	}

	public boolean isSeguroInvalidez() {
		return seguroInvalidez;
	}

	public void setSeguroInvalidez(boolean seguroInvalidez) {
		this.seguroInvalidez = seguroInvalidez;
	}

	public boolean isSeguroDesemprego() {
		return seguroDesemprego;
	}

	public void setSeguroDesemprego(boolean seguroDesemprego) {
		this.seguroDesemprego = seguroDesemprego;
	}

}
