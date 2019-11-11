/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Fornecedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class FornecedorDaoImpl extends BaseDaoImpl<Fornecedor, Long> 
                               implements FornecedorDao, Serializable{

    @Override
    public Fornecedor pesquisarPorId(Long id, Session sessao) throws HibernateException {
        Fornecedor fornecedor = (Fornecedor) sessao.get(Fornecedor.class, id);
        return fornecedor;
    }

    @Override
    public List listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Fornecedor");
        List<Fornecedor> fornecedores = consulta.list();
        return fornecedores;
    }

    @Override
    public List<Fornecedor> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Fornecedor where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        List<Fornecedor> fornecedores = consulta.list();
        return fornecedores;
    }
    
}
