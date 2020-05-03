package br.com.als.classes.calculos.compressao;

import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemLocalQ {

    public float getQ(PerfilModel perfilCalculo, Aco aco) {
        return getQs(perfilCalculo, aco) * getQa(perfilCalculo, aco);
    }

    private float getQa(PerfilModel perfilCalculo, Aco aco) {
        float qa = 1;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez = perfilCalculo.getEsbeltezAlma();

        switch (perfil) {
            case L:
                qa = 1;
                break;
            case W:
                break;
            case H:
                break;
            case C:
                break;
            case U:
                Grupo grupoAlma = perfilCalculo.getGrupoAlma();
                if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(aco, grupoAlma)) {
                    qa = 1;
                } else {
                    float areaBruta = perfilCalculo.getAreaBruta();
                    float coeficienteCa = 0.34f;
                    float tensao = tensaoEscoamentoKNcm2;
                    float larguraEfetiva = (float) (1.92 * perfilCalculo.getEspessuraAlma() * Math.sqrt(moduloElasticidadeKNcm2 / tensao)
                            * (1 - ((coeficienteCa / (perfilCalculo.getLarguraAlma() / perfilCalculo.getEspessuraAlma())) *
                            Math.sqrt(moduloElasticidadeKNcm2 / tensao))));
                    float areaEfetiva = areaBruta - ((perfilCalculo.getLarguraAlma() - larguraEfetiva) * perfilCalculo.getEspessuraAlma());

                    qa = areaEfetiva / areaBruta;
                }
                break;
            case T:
                break;
        }

        return qa;

    }

    private float getQs(PerfilModel perfilCalculo, Aco aco) {

        float qs = 1;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez;
        Grupo grupo;

        switch (perfil) {
            case L:
                grupo = perfilCalculo.getGrupoAba();
                esbeltez = perfilCalculo.getEsbeltezAba();
                if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(aco, grupo)) {
                    qs = 1;
                } else {
                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(aco, grupo)) {
                        qs = (float) (1.340 - (0.76 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                    } else {
                        qs = (float) ((0.53 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                    }
                }
                break;
            case W:
                break;
            case H:
                break;
            case C:
                break;
            case U:
                grupo = perfilCalculo.getGrupoAba();
                esbeltez = perfilCalculo.getEsbeltezAba();
                if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(aco, grupo)) {
                    qs = 1;
                } else {
                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(aco, grupo)) {
                        qs = (float) (1.415 - (0.74 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                    } else {
                        qs = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                    }
                }

                break;
            case T:
                break;
        }

        return qs;
    }
}
