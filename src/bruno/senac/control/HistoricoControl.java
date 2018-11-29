package bruno.senac.control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import senac.bruno.dao.VendasDao;
import senac.bruno.model.Vendas;

public class HistoricoControl {
	private JTable table;
	private JTextField tfPesquisar;
	private ClienteControl clientecontrol;
	private VeiculoControl veiculocontrol;
	private VendedorControl vendedorcontrol;
	private VendasControl vendacontrol;
	private Vendas vendas = null;
	private VendasDao vendaDao = null;
	private List<Vendas> listVendas;
	private Date dataSistema = new Date();
	
	
	public HistoricoControl(JTable table, JTextField tfPesquisar) {
		super();
		this.table = table;
		this.tfPesquisar = tfPesquisar;
		vendaDao = new VendasDao();
	}



	public void atualizarHistoricoVendasJTableAction() {
		listVendas = vendaDao.listarRelatorio();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		// limpar tabela e listar novamente
		model.setNumRows(0);
		for (Vendas v : listVendas) {
			model.addRow(new Object[] { 
					//v.getIdVendas(), 
					v.getDataVenda(), 
					v.getVendedor().getNome(), 
					v.getClientes().getNome(),
					//v.getVeiculo().getModelos().getMarcas(),
					v.getVeiculo().getModelos(),
					v.getVeiculo().getCor(),
					v.getVeiculo().getChassis(),
					v.getVeiculo().getValor()
			});
		}
	}
	
		public void relatorioVendasTXT() {
			FileWriter arq;
			SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
			String data = dtf.format(dataSistema);
			try {
				arq = new FileWriter("C:\\Users\\Bruno\\git\\ProjetoDesktop\\ProjetoConcessionaria\\relatorios\\"+ data +".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("-------RELATÓRIO-DE-VENDAS-------%n");
				gravarArq.printf("                      %n");
				for (int i = 0; i < listVendas.size(); i++) {
					gravarArq.printf("");
					gravarArq.printf("                      %n");
					gravarArq.printf("-DATA VENDA: %3s%n"
							+ "-Id da venda: %3s%n"
							+ "-Nome vendedor: %3s%n"
							+ "-Nome cliente: %3s%n"
							+ "-Marca: %3s%n"
							+ "-Modelo: %3s%n"
							+ "-Cor: %3s%n"
							+ "-Chassis: %3s%n"
							+ "-Valor: R$ %3s%n",
							
							listVendas.get(i).getDataVenda(), 
							listVendas.get(i).getIdVendas(),
							listVendas.get(i).getVendedor().getNome(), 
							listVendas.get(i).getClientes().getNome(),
							listVendas.get(i).getVeiculo().getModelos().getMarcas(), 
							listVendas.get(i).getVeiculo().getModelos(), 
							listVendas.get(i).getVeiculo().getCor(), 
							listVendas.get(i).getVeiculo().getChassis(), 
							listVendas.get(i).getVeiculo().getValor());
					gravarArq.printf("------------------------------------%n");
				}
				gravarArq.printf("                      %n");
				gravarArq.printf("----------------------------------%n");
				gravarArq.printf("RELATÓRIO EMITIDO EM "+ data +"%n");
				gravarArq.printf("----------------------------------%n");
				gravarArq.printf("                      %n");
				gravarArq.printf("Versão 1.0%n");

				arq.close();

				JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!" + arq,
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível salvar os arquivos em .txt", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void relatorioVendedorTXT() {
			FileWriter arq;
			SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
			String data = dtf.format(dataSistema);
			try {
				arq = new FileWriter("C:\\Users\\Bruno\\git\\ProjetoDesktop\\ProjetoConcessionaria\\relatorios\\"+ data +".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("---- RELATÓRIO DOS VENDEDORES ----%n");
				gravarArq.printf("                      %n");
				Double totalVendas = 0.0;
				int numeroVendas = 0;
				for (int i = 0; i < listVendas.size(); i++) {
					numeroVendas = listVendas.size();
					
					gravarArq.printf("");
					gravarArq.printf("                      %n");
					gravarArq.printf("-DATA VENDA: %3s%n"
							+ "-Id da venda: %3s%n"
							+ "-Nome vendedor: %3s%n"
							+ "-Nome cliente: %3s%n"
							+ "-Marca: %3s%n"
							+ "-Modelo: %3s%n"
							+ "-Cor: %3s%n"
							+ "-Chassis: %3s%n"
							+ "-Valor: R$ %3s%n",
							
							listVendas.get(i).getDataVenda(), 
							listVendas.get(i).getIdVendas(),
							listVendas.get(i).getVendedor().getNome(), 
							listVendas.get(i).getClientes().getNome(),
							listVendas.get(i).getVeiculo().getModelos().getMarcas(), 
							listVendas.get(i).getVeiculo().getModelos(), 
							listVendas.get(i).getVeiculo().getCor(), 
							listVendas.get(i).getVeiculo().getChassis(), 
							listVendas.get(i).getVeiculo().getValor());
							
					totalVendas = totalVendas + listVendas.get(i).getVeiculo().getValor();
							
					gravarArq.printf("------------------------------------%n");
				}
				gravarArq.printf("Veículos vendidos: "+ numeroVendas + "%n");
				gravarArq.printf("Total das vendas R$ "+ totalVendas);
				gravarArq.printf("                      %n");
				gravarArq.printf("------------------------------------%n");
				gravarArq.printf("RELATÓRIO EMITIDO EM "+ data +"%n");
				gravarArq.printf("------------------------------------%n");
				gravarArq.printf("                      %n");
				gravarArq.printf("Versão 1.0%n");

				arq.close();

				JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso!" + arq,
						"Sucesso", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possível salvar os arquivos em .txt", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void pesquisarNomeCliente(){
			try {
				List<Vendas> listVendas = vendaDao.pesquisarNomeCliente(tfPesquisar.getText());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setNumRows(0);
				for(Vendas v : listVendas){
					model.addRow(new Object[]{
							//v.getIdVendas(), 
							v.getDataVenda(), 
							v.getVendedor().getNome(), 
							v.getClientes().getNome(),
							//v.getVeiculo().getModelos().getMarcas(),
							v.getVeiculo().getModelos(),
							v.getVeiculo().getCor(),
							v.getVeiculo().getChassis(),
							v.getVeiculo().getValor()
					});
				}
			} catch (Exception e) {
				System.out.println("Erro ao pesquisar pelo nome do cliente: "+e.getMessage());
			}
		}
}
