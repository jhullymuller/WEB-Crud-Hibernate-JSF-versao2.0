package br.com.jsf.controle;

import br.com.jsf.dao.ClassificacaoDao;
import br.com.jsf.dao.ClassificacaoDaoImpl;
import br.com.jsf.dao.ClienteDao;
import br.com.jsf.dao.ClienteDaoImpl;
import br.com.jsf.dao.HibernateUtil;
import br.com.jsf.entidade.Classificacao;
import br.com.jsf.entidade.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.hibernate.HibernateException;
import org.hibernate.Session;

@ManagedBean(name = "clienteC")
@ViewScoped
public class ClienteControle {

    private Cliente cliente;
    private Classificacao classificacao;
    private ClienteDao clienteDao;
    private ClassificacaoDao classificacaoDao;
    private Session session;
    private DataModel<Cliente> clientes;
    private boolean mostra_toolbar = true;
    private List<SelectItem> classificacoesCombo;

    @PostConstruct
    public void iniciar() {
        cliente = new Cliente();
        clienteDao = new ClienteDaoImpl();
    }

    public void novo() {
        carregarClassificacaoCombobox();
        mostra_toolbar = !mostra_toolbar;
    }

    public void carregarClassificacaoCombobox() {
        classificacoesCombo = new ArrayList<>();
        try {
            ClassificacaoDao classificacaoDao = new ClassificacaoDaoImpl();
            session = HibernateUtil.abrirSessao();
            List<Classificacao> classificacoes = classificacaoDao.listarTodo(session);
            session.close();
            for (Classificacao clas : classificacoes) {
                classificacoesCombo.add(new SelectItem(clas.getId(), clas.getNome()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar Combobox" + e.getMessage());
        }
    }

    public String salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        try {
            session = HibernateUtil.abrirSessao();
            cliente.setClassificacao(classificacao);
            clienteDao.salvarOuAlterar(cliente, session);
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com Sucesso!", "");
            contexto.addMessage(null, mensagem);
            cliente = new Cliente();
        } catch (HibernateException e) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao salvar!", "");
            contexto.addMessage(null, mensagem);
        } finally {
            session.close();
        }
        return "principal";
    }

    public void pesquisarCliente() {
        try {
            clienteDao = new ClienteDaoImpl();
            session = HibernateUtil.abrirSessao();
            List<Cliente> listClientes = clienteDao.
                    pesquisarPorNome(cliente.getNome(), session);
            clientes = new ListDataModel(listClientes);
        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar por nome " + e.getMessage());
        }
    }
    

    public void carregaCliente() {
        carregarClassificacaoCombobox();
        cliente = clientes.getRowData();
        classificacao = cliente.getClassificacao();
        mostra_toolbar = !mostra_toolbar;
    }

    public void excluir() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        cliente = clientes.getRowData();
        try {
            session = HibernateUtil.abrirSessao();
            clienteDao = new ClienteDaoImpl();
            clienteDao.excluir(cliente, session);
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Excluído com Sucesso!", "");
            contexto.addMessage(null, mensagem);
            clientes = null;
            cliente = new Cliente();

        } catch (HibernateException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
        } finally {
            session.close();
        }
    }

//    getters e setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DataModel getClientes() {
        return clientes;
    }

    public Classificacao getClassificacao() {
        if(classificacao == null){
            classificacao = new Classificacao();
        }
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public List<SelectItem> getClassificacoesCombo() {
        return classificacoesCombo;
    }

    public void setClassificacoesCombo(List<SelectItem> classificacoesCombo) {
        this.classificacoesCombo = classificacoesCombo;
    }
    
    public boolean isMostra_toolbar() {
        return mostra_toolbar;
    }

    public void setMostra_toolbar(boolean mostra_toolbar) {
        this.mostra_toolbar = mostra_toolbar;
    }

}
