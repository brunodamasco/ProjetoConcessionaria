package senac.bruno.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import bruno.senac.control.ClienteControl;
import bruno.senac.control.HistoricoControl;
import bruno.senac.control.VeiculoControl;
import bruno.senac.control.VendasControl;
import bruno.senac.control.VendedorControl;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class HistoricoVendas extends JInternalFrame {
	private JTable table;
	private ClienteControl clientecontrol;
	private VeiculoControl veiculocontrol;
	private VendedorControl vendedorcontrol;
	private VendasControl vendacontrol;
	private HistoricoControl historicoControl;
	private Date dataSistema = new Date();
	private JTextField tfPesquisar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoricoVendas frame = new HistoricoVendas();
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
	public HistoricoVendas() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				historicoControl.atualizarHistoricoVendasJTableAction();

			}
		});
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Hist\u00F3rico de vendas");
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblHistricoDeVendas = new JLabel("Pesquisar vendas");
		lblHistricoDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistricoDeVendas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistricoDeVendas.setBounds(77, 28, 426, 33);
		getContentPane().add(lblHistricoDeVendas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 16, 796, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(30, 16, 2, 192);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(30, 206, 796, 7);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(824, 16, 2, 192);
		getContentPane().add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 229, 864, 340);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Data venda", "Vendedor", "Cliente", "Modelo", "Cor", "Chassis", "Valor" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(HistoricoVendas.class.getResource("/icones/icons8-arquivo-64.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
				String data = dtf.format(dataSistema);
				historicoControl.relatorioVendedorTXT();;
				Desktop desk = Desktop.getDesktop();
				try {
					desk.open(new File("C:\\Users\\Bruno\\git\\ProjetoDesktop\\ProjetoConcessionaria\\relatorios\\"+ data +".txt"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(749, 129, 48, 61);
		getContentPane().add(btnNewButton);
		
		tfPesquisar = new JTextField();
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				historicoControl.pesquisarNomeCliente();
			}
		});
		tfPesquisar.setBounds(273, 166, 268, 30);
		getContentPane().add(tfPesquisar);
		tfPesquisar.setColumns(10);

		historicoControl = new HistoricoControl(table, tfPesquisar);
		
		JLabel lblPesquisarVendedorcliente = new JLabel("Nome vendedor/cliente:");
		lblPesquisarVendedorcliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarVendedorcliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPesquisarVendedorcliente.setBounds(49, 164, 226, 33);
		getContentPane().add(lblPesquisarVendedorcliente);
		
		JLabel lblGerarRelatrioDas = new JLabel("Gerar relat\u00F3rios");
		lblGerarRelatrioDas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerarRelatrioDas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGerarRelatrioDas.setBounds(600, 28, 226, 33);
		getContentPane().add(lblGerarRelatrioDas);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(HistoricoVendas.class.getResource("/icones/icons8-arquivo-64.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
				String data = dtf.format(dataSistema);
				historicoControl.relatorioVendasTXT();;
				Desktop desk = Desktop.getDesktop();
				try {
					desk.open(new File("C:\\Users\\Bruno\\git\\ProjetoDesktop\\ProjetoConcessionaria\\relatorios\\"+ data +".txt"));
				} catch (IOException b) {
					b.printStackTrace();
				}
			}
		});
		button.setBounds(635, 129, 53, 61);
		getContentPane().add(button);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendedor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVendedor.setBounds(715, 80, 111, 33);
		getContentPane().add(lblVendedor);
		
		JLabel lblVendas = new JLabel("Vendas");
		lblVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVendas.setBounds(604, 80, 113, 33);
		getContentPane().add(lblVendas);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(600, 16, 2, 192);
		getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(715, 60, 2, 148);
		getContentPane().add(separator_5);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(30, 60, 796, 2);
		getContentPane().add(separator_7);
	}
}
