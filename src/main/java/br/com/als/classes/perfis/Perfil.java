package br.com.als.classes.perfis;

public enum Perfil {
    L("Perfil L (Cantoneira)"),
    T("Perfil T"),

    W("Perfil W"),
    I("Perfil I"),
    H("Perfil H"),

    U("Perfil U");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}