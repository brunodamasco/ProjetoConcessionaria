package senac.bruno.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import javax.swing.table.DefaultTableModel;

import bruno.senac.control.VendedorControl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GerenciarVendedor extends JInternalFrame {
	private JComboBox<Object> cbVendedor;
	private JTextField tfNomeVendedor;
	private JTextField tfCpf;
	private JTextField tfComissao;
	private JTextField tfSalario;
	private JTextField tfPesquisaVendedores;
	private JTable table;
	private VendedorControl vendedorControl;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarVendedor frame = new GerenciarVendedor();
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
	public GerenciarVendedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initianeze();
		vendedorControl.atualizarJTableAction();
	}
	
	public void initianeze() {
		setTitle("Gerenciar vendedores");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblGerenciarVendedores = new JLabel("Gerenciar Vendedores");
		lblGerenciarVendedores.setBounds(0, 0, 864, 43);
		lblGerenciarVendedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciarVendedores.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(lblGerenciarVendedores);
		
		JLabel lblNomeDoVendedor = new JLabel("Nome do Vendedor:");
		lblNomeDoVendedor.setBounds(41, 104, 169, 33);
		lblNomeDoVendedor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNomeDoVendedor);
		
		tfNomeVendedor = new JTextField();
		tfNomeVendedor.setBounds(225, 103, 343, 33);
		tfNomeVendedor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeVendedor.setColumns(10);
		getContentPane().add(tfNomeVendedor);
		
		JLabel label_2 = new JLabel("CPF:");
		label_2.setBounds(164, 142, 46, 33);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(label_2);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(225, 141, 343, 33);
		tfCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCpf.setColumns(10);
		getContentPane().add(tfCpf);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o %");
		lblComisso.setBounds(100, 180, 110, 33);
		lblComisso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblComisso);
		
		tfComissao = new JTextField();
		tfComissao.setBounds(225, 179, 343, 33);
		tfComissao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfComissao.setColumns(10);
		getContentPane().add(tfComissao);
		
		JLabel lblSalrio = new JLabel("Sal\u00E1rio R$");
		lblSalrio.setBounds(123, 219, 87, 33);
		lblSalrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblSalrio);
		
		tfSalario = new JTextField();
		tfSalario.setBounds(225, 218, 343, 33);
		tfSalario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfSalario.setColumns(10);
		getContentPane().add(tfSalario);
		
		tfPesquisaVendedores = new JTextField();
		tfPesquisaVendedores.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				vendedorControl.pesquisarNomeVendedor();
			}
		});
		tfPesquisaVendedores.setBounds(225, 278, 343, 33);
		tfPesquisaVendedores.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPesquisaVendedores.setColumns(10);
		getContentPane().add(tfPesquisaVendedores);
		
		JLabel lblPesquisarVendedores = new JLabel("Pesquisar vendedores:");
		lblPesquisarVendedores.setBounds(25, 279, 195, 33);
		lblPesquisarVendedores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblPesquisarVendedores);
		
		JButton btSalvar = new JButton("");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendedorControl.salvarAction();
				vendedorControl.atualizarJTableAction();
			}
		});
		btSalvar.setIcon(new ImageIcon(GerenciarVendedor.class.getResource("/icones/icons8-salvar-filled-50.png")));
		btSalvar.setBounds(661, 184, 72, 68);
		getContentPane().add(btSalvar);
		
		JButton btAtualizar = new JButton("");
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vendedorControl.limparTextfield();
				vendedorControl.atualizarJTableAction();
				JOptionPane.showMessageDialog(null, "Tabela atualizada com sucesso", "Notificação", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btAtualizar.setIcon(new ImageIcon(GerenciarVendedor.class.getResource("/icones/icons8-actualizar-48.png")));
		btAtualizar.setBounds(700, 104, 72, 68);
		getContentPane().add(btAtualizar);
		
		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vendedorControl.excluirAction();
				vendedorControl.atualizarJTableAction();
			}
		});
		btExcluir.setIcon(new ImageIcon(GerenciarVendedor.class.getResource("/icones/icons8-lixo-64.png")));
		btExcluir.setBounds(748, 184, 72, 68);
		getContentPane().add(btExcluir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 268, 834, 7);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 45, 834, 8);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(15, 48, 2, 223);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(847, 48, 2, 223);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(separator_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 326, 834, 243);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				vendedorControl.selecionaLinhaTable();
				//vendedorControl.preencherForm();

			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vendedorControl.selecionaLinhaTable();
				//vendedorControl.preencherForm();
				
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {},
			new String[] { "ID", "Nome do vendedor", "CPF", "Comissão", "Salário"
			}) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(210);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		scrollPane.setViewportView(table);
		vendedorControl = new VendedorControl(cbVendedor, tfNomeVendedor, tfCpf, tfComissao, tfSalario, tfPesquisaVendedores, table);
		
		JLabel lblCadastrarVendedores = new JLabel("Cadastrar vendedores");
		lblCadastrarVendedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarVendedores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarVendedores.setBounds(64, 54, 716, 33);
		getContentPane().add(lblCadastrarVendedores);

	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
