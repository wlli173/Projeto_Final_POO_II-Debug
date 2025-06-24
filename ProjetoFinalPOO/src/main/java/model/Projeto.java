package model;

import java.util.ArrayList;
import java.util.List;

import DAO.ProjetoDAO;
import DAO.UsuariosDAO;

public class Projeto {
    
    private int idProjeto;
    private String nome;
    private String descricao;
    private String dataCriacao;
    private String dataFimPrevista;
    private int idLider;
    private List<Tarefa> tarefas = new ArrayList<>(); //Composite

    public Projeto(int idProjeto, String nome, String descricao, String dataCriacao, String dataFimPrevista, int idLider) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFimPrevista = dataFimPrevista;
        this.idLider = idLider;
    }

    public Projeto(String nome, String descricao, String dataCriacao, String dataFimPrevista, int idLider) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataFimPrevista = dataFimPrevista;
        this.idLider = idLider;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(String dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public int getIdLider() {
        return idLider;
    }

    public Usuario getLider() {
        
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        Usuario lider = usuariosDAO.buscarUsuarioPorId(idLider);

        return lider;

    }

    public void setIdLider(int idLider) {
        this.idLider = idLider;
    }

    public void setLider(Usuario lider) {
        this.idLider = lider.getIdUsuario();
    }

    public int porcentagemConcluida() {
        
        ProjetoDAO projetoDAO = new ProjetoDAO();

        int totalTarefas = projetoDAO.quantidadeTarefas(this.idProjeto);
        int tarefasConcluidas = projetoDAO.quantidadeTarefasConcluidas(this.idProjeto);

        if (totalTarefas == 0) {
            return 0; // Evita divisão por zero
        }

        return (tarefasConcluidas * 100) / totalTarefas;

    }
    
    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

    public void carregarTarefas(ProjetoDAO projetoDAO) {
        this.tarefas = projetoDAO.buscarTarefasDoProjeto(this.idProjeto);
    }

    public void carregarTarefasDoUsuario(int idUsuario, ProjetoDAO projetoDAO) {
        this.tarefas = projetoDAO.buscarTarefasDoUsuario(idUsuario, this.idProjeto);
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

}
