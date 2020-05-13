package br.com.als.classes.anexos.anexog;

public enum EstadoLimiteAplicavel {

    FLT("Flambagem Lateral por Torção"),
    FLM("Flambagem Local na Mesa"),
    FLA("Flmabagem Local na Alma");

    String descricao;

    EstadoLimiteAplicavel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
