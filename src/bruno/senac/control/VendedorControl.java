package bruno.senac.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.ModeloDao;
import senac.bruno.dao.VendasDao;
import senac.bruno.dao.VendedorDao;
import senac.bruno.model.Vendas;
import senac.bruno.model.Vendedor;

public class VendedorControl {
	private JComboBox<Object> cbVendedor;
	private JTextField tfNomeVendedor;
	private JTextField tfCpf;
	private JTextField tfComissao;
	private JTextField tfSalario;
	private JTextField tfPesquisaVendedores;
	private JTable table;
	private List<Vendedor> listVendedor;
	private Vendedor vendedor;
	private VendedorDao vendedorDao = null;
	private Vendas vendas = null;
	private VendasDao vendasDao = null;
	private ModeloDao modeloDao;
	
	
	
	public VendedorControl(JComboBox<Object> cbVendedor, JTextField tfNomeVendedor, JTextField tfCpf, JTextField tfComissao, JTextField tfSalario,
			JTextField tfPesquisaVendedores, JTable table) {
		super();
		this.cbVendedor = cbVendedor;
		this.tfNomeVendedor = tfNomeVendedor;
		this.tfCpf = tfCpf;
		this.tfComissao = tfComissao;
		this.tfSalario = tfSalario;
		this.tfPesquisaVendedores = tfPesquisaVendedores;
		this.table = table;
		listVendedor = new ArrayList<>();
		modeloDao = new ModeloDao();
		vendedorDao = new VendedorDao();
	}
	public VendedorControl(JComboBox<Object> cbVendedor) {
		super();
		this.cbVendedor = cbVendedor;
		listVendedor = new ArrayList<>();
		modeloDao = new ModeloDao();
		vendedorDao = new VendedorDao();
	}
	
	public void atualizarJTableAction() {
		listVendedor = vendedorDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		//limpar tabela e listar novamente
		model.setNumRows(0);
		for(Vendedor v : listVendedor) {
			model.addRow(new Object[] {
					v.getIdVendedor(),
					v.getNome(),
					v.getCpf(),
					v.getComissao(),
					v.getSalario(),
			});
		}
	}
	
	public void pesquisarNomeVendedor(){
		try {
			listVendedor = vendedorDao.buscaPorNome(tfPesquisaVendedores.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for(Vendedor v : listVendedor){
				model.addRow(new Object[]{
						v.getIdVendedor(),
						v.getNome(),
						v.getCpf(),
						v.getComissao(),
						v.getSalario()
				});
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo nome do cliente: "+e.getMessage());
		}
	}
	
	public void salvarAction() {
		
		if(vendedor==null) {
			cadastrarAction();
		} else {
			editarAction();
		}
		vendedor=null;
		atualizarJTableAction();
	}
	
	public void cadastrarAction() {
		
		if (tfNomeVendedor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o nome desejado");
			tfNomeVendedor.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o cpf desejado");
			tfNomeVendedor.requestFocus();
			return;
		}
		if (tfComissao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o valor da comissão");
			tfNomeVendedor.requestFocus();
			return;
		}
		if (tfSalario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o salário desejado");
			tfNomeVendedor.requestFocus();
			return;
		}
		vendedor = new Vendedor();
		vendedor.setNome(tfNomeVendedor.getText());
		vendedor.setCpf(tfCpf.getText());
		vendedor.setComissao(Double.parseDouble(tfComissao.getText()));
		vendedor.setSalario(Double.parseDouble(tfSalario.getText()));
		
		
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar este vendedor", "Cadastro de vendedores", JOptionPane.INFORMATION_MESSAGE);
		if(resposta == JOptionPane.YES_OPTION) {
			vendedorDao.cadastrar(vendedor);
			JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso");
		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "O vendedor não foi cadastrado");
		}
		atualizarJTableAction();
	}
	
	public void editarAction() {
		vendedor.setNome(tfNomeVendedor.getText());
		vendedor.setCpf(tfCpf.getText());
		vendedor.setComissao(Double.parseDouble(tfComissao.getText()));
		vendedor.setSalario(Double.parseDouble(tfSalario.getText()));

		boolean res = vendedorDao.cadastrar(vendedor);
		if(res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		tfNomeVendedor.setText(null);
		atualizarJTableAction();
	}
	
	public void excluirAction() {
		
		if (tfNomeVendedor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um vendedor na tabela", "Exclusão de Vendedores", JOptionPane.INFORMATION_MESSAGE);
			tfNomeVendedor.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um vendedor na tabela", "Exclusão de Vendedores", JOptionPane.INFORMATION_MESSAGE);
			tfCpf.requestFocus();
			return;
		}
		if (tfComissao.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um vendedor na tabela", "Exclusão de Vendedores", JOptionPane.INFORMATION_MESSAGE);
			tfComissao.requestFocus();
			return;
		}
		if (tfSalario.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um vendedor na tabela", "Exclusão de Vendedores", JOptionPane.INFORMATION_MESSAGE);
			tfSalario.requestFocus();
			return;
		}
		
		getItemSelecionadoTable();
		
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este vendendor?", "Exclusão de vendedores", JOptionPane.YES_NO_OPTION);
		if(resposta ==JOptionPane.YES_OPTION) {
			vendedorDao.deletar(vendedor);
			JOptionPane.showMessageDialog(null, "vendedor excluído com sucesso");
		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "vendedor não foi excluído");
		}
		limparTextfield();
		vendedor = null;
	}
	
	public void getItemSelecionadoTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		vendedor = listVendedor.get(linha);
	}
	
	public void preencherForm() {
		getItemSelecionadoTable();
		tfNomeVendedor.setText(vendedor.getNome());
		tfCpf.setText(vendedor.getCpf());
		tfComissao.setText(Double.toString(vendedor.getComissao()));
		tfSalario.setText(Double.toString(vendedor.getSalario()));
		//atualizarJTableAction();
	}
	
	public void confirmaExclusaoAction() {
		getItemSelecionadoTable();
		int res = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o " + vendedor.getNome());
		if (res == 0) {
			excluirAction();
			atualizarJTableAction();
		}
	}
	
	public void limparTextfield() {
		tfNomeVendedor.setText(null);
		tfCpf.setText(null);
		tfComissao.setText(null);
		tfSalario.setText(null);
	}
	
	public void selecionaLinhaTable() {
		int indiceLinha = table.getSelectedRow();
		tfNomeVendedor.setText(table.getValueAt(indiceLinha, 1).toString());
		tfCpf.setText(table.getValueAt(indiceLinha, 2).toString());
		tfComissao.setText(table.getValueAt(indiceLinha, 3).toString());
		tfSalario.setText(table.getValueAt(indiceLinha, 4).toString());
	}

	public void popularComboboxVendedorAction() {
			listVendedor = vendedorDao.listar();
			ComboBoxModel<Object> modelCombo;
			modelCombo = new DefaultComboBoxModel<Object>(listVendedor.toArray());
			cbVendedor.setModel(modelCombo);
	}
}
