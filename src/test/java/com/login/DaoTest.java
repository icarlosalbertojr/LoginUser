package com.login;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.loginuser.dao.DaoTelefone;
import br.com.loginuser.dao.DaoUsuario;
import br.com.loginuser.model.Telefone;
import br.com.loginuser.model.Usuario;

public class DaoTest {
	
	//Teste DAO do usuario
	@Test
	public void testUsuarioDao() {
		List<Usuario> list = new ArrayList<>();
		DaoUsuario dao = new DaoUsuario();
		Usuario user = new Usuario("teste@gmail.com", "teste", "teste123", null);
		
		// Teste Save Usuario
		assertNotNull(dao.save(user));

		// Lista todos os usuarios da lista
		list = dao.findAll();
		assertNotNull(list);

		// Mostra os dados dos usuarios obtidos na lista e testa individualmente
		System.out.println("________________LISTA GERAL__________________");
		for (Usuario u : list) {
			System.out.println("__________________________________");
			System.out.println("ID: " + u.getId());
			System.out.println("NOME: " + u.getNome());
			System.out.println("E-MAIL: " + u.getEmail());
			System.out.println("SENHA: " + u.getSenha());
			System.out.println("");

			user = dao.findById(u.getId());
			assertNotNull(user);
		}
		
		//Teste Update
		user.setEmail("updateTeste@gmail.com");
		user.setNome("updateTeste");
		user.setSenha("updateTeste123");
		assertNotNull(dao.update(user));
		System.out.println("_______________UPDATE_________________");
		System.out.println("ID: " + user.getId());
		System.out.println("NOME: " + user.getNome());
		System.out.println("E-MAIL: " + user.getEmail());
		System.out.println("SENHA: " + user.getSenha());
		System.out.println("");
		
		//Teste Delete by Id
		assertNotNull(dao.deleteById(user.getId()));
	}
	
	//Teste DAO do telefone
	@Test
	public void testDaoTelefone() {
		Usuario user = new Usuario("teste@gmail.com", "teste", "teste123", null);
		Telefone tel = new Telefone(81,"85192299","celular",user);
		DaoUsuario daoUser = new DaoUsuario();
		DaoTelefone dao = new DaoTelefone();
		
		//Salva usuario para ser referenciado pelo telefone
		daoUser.save(user);
		
		//Teste Save Telefone
		assertNotNull(dao.save(tel));
		
		//Teste FindById Telefone
		assertNotNull(dao.findById(tel.getId()));
		
		//Teste Find by User
		assertNotNull(dao.findByUser(user.getId()));
		
		tel.setNumero("87463545");
		
		//Teste Update telefone
		assertNotNull(dao.update(tel));
		assertSame(tel, dao.update(tel));
		
		//Teste Delete by Id
		assertNotNull(dao.deleteById(tel.getId()));
		
		//Deleta o usuario auxiliar de teste
		daoUser.deleteById(user.getId());
		
	}
	
}
