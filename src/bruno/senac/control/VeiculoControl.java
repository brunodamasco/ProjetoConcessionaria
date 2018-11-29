package bruno.senac.control;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.MarcasDao;
import senac.bruno.dao.ModeloDao;
import senac.bruno.dao.VeiculosDao;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;
import senac.bruno.model.Veiculos;

public class VeiculoControl {

	private JComboBox<Object> cbMarca;
	private JComboBox<Object> cbModelo;
	private JTextField tfAno;
	private JTextField tfQuilometragem;
	private JTextField tfValor;
	private JTextField tfChassis;
	private JTextField tfValorFipe;
	private JTextField tfCor;
	private JTextField tfNomeVeiculo;
	private JTable table;
	private JTextField tfPesquisa;
	private JLabel lbChassis;

	private Veiculos veiculos = null;
	private VeiculosDao veiculosDao = null;
	private Marcas marcas = null;
	private MarcasDao marcasDao = null;
	private Modelos modelos = null;
	private ModeloDao modeloDao = null;
	private List<Veiculos> listVeiculos;
	private List<Marcas> listMarcas;
	private List<Modelos> listModelos;

	public VeiculoControl() {
		super();
	}

	public VeiculoControl(JComboBox<Object> cbMarca, JComboBox<Object> cbModelo, JTextField tfAno,
			JTextField tfQuilometragem, JTextField tfValor, JTextField tfChassis, JTextField tfValorFipe,
			JTextField tfCor, JTextField tfNomeVeiculo, JTable table, JTextField tfPesquisa) {
		super();
		this.cbMarca = cbMarca;
		this.cbModelo = cbModelo;
		this.tfAno = tfAno;
		this.tfQuilometragem = tfQuilometragem;
		this.tfValor = tfValor;
		this.tfChassis = tfChassis;
		this.tfValorFipe = tfValorFipe;
		this.tfCor = tfCor;
		this.tfNomeVeiculo = tfNomeVeiculo;
		this.table = table;
		this.tfPesquisa = tfPesquisa;
		veiculosDao = new VeiculosDao();
		marcasDao = new MarcasDao();
	}

	public VeiculoControl(JTextField tfCor, JTextField tfNomeVeiculo, JTable table, JLabel lbChassis) {
		super();
		this.tfCor = tfCor;
		this.tfNomeVeiculo = tfNomeVeiculo;
		this.table = table;
		this.lbChassis = lbChassis;
		veiculosDao = new VeiculosDao();
		marcasDao = new MarcasDao();
	}

	public void atualizarJTableAction() {
		listVeiculos = veiculosDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Veiculos v : listVeiculos) {
			model.addRow(new Object[] { 
					v.getIdVeiculos(), 
					v.getModelos().getMarcas().getNomeMarca(),
					v.getModelos().getNome(), 
					v.getAno(), 
					v.getQuilometragem(), 
					v.getValor(), 
					v.getValorFipe(),
					v.getCor(), 
					v.getChassis(), 
			});
		}
	}

	public void atualizarTableVenda() {
		listVeiculos = veiculosDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Veiculos v : listVeiculos) {
			model.addRow(new Object[] { 
					v.getModelos().getNome(), 
					v.getAno(), 
					v.getQuilometragem(), 
					v.getValor(),
					v.getValorFipe(), 
					v.getChassis()
			});
		}
	}

	public void pesquisarIdVeiculo() {

		try {
			listVeiculos = veiculosDao.buscarporNome(tfPesquisa.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (Veiculos v : listVeiculos) {
				model.addRow(new Object[] {
						v.getIdVeiculos(),
						v.getModelos().getMarcas().getNomeMarca(),
						v.getModelos().getNome(),
						v.getAno(), 
						v.getQuilometragem(),
						v.getValor(), 
						v.getValorFipe(),
						v.getCor(), 
						v.getChassis() });
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo id do veículo: " + e.getMessage());
		}
	}

	public void salvarAction() {
		if (veiculos == null) {
			cadastrarAction();
		} else {
			editarAction();
		}
		veiculos = null;
		atualizarJTableAction();
	}

	public void cadastrarAction() {
		if (tfAno.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com o ano do veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfAno.requestFocus();
			return;
		}
		if (tfQuilometragem.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com a quilometragem do veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfQuilometragem.requestFocus();
			return;
		}
		if (tfValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com o valor de veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfValor.requestFocus();
			return;
		}
		if (tfValorFipe.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com o valor da tabela fipe do veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfValorFipe.requestFocus();
			return;
		}
		if (tfCor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com a cor do veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfCor.requestFocus();
			return;
		}
		if (tfChassis.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo com o chassis do veículo", "Cadastro Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfChassis.requestFocus();
			return;
		}
		
		veiculos = new Veiculos();
		marcas = new Marcas();
		modelos = new Modelos();
		modelos.setMarcas((Marcas) cbMarca.getSelectedItem());
		// marcas.setNomeMarca( (String) cbMarca.getSelectedItem());
		veiculos.setModelos((Modelos) cbModelo.getSelectedItem());

		veiculos.setAno(Integer.parseInt(tfAno.getText()));
		veiculos.setQuilometragem(Integer.parseInt(tfQuilometragem.getText()));
		veiculos.setValor(Double.parseDouble(tfValor.getText()));
		veiculos.setValorFipe(Double.parseDouble(tfValorFipe.getText()));
		veiculos.setCor(tfCor.getText());
		veiculos.setChassis(tfChassis.getText());
		
		
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar o veículo "+veiculos.toString()+"?", "Cadastro de veículos", JOptionPane.INFORMATION_MESSAGE);
		if(resposta == JOptionPane.YES_OPTION) {
			veiculosDao.cadastrar(veiculos);
			JOptionPane.showMessageDialog(null, "Veículo cadastrado com sucesso");
		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "O veículo não foi cadastrado");
		}

		atualizarJTableAction();
	
	}

	public void editarAction() {
		// marcas.setNomeMarca( (String) cbMarca.getSelectedItem());
		// modelos.setNome( (String) cbModelo.getSelectedItem());
		veiculos.setAno(Integer.parseInt(tfAno.getText()));
		veiculos.setQuilometragem(Integer.parseInt(tfQuilometragem.getText()));
		veiculos.setValor(Double.parseDouble(tfValor.getText()));
		veiculos.setValorFipe(Double.parseDouble(tfValorFipe.getText()));
		veiculos.setCor(tfCor.getText());
		veiculos.setChassis(tfChassis.getText());

		boolean res = veiculosDao.alterar(veiculos);
		if (res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		limparTextfield();
		atualizarJTableAction();
		/*
		 * boolean res = veiculosDao.cadastrar(veiculos); if(res) {
		 * atualizarJTableAction(); JOptionPane.showMessageDialog(null, "Sucesso"); }
		 * else { JOptionPane.showMessageDialog(null, "Erro"); } limparTextfield();
		 * atualizarJTableAction();
		 */

	}

	public void excluirAction() {
		
		if (tfAno.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfAno.requestFocus();
			return;
		}
		if (tfQuilometragem.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfQuilometragem.requestFocus();
			return;
		}
		if (tfValor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfValor.requestFocus();
			return;
		}
		if (tfValorFipe.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfValorFipe.requestFocus();
			return;
		}
		if (tfCor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfCor.requestFocus();
			return;
		}
		if (tfChassis.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um veículo a ser excluído na tabela", "Exclusão Veículos", JOptionPane.INFORMATION_MESSAGE);
			tfChassis.requestFocus();
			return;
		}
		
		
		getItemSelecionadoTable();
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este veículo?", "Exclusão de veículos",
				JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			veiculosDao.deletar(veiculos);
			JOptionPane.showMessageDialog(null, "veículo excluído com sucesso");

		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "o veículo não foi excluído");
		}
		limparTextfield();
		veiculos = null;
	}

	public void getItemSelecionadoTable() {
		int linha = table.getSelectedRow();
		veiculos = listVeiculos.get(linha);
	}

	public void preencherForm() {
		getItemSelecionadoTable();
		// Popular combobox com o marca do veículo selecionado
		int indiceSelecionar = 0;
		for (Marcas m : listMarcas) {

			if (m.getIdMarcas() == veiculos.getModelos().getMarcas().getIdMarcas()) {
				cbMarca.setSelectedIndex(indiceSelecionar);
				popularComboBoxModeloAction();
				break;
			}
			indiceSelecionar++;
		}

		// Popular combobox com o modelo do veículo selecionado
		int indiceSelecionar1 = 0;
		for (Modelos modelos : listModelos) {
			if (modelos.getIdModelo() == veiculos.getModelos().getIdModelo()) {
				cbModelo.setSelectedIndex(indiceSelecionar1);
				break;
			}
			indiceSelecionar1++;
		}

		// cbModelo.setSelectedIndex(Integer.parseInt(modelos.getNome()));
		tfAno.setText(Integer.toString(veiculos.getAno()));
		tfQuilometragem.setText(Integer.toString(veiculos.getQuilometragem()));
		tfValor.setText(Double.toString(veiculos.getValor()));
		tfValorFipe.setText(Double.toString(veiculos.getValorFipe()));
		tfCor.setText(veiculos.getCor());
		tfChassis.setText(veiculos.getChassis());

	}

	public void preencherFormVenda() {
		getItemSelecionadoTable();

		tfCor.setText(String.valueOf(veiculos.getIdVeiculos()));
		tfNomeVeiculo.setText(String.valueOf(veiculos.getModelos().getNome()));
		lbChassis.setText(String.valueOf(veiculos.getChassis()));

	}

	public void confirmaExclusaoAction() {
		getItemSelecionadoTable();
		int res = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o " + marcas.getNomeMarca());
		if (res == 0) {
			excluirAction();
			atualizarJTableAction();
		}
	}

	public void limparTextfield() {
		tfAno.setText(null);
		tfQuilometragem.setText(null);
		tfValor.setText(null);
		tfValorFipe.setText(null);
		tfCor.setText(null);
		tfChassis.setText(null);
	}

	public void popularComboBoxMarcaAction() {
		listMarcas = marcasDao.listar();
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listMarcas.toArray());
		cbMarca.setModel(modelCombo);
	}

	public void popularComboBoxModeloAction() {
		Marcas marcaEscolhida = (Marcas) cbMarca.getSelectedItem();
		listModelos = marcasDao.buscaModelosPorMarca(marcaEscolhida.getIdMarcas());
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listModelos.toArray());
		cbModelo.setModel(modelCombo);
	}

	public void ativoVenda(JTextField tfcarro) {
		int id = Integer.parseInt(tfcarro.getText());
		boolean res = veiculosDao.ativo(id);
		/*if (res) {
			JOptionPane.showMessageDialog(null, "ativo = 0 ");
		} else {
			JOptionPane.showMessageDialog(null, "ativo = 1");
		}*/
	}
}
