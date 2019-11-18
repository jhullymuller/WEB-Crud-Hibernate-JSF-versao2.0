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
        Query consulta = sessao.createQuery("from Pessoa p join fetch p.enderecos where p.id = :id");
        return consulta.list();
    }

    @Override
    public PessoaJuridica pesquisaPorIdcomEndereco(Long id, Session sessao) throws HibernateException {
     Query consulta = sessao.createQuery("from Pessoa p join p.enderecos where p.id = :id");
        return (PessoaJuridica) consulta.uniqueResult();
    }

    @Override
    public List<PessoaJuridica> pesquisarPessoaPornome(String nome, Session session) throws HibernateException {
        Query consulta = session.createQuery("Select distinct (p)from Pessoa p join fetch p.enderecos where p.nome like :nome");
        consulta.setParameter("nome","%"+ nome + "%");
        return consulta.list();
    }

    @Override
    public PessoaJuridica pesquisaCNPJ(String cnpj, Session session) throws HibernateException {
        Query consulta = session.createQuery("from Pessoa p where p.cnpj = :cnpj");
        consulta.setParameter("cnpj", cnpj);
        return (PessoaJuridica) consulta.uniqueResult();
               
    }
    
    
}
