package br.com.loginuser.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.loginuser.dao.DaoUsuario;
import br.com.loginuser.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginManagedBean {

	private Usuario user = new Usuario();
	
	private DaoUsuario dao = new DaoUsuario();
	
	private String mensagem = "";

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
	//Metodo para logar no sistema
	public String login() {
		
		//Usuario padrao
		boolean admin = user.getEmail().equals("admin") && user.getSenha().equals("admin");
		
		//Valida as informacoes de login buscando no banco de dados 
		boolean login = dao.findLogin(user.getEmail(), user.getSenha());
		
		//Verifica se os campos não sao vazios
		boolean isNull = user.getEmail().equals("") || user.getSenha().equals("");
		
		if(isNull) {
			
			user = new Usuario();
		
			mensagem = "Digite as informacoes de login corretamente!";
			
			return "/login.xhtml?faces-redirect=true";
		
		} else if(admin || login){
		
			//Captura a sessao 
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			
			//Atribui o usuario como valor do atributo "logado" da sessao capturada
			session.setAttribute("logado", user);
			
			return "/index.xhtml?faces-redirect=true";
		
		}else {
			
			mensagem = "Usuario e/ou senha invalidos!";
			
			return "/login.xhtml?faces-redirect=true";
		}
	}
	//Metodo para sair do sistema
	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/login.xhtml?faces-redirect=true";
	}

}
