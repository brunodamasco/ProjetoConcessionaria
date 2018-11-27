package senac.bruno.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Veiculos;

public class VeiculosDao extends Dao implements DaoI<Veiculos> {

	@Override
	public boolean cadastrar(Veiculos obj) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("INSERT INTO veiculos (ano,quilometragem,cor,valor,valorfipe,chassis,idmodelo) "
							+ "VALUES (?,?,?,?,?,?,?);");
			stmt.setInt(1, obj.getAno());
			stmt.setInt(2, obj.getQuilometragem());
			stmt.setString(3, obj.getCor());
			stmt.setDouble(4, obj.getValor());
			stmt.setDouble(5, obj.getValorFipe());
			stmt.setString(6, obj.getChassis());
			stmt.setInt(7, obj.getModelos().getIdModelo());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar veiculos " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Veiculos obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement(
					"UPDATE veiculos SET ano=? quilometragem=? cor=? valor=? valorFipe=? chassis=? idmodelo=? "
							+ "WHERE idveiculos=?);");
			stmt.setInt(1, obj.getAno());
			stmt.setInt(2, obj.getQuilometragem());
			stmt.setString(3, obj.getCor());
			stmt.setDouble(4, obj.getValor());
			stmt.setDouble(5, obj.getValorFipe());
			stmt.setString(6, obj.getChassis());
			stmt.setInt(7, obj.getModelos().getIdModelo());
			stmt.setInt(8, obj.getIdVeiculos());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar veiculos " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Veiculos obj) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("UPDATE veiculos SET ativo= 0 WHERE idveiculos=?");
			stmt.setInt(1, obj.getIdVeiculos());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar veiculos " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Veiculos> listar() {
		List<Veiculos> listVeiculos = new ArrayList<Veiculos>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT " 
					+ "veiculos.idveiculos, marcas.nome , modelo.nome , veiculos.ano, veiculos.quilometragem, " 
					+ "veiculos.valor, veiculos.valorfipe, veiculos.cor, veiculos.chassis, modelo.idmarcas, veiculos.idmodelo "
					+ "FROM veiculos "
					+ "INNER JOIN modelo ON veiculos.idmodelo = modelo.idmodelo "
					+ "INNER JOIN marcas ON modelo.idmarcas = marcas.idmarcas "
					+ "WHERE veiculos.ativo = 1");

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Veiculos v = new Veiculos();
				v.setIdVeiculos(result.getInt("veiculos.idveiculos"));
				v.getModelos().getMarcas().setNomeMarca(result.getString("marcas.nome"));
				v.getModelos().setNome(result.getString("modelo.nome"));
				v.setAno(result.getInt("veiculos.ano"));
				v.setQuilometragem(result.getInt("veiculos.quilometragem"));
				v.setValor(result.getDouble("veiculos.valor"));
				v.setValorFipe(result.getDouble("veiculos.valorfipe"));
				v.setCor(result.getString("veiculos.cor"));
				v.setChassis(result.getString("veiculos.chassis"));
				v.getModelos().getMarcas().setIdMarcas(result.getInt("modelo.idmarcas"));
				v.getModelos().setIdModelo(result.getInt("veiculos.idmodelo"));
				listVeiculos.add(v);
			}
			return listVeiculos;
		} catch (SQLException e) {
			System.out.println("erro ao listar veiculos " + e.getMessage());
			return null;
		}
	}

	
	public List<Veiculos> buscarporNome(String nome) {
		List<Veiculos> listVeiculos = new ArrayList<Veiculos>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT " 
					+ "veiculos.idveiculos, marcas.nome , modelo.nome , veiculos.ano, " 
					+ "veiculos.quilometragem, veiculos.valor, veiculos.valorfipe, veiculos.cor, " 
					+ "veiculos.chassis, modelo.idmarcas, veiculos.idmodelo "
					+ "FROM veiculos "
					+ "INNER JOIN modelo ON veiculos.idmodelo = modelo.idmodelo "
					+ "INNER JOIN marcas ON modelo.idmarcas = marcas.idmarcas "
					+ "WHERE marcas.nome like ? or modelo.nome like ? "
					+ "ORDER BY veiculos.idveiculos asc");

			stmt.setString(1, nome+"%");
			stmt.setString(2, nome+"%");
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Veiculos v = new Veiculos();
				v.setIdVeiculos(result.getInt("veiculos.idveiculos"));
				v.getModelos().getMarcas().setNomeMarca(result.getString("marcas.nome"));
				v.getModelos().setNome(result.getString("modelo.nome"));
				v.setAno(result.getInt("veiculos.ano"));
				v.setQuilometragem(result.getInt("veiculos.quilometragem"));
				v.setValor(result.getDouble("veiculos.valor"));
				v.setValorFipe(result.getDouble("veiculos.valorfipe"));
				v.setCor(result.getString("veiculos.cor"));
				v.setChassis(result.getString("veiculos.chassis"));
				v.getModelos().getMarcas().setIdMarcas(result.getInt("modelo.idmarcas"));
				v.getModelos().setIdModelo(result.getInt("veiculos.idmodelo"));
				listVeiculos.add(v);
			}
			return listVeiculos;
		} catch (SQLException e) {
			System.out.println("erro ao buscar veiculos por id " + e.getMessage());
			return null;
		}
	}

	public List<Veiculos> buscaPorMarcaVeiculo(String marca) {
		List<Veiculos> listVeiculos = new ArrayList<Veiculos>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT " 
					+ "veiculos.idveiculos, " + "marcas.nome as marca, "
					+ "modelo.nome as modelo, veiculos.ano, veiculos.quilometragem, veiculos.valor, veiculos.valorFipe, " 
					+ "veiculos.cor, veiculos.chassis, modelo.idmarcas, veiculos.idmodelo "
					+ "FROM veiculos "
					+ "INNER JOIN modelo ON veiculos.idmodelo = modelo.idmodelo "
					+ "INNER JOIN marcas ON modelo.idmarcas = marcas.idmarcas "
					+ "WHERE marcas.nome LIKE ? ORDER BY marcas.nome DESC;");
			stmt.setString(1, marca + "%");
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				Veiculos v = new Veiculos();
				v.setIdVeiculos(result.getInt("idveiculos"));
				v.getModelos().getMarcas().setNomeMarca(result.getString("marca"));
				v.getModelos().setNome(result.getString("modelo"));
				v.setAno(result.getInt("ano"));
				v.setQuilometragem(result.getInt("quilometragem"));
				v.setValor(result.getDouble("valor"));
				v.setValorFipe(result.getDouble("valorfipe"));
				v.setCor(result.getString("cor"));
				v.setChassis(result.getString("chassis"));
				v.getModelos().getMarcas().setIdMarcas(result.getInt("idmarcas"));
				v.getModelos().setIdModelo(result.getInt("idmodelo"));
				listVeiculos.add(v);
			}
			return listVeiculos;
		} catch (SQLException e) {
			System.out.println("erro ao buscar veiculos por marca " + e.getMessage());
			return null;
		}
	}

	public boolean ativo(int id) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("UPDATE veiculos SET ativo=0 " + "WHERE idveiculos=?;");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar veiculos " + e.getMessage());
			return false;
		}
	}

}
