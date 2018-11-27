package senac.bruno.model;

public class Modelos {
	private int idModelo;
	private String nome;
	private Marcas marcas;
	private int ativo;
	
	public Modelos() {
		this.marcas = new Marcas();
	}

	public Modelos(int idModelo, String nome, Marcas marcas, int ativo) {
		super();
		this.idModelo = idModelo;
		this.nome = nome;
		this.marcas = marcas;
		this.ativo = ativo;
	}

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Marcas getMarcas() {
		return marcas;
	}

	public void setMarcas(Marcas marcas) {
		this.marcas = marcas;
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	
}
