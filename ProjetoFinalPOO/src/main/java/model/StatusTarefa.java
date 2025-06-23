package model;

public enum StatusTarefa {
    
    ATRASADA("Atrasada"),
    EM_PROGRESSO("Em Progresso"),
    CONCLUIDA("Concluída");

    private final String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
