package senac.bruno.model;

public class Veiculos {
	private int idVeiculos;
	private int ano;
	private int quilometragem;
	private String cor;
	private double valor;
	private double valorFipe;
	private String chassis;
	private Modelos modelos;
	
	public Veiculos() {
		super();
		modelos = new Modelos();
	}

	public Veiculos(int idVeiculos, int ano, int quilometragem, String cor, double valor, double valorFipe,
			String chassis, Modelos modelos) {
		super();
		this.idVeiculos = idVeiculos;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.cor = cor;
		this.valor = valor;
		this.valorFipe = valorFipe;
		this.chassis = chassis;
		this.modelos = modelos;
	}

	public int getIdVeiculos() {
		return idVeiculos;
	}

	public void setIdVeiculos(int idVeiculos) {
		this.idVeiculos = idVeiculos;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValorFipe() {
		return valorFipe;
	}

	public void setValorFipe(double valorFipe) {
		this.valorFipe = valorFipe;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public Modelos getModelos() {
		return modelos;
	}

	public void setModelos(Modelos modelos) {
		this.modelos = modelos;
	}

	@Override
	public String toString() {
		return modelos + "R$ " + valor;
	}
	
	
}
