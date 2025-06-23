package model;

import java.util.List;

public class MembrosProjeto {
    
    private int idProjeto;
    private List<Usuario> membros;

    public MembrosProjeto(int idProjeto, List<Usuario> membros) {
        this.idProjeto = idProjeto;
        this.membros = membros;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }

    public void addMembro(Usuario membro) {
        this.membros.add(membro);
    }

}
