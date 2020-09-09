package br.com.loginuser.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Abre conexão com o Driver do banco de dados
public class EntityManagerUtil {
	
	static EntityManagerFactory emf = null;
	
	public static EntityManager getConnection(){
		if(emf==null){
			try {
				emf = Persistence.createEntityManagerFactory("LOGINUSER");
			}catch(Exception e) {
				System.err.println("Erro ao obter conexão: "+e);
			}
		}
		return emf.createEntityManager();
	}

}
