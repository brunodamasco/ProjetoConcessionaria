package bruno.senac.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conexao;
	
	public static Connection getConexao(){
		if(conexao==null){
			try {
				conexao = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("Conectou...");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexao;
	}
}
