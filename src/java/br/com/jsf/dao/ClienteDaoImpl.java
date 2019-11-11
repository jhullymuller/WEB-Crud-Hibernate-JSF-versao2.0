/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> 
                               implements ClienteDao, Serializable{

    @Override
    public Cliente pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Cliente)sessao.get(Cliente.class, id);
    }

    @Override
    public List listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente");
        return consulta.list();
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Cliente where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.list();
    }
    
}
