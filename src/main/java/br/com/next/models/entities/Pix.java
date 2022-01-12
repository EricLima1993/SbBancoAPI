package br.com.next.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PIX")
public class Pix {

	@Id
	@Column(name = "ID_PIX")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIX_SEQ")
	@SequenceGenerator(name = "PIX_SEQ", initialValue = 1, allocationSize = 1)
	private int id;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	@Column(name = "ID_CLIENTE")
	private int idRecebe;
	
	@Column(name = "VALOR")
	private double valor;

	
	
	public Pix() {
		super();
	}

	public Pix(int id, String codigo, int idRecebe, double valor) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.idRecebe = idRecebe;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getIdRecebe() {
		return idRecebe;
	}

	public void setIdRecebe(int idRecebe) {
		this.idRecebe = idRecebe;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
