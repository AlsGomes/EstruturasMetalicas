package br.com.als.classes.anexos.anexog;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class ParametrosMomentoFletor {

    float mr;
    float mcr;
    float esbeltez;
    float esbetezPlastificacao;
    float esbetezInicioEscoamento;

    public float getMr(TipoSecaoEEixoFlexao tipoSecaoEEixoFlexao, EstadoLimiteAplicavel estadoLimiteAplicavel, Aco aco, PerfilModel perfilCalculo,
                       ModuloElasticidadeAco moduloElasticidadeAco) {


        return mr;
    }

    public float getMcr() {
        return mcr;
    }

    public float getEsbeltez() {
        return esbeltez;
    }

    public float getEsbetezPlastificacao() {
        return esbetezPlastificacao;
    }

    public float getEsbetezInicioEscoamento() {
        return esbetezInicioEscoamento;
    }
}
