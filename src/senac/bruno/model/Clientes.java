package senac.bruno.model;

import java.sql.Date;

public class Clientes {
	

	private int idClientes;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private String telefone;
	private int ativo;
	
	public Clientes() {
		super();
	}

	public Clientes(int idClientes, String nome, String cpf, Date dataNascimento, String telefone, int ativo) {
		super();
		this.idClientes = idClientes;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.ativo = ativo;
	}

	public int getIdClientes() {
		return idClientes;
	}

	public void setIdClientes(int idClientes) {
		this.idClientes = idClientes;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	@Override
	public String toString() {
		return nome ;
	}
	
}
