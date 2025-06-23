package model;

public class Comentario {

    private int idComentario;
    private String conteudo;
    private String dataComentario;
    private int idTarefa;
    private int idUsuario;

    public Comentario(int idComentario, String conteudo, String dataComentario, int idTarefa, int idUsuario) {
        this.idComentario = idComentario;
        this.conteudo = conteudo;
        this.dataComentario = dataComentario;
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
    }

    public Comentario(String conteudo, String dataComentario, int idTarefa, int idUsuario) {
        this.conteudo = conteudo;
        this.dataComentario = dataComentario;
        this.idTarefa = idTarefa;
        this.idUsuario = idUsuario;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(String dataComentario) {
        this.dataComentario = dataComentario;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
