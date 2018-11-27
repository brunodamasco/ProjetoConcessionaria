package senac.bruno.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senac.bruno.interfaces.DaoI;
import senac.bruno.model.Clientes;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;
import senac.bruno.model.Veiculos;
import senac.bruno.model.Vendas;
import senac.bruno.model.Vendedor;

public class VendasDao extends Dao implements DaoI<Vendas> {

	@Override
	public boolean cadastrar(Vendas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(
					"INSERT INTO vendas (dataVenda, idclientes, idvendedor,idveiculos) " + "VALUES(now(),?,?,?);");
			// stmt.setDate(1, "now()");
			int fkCliente = obj.getClientes().getIdClientes();
			stmt.setInt(1, fkCliente);
			int fkVendedor = obj.getVendedor().getIdVendedor();
			stmt.setInt(2, fkVendedor);
			stmt.setInt(3, obj.getVeiculo().getIdVeiculos());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao cadastrar vendas " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean alterar(Vendas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao
					.prepareStatement("UPDATE vendas SET dataVenda=?, idCliente=?, idVendedor=? WHERE idVendas=?");
			stmt.setDate(1, obj.getDataVenda());
			int fkCliente = obj.getClientes().getIdClientes();
			stmt.setInt(2, fkCliente);
			int fkVendedor = obj.getVendedor().getIdVendedor();
			stmt.setInt(3, fkVendedor);
			stmt.setInt(4, obj.getIdVendas());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao alterar vendas " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deletar(Vendas obj) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("DELETE FROM vendas WHERE idVendas=?");
			stmt.setInt(1, obj.getIdVendas());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erro ao deletar vendas " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Vendas> listar() {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT vendas.idVendas, vendas.dataVenda, clientes.nome, vendedor.nome,veiculos.chassis "
					+ "FROM vendas INNER JOIN clientes ON vendas.idclientes = clientes.idClientes " 
					+"INNER JOIN vendedor ON vendas.idvendedor = vendedor.idvendedor  "
					+"INNER JOIN  veiculos  on vendas.idveiculos = veiculos.idveiculos "
					+"order by vendas.idvendas;");
			ResultSet result = stmt.executeQuery();
			List<Vendas> listVendas = new ArrayList<Vendas>();
			while (result.next()) {
				Vendas v = new Vendas();
				v.setIdVendas(result.getInt("vendas.idVendas"));
				v.setDataVenda(result.getDate("vendas.dataVenda"));
				Clientes c = new Clientes();
				c.setNome(result.getString("clientes.nome"));
				Vendedor vendedor = new Vendedor();
				vendedor.setNome(result.getString("vendedor.nome"));
				Veiculos veiculo = new Veiculos();
				veiculo.setChassis(result.getString("veiculos.chassis"));
				v.setVeiculo(veiculo);
				v.setClientes(c);
				v.setVendedor(vendedor);
				listVendas.add(v);
			}
			return listVendas;
		} catch (SQLException e) {
			System.out.println("erro ao listar vendas " + e.getMessage());
			return null;
		}
	}
	
	public List<Vendas> listarRelatorio() {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT " + 
					"vendas.idVendas, vendas.dataVenda, vendedor.nome, clientes.nome, " + 
					"marcas.nome, modelo.nome, veiculos.cor, veiculos.chassis, veiculos.valor " + 
					"FROM vendas " + 
					"INNER JOIN vendedor ON vendas.idvendedor = vendedor.idvendedor " + 
					"INNER JOIN clientes ON vendas.idclientes = clientes.idClientes " + 
					"INNER JOIN veiculos  ON vendas.idveiculos = veiculos.idveiculos " + 
					"INNER JOIN modelo on veiculos.idmodelo = modelo.idmodelo " +
					"INNER JOIN marcas on modelo.idmarcas = marcas.idmarcas " + 
					"ORDER BY vendas.idvendas");
			ResultSet result = stmt.executeQuery();
			List<Vendas> listVendas = new ArrayList<Vendas>();
			while (result.next()) {
				Vendas venda = new Vendas();
				Clientes cliente = new Clientes();
				Vendedor vendedor = new Vendedor();
				Veiculos veiculo = new Veiculos();
				Marcas marca = new Marcas();
				Modelos modelo = new Modelos();
				
				venda.setIdVendas(result.getInt("vendas.idVendas"));
				venda.setDataVenda(result.getDate("vendas.dataVenda"));
				vendedor.setNome(result.getString("vendedor.nome"));
				cliente.setNome(result.getString("clientes.nome"));
				marca.setNomeMarca(result.getString("marcas.nome"));
				modelo.setNome(result.getString("modelo.nome"));
				veiculo.setCor(result.getString("veiculos.cor"));
				veiculo.setChassis(result.getString("veiculos.chassis"));
				veiculo.setValor(result.getDouble("veiculos.valor"));
				
				venda.setVeiculo(veiculo);
				venda.setClientes(cliente);
				venda.setVendedor(vendedor);
				venda.getVeiculo().getModelos().setNome(String.valueOf(modelo));
				venda.getVeiculo().getModelos().getMarcas().setNomeMarca(String.valueOf(marca));
				listVendas.add(venda);
			}
			return listVendas;
		} catch (SQLException e) {
			System.out.println("erro ao listar lista relatorio de vendas " + e.getMessage());
			return null;
		}
	}
	
	public List<Vendas> pesquisarNomeCliente(String nome) {
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement("SELECT " + 
					"vendas.idVendas, vendas.dataVenda, vendedor.nome, clientes.nome, " + 
					"marcas.nome, modelo.nome, veiculos.cor, veiculos.chassis, veiculos.valor " + 
					"FROM vendas " + 
					"INNER JOIN vendedor ON vendas.idvendedor = vendedor.idvendedor " + 
					"INNER JOIN clientes ON vendas.idclientes = clientes.idClientes " + 
					"INNER JOIN veiculos  ON vendas.idveiculos = veiculos.idveiculos " + 
					"INNER JOIN modelo on veiculos.idmodelo = modelo.idmodelo " +
					"INNER JOIN marcas on modelo.idmarcas = marcas.idmarcas " + 
					"WHERE clientes.nome or vendedor.nome LIKE ? ORDER BY vendas.idvendas asc");
			stmt.setString(1, nome+"%");
			ResultSet result = stmt.executeQuery();
			List<Vendas> listVendas = new ArrayList<Vendas>();
			while (result.next()) {
				Vendas venda = new Vendas();
				Clientes cliente = new Clientes();
				Vendedor vendedor = new Vendedor();
				Veiculos veiculo = new Veiculos();
				Marcas marca = new Marcas();
				Modelos modelo = new Modelos();
				
				venda.setIdVendas(result.getInt("vendas.idVendas"));
				venda.setDataVenda(result.getDate("vendas.dataVenda"));
				vendedor.setNome(result.getString("vendedor.nome"));
				cliente.setNome(result.getString("clientes.nome"));
				marca.setNomeMarca(result.getString("marcas.nome"));
				modelo.setNome(result.getString("modelo.nome"));
				veiculo.setCor(result.getString("veiculos.cor"));
				veiculo.setChassis(result.getString("veiculos.chassis"));
				veiculo.setValor(result.getDouble("veiculos.valor"));
				
				venda.setVeiculo(veiculo);
				venda.setClientes(cliente);
				venda.setVendedor(vendedor);
				venda.getVeiculo().getModelos().setNome(String.valueOf(modelo));
				venda.getVeiculo().getModelos().getMarcas().setNomeMarca(String.valueOf(marca));
				listVendas.add(venda);
			}
			return listVendas;
		} catch (SQLException e) {
			System.out.println("erro ao listar lista relatorio de vendas " + e.getMessage());
			return null;
		}
	}

}
