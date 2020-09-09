package br.com.loginuser.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//Classe modelo de telefone
@Entity
public class Telefone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DDD",length = 2,nullable = false)
	private Integer ddd;
	
	@Column(name = "NUMERO",length = 10,nullable = false)
	private String numero;
	
	@Column(name = "TIPO", length = 20,nullable = false)
	private String tipo;
	
	@ManyToOne
	private Usuario usuario;
	
	
	public Telefone() {
	}
	
	public Telefone(Integer ddd,String numero,String tipo,Usuario user) {
		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;
		this.usuario = user;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getDdd() {
		return ddd;
	}
	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
