package senac.bruno.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bruno.senac.control.ClienteControl;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class GerenciarClientes extends JInternalFrame {
	private JTextField tfNomeCliente;
	private JTextField tfDataNascimento;
	private JTextField tfCpf;
	private JTextField tfTelefone;
	private JTextField tfPesquisarNomeCliente;
	private JTable table;
	private ClienteControl clienteControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
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
	public GerenciarClientes() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		clienteControl = new ClienteControl(tfNomeCliente, tfCpf, tfDataNascimento, tfTelefone, tfPesquisarNomeCliente, table);
		initianeze();
		clienteControl.atualizarJTableAction();
	}

	public void initianeze() {
		setClosable(true);
		setIconifiable(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		setTitle("Gerenciar Clientes");
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);

		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente:");
		lblNomeDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoCliente.setBounds(80, 85, 147, 33);
		getContentPane().add(lblNomeDoCliente);

		tfNomeCliente = new JTextField();
		tfNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeCliente.setBounds(242, 84, 343, 33);
		getContentPane().add(tfNomeCliente);
		tfNomeCliente.setColumns(10);

		JLabel lblCadastrarClientes = new JLabel("Gerenciar Clientes");
		lblCadastrarClientes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastrarClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarClientes.setBounds(43, 1, 716, 33);
		getContentPane().add(lblCadastrarClientes);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCpf.setBounds(181, 123, 46, 33);
		getContentPane().add(lblCpf);

		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCpf.setColumns(10);
		tfCpf.setBounds(242, 122, 343, 33);
		getContentPane().add(tfCpf);

		JLabel lblDataNascimento = new JLabel("Data nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataNascimento.setBounds(80, 161, 147, 33);
		getContentPane().add(lblDataNascimento);

		tfDataNascimento = new JTextField();
		tfDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfDataNascimento.setColumns(10);
		tfDataNascimento.setBounds(242, 160, 343, 33);
		getContentPane().add(tfDataNascimento);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefone.setBounds(144, 200, 83, 33);
		getContentPane().add(lblTelefone);

		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(242, 199, 343, 33);
		getContentPane().add(tfTelefone);

		tfPesquisarNomeCliente = new JTextField();
		tfPesquisarNomeCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				clienteControl.pesquisarNomeCliente();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 320, 834, 249);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				clienteControl.preencherForm();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clienteControl.preencherForm();
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome do cliente", "CPF", "Data de nascimento", "Telefone"}){
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
		clienteControl = new ClienteControl(tfNomeCliente, tfCpf, tfDataNascimento, 
				tfTelefone, tfPesquisarNomeCliente, table);
		tfPesquisarNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPesquisarNomeCliente.setColumns(10);
		tfPesquisarNomeCliente.setBounds(242, 271, 343, 33);
		getContentPane().add(tfPesquisarNomeCliente);

		JLabel lblPesquisarClientes = new JLabel("Pesquisar clientes:");
		lblPesquisarClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPesquisarClientes.setBounds(74, 272, 148, 33);
		getContentPane().add(lblPesquisarClientes);

		JButton btSalvar = new JButton("");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clienteControl.salvarAction();
				clienteControl.atualizarJTableAction();
			}
		});
		btSalvar.setIcon(new ImageIcon(GerenciarClientes.class.getResource("/icones/icons8-salvar-filled-50.png")));
		btSalvar.setBounds(661, 164, 72, 68);
		getContentPane().add(btSalvar);

		JButton btAtualizar = new JButton("");
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteControl.limparTextfield();
				clienteControl.atualizarJTableAction();
				JOptionPane.showMessageDialog(null, "Tabela atualizada com sucesso", "Notificação", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btAtualizar.setIcon(new ImageIcon(GerenciarClientes.class.getResource("/icones/icons8-actualizar-48.png")));
		btAtualizar.setBounds(703, 85, 72, 68);
		getContentPane().add(btAtualizar);

		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteControl.excluirAction();
				clienteControl.atualizarJTableAction();
			}
		});
		btExcluir.setIcon(new ImageIcon(GerenciarClientes.class.getResource("/icones/icons8-lixo-64.png")));
		btExcluir.setBounds(748, 164, 72, 68);
		getContentPane().add(btExcluir);

		JSeparator separator = new JSeparator();
		separator.setBounds(15, 248, 834, 7);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 33, 834, 8);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(15, 33, 2, 218);
		getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(847, 33, 2, 218);
		getContentPane().add(separator_3);

		JLabel lblCadastrarClientes_1 = new JLabel("Cadastrar clientes");
		lblCadastrarClientes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarClientes_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarClientes_1.setBounds(43, 34, 716, 33);
		getContentPane().add(lblCadastrarClientes_1);

	}
}
