/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jsf.controle;

import br.com.jsf.dao.HibernateUtil;
import br.com.jsf.dao.PessoaFisicaDao;
import br.com.jsf.dao.PessoaFisicaDaoImpl;
import br.com.jsf.entidade.PessoaFisica;
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
@ManagedBean(name = "pessoaFisicaC")
@ViewScoped
public class PessoaFisicaControle {

    private boolean mostra_toolbar = true;
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session sessao;
    private DataModel<PessoaFisica> modelPessoasFisicas;

    public void pesquisarPessoaFisica() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
        List<PessoaFisica> pessoas;
        try {
            sessao = HibernateUtil.abrirSessao();
            pessoas = pessoaFisicaDao.pesquisarPessoaPornome(pessoaFisica.getNome(), sessao);
            modelPessoasFisicas = new ListDataModel<>(pessoas);
        } catch (Exception e) {
            System.out.println("Erro ao pesquisar pf por nome");
        } finally {
            sessao.close();
        }
    }

    public void novo() {
        mostra_toolbar = !mostra_toolbar;
    }

    public void carregarPessoaFisica() {
        pessoaFisica = modelPessoasFisicas.getRowData();
         mostra_toolbar = !mostra_toolbar;
    }
    
    public void excluir(){
         FacesContext contexto = FacesContext.getCurrentInstance();
        pessoaFisica = modelPessoasFisicas.getRowData();
        sessao = HibernateUtil.abrirSessao();
        try {
            
            pessoaFisicaDao = PessoaFisicaDaoImpl();
            pessoaFisicaDao.excluir(pessoaFisica, sessao);
            sessao.close();
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluído ao Salvar!", "");
            contexto.addMessage(null, mensagem);
        } catch (HibernateException e) {
            System.err.println("Erro ao excluir " + e.getMessage());
        }
    }
    
    public void salvar(){
          FacesContext contexto = FacesContext.getCurrentInstance();
          sessao = HibernateUtil.abrirSessao();
          try {
            pessoaFisicaDao = new PessoaFisicaDaoImpl();
            pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
            
             FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com Sucesso!", "");
            contexto.addMessage(null, mensagem);
            pessoaFisica = null;
        } catch (Exception e) {
              System.out.println("erro ao salvar "+ e.getMessage());
             FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Erro ao Salvar!", "");
            contexto.addMessage(null, mensagem);
        }finally{
              sessao.close();
          }
    }

//getter e setter
    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public DataModel<PessoaFisica> getModelPessoasFisicas() {
        return modelPessoasFisicas;
    }

    private PessoaFisicaDao PessoaFisicaDaoImpl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
