package senac.bruno.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JEditorPane;

public class TelaInicial {

	private JFrame frmConcessionriaLambadaNo;
	private CadastroVendaCliente cadastroVendaCliente = null;
	private HistoricoVendas historicoVendas = null;
	private GerenciarClientes gerenciarClientes = null;
	private GerenciarVendedor gerenciarVendedor = null;
	private GerenciarVeiculos gerenciarVeiculos = null;
	private GerenciarModelos gerenciarModelos = null;
	private GerenciarMarcas gerenciarMarcas = null;
	private Autores autores = null;
	public static JDesktopPane desktopPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frmConcessionriaLambadaNo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmConcessionriaLambadaNo = new JFrame();
		frmConcessionriaLambadaNo.setBackground(SystemColor.activeCaption);
		frmConcessionriaLambadaNo.setResizable(false);
		frmConcessionriaLambadaNo.setTitle("Concession\u00E1ria Lambada no Alambrado");
		frmConcessionriaLambadaNo.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/icones/icons8-sedan-64.png")));
		frmConcessionriaLambadaNo.setBounds(100, 100, 887, 817);
		frmConcessionriaLambadaNo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConcessionriaLambadaNo.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 880, 116);
		frmConcessionriaLambadaNo.getContentPane().add(menuBar);
		
		JMenu mnVendas = new JMenu("Vendas");
		mnVendas.setHorizontalAlignment(SwingConstants.LEFT);
		mnVendas.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-comprar.png")));
		mnVendas.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnVendas);
		
		JMenuItem mntmCadastrarVendas = new JMenuItem("Cadastrar vendas");
		mntmCadastrarVendas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmCadastrarVendas.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-comprar.png")));
		mntmCadastrarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cadastroVendaCliente==null || cadastroVendaCliente.isVisible()==false) {
					cadastroVendaCliente = new CadastroVendaCliente();
					desktopPane.add(cadastroVendaCliente);
				}
				cadastroVendaCliente.setLocation(0, 0);
				cadastroVendaCliente.show();
			}
		});
		mntmCadastrarVendas.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnVendas.add(mntmCadastrarVendas);
		
		JMenuItem mntmHistricoDeVendas = new JMenuItem("Hist\u00F3rico de vendas");
		mntmHistricoDeVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(historicoVendas==null || historicoVendas.isVisible()==false) {
					historicoVendas = new HistoricoVendas();
					desktopPane.add(historicoVendas);
				}
				historicoVendas.setLocation(0, 0);
				historicoVendas.show();
			}
		});
		mntmHistricoDeVendas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmHistricoDeVendas.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-documento-regular.png")));
		mntmHistricoDeVendas.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnVendas.add(mntmHistricoDeVendas);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(gerenciarClientes==null || gerenciarClientes.isVisible()==false) {
					gerenciarClientes = new GerenciarClientes();
					desktopPane.add(gerenciarClientes);
				}
				gerenciarClientes.setLocation(0, 0);
				gerenciarClientes.show();
			}
		});
		mnClientes.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-fila.png")));
		mnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnClientes);
		
		JMenu mnVendedores = new JMenu("Vendedores");
		mnVendedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(gerenciarVendedor==null || gerenciarVendedor.isVisible()==false) {
					gerenciarVendedor = new GerenciarVendedor();
					desktopPane.add(gerenciarVendedor);
				}
				gerenciarVendedor.setLocation(0, 0);
				gerenciarVendedor.show();
			}
		});
		mnVendedores.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-administrador-masculino-80.png")));
		mnVendedores.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnVendedores);
		
		JMenu mnCarros = new JMenu("Ve\u00EDculos");
		mnCarros.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-engarrafamento-filled-50.png")));
		mnCarros.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnCarros);
		
		JMenuItem mntmCadastrarVeculos = new JMenuItem("Cadastrar ve\u00EDculos");
		mntmCadastrarVeculos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmCadastrarVeculos.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-sedan-filled-50.png")));
		mntmCadastrarVeculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gerenciarVeiculos==null || gerenciarVeiculos.isVisible()==false) {
					gerenciarVeiculos = new GerenciarVeiculos();
					desktopPane.add(gerenciarVeiculos);
				}
				gerenciarVeiculos.setLocation(0, 0);
				gerenciarVeiculos.show();
			}
		});
		mntmCadastrarVeculos.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnCarros.add(mntmCadastrarVeculos);
		
		JMenuItem mntmGerenciarMarcas = new JMenuItem("Gerenciar modelos");
		mntmGerenciarMarcas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmGerenciarMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gerenciarModelos==null || gerenciarModelos.isVisible()==false) {
					gerenciarModelos = new GerenciarModelos();
					desktopPane.add(gerenciarModelos);
				}
				gerenciarModelos.setLocation(0, 0);
				gerenciarModelos.show();
			}
		});
		mntmGerenciarMarcas.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-carro-de-pol\u00EDcia-60.png")));
		mntmGerenciarMarcas.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnCarros.add(mntmGerenciarMarcas);
		
		JMenuItem mntmGerenciarMarcas_1 = new JMenuItem("Gerenciar Marcas");
		mntmGerenciarMarcas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(gerenciarMarcas==null || gerenciarMarcas.isVisible()==false) {
					gerenciarMarcas = new GerenciarMarcas();
					desktopPane.add(gerenciarMarcas);
				}
				gerenciarMarcas.setLocation(0, 0);
				gerenciarMarcas.show();
			}
		});
		mntmGerenciarMarcas_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmGerenciarMarcas_1.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-carro-de-pol\u00EDcia-60.png")));
		mntmGerenciarMarcas_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnCarros.add(mntmGerenciarMarcas_1);
		
		JMenu mnAdministrador = new JMenu("Configura\u00E7\u00F5es");
		mnAdministrador.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-ferramentas-administrativas-filled-50.png")));
		mnAdministrador.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(mnAdministrador);
		
		JMenuItem mntmAutores = new JMenuItem("Autores");
		mntmAutores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(autores==null || autores.isVisible()==false) {
					autores = new Autores();
					desktopPane.add(autores);
				}
				autores.setLocation(0, 0);
				autores.show();
			}
		});
		mntmAutores.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-usu+\u00EDrio.png")));
		mntmAutores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmAutores.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		mnAdministrador.add(mntmAutores);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 120, 880, 626);
		desktopPane.setForeground(SystemColor.controlDkShadow);
		desktopPane.setBorder(new LineBorder(SystemColor.activeCaption));
		desktopPane.setBackground(new Color(192, 192, 192));
		frmConcessionriaLambadaNo.getContentPane().add(desktopPane);
		desktopPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setForeground(new Color(51, 204, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(TelaInicial.class.getResource("/icones/icons8-engarrafamento-512.png")));
		lblNewLabel.setBounds(0, 0, 880, 626);
		desktopPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Concession\u00E1ria");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(15, 45, 339, 40);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblLambadaNoAlambrado = new JLabel("Lambada no Alambrado");
		lblLambadaNoAlambrado.setHorizontalAlignment(SwingConstants.CENTER);
		lblLambadaNoAlambrado.setFont(new Font("Comic Sans MS", Font.ITALIC, 60));
		lblLambadaNoAlambrado.setBounds(0, 548, 880, 78);
		desktopPane.add(lblLambadaNoAlambrado);
		
		JLabel lblSistemaDeGerenciamento = new JLabel("Sistema de gerenciamento de vendas de carro");
		lblSistemaDeGerenciamento.setBounds(35, 745, 727, 32);
		lblSistemaDeGerenciamento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSistemaDeGerenciamento.setHorizontalAlignment(SwingConstants.CENTER);
		frmConcessionriaLambadaNo.getContentPane().add(lblSistemaDeGerenciamento);
		
		
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
