package br.com.loginuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Filtro de controle de acesso da pagina principal
@WebFilter("/index.xhtml")
public class AppFilter implements Filter {

	public AppFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		if(session.getAttribute("logado") != null) {
			if(session.getAttribute("contato")!=null) {
				session.setAttribute("contato", null);
			}
				chain.doFilter(request, response);
		}else {
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
