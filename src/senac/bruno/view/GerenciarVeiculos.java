package senac.bruno.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

import bruno.senac.control.MarcasControl;
import bruno.senac.control.VeiculoControl;
import bruno.senac.control.VendedorControl;
import senac.bruno.model.Marcas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class GerenciarVeiculos extends JInternalFrame {
	private JComboBox<Object> cbMarca;
	private JComboBox<Object> cbModelo;
	private JComboBox<Object> cbPesquisa;
	private JTextField tfAno;
	private JTextField tfQuilometragem;
	private JTextField tfValor;
	private JTextField tfChassis;
	private JTextField tfValorFipe;
	private JTextField tfCor;
	private JTable table;
	private VeiculoControl veiculoControl;
	private MarcasControl marcasControl;
	private JTextField tfPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarVeiculos frame = new GerenciarVeiculos();
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
	public GerenciarVeiculos() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		initianeze();
		veiculoControl = new VeiculoControl(cbMarca, cbModelo, tfAno, tfQuilometragem, tfValor, tfChassis, tfValorFipe, tfCor, null, table, tfPesquisa);
		veiculoControl.atualizarJTableAction();
		veiculoControl.popularComboBoxMarcaAction();
		veiculoControl.popularComboBoxModeloAction();
	
	}
	
	public void initianeze() {
		
		setTitle("Gerenciar ve\u00EDculos");
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblCadastrarVendedores = new JLabel("Gerenciar ve\u00EDculos");
		lblCadastrarVendedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarVendedores.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCadastrarVendedores.setBounds(0, 0, 864, 43);
		getContentPane().add(lblCadastrarVendedores);
		
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAno.setBounds(66, 142, 46, 33);
		getContentPane().add(lblAno);
		
		tfAno = new JTextField();
		tfAno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfAno.setColumns(10);
		tfAno.setBounds(115, 139, 170, 33);
		getContentPane().add(tfAno);
		
		JLabel lblQuilometragem = new JLabel("Quilometragem:");
		lblQuilometragem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuilometragem.setBounds(305, 142, 136, 33);
		getContentPane().add(lblQuilometragem);
		
		tfQuilometragem = new JTextField();
		tfQuilometragem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfQuilometragem.setColumns(10);
		tfQuilometragem.setBounds(443, 139, 171, 33);
		getContentPane().add(tfQuilometragem);
		
		JLabel lblValor = new JLabel("Valor R$:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValor.setBounds(32, 180, 80, 33);
		getContentPane().add(lblValor);
		
		tfValor = new JTextField();
		tfValor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfValor.setColumns(10);
		tfValor.setBounds(115, 179, 170, 33);
		getContentPane().add(tfValor);
		
		JLabel lblValorFipeR = new JLabel("Valor fipe R$:");
		lblValorFipeR.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorFipeR.setBounds(322, 180, 119, 33);
		getContentPane().add(lblValorFipeR);
		
		tfChassis = new JTextField();
		tfChassis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfChassis.setColumns(10);
		tfChassis.setBounds(443, 219, 171, 33);
		getContentPane().add(tfChassis);
		
		JButton btSalvar = new JButton("");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculoControl.salvarAction();
				veiculoControl.atualizarJTableAction();
			}
		});
		btSalvar.setIcon(new ImageIcon(GerenciarVeiculos.class.getResource("/icones/icons8-salvar-filled-50.png")));
		btSalvar.setBounds(661, 184, 72, 68);
		getContentPane().add(btSalvar);
		
		JButton btAtualizar = new JButton("");
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculoControl.limparTextfield();
				veiculoControl.atualizarJTableAction();
				JOptionPane.showMessageDialog(null, "Tabela atualizada com sucesso", "Notificação", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btAtualizar.setIcon(new ImageIcon(GerenciarVeiculos.class.getResource("/icones/icons8-actualizar-48.png")));
		btAtualizar.setBounds(702, 107, 72, 68);
		getContentPane().add(btAtualizar);
		
		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				veiculoControl.excluirAction();
				veiculoControl.atualizarJTableAction();
			}
		});
		btExcluir.setIcon(new ImageIcon(GerenciarVeiculos.class.getResource("/icones/icons8-lixo-64.png")));
		btExcluir.setBounds(748, 184, 72, 68);
		getContentPane().add(btExcluir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 268, 834, 7);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 45, 834, 8);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(15, 48, 2, 223);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(847, 48, 2, 223);
		getContentPane().add(separator_3);
		
		JLabel lblCadastrarVeculos = new JLabel("Cadastrar ve\u00EDculos");
		lblCadastrarVeculos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarVeculos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarVeculos.setBounds(64, 54, 716, 33);
		getContentPane().add(lblCadastrarVeculos);
		
		tfValorFipe = new JTextField();
		tfValorFipe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfValorFipe.setColumns(10);
		tfValorFipe.setBounds(443, 179, 171, 33);
		getContentPane().add(tfValorFipe);
		
		JLabel lblChassis = new JLabel("Chassis:");
		lblChassis.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChassis.setBounds(369, 220, 72, 33);
		getContentPane().add(lblChassis);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarca.setBounds(50, 93, 62, 33);
		getContentPane().add(lblMarca);
		
		cbMarca = new JComboBox();
		cbMarca.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				veiculoControl.popularComboBoxModeloAction();
			}
		});
		cbMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbMarca.setBounds(115, 93, 170, 32);
		getContentPane().add(cbMarca);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModelo.setBounds(369, 93, 72, 33);
		getContentPane().add(lblModelo);
		
		cbModelo = new JComboBox();
		cbModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbModelo.setBounds(443, 93, 171, 32);
		getContentPane().add(cbModelo);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCor.setBounds(72, 219, 40, 33);
		getContentPane().add(lblCor);
		
		tfCor = new JTextField();
		tfCor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCor.setColumns(10);
		tfCor.setBounds(115, 219, 170, 33);
		getContentPane().add(tfCor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 319, 864, 266);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				veiculoControl.preencherForm();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				veiculoControl.preencherForm();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Marca", "Modelo", "Ano", "Km", "Valor", "Fipe", "Cor", "Chassis"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
		scrollPane.setViewportView(table);
		
		
		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				veiculoControl.pesquisarIdVeiculo();
				
			}
		});
		tfPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPesquisa.setColumns(10);
		tfPesquisa.setBounds(322, 281, 350, 33);
		getContentPane().add(tfPesquisa);
		
		JLabel lblPesquisarPor = new JLabel("Pesquisar por: ");
		lblPesquisarPor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPesquisarPor.setBounds(205, 277, 119, 33);
		getContentPane().add(lblPesquisarPor);
		
		
		
	}
	
}
