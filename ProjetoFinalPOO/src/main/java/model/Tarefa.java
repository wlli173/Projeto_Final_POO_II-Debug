package model;

public class Tarefa {
    
    private int idTarefa;
    private String titulo;
    private String descricao;
    private String dataFimPrevisto;
    private StatusTarefa status;
    private int idProjeto;

    public Tarefa(int idTarefa, String titulo, String descricao, String dataFimPrevisto, StatusTarefa status, int idProjeto) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFimPrevisto = dataFimPrevisto;
        this.status = status;
        this.idProjeto = idProjeto;
    }

    public Tarefa(String titulo, String descricao, String dataFimPrevisto, StatusTarefa status, int idProjeto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataFimPrevisto = dataFimPrevisto;
        this.status = status;
        this.idProjeto = idProjeto;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
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
