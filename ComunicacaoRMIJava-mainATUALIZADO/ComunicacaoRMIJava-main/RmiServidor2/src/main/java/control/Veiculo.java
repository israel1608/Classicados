/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.rmi.RemoteException;



/**
 *
 * @author flavi
 */
public class Veiculo{
    
    private String nome;
    private String marca;
    private int ano;
    private double Valor;
    
    public Veiculo(String nome,String marca,int ano,double Valor) throws RemoteException {
        super();
        this.nome= nome;
        this.marca = marca;
        this.ano = ano;
        this.Valor = Valor;
        
    }
    
    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return Valor;
    }
       
}

     
     
   