package br.com.loginuser.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.loginuser.dao.DaoUsuario;
import br.com.loginuser.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioManagedBean {
	
	@SuppressWarnings("unused")
	private List<Usuario> list = new ArrayList<>();
	private Usuario user = new Usuario();
	private DaoUsuario daoUser = new DaoUsuario();
	private String mensagem = "";
	private boolean btnSave = true;
	private boolean btnUpdate = false;
	
	public UsuarioManagedBean() {
	}
	
	public UsuarioManagedBean(Usuario user){
		this.user = user;
	}
	public boolean isBtnSave() {
		return btnSave;
	}

	public void setBtnSave(boolean btnSave) {
		this.btnSave = btnSave;
	}

	public boolean isBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(boolean btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	//Retorna a lista de todos os usuarios cadastrados para serem vizualizados na tabela.
	public List<Usuario> getList() {
		return daoUser.findAll();
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}
	
	//Funcao que e chamada na tela para salvar as informacoes do usuario no banco
	public String salvar() {

		//Verificacoes das restricoes do sistema
		boolean isntEmail = !user.getEmail().contains("@");
		boolean isntUniqueEmail = daoUser.findByEmail(user.getEmail()) != null;
		boolean minPass = user.getSenha().length() < 8;
		boolean maxPass = user.getSenha().length() > 16;
		boolean checkNull = user.getNome().equals("") || user.getEmail().equals("") || user.getSenha().equals("");
		
		try {
			if (checkNull) {
				mensagem = "Preencha todos os campos!";
			} else if (isntEmail) {
				mensagem = "E-mail invalido!";
			} else if (isntUniqueEmail) {
				mensagem = "O e-mail '" + user.getEmail() + "' ja foi cadastrado!";
			} else if (minPass) {
				mensagem = "A senha deve conter, no minimo, 8 digitos!";
			} else if (maxPass) {
				mensagem = "A senha deve conter, no maximo, 16 digitos!";
			} else if (user != null) {
				daoUser.save(user);
				user = new Usuario();
				mensagem = "Cadastrado com sucesso!";
			}
		} catch (Exception e) {
			mensagem = "Usuario nao foi cadastrado!";
			e.printStackTrace();
		}
		return "";
	}
	
	//Exclui o usuario do banco
	public String delete(long id) {
		try {
			daoUser.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//Captura as informacoes da tabela de usuario (atraves do botao "editar") para preencher os campos para possibilitar alteracao nas informacoes
	public String setUpdate(long id) {
		try {
			user = daoUser.findById(id);
			btnSave = false;
			btnUpdate = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//Confirma as alteracoes das informacoes ja cadastradas, que foram passadas para os campos (atraves do botao "atualizar")
	public String confirmUpdate() {
		boolean isntEmail = !user.getEmail().contains("@");
		boolean minPass = user.getSenha().length() < 8;
		boolean maxPass = user.getSenha().length() > 16;
		boolean checkNull = user.getNome().equals("") || user.getEmail().equals("") || user.getSenha().equals("");
		try {
			if (checkNull) {
				mensagem = "Preencha todos os campos!";
			} else if (isntEmail) {
				mensagem = "E-mail invalido!";
			} else if (minPass) {
				mensagem = "A senha deve conter, no minimo, 8 digitos!";
			} else if (maxPass) {
				mensagem = "A senha deve conter, no maximo, 16 digitos!";
			} else if (user != null) {
				daoUser.update(user);
				user = new Usuario();
				btnSave = true;
				btnUpdate = false;
				mensagem = "Atualizado com sucesso!";
			}
		} catch (Exception e) {
			mensagem = "Nao foi possivel salvar atualizacao!";
			e.printStackTrace();
		}
		return "";
	}
	
	//Funcao chamada ao clicar no botao "contato"; Captura o usuario especifico e passa como valor de atributo de sessao para a pagina de contato
	public String contato(Usuario usuario) {
		if (usuario != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("contato", usuario);
			return "/contato.xhtml?faces-redirect=true";
		} else {
			return "/index.xhtml?faces-redirect=true";
		}

	}
}
