package bruno.senac.control;

import java.io.LineNumberInputStream;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.MarcasDao;
import senac.bruno.dao.ModeloDao;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;

public class ModeloControl {
	private JComboBox cbMarca;
	private JTextField tfNomeMarca;
	private JTextField tfNomeModelo;

	private JTextField tfPesquisarModelos;
	private JTable table;
	private MarcasDao marcaDao;
	private Modelos modelos = null;
	private ModeloDao modeloDao = null;
	List<Marcas> listMarcas;
	List<Modelos> listModelos;

	public ModeloControl(JTextField tfNomeModelo, JTable table, JComboBox cbMarca, JTextField tfPesquisarModelos) {
		super();
		this.tfNomeModelo = tfNomeModelo;
		this.table = table;
		this.cbMarca = cbMarca;
		
		this.tfPesquisarModelos = tfPesquisarModelos;
		marcaDao = new MarcasDao();
		modeloDao = new ModeloDao();
	}

	public void cadastrarAction() {
		if (tfNomeModelo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o modelo", "Atenção! Campo vazio", JOptionPane.ERROR_MESSAGE);
			tfNomeModelo.requestFocus();
			return;
		}

		modelos = new Modelos();
		modelos.setMarcas((Marcas) cbMarca.getSelectedItem());
		modelos.setNome(tfNomeModelo.getText());

		boolean res = modeloDao.cadastrar(modelos);
		if (res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		limparTextfield();
		atualizarJTableAction();
	}

	public void salvarAction() {
		if (modelos == null) {
			cadastrarAction();
		} else {
			editarAction();
		}
		modelos = null;
		atualizarJTableAction();
		limparTextfield();
	}

	public void editarAction() {

		modelos.setMarcas((Marcas) cbMarca.getSelectedItem());
		modelos.setNome(tfNomeModelo.getText());

		boolean res = modeloDao.alterar(modelos);
		if (res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		limparTextfield();
		atualizarJTableAction();
	}

	public void excluirAction() {
		getItemSelecionadoTable();
		boolean res = modeloDao.deletar(modelos);
		if (res) {
			JOptionPane.showMessageDialog(null, "modelo excluído com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "erro ao excluir");
		}
	}

	public void atualizarJTableAction() {
		listModelos = modeloDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpar tabela e listar novamente
		model.setNumRows(0);
		for (Modelos modelo : listModelos) {
			model.addRow(new Object[] { 
					modelo.getIdModelo(), 
					modelo.getNome(), 
					modelo.getMarcas().getNomeMarca() 
			});
		}
	}

	public void pesquisarNomeModelo() {
		try {
			listModelos = modeloDao.buscaPorNome(tfPesquisarModelos.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (Modelos modelo : listModelos) {
				model.addRow(new Object[] { modelo.getIdModelo(), modelo.getNome(), modelo.getMarcas().getNomeMarca(),
						modelo.getNome() });
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo nome do modelo " + e.getMessage());
		}
	}

	public void getItemSelecionadoTable() {
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		modelos = listModelos.get(linha);
	}

	public void confirmaExclusaoAction() {
		getItemSelecionadoTable();
		int res = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o " + modelos.getMarcas().getNomeMarca());
		if (res == 0) {
			excluirAction();
			atualizarJTableAction();
		}
	}

	public void limparTextfield() {
		cbMarca.setSelectedIndex(0);
		tfNomeModelo.setText("");
	}

	public void preencherForm() {
		getItemSelecionadoTable();
		cbMarca.setSelectedIndex(modelos.getMarcas().getIdMarcas());
		tfNomeModelo.setText(modelos.getNome());
	}

	public void popularComboBoxMarcaAction() {
		listMarcas = marcaDao.listar();
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listMarcas.toArray());
		cbMarca.setModel(modelCombo);
	}

}
