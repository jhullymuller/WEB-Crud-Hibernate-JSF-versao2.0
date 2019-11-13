/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.dao;

import br.com.jsf.entidade.Endereco;
import br.com.jsf.entidade.PessoaJuridica;
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
public class PessoaJuridicaDaoImplTest {
    
    private PessoaJuridica pessoaJuridica;
    private Session sessao;
    private PessoaJuridicaDao pessoaJuridicaDao;
    
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

    @Test
      public void testSalvar() {
        System.out.println("teste Salvar pessoa Juridica");
        List<Endereco> enderecos = new ArrayList<Endereco>();
        pessoaJuridica = new PessoaJuridica(null, Gerador.gerarPalavra(10), "(48)99999-9999", Gerador.gerarPalavra(10) + "@email.com",
                Gerador.gerarNumero(3) + "."+ Gerador.gerarNumero(3) +".999-99");
        Endereco endereco1 = new Endereco(null, "rua teste", "99999-999", "9", "bairro teste", "cidade teste", "estado teste", "complemento teste", "observacao teste");
        Endereco endereco2 = new Endereco(null, "rua teste 2", "88888-888", "8", "bairro teste 2", "cidade teste 2", "estado teste 2", "complemento teste 2", "observacao teste 2");
        
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        pessoaJuridica.setEnderecos(enderecos);
        
        for(Endereco endereco : enderecos){
            endereco.setPessoa(pessoaJuridica);
        }
        sessao = HibernateUtil.abrirSessao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        assertNotNull(pessoaJuridica.getId());
    }

    @Test
    public void testListarTodo() {
        System.out.println("listarTodo");
        ultimaPessoaFisicaBancoDados();
       sessao = HibernateUtil.abrirSessao();
       List<PessoaJuridica> pessoas = pessoaJuridicaDao.listarTodo(sessao);
       sessao.close();
        assertTrue(!pessoas.isEmpty());
    }
@Test
    private PessoaJuridica ultimaPessoaFisicaBancoDados() {
       sessao = HibernateUtil.abrirSessao();
       Query consulta  = sessao.createQuery("select max(id)from PessoaFisica");
       Long id = (Long)consulta.uniqueResult();
       pessoaJuridica = pessoaJuridicaDao.pesquisaPorIdcomEndereco(id, sessao);
       sessao.close();
       if(id ==null){
           testSalvar();
       }else {
           sessao = HibernateUtil.abrirSessao();
           pessoaJuridica = pessoaJuridicaDao.pesquisaPorIdcomEndereco(id, sessao);
           sessao.close();
       } return pessoaJuridica;
    }
    @Test
     public void testExcluir(){
        System.out.println("Excluir pessoa Fisica");
        ultimaPessoaFisicaBancoDados();
        sessao = HibernateUtil.abrirSessao();
        pessoaJuridicaDao.excluir(pessoaJuridica, sessao);
        PessoaJuridica pJ = pessoaJuridicaDao.pesquisaPorIdcomEndereco(pessoaJuridica.getId(), sessao);
        sessao.close();
        assertNull(pJ.getId());
        assertNull(pJ.getEnderecos().get(0).getId());
        
    } 
     @Test
    public void testPesquisarPorNome() {
       System.out.println("Excluir pessoa Juridica");
       ultimaPessoaJuridicaBancoDados();
       sessao = HibernateUtil.abrirSessao();
       List<PessoaJuridica> pessoas = pessoaJuridicaDao.pesquisarPessoaPornome(pessoaJuridica.getNome(), sessao);
       sessao.close();
        assertTrue(!pessoas.isEmpty());
    }

    private void ultimaPessoaJuridicaBancoDados() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
