package br.com.loginuser.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Classe modelo do usuario

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME",nullable = false ,length = 50)
	private String nome;
	
	@Column(name = "EMAIL",nullable = false,length = 60)
	private String email;
	
	@Column(name = "SENHA",nullable = false, length = 16)
	private String senha;
	
	//Mapeamento dos telefones; Caso o usuario seja removido, os telefones referenciados pela PK tambem serao  
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "usuario",orphanRemoval = true)
	private List<Telefone> telefones = null;
	
	public Usuario () {
		
	}
	public Usuario(String nome, String email,String senha, List<Telefone> tel) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.telefones = tel;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefone(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefones="
				+ telefones + "]";
	}
	
	
}
