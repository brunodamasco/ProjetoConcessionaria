package senac.bruno.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Clientes;
import senac.bruno.model.Vendedor;

public class VendedorDao extends Dao implements DaoI<Vendedor>{

	@Override
	public boolean cadastrar(Vendedor obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("INSERT INTO vendedor (nome, cpf, comissao, salario) "
					+ "VALUES (?, ?, ?, ?);");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCpf());
			stmt.setDouble(3, obj.getComissao());
			stmt.setDouble(4, obj.getSalario());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro cadastro vendedor "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Vendedor obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("UPDATE vendedor SET nome=? cpf=? comissao=? salario=? WHERE idvendedor=?");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getCpf());
			stmt.setDouble(3, obj.getComissao());
			stmt.setDouble(4, obj.getSalario());
			stmt.setInt(5, obj.getIdVendedor());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar vendedor "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Vendedor obj) {
		try {
			//PreparedStatement stmt = conexao.prepareStatement("DELETE FROM vendedor WHERE idvendedor=?");
			PreparedStatement stmt = conexao.prepareStatement("UPDATE vendedor SET ativo= 0 WHERE idvendedor=?");
			stmt.setInt(1, obj.getIdVendedor());
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar vendedor "+e.getMessage());
			return false;
		}
	}

	@Override
	public List<Vendedor> listar() {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT idvendedor, nome, cpf, comissao, salario FROM vendedor WHERE ativo = 1");
			ResultSet result = stmt.executeQuery();
			List<Vendedor> listVendedor = new ArrayList<Vendedor>();
			while(result.next()) {
				Vendedor v = new Vendedor();
				v.setIdVendedor(result.getInt("idvendedor"));
				v.setNome(result.getString("nome"));
				v.setCpf(result.getString("cpf"));
				v.setComissao(result.getDouble("comissao"));
				v.setSalario(result.getDouble("salario"));
				listVendedor.add(v);
			}
			return listVendedor;
		} catch (SQLException e) {
			return null;
		}
	}

	
	
	
	public List<Vendedor> buscaPorNome(String nome) {
		List<Vendedor> listNome = new ArrayList<Vendedor>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT idvendedor, nome, cpf, "
					+ "comissao, salario FROM vendedor WHERE nome LIKE ? ORDER BY idvendedor asc");
			stmt.setString(1, nome+"%");
			
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				Vendedor v = new Vendedor();
				v.setIdVendedor(res.getInt("idvendedor"));
				v.setNome(res.getString("nome"));
				v.setCpf(res.getString("cpf"));
				v.setComissao(res.getDouble("comissao"));
				v.setSalario(res.getDouble("salario"));
				listNome.add(v);
			}
			return listNome;
		} catch (SQLException e) {
			System.out.println("erro ao buscar por nome do vendedor "+e.getMessage());
			return null;
		}
	}
	
}
