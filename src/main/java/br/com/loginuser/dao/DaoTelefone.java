package br.com.loginuser.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.loginuser.model.Telefone;
import br.com.loginuser.util.EntityManagerUtil;

public class DaoTelefone extends Dao<Telefone>{
	
	//Funcao para deletar os dados do telefone no banco
	@Override
	public Telefone deleteById(long id) {
		EntityManager em = EntityManagerUtil.getConnection();
		Telefone telefone = null;
		try {
			em.getTransaction().begin();
			telefone = em.find(Telefone.class,id);
			em.remove(telefone);
			em.getTransaction().commit();
			return telefone;
		}catch(Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
	}

	//Funcao para encontrar telefone pelo id.
	@Override
	public Telefone findById(long id) {
		if(id<0)return null;
		EntityManager em = EntityManagerUtil.getConnection();
		Telefone telefone = null;
		try {
			telefone = em.find(Telefone.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return telefone;
	}

	//Funcao para encontrar os telefones que pertencem ao usuario em que id foi passado como parametro.
	@SuppressWarnings({ "unchecked" })
	public List<Telefone> findByUser(long id){
		EntityManager em = EntityManagerUtil.getConnection();
		List<Telefone> telefones = null;
		try {
			telefones = em.createQuery("from Telefone where usuario_id ="+id).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return telefones;
	}
	
}
