package senac.bruno.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFrame;

public class Autores extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autores frame = new Autores();
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
	public Autores() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setIconifiable(true);
		setTitle("Autores");
		setBounds(0, 120, 880, 626);
		getContentPane().setLayout(null);
		
		JLabel lblAutores = new JLabel("Autores");
		lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutores.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAutores.setBounds(0, 0, 864, 43);
		getContentPane().add(lblAutores);
		
		JLabel lblSoftwareDesenvolvidoPelo = new JLabel("Software desenvolvido pelo aluno Bruno Damasco");
		lblSoftwareDesenvolvidoPelo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoftwareDesenvolvidoPelo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSoftwareDesenvolvidoPelo.setBounds(0, 59, 864, 53);
		getContentPane().add(lblSoftwareDesenvolvidoPelo);
		
		JLabel lblCursoAnliseE = new JLabel("Curso: An\u00E1lise e Desenvolvimento de Sistemas");
		lblCursoAnliseE.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursoAnliseE.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCursoAnliseE.setBounds(0, 114, 864, 43);
		getContentPane().add(lblCursoAnliseE);
		
		JLabel lblProfessorRenatoParanagua = new JLabel("Professor Renato Paranagua da Silva");
		lblProfessorRenatoParanagua.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorRenatoParanagua.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfessorRenatoParanagua.setBounds(0, 209, 864, 43);
		getContentPane().add(lblProfessorRenatoParanagua);
		
		JLabel lblMatriaDesenvolvimentoPara = new JLabel("Mat\u00E9ria: Desenvolvimento para Desktop");
		lblMatriaDesenvolvimentoPara.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriaDesenvolvimentoPara.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMatriaDesenvolvimentoPara.setBounds(0, 160, 864, 43);
		getContentPane().add(lblMatriaDesenvolvimentoPara);

	}

}
