/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.PessoaJuridica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author lais.santana
 */
public interface PessoaJuridicaDao extends BaseDao<PessoaJuridica, Long> {

    PessoaJuridica pesquisaPorIdcomEndereco(Long id, Session session) throws HibernateException;

    List<PessoaJuridica> pesquisarPessoaPornome(String nome, Session session) throws HibernateException;

    PessoaJuridica pesquisaCNPJ(String cnpj, Session session) throws HibernateException;

}
