package br.com.hightechcursos.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hightechcursos.entidades.Usuario;

public class UsuarioDAO {

	private Connection con = Conexao.getConnection();

	
	public void salvar(Usuario usuario) {
		
		
		if(usuario.getId()!=null && usuario.getId()!=0) {
			alterar(usuario);
		} else {
			cadastrar(usuario);
		}
			
		
	}
	
	
	public void cadastrar(Usuario usuario) {

		// Monta o SQL
		String sql = "INSERT INTO USUARIO (nome, login, senha) values (?,?,?)";

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.execute();
			preparador.close();
			System.out.println("Cadastrado com Sucesso");

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}
	}

	public void alterar(Usuario usuario) {

		// Monta o SQL
		String sql = "UPDATE USUARIO SET nome=?, login=?, senha=? WHERE id=?";

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Alterado com Sucesso");

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

	}

	public void excluir(Usuario usuario) {

		// Monta o SQL
		String sql = "DELETE FROM USUARIO WHERE id=?";

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usuario.getId());
			preparador.execute();
			preparador.close();
			System.out.println("Excluido com Sucesso");

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

	}

	public List<Usuario> buscarTodos() {

		// Monta o SQL
		String sql = "SELECT * FROM USUARIO ORDER BY NOME";
		List<Usuario> lista = new ArrayList<>();

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {

				Usuario usu = new Usuario();

				usu.setId(resultado.getInt("id")); // valor da coluna
				usu.setNome(resultado.getString("nome")); // valor da coluna
				usu.setLogin(resultado.getString("login")); // valor da coluna
				usu.setSenha(resultado.getString("senha")); // valor da coluna

				lista.add(usu);

			}

			preparador.close();
			System.out.println("Busca no Banco com Sucesso");

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}
		return lista;

	}

	public Usuario buscarID(Integer id) {

		// Monta o SQL
		String sql = "SELECT * FROM USUARIO WHERE ID=?";

		Usuario usuario = null;

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, id);

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usuario = new Usuario();
				usuario.setId(resultado.getInt("id")); // valor da coluna

				// usuario.setId(Integer.parseInt(resultado.getString("id")));

				usuario.setNome(resultado.getString("nome")); // valor da coluna
				usuario.setLogin(resultado.getString("login")); // valor da
																// coluna
				usuario.setSenha(resultado.getString("senha")); // valor da
																// coluna

				preparador.close();
				System.out.println("Busca no Banco por ID com Sucesso");

			}

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

		return usuario;

	}

	public List<Usuario> buscarNome(String nome) {

		// Monta o SQL
		String sql = "SELECT * FROM USUARIO WHERE nome like ?";

		List<Usuario> lista = new ArrayList<Usuario>();

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setString(1, "%" + nome + "%");

			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id")); // valor da coluna

				// usuario.setId(Integer.parseInt(resultado.getString("id")));

				usuario.setNome(resultado.getString("nome")); // valor da coluna
				usuario.setLogin(resultado.getString("login")); // valor da
																// coluna
				usuario.setSenha(resultado.getString("senha")); // valor da
																// coluna
				lista.add(usuario);

				preparador.close();
				System.out.println("Busca no Banco por ID com Sucesso");

			}

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

		return lista;

	}

	public Usuario autenticar(Usuario usuario) {

		// Monta o SQL
		String sql = "SELECT * FROM USUARIO WHERE login=? and senha=? ";

		Usuario usuarioRetorno = null;

		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());

			ResultSet resultado = preparador.executeQuery();

			if (resultado.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(resultado.getInt("id")); // valor da coluna

				// usuario.setId(Integer.parseInt(resultado.getString("id")));

				usuarioRetorno.setNome(resultado.getString("nome")); // valor da
																		// coluna
				usuarioRetorno.setLogin(resultado.getString("login")); // valor
																		// da
																		// coluna
				usuarioRetorno.setSenha(resultado.getString("senha")); // valor
																		// da
																		// coluna

				System.out.println("Busca no Banco por Login e Senha com Sucesso");

			}

		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

		return usuarioRetorno;

	}

	
	
	public Boolean existeUsuario(Usuario usuario) {

		// Monta o SQL
		String sql = "SELECT * FROM USUARIO WHERE login=? and senha=? ";
		boolean ret = false;
		
		// Constroi o Statement com o SQL
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setString(1, usuario.getLogin());
			preparador.setString(2, usuario.getSenha());

			ResultSet resultado = preparador.executeQuery();

			ret = resultado.next();
		
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage());
		}

		return ret;
		
	}

}
