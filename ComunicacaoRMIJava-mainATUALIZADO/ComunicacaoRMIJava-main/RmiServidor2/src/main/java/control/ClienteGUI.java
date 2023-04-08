package control;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.*;

public class ClienteGUI extends JFrame {
    private InterfaceClassificadoVeiculos serv;
    private JLabel nomeLabel, marcaLabel, anoLabel, valorLabel;
    private JTextField nomeTextField, marcaTextField, anoTextField, valorTextField,valorTextFieldPesquisa;
    private JTextArea listaVeiculoTextArea,listaVeiculoTextAreaPesquisa;
    private JButton cadastroButton;
    private JButton pesquisaButton;
    
    
    public ClienteGUI() throws RemoteException, NotBoundException, MalformedURLException {
        super("Cliente");
        
        String url = "rmi://localhost/RMIclassificados";
        serv = (InterfaceClassificadoVeiculos) Naming.lookup(url);
        
        JFrame janela01 = new JFrame("Menu");
		janela01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton cadastrarItem = new JButton("Cadastrar");
		JButton pesquisarItem = new JButton("Consultar");
		JButton excluirItem = new JButton("Deletar");
		JPanel panel = new JPanel();
		
		cadastrarItem.addActionListener(new CadastrarMenuItemListener());
		pesquisarItem.addActionListener(new PesquisarMenuItemListener());
		excluirItem.addActionListener(new ExcluirMenuItemListener());		
		
		panel.add(cadastrarItem);
		panel.add(pesquisarItem);
		panel.add(excluirItem);
		janela01.getContentPane().add(panel);
		janela01.setLocationRelativeTo(null); // Centraliza na tela
		janela01.setSize(300,200);
		janela01.setVisible(true);	  
       
    }
    
    private class CadastrarMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
             JFrame janelaCadastro = new JFrame("Cadastro");
        	 nomeLabel = new JLabel("Nome do Cliente:");
             nomeTextField = new JTextField(20);
             marcaLabel = new JLabel("Marca do Veículo:");
             marcaTextField = new JTextField(20);
             anoLabel = new JLabel("Ano do Veículo:");
             anoTextField = new JTextField(4);
             valorLabel = new JLabel("Valor do Veículo:");
             valorTextField = new JTextField(10);
             listaVeiculoTextArea = new JTextArea(10, 30);
             listaVeiculoTextArea.setEditable(false);
             cadastroButton = new JButton("Enviar");
             cadastroButton.addActionListener(new cadastroButtonListener());
             
             JPanel painel = new JPanel();
             painel.setLayout(new GridBagLayout());
             
             GridBagConstraints c = new GridBagConstraints();
             c.gridx = 0;
             c.gridy = 0;
             c.insets = new Insets(5, 5, 5, 5); // alteração aqui
             painel.add(nomeLabel, c);
             c.gridx = 1;
             painel.add(nomeTextField, c);
             c.gridx = 0;
             c.gridy = 1;
             painel.add(marcaLabel, c);
             c.gridx = 1;
             painel.add(marcaTextField, c);
             c.gridx = 0;
             c.gridy = 2;
             painel.add(anoLabel, c);
             c.gridx = 1;
             painel.add(anoTextField, c);
             c.gridx = 0;
             c.gridy = 3;
             painel.add(valorLabel, c);
             c.gridx = 1;
             painel.add(valorTextField, c);
             c.gridx = 0;
             c.gridy = 4;
             c.gridwidth = 2;
             painel.add(cadastroButton, c);
             c.gridx = 0;
             c.gridy = 5;
             c.gridwidth = 2;
             painel.add(new JScrollPane(listaVeiculoTextArea), c);
             
             janelaCadastro.add(painel);
             
             //janelaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             janelaCadastro.setSize(400, 400);
             janelaCadastro.setVisible(true);
         }
         
         private class cadastroButtonListener implements ActionListener {
             public void actionPerformed(ActionEvent e) {
                 String nome = nomeTextField.getText();
                 String marca = marcaTextField.getText();
                 int ano = Integer.parseInt(anoTextField.getText());
                 double valor = Double.parseDouble(valorTextField.getText());
                 try {
     				serv.criarVeiculo(nome, marca, ano, valor);
     				//nomeTextField.setText("");
     	            //marcaTextField.setText("");
     	            //anoTextField.setText("");
     	            //valorTextField.setText("");
     		        listaVeiculoTextArea.setText(serv.listaVeiculo());
     		        
     			} catch (RemoteException e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     			}
                
             }
        	
        }
    }
    
    private class ExcluirMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    
    private class PesquisarMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	 JFrame janelaConsulta = new JFrame("Consulta");
        	 valorLabel = new JLabel("Ano do carro:");
             valorTextFieldPesquisa = new JTextField(20);
             listaVeiculoTextAreaPesquisa = new JTextArea(10, 30);
             listaVeiculoTextAreaPesquisa.setEditable(false);
             pesquisaButton = new JButton("Pesquisar");
             pesquisaButton.addActionListener(new EnviarButtonListener());
             
             JPanel painel = new JPanel();
             painel.setLayout(new GridBagLayout());

             GridBagConstraints c = new GridBagConstraints();
             c.gridx = 0;
             c.gridy = 0;
             c.insets = new Insets(5, 5, 5, 5);
             painel.add(valorLabel, c);
             c.gridx = 1;
             painel.add(valorTextFieldPesquisa, c);
             c.gridx = 0;
             c.gridy = 1;
             c.gridwidth = 2;
             c.anchor = GridBagConstraints.CENTER;
             painel.add(pesquisaButton, c);
             c.gridx = 0;
             c.gridy = 2;
             c.gridwidth = 4;
             painel.add(new JScrollPane(listaVeiculoTextAreaPesquisa), c);
             
             janelaConsulta.add(painel);
             
             janelaConsulta.setSize(400, 400);
             janelaConsulta.setVisible(true);
         }
         
         private class EnviarButtonListener implements ActionListener {
             public void actionPerformed(ActionEvent e) {
            	 int ano = Integer.parseInt(valorTextFieldPesquisa.getText());
                
                 try {
                	 
                	 listaVeiculoTextAreaPesquisa.setText(serv.search2Ano(ano));
     				 //valorTextField.setText("");
    
     			} catch (RemoteException e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     			}
                
             }
        	
        }
        
    }

}

