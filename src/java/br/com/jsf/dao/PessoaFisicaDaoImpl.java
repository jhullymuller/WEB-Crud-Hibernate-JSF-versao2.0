/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author lais.santana
 */
public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long> implements PessoaFisicaDao, Serializable{

    @Override
    public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
     return (PessoaFisica) sessao.get(PessoaFisica.class, id);
    }

    @Override
    public List listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaFisica p join fetch p.enderecos");
        return consulta.list();
    }

    @Override
    public PessoaFisica pesquisarPorIDcomEndereco(Long id, Session session) throws HibernateException {
        
        Query consulta = session.createQuery("from Pessoa p join fetch p.enderecos where p.id = :id");
        consulta.setParameter("id",id );
        return (PessoaFisica) consulta.uniqueResult();
    }

    @Override
    public List<PessoaFisica> pesquisarPessoaPornome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Pessoa p join fetch p.enderecos where p.nome like :nome");
        consulta.setParameter("nome","%"+ nome +"%");
        return consulta.list();
    }

  
   

  
   
    
    
}
