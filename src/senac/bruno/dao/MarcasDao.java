package senac.bruno.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Clientes;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;

public class MarcasDao extends Dao implements DaoI<Marcas>{

	@Override
	public boolean cadastrar(Marcas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("INSERT INTO marcas "
					+ "(nome) VALUES(?)");
			stmt.setString(1, obj.getNomeMarca());
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar marcas "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Marcas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("UPDATE marcas SET nome=? "
					+ "WHERE idmarcas=?");
			stmt.setString(1, obj.getNomeMarca());
			stmt.setInt(2, obj.getIdMarcas());
			stmt.executeUpdate();			
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar marcas "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Marcas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("DELETE FROM marcas WHERE idmarcas=?");			

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar marcas "+ e.getMessage());
			return false;
		}
	}

	@Override
	public List<Marcas> listar() {
		PreparedStatement stmt;
		List<Marcas> listMarcas = new ArrayList<Marcas>();
		try {
			stmt = conexao.prepareStatement("SELECT idmarcas, nome FROM marcas");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Marcas m = new Marcas();
				m.setIdMarcas(result.getInt("idmarcas"));
				m.setNomeMarca(result.getString("nome"));
				listMarcas.add(m);
			}
			return listMarcas;
		} catch (SQLException e) {
			System.out.println("erro ao listar marcas"+e.getMessage());
			return null;
		}
	}


	public List<Marcas> buscaPorNome(String nome) {
		PreparedStatement stmt;
		List<Marcas> listNome = new ArrayList<Marcas>();
		try {
			stmt = conexao.prepareStatement("SELECT idmarcas, nome FROM marcas WHERE nome LIKE ? ORDER BY nome DESC");
			stmt.setString(1, nome+"%");
			
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				Marcas m = new Marcas();
				m.setIdMarcas(res.getInt("idmarcas"));
				m.setNomeMarca(res.getString("nome"));
				listNome.add(m);
			}
			return listNome;
		} catch (SQLException e) {
			System.out.println("erro ao buscar por nome da marca "+e.getMessage());
			return null;
		}
	}
	
	public List<Modelos> buscaModelosPorMarca(int idMarca) {
		PreparedStatement stmt;
		List<Modelos> listModelos = new ArrayList<Modelos>();
		try {
			stmt = conexao.prepareStatement("SELECT idmodelo, nome FROM modelo WHERE idmarcas=?");
			stmt.setInt(1, idMarca);
								
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
				Modelos m = new Modelos();
				m.setIdModelo(res.getInt("idmodelo"));
				m.setNome(res.getString("nome"));
				listModelos.add(m);
			}
			return listModelos;
		} catch (SQLException e) {
			System.out.println("erro ao buscar modelo por nome da marca "+e.getMessage());
			return null;
		}
	}
	
}
