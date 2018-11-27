package senac.bruno.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import bruno.senac.control.ClienteControl;
import bruno.senac.control.VeiculoControl;
import bruno.senac.control.VendasControl;
import bruno.senac.control.VendedorControl;
import senac.bruno.model.Clientes;
import senac.bruno.model.Veiculos;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import java.awt.Color;

public class CadastroVendaCliente extends JInternalFrame {
	private ClienteControl clientecontrol;
	private VeiculoControl veiculocontrol;
	private VendedorControl vendedorcontrol;
	private VendasControl vendacontrol;
	private JTextField tfCliente;
	private JTable tableCliente;
	private JTable tableCarro;
	private JTextField tfCarro;
	private JComboBox<Object> cbVendedor;
	private JTable tbVendas;
	private JTextField tfNomeCliente;
	private JTextField tfNomeVeiculo;
	private Clientes cliente;
	private JLabel lbCpf;
	private JLabel lbChassis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVendaCliente frame = new CadastroVendaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroVendaCliente() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Cadastrar clientes");
		setIconifiable(true);
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				clientecontrol.atualizarJTableAction();
				veiculocontrol.atualizarTableVenda();
				vendedorcontrol.popularComboboxVendedorAction();
				vendacontrol.atualizarJTableAction();

			}
		});
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(867, 16, 2, 218);
		getContentPane().add(separator);

		tfCliente = new JTextField();
		tfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		tfCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCliente.setColumns(10);
		tfCliente.setBounds(222, 240, 37, 33);
		getContentPane().add(tfCliente);

		JLabel lblIdCliente = new JLabel("CPF:");
		lblIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdCliente.setBounds(28, 320, 46, 33);
		getContentPane().add(lblIdCliente);

		JButton btnVenda = new JButton("");
		btnVenda.setBackground(new Color(255, 0, 0));
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int resposta = JOptionPane.showConfirmDialog(null, "Deseja efetuar esta venda", "Confirmação de venda", JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					vendacontrol.cadastrarVenda();
					veiculocontrol.ativoVenda(tfCarro);
					veiculocontrol.atualizarTableVenda();
					JOptionPane.showMessageDialog(null, "Venda efetuada com sucesso");
				} else if (resposta == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "A venda não foi efetuada");
				}
				
				//vendacontrol.cadastrarVenda();
				//veiculocontrol.ativoVenda(tfCarro);
				veiculocontrol.atualizarTableVenda();

			}
		});
		btnVenda.setIcon(new ImageIcon(CadastroVendaCliente.class.getResource("/icones/icons8-comprar.png")));
		btnVenda.setBounds(771, 284, 78, 68);
		getContentPane().add(btnVenda);

		JLabel lblVendedor = new JLabel("Selecione o vendedor");
		lblVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendedor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVendedor.setBounds(540, 241, 216, 33);
		getContentPane().add(lblVendedor);

		cbVendedor = new JComboBox();
		cbVendedor.setBounds(550, 284, 206, 33);
		getContentPane().add(cbVendedor);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				veiculocontrol.preencherFormVenda();
			}
		});
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				veiculocontrol.preencherFormVenda();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 36, 266, 171);
		getContentPane().add(scrollPane);

		tableCliente = new JTable();
		tableCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				clientecontrol.preencherFormVenda();
			}
		});
		tableCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				clientecontrol.preencherFormVenda();
				cliente = new Clientes();
				

			}
		});
		tableCliente.setModel(new DefaultTableModel(new Object[][] {}, 
			new String[] { "ID", "Nome", "CPF" }){
			boolean[] columnEditables = new boolean[] { false, false, false };
			public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
			}
		});
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(70);
		tableCliente.getColumnModel().getColumn(2).setPreferredWidth(60);
		scrollPane.setViewportView(tableCliente);

		scrollPane_1.setBounds(275, 36, 589, 171);
		getContentPane().add(scrollPane_1);

		tableCarro = new JTable();
		tableCarro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				veiculocontrol.preencherFormVenda();
			}
		});
		tableCarro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				veiculocontrol.preencherFormVenda();

			}
		});
		tableCarro.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Modelo", "Ano", "Km", "Valor", "Fipe", "Chassis" }){
				boolean[] columnEditables = new boolean[] { false, false, false, false, false };
				public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCarro.getColumnModel().getColumn(0).setPreferredWidth(45);
		tableCarro.getColumnModel().getColumn(1).setPreferredWidth(25);
		tableCarro.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableCarro.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableCarro.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableCarro.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane_1.setViewportView(tableCarro);

		tfCarro = new JTextField();
		tfCarro.setHorizontalAlignment(SwingConstants.CENTER);
		tfCarro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCarro.setColumns(10);
		tfCarro.setBounds(485, 240, 37, 33);
		getContentPane().add(tfCarro);

		JLabel lblIdVeculo = new JLabel("Chassi:");
		lblIdVeculo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdVeculo.setBounds(288, 320, 56, 33);
		getContentPane().add(lblIdVeculo);

		JLabel lblSelecioneOCliente = new JLabel("Selecione o cliente:");
		lblSelecioneOCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelecioneOCliente.setBounds(59, 0, 160, 33);
		getContentPane().add(lblSelecioneOCliente);

		JLabel lblSelecioneOVeculo = new JLabel("Selecione o ve\u00EDculo:");
		lblSelecioneOVeculo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelecioneOVeculo.setBounds(463, 0, 160, 33);
		getContentPane().add(lblSelecioneOVeculo);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 360, 854, 209);
		getContentPane().add(scrollPane_2);

		tbVendas = new JTable();
		tbVendas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Data venda", "Cliente", "Vendedor", "Chassis" }) {
		boolean[] columnEditables = new boolean[] { false, false, false, false, false };
		public boolean isCellEditable(int row, int column) {
		return columnEditables[column];
	}
});
		tbVendas.getColumnModel().getColumn(0).setPreferredWidth(15);
		tbVendas.getColumnModel().getColumn(1).setPreferredWidth(50);
		tbVendas.getColumnModel().getColumn(2).setPreferredWidth(130);
		tbVendas.getColumnModel().getColumn(3).setPreferredWidth(130);
		tbVendas.getColumnModel().getColumn(4).setPreferredWidth(130);
		scrollPane_2.setViewportView(tbVendas);

		tfNomeCliente = new JTextField();
		tfNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		tfNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeCliente.setColumns(10);
		tfNomeCliente.setBounds(26, 282, 233, 33);
		getContentPane().add(tfNomeCliente);

		tfNomeVeiculo = new JTextField();
		tfNomeVeiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tfNomeVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeVeiculo.setColumns(10);
		tfNomeVeiculo.setBounds(289, 282, 233, 33);
		getContentPane().add(tfNomeVeiculo);
		
		
		JLabel lblClienteSelecionado = new JLabel("Cliente selecionado");
		lblClienteSelecionado.setHorizontalAlignment(SwingConstants.CENTER);
		lblClienteSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClienteSelecionado.setBounds(41, 241, 150, 33);
		getContentPane().add(lblClienteSelecionado);
		
		JLabel lblVeculoSelecionado = new JLabel("Ve\u00EDculo selecionado");
		lblVeculoSelecionado.setHorizontalAlignment(SwingConstants.CENTER);
		lblVeculoSelecionado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVeculoSelecionado.setBounds(291, 241, 179, 33);
		getContentPane().add(lblVeculoSelecionado);
		
		JLabel lblComprar = new JLabel("VENDER");
		lblComprar.setBackground(Color.RED);
		lblComprar.setHorizontalAlignment(SwingConstants.CENTER);
		lblComprar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComprar.setBounds(771, 241, 78, 33);
		getContentPane().add(lblComprar);
		
		lbCpf = new JLabel("");
		lbCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbCpf.setBounds(80, 320, 179, 33);
		getContentPane().add(lbCpf);
		
		
		lbChassis = new JLabel("");
		lbChassis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbChassis.setBounds(342, 320, 179, 33);
		getContentPane().add(lbChassis);

		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(274, 205, 2, 154);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(540, 205, 2, 154);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(762, 205, 2, 154);
		getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(10, 205, 2, 154);
		getContentPane().add(separator_4);
		
		vendacontrol = new VendasControl(tfCliente, tfCarro, cbVendedor, tbVendas);
		clientecontrol = new ClienteControl(tfCliente, tfNomeCliente, tableCliente, lbCpf);
		veiculocontrol = new VeiculoControl(tfCarro, tfNomeVeiculo, tableCarro, lbChassis);
		vendedorcontrol = new VendedorControl(cbVendedor);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 205, 842, 2);
		getContentPane().add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(864, 205, 2, 154);
		getContentPane().add(separator_6);
	}
}
