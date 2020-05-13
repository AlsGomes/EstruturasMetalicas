package br.com.als.classes.anexos.anexog;

public enum TipoSecaoEEixoFlexao {

    TIPO_SECAO_E_EIXO_FLEXAO_I("Seções I e H com dois eixos de simetria e seções U não sujeitas a momento de torção,\n" +
            "fletidas em relação ao eixo de maior momento de inércia"),

    TIPO_SECAO_E_EIXO_FLEXAO_II("Seções I e H com apenas um eixo de simetria situado no plano médio\n" +
            "da alma, fletidas em relação ao eixo de maior momento de inércia (ver Nota 9 )"),

    TIPO_SECAO_E_EIXO_FLEXAO_III("Seções I e H com dois eixos de simetria e seções U fletidas em\n" +
            "relação ao eixo de menor momento de inércia"),

    TIPO_SECAO_E_EIXO_FLEXAO_IV("Seções sólidas retangulares fletidas em relação ao eixo de maior momento de inércia"),

    TIPO_SECAO_E_EIXO_FLEXAO_V("Seções-caixão e tubulares retangulares,\n" +
            "duplamente simétricas, fletidas em relação a um dos eixos de simetria que seja paralelo a dois lados");

    String descricao;

    TipoSecaoEEixoFlexao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
