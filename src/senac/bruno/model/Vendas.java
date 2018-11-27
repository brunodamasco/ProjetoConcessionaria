package senac.bruno.model;

import java.sql.Date;

public class Vendas {
	private int idVendas;
	private Date dataVenda;
	private Clientes clientes;
	private Vendedor vendedor;
	private Veiculos veiculo;

	public Veiculos getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculos veiculo) {
		this.veiculo = veiculo;
	}

	public Vendas() {
		super();
		clientes = new Clientes();
		vendedor = new Vendedor();
		veiculo = new Veiculos();
	}

	public Vendas(int idVendas, Date dataVenda, Clientes clientes, Vendedor vendedor) {
		super();
		this.idVendas = idVendas;
		this.dataVenda = dataVenda;
		this.clientes = clientes;
		this.vendedor = vendedor;
	}

	public int getIdVendas() {
		return idVendas;
	}

	public void setIdVendas(int idVendas) {
		this.idVendas = idVendas;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}
