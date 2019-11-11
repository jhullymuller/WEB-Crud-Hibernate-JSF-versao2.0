/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author lais.santana
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable{

    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
     return (PessoaJuridica) sessao.get(PessoaJuridica.class, id);
    }

    @Override
    public List listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaJuridica");
        return consulta.list();
    }
    
    
}
