package bruno.senac.control;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.MarcasDao;
import senac.bruno.dao.ModeloDao;
import senac.bruno.model.Marcas;
import senac.bruno.model.Modelos;

public class MarcasControl {
	
	private JTextField tfNomeMarca;
	private JTextField tfNomeModelo;
	private JTextField tfPesquisarMarcas;
	private JTable table;
	private Marcas marcas = null;
	private MarcasDao marcasDao = null;
	private Modelos modelos = null;
	private ModeloDao modeloDao = null;
	List<Marcas> listMarcas;
	List<Modelos> listModelos;
	
	public static final int COLUNA_IDMARCAS = 0;
	public static final int COLUNA_NOME_MARCAS = 1;
	public static final int COLUNA_MODELO = 2;
	
	public MarcasControl(JTextField tfNomeMarca, JTextField tfPesquisarMarcas, JTable table) {
		super();
		this.tfNomeMarca = tfNomeMarca;
		this.tfPesquisarMarcas = tfPesquisarMarcas;
		this.table = table;
		marcasDao = new MarcasDao();
	}
	
	public void atualizarJTableAction() {
		listMarcas = marcasDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		//limpar tabela e listar novamente
		model.setNumRows(0);
		for(Marcas m : listMarcas) {
			model.addRow(new Object[] {
					m.getIdMarcas(),
					m.getNomeMarca()
			});
		}
	}
	
	public void pesquisarNomeMarca(){
		try {
			listMarcas = marcasDao.buscaPorNome(tfPesquisarMarcas.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for(Marcas m : listMarcas){
				model.addRow(new Object[]{
						m.getIdMarcas(),
						m.getNomeMarca(),
				});
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo nome da marca: "+e.getMessage());
		}
	}
	
	public void salvarAction() {
		if(marcas==null) {
			cadastrarAction();
		} else {
			editarAction();
		}
		marcas=null;
		atualizarJTableAction();
		limparTextfield();
	}
	
	public void cadastrarAction() {
		if (tfNomeMarca.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Digite o nome da marca", "Atenção! Campo vazio", JOptionPane.ERROR_MESSAGE);
			tfNomeMarca.requestFocus();
			return;
		}
		marcas = new Marcas();
		marcas.setNomeMarca(tfNomeMarca.getText());
		
		boolean res = marcasDao.cadastrar(marcas);
		if(res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Marca "+marcas.getNomeMarca()+" cadastrada com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		limparTextfield();
		atualizarJTableAction();
	}
	
	public void editarAction() {
		marcas.setNomeMarca(tfNomeMarca.getText());
		modelos.setNome(tfNomeModelo.getText());

		boolean res = marcasDao.cadastrar(marcas);
		if(res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		tfNomeMarca.setText(null);
		atualizarJTableAction();
	}
	
	public void excluirAction() {
		getItemSelecionadoTable();
		boolean res = marcasDao.deletar(marcas);
		if (res) {
			JOptionPane.showMessageDialog(null, "marca excluída com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "erro ao excluir");
		}
	}
	
	public void getItemSelecionadoTable() {
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		marcas = listMarcas.get(linha);
		
	}
	
	public void preencherForm() {
		getItemSelecionadoTable();
		tfNomeMarca.setText(marcas.getNomeMarca());
		tfNomeModelo.setText(modelos.getNome());
		atualizarJTableAction();
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
		tfNomeMarca.setText(null);
	}
}
