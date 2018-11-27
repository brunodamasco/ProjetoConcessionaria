package senac.bruno.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;

public class ModeloDao extends Dao implements DaoI<Modelos>{

	@Override
	public boolean cadastrar(Modelos obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("INSERT INTO modelo "
					+ "(nome, idmarcas) VALUES(?,?)");
			stmt.setString(1, obj.getNome());
			stmt.setInt(2, obj.getMarcas().getIdMarcas());
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar modelos "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Modelos obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("UPDATE modelo SET nome=?, idmarcas = ? "
					+ "WHERE idmodelo=?");
			stmt.setString(1, obj.getNome());
			stmt.setInt(2, obj.getMarcas().getIdMarcas());
			stmt.setInt(3, obj.getIdModelo());
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar modelos "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Modelos obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("UPDATE modelo SET ativo = 0 WHERE idmodelo=?");			
			stmt.setInt(1, obj.getIdModelo());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar modelos "+ e.getMessage());
			return false;
		}
	}

	@Override
	public List<Modelos> listar() {
		PreparedStatement stmt;
		List<Modelos> listModelo = new ArrayList<Modelos>();
		try {
			stmt = conexao.prepareStatement("select m.idmodelo,m.nome,ma.nome "
					+ "from modelo as m inner join marcas as ma on ma.idmarcas = m.idmarcas  "
					+ "where  m.ativo = 1 order by m.idmodelo ;");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Modelos modelos = new Modelos();
				modelos.setIdModelo(result.getInt("m.idmodelo"));
				modelos.setNome(result.getString("m.nome"));
				modelos.getMarcas().setNomeMarca(result.getString("ma.nome"));
				listModelo.add(modelos);
			}
			return listModelo;
		} catch (SQLException e) {
			System.out.println("erro ao listar modelos"+e.getMessage());
			return null;
		}
	}

	
	
	public List<Modelos> buscaPorNome(String nome) {
		PreparedStatement stmt;
		List<Modelos> listNome = new ArrayList<Modelos>();
		try {
			stmt = conexao.prepareStatement("select m.idmodelo,m.nome,ma.nome from "
					+ "modelo as m inner join marcas as ma on ma.idmarcas = m.idmarcas "
					+ " where  m.nome like ? and  m.ativo = 1 order by m.idmodelo ;");
			stmt.setString(1, nome+"%");
			
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				Modelos m = new Modelos();
				m.setIdModelo(res.getInt("m.idmodelo"));
				m.setNome(res.getString("m.nome"));
				m.getMarcas().setNomeMarca(res.getString("ma.nome"));
				listNome.add(m);
			}
			return listNome;
		} catch (SQLException e) {
			System.out.println("erro ao buscar por nome do modelo "+e.getMessage());
			return null;
		}
	}
	
}
