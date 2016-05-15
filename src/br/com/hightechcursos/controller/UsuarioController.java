package br.com.hightechcursos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLWriter;

import com.sun.webkit.dom.HTMLUListElementImpl;

import br.com.hightechcursos.entidades.Usuario;
import br.com.hightechcursos.jdbc.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// // TODO Auto-generated method stub
		// //response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		// System.out.println("Metodo GET");
		// String nome = request.getParameter("nome");
		// //System.out.println("Nome:" + nome);
		// String empresa = request.getParameter("empresa");
		// //System.out.println("Empresa:" + empresa);
		// PrintWriter saida = response.getWriter();
		// saida.println("Nome e Empresa: " + nome + empresa );
		//

		// Instancia usuario DAO
		UsuarioDAO usuDAO = new UsuarioDAO();

		// Captura Parametro antes do IF
		String acao = request.getParameter("acao");

		// Comando de Excluir
		if (acao != null && acao.equals("exc")) {
			// Captura Parametro
			String id = request.getParameter("id");
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(id));
			usuDAO.excluir(usuario);
			//Redirecionando para o cliente
			response.sendRedirect("usucontroller.do?acao=lis");

		} else if (acao != null && acao.equals("alt")) {
			String id = request.getParameter("id");
			Usuario usuario = usuDAO.buscarID(Integer.parseInt(id));
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);

		} else if (acao != null && acao.equals("cad")) {
			
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			
			request.setAttribute("usuario", usuario);
			RequestDispatcher saida = request.getRequestDispatcher("frmusuario.jsp");
			saida.forward(request, response);
			
			
		} else if (acao != null  && acao.equals("lis")) {
			// Obter a Lista
			List<Usuario> lista = usuDAO.buscarTodos();

			// Engavetar a lista no JSB ; attribuir
			request.setAttribute("lista", lista);

			// Encaminhar para o JPS
			RequestDispatcher saida = request.getRequestDispatcher("listausuarios.jsp");
			saida.forward(request, response);
		
		}

		// SCRIPTLET
		// String htmlSaida = "<html> <body> <table border='1'>";
		// htmlSaida += "<tr> <td> ID </td> <td> Nome </td> <td> Login </td>
		// <td> Senha </td> </tr>";
		// for(Usuario usu: lista){
		// htmlSaida += "<tr> <td>" + usu.getId() + "</td> <td>" + usu.getNome()
		// + "</td> <td>" + usu.getLogin() + "</td> <td>" + usu.getSenha() +
		// "</td> </tr>";
		// }
		// htmlSaida += "</table></body></html>";
		// PrintWriter saida = response.getWriter();
		// saida.println(htmlSaida);
		//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Metodo POST");

		String id = request.getParameter("txtid");
		String nome = request.getParameter("txtnome");
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");

		Usuario usuario = new Usuario();

		if (id != null && id != "" && id != "0") {
			usuario.setId(Integer.parseInt(id)); // Convertendo para ID
		}

		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

		PrintWriter saida = response.getWriter();
		saida.println("Salvo!");

	}

}
