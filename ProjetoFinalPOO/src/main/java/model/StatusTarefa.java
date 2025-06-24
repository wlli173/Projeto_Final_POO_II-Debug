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
    
    public static StatusTarefa fromDescricao(String descricao) {
        
        for (StatusTarefa status : StatusTarefa.values()) {
            if (status.getDescricao().equalsIgnoreCase(descricao.trim())) {
                return status;
            }
        }
        
        throw new IllegalArgumentException("Status inválido: " + descricao);
    }

}
