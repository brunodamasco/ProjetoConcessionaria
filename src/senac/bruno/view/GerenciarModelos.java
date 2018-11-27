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

import bruno.senac.control.MarcasControl;
import bruno.senac.control.ModeloControl;
import bruno.senac.control.VeiculoControl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GerenciarModelos extends JInternalFrame {
	private JComboBox cbMarca;
	private JTextField tfNomeModelo;
	private JTable table;
	private JTextField tfPesquisarModelos;
	private MarcasControl marcasControl;
	private ModeloControl modeloControl;
	private VeiculoControl veiculoControl;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarModelos frame = new GerenciarModelos();
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
	public GerenciarModelos() {
		initianeze();
		modeloControl.atualizarJTableAction();
		modeloControl.popularComboBoxMarcaAction();
		
		JSeparator separator = new JSeparator();
		separator.setBounds(15, 222, 834, 8);
		getContentPane().add(separator);
	}
	
	
	public void initianeze() {
		
		JLabel lblCadastrarNovaModelo = new JLabel("Cadastrar novo modelo");
		lblCadastrarNovaModelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarNovaModelo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarNovaModelo.setBounds(236, 44, 395, 33);
		getContentPane().add(lblCadastrarNovaModelo);
		
		JLabel lblSelecioneAMarca = new JLabel("Selecione a marca");
		lblSelecioneAMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelecioneAMarca.setBounds(53, 93, 148, 33);
		getContentPane().add(lblSelecioneAMarca);
		
		setTitle("Gerenciar marcas");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblGerenciarMarcas = new JLabel("Gerenciar modelos");
		lblGerenciarMarcas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciarMarcas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGerenciarMarcas.setBounds(0, 0, 864, 43);
		getContentPane().add(lblGerenciarMarcas);
		
		JLabel lblModelo = new JLabel("Novo modelo");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblModelo.setBounds(53, 143, 111, 33);
		getContentPane().add(lblModelo);
		
		tfNomeModelo = new JTextField();
		tfNomeModelo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeModelo.setColumns(10);
		tfNomeModelo.setBounds(236, 142, 319, 33);
		getContentPane().add(tfNomeModelo);
		
		JButton btSalvarNovoModelo = new JButton("");
		btSalvarNovoModelo.setIcon(new ImageIcon(GerenciarModelos.class.getResource("/icones/icons8-salvar-filled-50.png")));
		btSalvarNovoModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloControl.salvarAction();
				modeloControl.atualizarJTableAction();
			}
		});
		btSalvarNovoModelo.setBounds(674, 159, 69, 59);
		getContentPane().add(btSalvarNovoModelo);
		
		JButton btAtualizar = new JButton("");
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloControl.popularComboBoxMarcaAction();
				modeloControl.limparTextfield();
				modeloControl.atualizarJTableAction();
			}
		});
		btAtualizar.setIcon(new ImageIcon(GerenciarModelos.class.getResource("/icones/icons8-actualizar-48.png")));
		btAtualizar.setBounds(714, 84, 72, 59);
		getContentPane().add(btAtualizar);
		
		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloControl.excluirAction();
				modeloControl.atualizarJTableAction();
				//marcasControl.excluirAction();
				//marcasControl.atualizarJTableAction();
			}
		});
		btExcluir.setIcon(new ImageIcon(GerenciarModelos.class.getResource("/icones/icons8-lixo-64.png")));
		btExcluir.setBounds(763, 159, 72, 59);
		getContentPane().add(btExcluir);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(15, 44, 834, 8);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(15, 44, 2, 180);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(847, 47, 2, 177);
		getContentPane().add(separator_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 295, 834, 274);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)  { 
					modeloControl.preencherForm();
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				modeloControl.preencherForm();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID MODELO", "MODELOS", "MARCA"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		JLabel lblPesquisarMarcamodelo = new JLabel("Pesquisar marca/modelo");
		lblPesquisarMarcamodelo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPesquisarMarcamodelo.setBounds(15, 246, 202, 33);
		getContentPane().add(lblPesquisarMarcamodelo);
		
		tfPesquisarModelos = new JTextField();
		tfPesquisarModelos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				modeloControl.pesquisarNomeModelo();
				//marcasControl.pesquisarNomeMarca();
			}
		});
		tfPesquisarModelos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPesquisarModelos.setColumns(10);
		tfPesquisarModelos.setBounds(218, 246, 202, 33);
		getContentPane().add(tfPesquisarModelos);
		
		cbMarca = new JComboBox();
		cbMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbMarca.setBounds(236, 93, 319, 33);
		getContentPane().add(cbMarca);
		modeloControl = new ModeloControl(tfNomeModelo, table, cbMarca,tfPesquisarModelos);

	}
	public JComboBox getCbMarca() {
		return cbMarca;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
}
