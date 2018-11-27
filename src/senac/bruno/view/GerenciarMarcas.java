package senac.bruno.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bruno.senac.control.MarcasControl;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GerenciarMarcas extends JInternalFrame {
	private JTextField tfNomeMarca;
	private JTextField tfPesquisarMarcas;
	private JTable table;
	private MarcasControl marcasControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarMarcas frame = new GerenciarMarcas();
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
	public GerenciarMarcas() {
		initianeze();
		marcasControl.atualizarJTableAction();
	}
	
	public void initianeze() {
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Gerenciar marcas");
		setClosable(true);
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblGerenciarMarcas = new JLabel("Gerenciar marcas");
		lblGerenciarMarcas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciarMarcas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGerenciarMarcas.setBounds(0, 0, 864, 40);
		getContentPane().add(lblGerenciarMarcas);
		
		JLabel lblCadastrarNovaMarca = new JLabel("Cadastrar nova marca");
		lblCadastrarNovaMarca.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarNovaMarca.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrarNovaMarca.setBounds(236, 44, 395, 33);
		getContentPane().add(lblCadastrarNovaMarca);
		
		JLabel lblNovaMarca = new JLabel("Nova marca");
		lblNovaMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNovaMarca.setBounds(150, 123, 102, 33);
		getContentPane().add(lblNovaMarca);
		
		tfNomeMarca = new JTextField();
		tfNomeMarca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfNomeMarca.setColumns(10);
		tfNomeMarca.setBounds(278, 122, 319, 33);
		getContentPane().add(tfNomeMarca);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(GerenciarMarcas.class.getResource("/icones/icons8-salvar-filled-50.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marcasControl.salvarAction();
				marcasControl.atualizarJTableAction();
			}
		});
		button.setBounds(666, 122, 73, 66);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(GerenciarMarcas.class.getResource("/icones/icons8-lixo-64.png")));
		button_1.setBounds(754, 122, 73, 66);
		getContentPane().add(button_1);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon(GerenciarMarcas.class.getResource("/icones/icons8-actualizar-48.png")));
		button_3.setBounds(709, 44, 73, 66);
		getContentPane().add(button_3);
		
		JLabel lblPesquisarMarca = new JLabel("Pesquisar marca");
		lblPesquisarMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPesquisarMarca.setBounds(125, 230, 138, 33);
		getContentPane().add(lblPesquisarMarca);
		
		tfPesquisarMarcas = new JTextField();
		tfPesquisarMarcas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfPesquisarMarcas.setColumns(10);
		tfPesquisarMarcas.setBounds(278, 229, 319, 33);
		getContentPane().add(tfPesquisarMarcas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 834, 8);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(842, 32, 2, 168);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 205, 834, 8);
		getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(10, 32, 2, 168);
		getContentPane().add(separator_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 278, 805, 291);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id marcas", "Nome marcas"
			}
		));
		scrollPane.setViewportView(table);
		marcasControl = new MarcasControl(tfNomeMarca, tfPesquisarMarcas, table);

	}
}
