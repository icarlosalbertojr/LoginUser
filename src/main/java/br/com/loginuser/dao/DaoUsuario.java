package br.com.loginuser.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

import br.com.loginuser.model.Usuario;
import br.com.loginuser.util.EntityManagerUtil;

public class DaoUsuario extends Dao<Usuario>{
	
	
	//Funcao para deletar os dados do usuario no banco
	@Override
	public Usuario deleteById(long id) {
		EntityManager em = EntityManagerUtil.getConnection();
		Usuario usuario = null;
		try {
			em.getTransaction().begin();
			usuario = em.find(Usuario.class,id);
			em.remove(usuario);
			em.getTransaction().commit();
			return usuario;
		}catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return null;
	}

	//Funcao para encontrar usuario pelo id
	@Override
	public Usuario findById(long id) {
		EntityManager em = EntityManagerUtil.getConnection();
		Usuario usuario = null;
		try {
			usuario = em.find(Usuario.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return usuario;
	}
	
	//Funcao para listar todos os usuarios cadastrados no banco de dados 
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		EntityManager em = EntityManagerUtil.getConnection();
		List<Usuario> usuarios = null;
		try {
			usuarios = em.createQuery("from Usuario").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return usuarios;
	}
	
	
	//Funcao para encontrar usuario atraves do e-mail
	@SuppressWarnings("unchecked")
	public Usuario findByEmail(String email) {
		EntityManager em = EntityManagerUtil.getConnection();
		Usuario usuario = null;
		List<Usuario> users = new ArrayList<>();
		try {
			users = em.createQuery("FROM Usuario u where u.email = '"+email+"'").getResultList();
			usuario = users.get(0);
			return usuario;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return usuario;
	}
	
	//Funcao para para validacao de login(e-mail+senha)
	@SuppressWarnings("unchecked")
	public boolean findLogin(String email,String senha) {
		EntityManager em = EntityManagerUtil.getConnection();
		Usuario usuario = null;
		List<Usuario> user = new ArrayList<>();
		try {
			user = em.createQuery("FROM Usuario u where u.email = '"+email+"' and u.senha = '"+senha+"'").getResultList();
			usuario = user.get(0);
			if(usuario!=null) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return false;
	}

}
