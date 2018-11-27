package senac.bruno.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Clientes;

public class ClientesDao extends Dao implements DaoI<Clientes>{

	@Override
	public boolean cadastrar(Clientes obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("INSERT INTO clientes (nome, cpf, dataNascimento, telefone) "
					+ "VALUES(?,?,?,?);");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCpf());
			stmt.setDate(3, obj.getDataNascimento());
			stmt.setString(4, obj.getTelefone());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar clientes "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Clientes obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("UPDATE clientes SET nome=?, cpf=?, dataNascimento=?, telefone=? "
					+ "WHERE idclientes=?");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCpf());
			stmt.setDate(3, obj.getDataNascimento());
			stmt.setString(4, obj.getTelefone());
			stmt.setInt(5, obj.getIdClientes());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar clientes "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Clientes obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("UPDATE clientes SET ativo= 0 WHERE idclientes=?");
			stmt.setInt(1, obj.getIdClientes());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar clientes "+e.getMessage());
			return false;
		}
	}

	@Override
	public List<Clientes> listar() {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT idclientes, nome, cpf, "
					+ "datanascimento, telefone FROM clientes WHERE ativo = 1");
			ResultSet result = stmt.executeQuery();
			List<Clientes> listClientes = new ArrayList<Clientes>();
			while(result.next()) {
				Clientes c = new Clientes();
				c.setIdClientes(result.getInt("idclientes"));
				c.setNome(result.getString("nome"));
				c.setCpf(result.getString("cpf"));
				c.setDataNascimento(result.getDate("dataNascimento"));
				c.setTelefone(result.getString("telefone"));
				listClientes.add(c);
			}
			return listClientes;
		} catch (SQLException e) {
			System.out.println("erro ao listar clientes "+e.getMessage());
			return null;
		}
	}
	public List<Clientes> buscaPorNome(String nome) {
		List<Clientes> listNome = new ArrayList<Clientes>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT idclientes, nome, cpf, "
					+ "datanascimento, telefone FROM clientes WHERE nome LIKE ? ORDER BY nome DESC");
			stmt.setString(1, nome+"%");
			
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				Clientes c = new Clientes();
				c.setIdClientes(res.getInt("idclientes"));
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setDataNascimento(res.getDate("datanascimento"));
				c.setTelefone(res.getString("telefone"));
				listNome.add(c);
			}
			return listNome;
		} catch (SQLException e) {
			System.out.println("erro no clientedao ao buscar por nome do clientes "+e.getMessage());
			return null;
		}
	}

	public Clientes verifica(String nome) {
		List<Clientes> listNome = new ArrayList<Clientes>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT idclientes, nome, cpf, "
					+ "datanascimento, telefone FROM clientes WHERE nome = ? ");
			stmt.setString(1, nome);
			
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				Clientes c = new Clientes();
				c.setIdClientes(res.getInt("idclientes"));
				c.setNome(res.getString("nome"));
				c.setCpf(res.getString("cpf"));
				c.setDataNascimento(res.getDate("datanascimento"));
				c.setTelefone(res.getString("telefone"));
				listNome.add(c);
				return c;
			}
			
		} catch (SQLException e) {
			System.out.println("erro ao buscar por nome do clientes "+e.getMessage());
			return null;
		}
		return null;
	}
}
