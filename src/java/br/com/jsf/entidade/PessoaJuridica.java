/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author lais.santana
 */
@Entity
@Table(name = "pessoaJuridica")
@PrimaryKeyJoinColumn(name = "idPessoa")
public class PessoaJuridica extends Pessoa {

    @Column(nullable = false, unique = true)
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String nome, String telefone, String email, String cnpj) {
        super(id, nome, telefone, email);
        this.cnpj = cnpj;
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    
    
    
    
    

}
