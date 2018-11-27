package senac.bruno.model;

public class Vendedor {
	private int idVendedor;
	private String nome;
	private String cpf;
	private double comissao;
	private double salario;
	
	public Vendedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendedor(int idVendedor, String nome, String cpf, double comissao, double salario) {
		super();
		this.idVendedor = idVendedor;
		this.nome = nome;
		this.cpf = cpf;
		this.comissao = comissao;
		this.salario = salario;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idvendedor) {
		this.idVendedor = idvendedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getComissao() {
		return comissao;
	}
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return  nome ;
	}
	
}
