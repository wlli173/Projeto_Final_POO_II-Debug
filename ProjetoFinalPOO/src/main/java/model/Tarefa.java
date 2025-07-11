package model;

import DAO.UsuariosDAO;

public class Tarefa {
    
    private int idTarefa;
    private int idUsuarioResponsavel;
    private String titulo;
    private String descricao;
    private String dataFimPrevisto;
    private StatusTarefa status;
    private int idProjeto;

    public Tarefa(int idTarefa, String titulo, String descricao, String dataFimPrevisto, StatusTarefa status, int idProjeto, int idUsuarioResponsavel) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFimPrevisto = dataFimPrevisto;
        this.status = status;
        this.idProjeto = idProjeto;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public Tarefa(String titulo, String descricao, String dataFimPrevisto, StatusTarefa status, int idProjeto, int idUsuarioResponsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFimPrevisto = dataFimPrevisto;
        this.status = status;
        this.idProjeto = idProjeto;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }
    
    public Usuario getUsuarioResponsavel(){
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        return usuariosDAO.buscarUsuarioPorId(getIdUsuarioResponsavel());
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataFimPrevisto() {
        return dataFimPrevisto;
    }

    public void setDataFimPrevisto(String dataFimPrevisto) {
        this.dataFimPrevisto = dataFimPrevisto;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

}
