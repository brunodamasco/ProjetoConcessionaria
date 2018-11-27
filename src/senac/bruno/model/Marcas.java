package senac.bruno.model;

public class Marcas {
	private int idMarcas;
	private String nomeMarca;
	private int ativo;
	
	public Marcas() {
		super();
	}

	public Marcas(int idMarcas, String nomeMarca, int ativo) {
		super();
		this.idMarcas = idMarcas;
		this.nomeMarca = nomeMarca;
		this.ativo = ativo;
	}

	public int getIdMarcas() {
		return idMarcas;
	}

	public void setIdMarcas(int idMarcas) {
		this.idMarcas = idMarcas;
	}

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	
	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return nomeMarca;
	}
	
}
