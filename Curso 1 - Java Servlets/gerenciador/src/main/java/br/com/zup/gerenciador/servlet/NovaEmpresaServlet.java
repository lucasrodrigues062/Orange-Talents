package br.com.zup.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/novaEmpresa")
public class NovaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Cadastrando nova empresa");
		PrintWriter out = response.getWriter();
		//Pegando um parametro de URL
		String nomeEmpresa = request.getParameter("nome");
		String paramDataAbertura = request.getParameter("data");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAbertura = null;
		try {
			dataAbertura = sdf.parse(paramDataAbertura);
		} catch (ParseException e) {

			throw new ServletException(e);
		}
		
		
		Empresa empresa = new Empresa();
		
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
		banco.adiciona(empresa);
		
		request.setAttribute("empresa", empresa.getNome());
		response.sendRedirect("listaEmpresa");
		
		
		
		// Chamar o JSP, Request Dispatcher
		
		//RequestDispatcher rd = request.getRequestDispatcher("/novaEmpresaCriada.jsp");
		
		// Chamar um novo dispatcher
//		RequestDispatcher rd = request.getRequestDispatcher("/listaEmpresa");
//		
//		//enviado atributos na requisicao
//		request.setAttribute("empresa", empresa.getNome());
//		rd.forward(request, response);
	
	}

}
