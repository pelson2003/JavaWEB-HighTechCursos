package br.com.hightechcursos.teste;

import java.util.List;

import br.com.hightechcursos.entidades.Usuario;
import br.com.hightechcursos.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {

		 testeCadastrar();
		// testealterar();
		// testeexcluir();
		// testeBuscarTodos();
		// testeBuscarID();
		//testeAutenticar();
	}

	private static void testeCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Vimerson");
		usu.setLogin("vir");
		usu.setSenha("vv123");
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.cadastrar(usu);
	}

	private static void testealterar() {
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("Jao da Silva");
		usu.setLogin("js");
		usu.setSenha("js123");
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.alterar(usu);
	}

	private static void testeexcluir() {
		Usuario usu = new Usuario();
		usu.setId(6);
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.excluir(usu);
	}

	private static void testeBuscarTodos() {

		UsuarioDAO usuDao = new UsuarioDAO();
		List<Usuario> listaResultado = usuDao.buscarTodos();
		for (Usuario u : listaResultado) {
			System.out.println(u.getId() + " " + u.getNome() + " " + u.getLogin() + " " + u.getSenha());
		}
	}

	private static void testeBuscarID() {

		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usuario = usuDao.buscarID(3);

		if (usuario != null) {
			System.out.println(
					usuario.getId() + " " + usuario.getNome() + " " + usuario.getLogin() + " " + usuario.getSenha());
			// sysout(usuarioDAO.buscaporID(1).getnome());
		}
	}

	private static void testeAutenticar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("juju");
		usuario.setSenha("ju123");

		UsuarioDAO usuarioDao = new UsuarioDAO();
		System.out.println(usuarioDao.autenticar(usuario));

	}

}
