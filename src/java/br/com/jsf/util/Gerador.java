/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.util;

/**
 *
 * @author Aluno
 */
public class Gerador {

    public static String gerarCpf() {
        //999.999.999-99        
        return gerarNumero(3) + "." + gerarNumero(3) + "." +
                gerarNumero(3) + "-" + gerarNumero(2);
    }

    public static String gerarNumero(int quantidade) {
        String numero = "";
        for (int i = 0; i < quantidade; i++) {
            numero += (int) (Math.random() * 10);
        }
        return numero;
    }
    
    public static String gerarTelefoneFixo(){
        return "4" + gerarNumero(3) + "-" + gerarNumero(4);
    }
    
    public static String gerarTelefoneCelular(){
        return "9" + gerarNumero(4) + "-" + gerarNumero(4);
    }
    
    public static String gerarPalavra(int tamanho){
        String[] letras ={"0","1","2","3","4","5","6","7","8",
            "9","a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int tamanhoVetor = letras.length;
        String palavra = "";
        int indice;
        for (int i = 0; i < tamanho; i++) {
            indice = (int)(Math.random() * tamanhoVetor);
            palavra += letras[indice];
        }
        return palavra;
    }
    
    
   

}
