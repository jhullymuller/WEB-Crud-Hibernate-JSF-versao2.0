/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import static br.com.jsf.entidade.Classificacao_.id;
import br.com.jsf.entidade.Endereco;
import br.com.jsf.entidade.PessoaFisica;
import br.com.jsf.util.Gerador;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lais.santana
 */
public class PessoaFisicaDaoImplTest {
    
    private PessoaFisica pessoaFisica;
    private Session sessao;
    private PessoaFisicaDao pessoaFisicaDao;
    
    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }

   @Test
    public void testSalvar() {
        System.out.println("teste Salvar pessoa fisica");
        List<Endereco> enderecos = new ArrayList<Endereco>();
        pessoaFisica = new PessoaFisica(null, Gerador.gerarPalavra(10), "(48)99999-9999", Gerador.gerarPalavra(10) + "@email.com",
                Gerador.gerarNumero(3) + "."+ Gerador.gerarNumero(3) +".999-99", "99999999");
        Endereco endereco1 = new Endereco(null, "rua teste", "99999-999", "9", "bairro teste", "cidade teste", "estado teste", "complemento teste", "observacao teste");
        Endereco endereco2 = new Endereco(null, "rua teste 2", "88888-888", "8", "bairro teste 2", "cidade teste 2", "estado teste 2", "complemento teste 2", "observacao teste 2");
        
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        pessoaFisica.setEnderecos(enderecos);
        
        for(Endereco endereco : enderecos){
            endereco.setPessoa(pessoaFisica);
        }
        sessao = HibernateUtil.abrirSessao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        assertNotNull(pessoaFisica.getId());
    }
     @Test
    public void testAlterar(){
        System.out.println("alterar pessoa fisica");
        ultimaPessoaFisicaBancoDados();
        pessoaFisica.setEmail(Gerador.gerarPalavra(5)+"@email_alterado.com");
        Endereco endereco2 = new Endereco(null,"Rua"+ Gerador.gerarPalavra(6),"88999-999","21","Centro","BananalAD","Bananeira","casa","px a banana");
        pessoaFisica.getEnderecos().add(endereco2);
        Session session = HibernateUtil.abrirSessao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        session.close();
    } 
    
    
    @Test
    public void testListarTodo() {
       System.out.println("Excluir pessoa Fisica");
       ultimaPessoaFisicaBancoDados();
       sessao = HibernateUtil.abrirSessao();
       List<PessoaFisica> pessoas = pessoaFisicaDao.listarTodo(sessao);
       sessao.close();
        assertTrue(!pessoas.isEmpty());
    }

    private PessoaFisica ultimaPessoaFisicaBancoDados() {
       sessao = HibernateUtil.abrirSessao();
       Query consulta  = sessao.createQuery("select max(id)from PessoaFisica");
       Long id = (Long)consulta.uniqueResult();
       pessoaFisica = pessoaFisicaDao.pesquisarPorIDcomEndereco(id, sessao);
       sessao.close();
       if(id ==null){
           testSalvar();
       }else {
           sessao = HibernateUtil.abrirSessao();
           pessoaFisica = pessoaFisicaDao.pesquisarPorIDcomEndereco(id, sessao);
           sessao.close();
       } return pessoaFisica;
    }
     @Test
    public void testExcluir(){
        System.out.println("Excluir pessoa Fisica");
        ultimaPessoaFisicaBancoDados();
        sessao = HibernateUtil.abrirSessao();
        pessoaFisicaDao.excluir(pessoaFisica, sessao);
        PessoaFisica pF = pessoaFisicaDao.pesquisarPorIDcomEndereco(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNull(pF.getId());
        assertNull(pF.getEnderecos().get(0).getId());
        
    }
    
        @Test
    public void testPesquisarPorNome() {
       System.out.println("Excluir pessoa Fisica");
       ultimaPessoaFisicaBancoDados();
       sessao = HibernateUtil.abrirSessao();
       List<PessoaFisica> pessoas = pessoaFisicaDao.pesquisarPessoaPornome(pessoaFisica.getNome(), sessao);
       sessao.close();
        assertTrue(!pessoas.isEmpty());
    }
}
