/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.rmi.RemoteException;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/**
 *
 * @author flavi
 */
public class ImpClassificados extends UnicastRemoteObject implements InterfaceClassificadoVeiculos {
    private static final long serialVersionUID = 1L;
    //Veiculo veiculo = new Veiculo(nome,marca,ano,Valor);
    ArrayList<Veiculo> carros = new ArrayList<>();   

    public ImpClassificados() throws RemoteException {
        super();
        
    }
    
    public boolean criarVeiculo(String nome,String marca,int ano,double Valor) throws RemoteException {
    	Veiculo veiculo = new Veiculo(nome,marca,ano,Valor);
    	guardarVeiculo(veiculo);
    	return true;
	}
    
    private void guardarVeiculo(Veiculo aThis) {
        carros.add(aThis);   
   }
    
    public String search2Ano(int ano){
		// TODO Auto-generated method stub
		 int total = 0;
		 StringBuilder carroList = new StringBuilder("lista de carros:");
		 
         for(Veiculo carro:carros){
	       	 if(carro.getAno() == ano) {
	    		 total++;
	    		 String info = "Cliente: "+carro.getNome()+ "\nMarca: "+carro.getMarca()+"\nAno: "+Integer.toString(carro.getAno())+"\nValor: R$"+Double.toString(carro.getValor())+"\n-------------";
	             carroList.append("\n").append(info);                    		             		
	       	 }       
         }
         carroList.append("\ntotal encontrado: "+Integer.toString(total));
         return carroList.toString();
    }
    

	@Override
	public String listaVeiculo() throws RemoteException {
		// TODO Auto-generated method stub
		int total = 0;
		StringBuilder carroList = new StringBuilder("Histórico: ");
		 
        for(Veiculo carro:carros){
	         total++;     	 
	   		 String info = "Cliente: "+carro.getNome()+ "\nMarca: "+carro.getMarca()+"\nAno: "+Integer.toString(carro.getAno())+"\nValor: R$"+Double.toString(carro.getValor())+"\n-------------";
	         carroList.append("\n").append(info);
		                    		             		
        }
        carroList.append("\ntotal encontrado: "+Integer.toString(total));
        return carroList.toString();
	}
}

    
    


	
   
 
	
