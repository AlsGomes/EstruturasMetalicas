package br.com.als.classes.anexos.anexof;

public enum Grupo {
    GRUPO1(""),
    GRUPO2(""),
    GRUPO3(""),
    GRUPO4("Abas de cantoneiras simples ou múltiplas providas de chapas de travejamento"),
    GRUPO5(""),
    GRUPO6("");

    private String descricao;

    Grupo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
