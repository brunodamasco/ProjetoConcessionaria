package bruno.senac.control;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.ClientesDao;
import senac.bruno.model.Clientes;

public class ClienteControl {
	// private JComboBox<Object> cbClientes;
	private JTextField tfNomeCliente;
	private JTextField tfNomeClienteVendas;
	private JTextField tfCpf;
	private JTextField tfDataNascimento;
	private JTextField tfTelefone;
	private JTextField tfPesquisarNomeCliente;
	private JTextField tfClienteSelecionado;
	private JTextField tfCarroSelecionado;
	
	private JLabel lbCpf;
	
	private JTable table;
	

	private Clientes clientes;
	private ClientesDao clientesDao = null;
	private List<Clientes> listClientes;

	public ClienteControl(JTextField tfNomeCliente, JTextField tfCpf, JTextField tfDataNascimento,
			JTextField tfTelefone, JTextField tfPesquisarNomeCliente, JTable table) {
		super();
		this.tfNomeCliente = tfNomeCliente;
		this.tfCpf = tfCpf;
		this.tfDataNascimento = tfDataNascimento;
		this.tfTelefone = tfTelefone;
		this.tfPesquisarNomeCliente = tfPesquisarNomeCliente;
		this.table = table;
		clientesDao = new ClientesDao();
	}

	public ClienteControl(JTextField tfNomeCliente, JTextField tfNomeClienteVendas, JTable table, JLabel lbCpf) {
		super();
		this.tfNomeCliente = tfNomeCliente;
		this.tfNomeClienteVendas = tfNomeClienteVendas;
		this.table = table;
		this.lbCpf = lbCpf;
		
		clientesDao = new ClientesDao();
	}

	public void atualizarJTableAction() {
		listClientes = clientesDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// limpar tabela e listar novamente
		model.setNumRows(0);
		for (Clientes c : listClientes) {
			model.addRow(new Object[] { 
					c.getIdClientes(), 
					c.getNome(), 
					c.getCpf(),
					dateFormat.format(c.getDataNascimento()), 
					c.getTelefone() 
			});
		}
	}
	

	public void pesquisarNomeCliente() {
		try {
			listClientes = clientesDao.buscaPorNome(tfPesquisarNomeCliente.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			model.setNumRows(0);
			for (Clientes c : listClientes) {
				model.addRow(new Object[] { 
						c.getIdClientes(), 
						c.getNome(), 
						c.getCpf(),
						dateFormat.format(c.getDataNascimento()), 
						c.getTelefone() 
				});
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo nome do cliente: " + e.getMessage());
		}
	}
	
	public void pesquisarNomeClienteCadastroVendas() {
		try {
			listClientes = clientesDao.buscaPorNome(tfPesquisarNomeCliente.getText());
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (Clientes c : listClientes) {
				model.addRow(new Object[] { 
						c.getIdClientes(), 
						c.getNome(), 
						c.getCpf(),
				});
			}
		} catch (Exception e) {
			System.out.println("Erro ao pesquisar pelo nome do cliente no cadastro de vendas: " + e.getMessage());
		}
	}

	public void salvarAction() {
		if (clientes == null) {
			cadastrarAction();
		} else {
			editarAction();
		}
		clientes = null;
		atualizarJTableAction();
	}

	public void cadastrarAction() {
		if (tfNomeCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o nome de cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o cpf do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfDataNascimento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva a data de nascimento do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o telefone do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		
		//validarCampoCadastro();
		
		clientes = new Clientes();
		clientes.setNome(tfNomeCliente.getText());
		clientes.setCpf(tfCpf.getText());

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date data;
		try {
			data = dateFormat.parse(tfDataNascimento.getText());
			clientes.setDataNascimento(new Date(data.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Data inválida", "Erro formulário", JOptionPane.ERROR_MESSAGE);
			return;
		}
		clientes.setTelefone(tfTelefone.getText());
		
		
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar este cliente", "Cadastro de clientes", JOptionPane.INFORMATION_MESSAGE);
		if(resposta == JOptionPane.YES_OPTION) {
			clientesDao.cadastrar(clientes);
			JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "O cliente não foi cadastrado");
		}
		
		atualizarJTableAction();
	}

	public void editarAction() {
		clientes.setNome(tfNomeCliente.getText());
		clientes.setCpf(tfCpf.getText());

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dataUtil;
			dataUtil = dateFormat.parse(tfDataNascimento.getText());
			Date dataSQL = new Date(dataUtil.getTime());
			clientes.setDataNascimento(dataSQL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// clientes.setDataNascimento(Date.valueOf(tfDataNascimento.getText()));
		clientes.setTelefone(tfTelefone.getText());

		boolean res = clientesDao.alterar(clientes);
		if (res) {
			atualizarJTableAction();
			JOptionPane.showMessageDialog(null, "Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Erro");
		}
		tfNomeCliente.setText(null);
		atualizarJTableAction();
	}

	public void excluirAction() {
		if (tfNomeCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfDataNascimento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		
		//validarCampoExcluir();
		getItemSelecionadoTable();

		int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Exclusão de clientes", JOptionPane.YES_NO_OPTION);
		if (resposta == JOptionPane.YES_OPTION) {
			clientesDao.deletar(clientes);
			JOptionPane.showMessageDialog(null, "cliente excluído com sucesso");

		} else if (resposta == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "o cliente não foi excluído");
		}
		limparTextfield();
		//clientes = null;
	}
	
	public void getItemSelecionadoTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		clientes = listClientes.get(linha);
	}

	public void preencherForm() {
		getItemSelecionadoTable();
		tfNomeCliente.setText(clientes.getNome());
		tfCpf.setText(clientes.getCpf());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		tfDataNascimento.setText(dateFormat.format(clientes.getDataNascimento()));
		tfTelefone.setText(clientes.getTelefone());
	}

	public void preencherFormVenda() {
		getItemSelecionadoTable();
		tfNomeCliente.setText(String.valueOf(clientes.getIdClientes()));
		tfNomeClienteVendas.setText(String.valueOf(clientes.getNome()));
		lbCpf.setText(String.valueOf(clientes.getCpf()));
	}

	public void confirmaExclusaoAction() {
		getItemSelecionadoTable();
		int res = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir o " + clientes.getNome());
		if (res == 0) {
			excluirAction();
			atualizarJTableAction();
		}
	}

	public void limparTextfield() {
		tfNomeCliente.setText("");
		tfCpf.setText("");
		tfDataNascimento.setText("");
		tfTelefone.setText("");
	}

	public Clientes armazenaCliente() {
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();
		return clientes = listClientes.get(linha);
	}
	
	public void validarCampoCadastro() {
		if (tfNomeCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o nome de cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o cpf do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfDataNascimento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva a data de nascimento do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Escreva o telefone do cliente", "Cadastro Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		return;
	}
	public void validarCampoExcluir() {
		if (tfNomeCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfCpf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfDataNascimento.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		if (tfTelefone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "Exclusão Clientes", JOptionPane.INFORMATION_MESSAGE);
			tfNomeCliente.requestFocus();
			return;
		}
		return;
	}		
}
