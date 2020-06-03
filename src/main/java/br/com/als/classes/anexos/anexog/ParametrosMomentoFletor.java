package br.com.als.classes.anexos.anexog;

import br.com.als.classes.acos.model.AcoModel;
import br.com.als.classes.perfis.PerfilModel;

public class ParametrosMomentoFletor {

    float mr;
    float mcr;
    float esbeltez;
    float esbetezPlastificacao;
    float esbetezInicioEscoamento;

    public float getMr(TipoSecaoEEixoFlexao tipoSecaoEEixoFlexao, EstadoLimiteAplicavel estadoLimiteAplicavel, AcoModel aco, PerfilModel perfilCalculo) {
        switch (tipoSecaoEEixoFlexao) {
            case TIPO_SECAO_E_EIXO_FLEXAO_I:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                    case FLM:
                        this.mr = (float) (aco.getTensaoEscoamento() - (0.3 * aco.getTensaoEscoamento())) * perfilCalculo.getModuloResistenciaWx();
                        break;
                    case FLA:
                        this.mr = aco.getTensaoEscoamento() * perfilCalculo.getModuloResistenciaWx();
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_II:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_III:
                switch (estadoLimiteAplicavel) {
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_IV:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_V:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
        }

        return mr;
    }

    public float getMcr() {
        return mcr;
    }

    public float getEsbeltez(TipoSecaoEEixoFlexao tipoSecaoEEixoFlexao, EstadoLimiteAplicavel estadoLimiteAplicavel, PerfilModel perfilCalculo, float comprimento) {
        switch (tipoSecaoEEixoFlexao) {
            case TIPO_SECAO_E_EIXO_FLEXAO_I:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        this.esbeltez = comprimento / perfilCalculo.getRaioGiracaoY();
                        break;
                    case FLM:
                        switch (perfilCalculo.getPerfil()) {
                            case I:
                            case W:
                            case H:
                            case U:
                                this.esbeltez = perfilCalculo.getEsbeltezMesa();
                                break;
                        }
                        break;
                    case FLA:
                        this.esbeltez = perfilCalculo.getEsbeltezAlma();
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_II:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_III:
                switch (estadoLimiteAplicavel) {
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_IV:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                }
                break;
            case TIPO_SECAO_E_EIXO_FLEXAO_V:
                switch (estadoLimiteAplicavel) {
                    case FLT:
                        break;
                    case FLM:
                        break;
                    case FLA:
                        break;
                }
                break;
        }
        return esbeltez;
    }

    public float getEsbetezPlastificacao() {
        return esbetezPlastificacao;
    }

    public float getEsbetezInicioEscoamento() {
        return esbetezInicioEscoamento;
    }
}
