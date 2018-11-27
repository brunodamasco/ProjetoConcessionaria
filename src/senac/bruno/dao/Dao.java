package senac.bruno.dao;

import java.sql.Connection;

import bruno.senac.factory.Conexao;

public class Dao {
	protected Connection conexao;
	
	public Dao() {
		conexao = Conexao.getConexao();
	}
}
