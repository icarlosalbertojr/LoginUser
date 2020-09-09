package br.com.loginuser.dao;


import javax.persistence.EntityManager;
import br.com.loginuser.util.EntityManagerUtil;

public abstract class Dao<Objeto> {
	
	//Funcao que persiste dados no banco 
	public Objeto save(Objeto objeto) {
		EntityManager em = EntityManagerUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.persist(objeto);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	//Funcao que atualiza informacoes ja cadastradas
	public Objeto update(Objeto objeto) {
		EntityManager em = EntityManagerUtil.getConnection();
		try {
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	//Funcoes que devem ser implementadas pelas subclasses
	public abstract Objeto deleteById(long id);
	public abstract Objeto findById(long id);


}
