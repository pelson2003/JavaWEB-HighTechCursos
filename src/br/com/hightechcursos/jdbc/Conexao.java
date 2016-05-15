package br.com.hightechcursos.jdbc;

import java.sql.Connection; // Todos os tipos do JDBC
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	
	
	public static Connection getConnection(){
		
		Connection con=null;
		try {
			try {
				Class.forName("org.postgresql.Driver"); // Força o carregamento do Drive
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Driver nao encontrado");;
			}
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bancocjweb1", "postgres", "06122014");
			System.out.println("Conexao Funcionou");
		} catch (SQLException e) {
			System.out.println("Erro" + e.getMessage() );
			//e.printStackTrace();
		}
		return con;
		
	}
	

}
