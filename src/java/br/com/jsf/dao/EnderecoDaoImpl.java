/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Endereco;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author Aluno
 */
public class EnderecoDaoImpl implements EnderecoDao{

   private Transaction transacao;
    @Override
    public void excluir(Endereco entidade, Session sessao) throws HibernateException {
        transacao = sessao.beginTransaction();
        sessao.delete(entidade);
        transacao.commit();
    }

   
}
