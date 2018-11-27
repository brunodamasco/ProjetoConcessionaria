package bruno.senac.control;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.VendasDao;
import senac.bruno.model.Clientes;
import senac.bruno.model.Veiculos;
import senac.bruno.model.Vendas;
import senac.bruno.model.Vendedor;

public class VendasControl {

	private JTextField tfCarro;
	private JTextField tfCliente;
	private JTextField tfCpf;
	
	private JLabel lbNomeCliente;

	private Clientes cliente = null;
	private Veiculos veiculo = null;
	private VendasDao vendaDao = null;
	private Vendas vendas = null;
	
	private JTable table;
	
	private JComboBox<Object> cbvendedor;

	public VendasControl(JTextField tfCliente, JTextField tfCarro, JComboBox<Object> cbvendedor, JTable table) {
		super();
		this.tfCarro = tfCarro;
		this.tfCliente = tfCliente;
		this.cbvendedor = cbvendedor;
		this.table = table;
		//this.lbNomeCliente = lbNomeCliente;
		vendaDao = new VendasDao();
		vendas = new Vendas();
	}

	public void cadastrarVenda() {
		vendas = new Vendas();
		
		//vendas.getClientes().setIdClientes(Integer.parseInt(lbNomeCliente.getText()));
		vendas.getClientes().setIdClientes(Integer.parseInt(tfCliente.getText()));
		System.out.println(vendas.getClientes().getIdClientes());
		vendas.getVeiculo().setIdVeiculos(Integer.parseInt(tfCarro.getText()));
		System.out.println(vendas.getVeiculo().getIdVeiculos());
		vendas.setVendedor((Vendedor) cbvendedor.getSelectedItem());
		System.out.println(vendas.getVendedor());

		boolean res = vendaDao.cadastrar(vendas);
		if (res) {
			JOptionPane.showMessageDialog(null, "Venda efetuada");
		} else {
			JOptionPane.showMessageDialog(null, " n deu");
		}
		atualizarJTableAction();
	}

	public void atualizarJTableAction() {
		List<Vendas> listVendas = vendaDao.listarRelatorio();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpar tabela e listar novamente
		model.setNumRows(0);
		for (Vendas v : listVendas) {
			model.addRow(new Object[] { 
					v.getIdVendas(), 
					v.getDataVenda(), 
					v.getClientes().getNome(),
					v.getVendedor().getNome(), 
					v.getVeiculo().getChassis() 
			});
		}
	}
	
	public void atualizarHistoricoVendasJTableAction() {
		List<Vendas> listVendas = vendaDao.listarRelatorio();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpar tabela e listar novamente
		model.setNumRows(0);
		for (Vendas v : listVendas) {
			model.addRow(new Object[] { 
					v.getIdVendas(), 
					v.getDataVenda(), 
					v.getVendedor().getNome(), 
					v.getClientes().getNome(),
					v.getVeiculo().getModelos().getMarcas(),
					v.getVeiculo().getModelos(),
					v.getVeiculo().getCor(),
					v.getVeiculo().getChassis(),
					v.getVeiculo().getValor()
			});
		}
	}
}
