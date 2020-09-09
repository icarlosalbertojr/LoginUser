package br.com.loginuser.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.loginuser.dao.DaoTelefone;
import br.com.loginuser.model.Telefone;
import br.com.loginuser.model.Usuario;

@ManagedBean
@ViewScoped
public class TelefoneManagedBean {
	
	@SuppressWarnings("unused")
	private List<Telefone> list = new ArrayList<>();
	private Telefone telefone = new Telefone();
	private DaoTelefone dao = new DaoTelefone();
	private String mensagem = "";
	private Usuario user = new Usuario();
	private boolean btnSave = true;
	private boolean btnUpdate = false;
	
	//Inicia a tela de contato capturando o usuario que foi passado como valor do atributo de sessao "contato".
	public TelefoneManagedBean() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		user = (Usuario) session.getAttribute("contato");
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
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
	
	//Retorna a lista de telefones do usuario para ser vizualizada na tabela.
	public List<Telefone> getList() {
		return dao.findByUser(user.getId());
	}

	public void setList(List<Telefone> list) {
		this.list = list;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	//Funcao que e chamada na view para salvar os dados digitados nos campos, se estiverem de acordo com as restricoes.
	public String salvar() {
		
		try {
			if(Integer.toString(telefone.getDdd()).equals("")) {
				mensagem = "Digite o ddd!";
			}else if(telefone.getNumero().equals("")) {
				mensagem = "Digite o numero"; 
			}else if(telefone!=null) {
				telefone.setUsuario(user);
				dao.save(telefone);
				telefone = new Telefone();
				mensagem = "Contato foi cadastrado!";
			}
		}catch(Exception e) {
			mensagem = "Contato nao foi cadastrado!";
			e.printStackTrace();
		}
		return "";
	}
	
	//Funcao para capturar os dados do telefone na tabela e colocar nos campos para que seja feita alteracao nas informacoes.
	public String setUpdate(long id){
		try {
			telefone = dao.findById(id);
			btnSave = false;
			btnUpdate = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//Funcao que faz a verificacao das restricoes, e confirma a alteracao nas informacoes
	public String confirmUpdate() {
		try {
			if(Integer.toString(telefone.getDdd()).equals("")) {
				mensagem = "Digite o ddd!";
			}else if(telefone.getNumero().equals("")) {
				mensagem = "Digite o numero"; 
			}else if(telefone!=null) {
			dao.update(telefone);
			btnSave = true;
			btnUpdate = false;
			telefone = new Telefone();
			mensagem = "Contato foi atualizado!";
			}
		}catch(Exception e) {
			mensagem = "Contato nao foi atualizado!";
			e.printStackTrace();
		}
		return "";
	}
	
	//Funcao que e chamada pelo botao para excluir o telefone do banco de dados
	public String delete(long id) {
		try {
			dao.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	
}
