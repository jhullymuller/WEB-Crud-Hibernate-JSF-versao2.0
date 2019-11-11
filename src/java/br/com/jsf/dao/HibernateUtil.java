/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Classificacao;
import br.com.jsf.entidade.Cliente;
import br.com.jsf.entidade.Endereco;
import br.com.jsf.entidade.Fornecedor;
import br.com.jsf.entidade.Pessoa;
import br.com.jsf.entidade.PessoaFisica;
import br.com.jsf.entidade.Produto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Aluno
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    // Designer Pattern SINGLETON
    static {
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Produto.class);
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(Fornecedor.class);
            cfg.addAnnotatedClass(Classificacao.class);
            cfg.addAnnotatedClass(Pessoa.class);
            cfg.addAnnotatedClass(PessoaFisica.class);
            cfg.addAnnotatedClass(Endereco.class);

            cfg.configure("/br/com/jsf/dao/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirSessao() {
        return sessionFactory.openSession();
    }
}
