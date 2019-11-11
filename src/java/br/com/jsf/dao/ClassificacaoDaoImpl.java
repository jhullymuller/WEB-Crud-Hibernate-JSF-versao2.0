/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Classificacao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
public class ClassificacaoDaoImpl extends BaseDaoImpl<Classificacao, Long> 
                               implements ClassificacaoDao, Serializable{

    @Override
    public Classificacao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        Classificacao classificacao = (Classificacao) sessao.get(Classificacao.class, id);
        return classificacao;
    }

    @Override
    public List listarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Classificacao");
        List<Classificacao> classificacoes = consulta.list();
        return classificacoes;
    }

    @Override
    public List<Classificacao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Classificacao where nome like :nome");
        consulta.setParameter("nome", "%" + nome + "%");
        List<Classificacao> classificacoes = consulta.list();
        return classificacoes;
    }
    
}
