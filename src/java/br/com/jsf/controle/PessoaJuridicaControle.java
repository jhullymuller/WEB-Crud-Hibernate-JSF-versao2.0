/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.controle;

import br.com.jsf.dao.HibernateUtil;
import br.com.jsf.dao.PessoaJuridicaDao;
import br.com.jsf.dao.PessoaJuridicaDaoImpl;
import br.com.jsf.entidade.Endereco;
import br.com.jsf.entidade.PessoaJuridica;
import br.com.jsf.entidade.PessoaJuridica;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Aluno
 */
@ManagedBean(name = "pessoaJuridicaC")
@ViewScoped
public class PessoaJuridicaControle {

    private boolean mostra_toolbar = true;
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session sessao;
    private Endereco endereco;
    private DataModel<PessoaJuridica> modelPessoasJuridicas;
    private List<Endereco> enderecos;

    public void pesquisarPessoaJuridica() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
        List<PessoaJuridica> pessoas;
        try {
            sessao = HibernateUtil.abrirSessao();
            pessoas = pessoaJuridicaDao.pesquisarPessoaPornome(pessoaJuridica.getNome(), sessao);
            modelPessoasJuridicas = new ListDataModel<>(pessoas);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar pf por nome " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;
        enderecos = new ArrayList<>();
        pessoaJuridica = new PessoaJuridica();
    }
    
    public void carregarPessoaJuridica() {
         pessoaJuridica = modelPessoasJuridicas.getRowData();
         mostra_toolbar = !mostra_toolbar;
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
        try {
            sessao = HibernateUtil.abrirSessao();
            enderecos = pessoaJuridica.getEnderecos();
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar pf por nome " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void excluir(){
         FacesContext contexto = FacesContext.getCurrentInstance();
        pessoaJuridica = modelPessoasJuridicas.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
            pessoaJuridicaDao.excluir(pessoaJuridica, sessao);
            sessao.close();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluído ao Salvar!", "");
            contexto.addMessage(null, mensagem);
        } catch (HibernateException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        }
        pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("");
        pesquisarPessoaJuridica();
    }
    
    public void excluirEndereco(){
        enderecos.remove(endereco);
        endereco= new Endereco();
}
    
    public void salvar(){
          FacesContext contexto = FacesContext.getCurrentInstance();
          sessao = HibernateUtil.abrirSessao();
          try {
            pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
            pessoaJuridica.setEnderecos(enderecos);
            for(Endereco endereco1 : enderecos){
                endereco1.setPessoa(pessoaJuridica);
            }
            pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
            
             FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com Sucesso!", "");
            contexto.addMessage(null, mensagem);
            pessoaJuridica = null;
        } catch (Exception e) {
              System.out.println("erro ao salvar "+ e.getMessage());
             FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erro ao Salvar!", "");
            contexto.addMessage(null, mensagem);
        }finally{
              sessao.close();
          }
    }
    public void addEndereco(){
       if(enderecos == null){
           enderecos = new ArrayList<>();
       } 
       enderecos.add(endereco);
       
       endereco = new Endereco();
    }

//getter e setter
    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public PessoaJuridica getPessoaJuridica() {
        if(pessoaJuridica== null){
            pessoaJuridica = new PessoaJuridica();
        }
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public DataModel<PessoaJuridica> getModelPessoasJuridicas() {
        return modelPessoasJuridicas;
    }

    public Endereco getEndereco() {
        if(endereco == null){
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
  
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

}
