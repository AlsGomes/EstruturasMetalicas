package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexof.CoeficienteKcGrupo5;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemLocalQ {

    public float getQ(PerfilModel perfilCalculo, Aco aco, ModuloElasticidadeAco moduloElasticidadeAco) {
        return getQs(perfilCalculo, aco, moduloElasticidadeAco) * getQa(perfilCalculo, aco, moduloElasticidadeAco);
    }

    private float getQa(PerfilModel perfilCalculo, Aco aco, ModuloElasticidadeAco moduloElasticidadeAco) {
        float qa = 1;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez = perfilCalculo.getEsbeltezAlma();

//        System.out.println("PERFIL " + perfil);
//        System.out.println("ESBELTEZ ALMA QA " + esbeltez);

        switch (perfil) {
            case L:
                qa = 1;
                break;
            case W:
                break;
            case U:
            case H:
                Grupo grupoAlma = perfilCalculo.getGrupoAlma();
//                System.out.println("GRUPO DA ALMA " + grupoAlma);
                if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoAlma, moduloElasticidadeAco)) {
                    qa = 1;
                } else {
                    float areaBruta = perfilCalculo.getAreaBruta();
                    float coeficienteCa = 0.34f;
                    float tensao = tensaoEscoamentoKNcm2;
                    float larguraEfetiva = (float) (1.92 * (perfilCalculo.getEspessuraAlma() / 10) * Math.sqrt(moduloElasticidadeKNcm2 / tensao)
                            * (1 - ((coeficienteCa / ((perfilCalculo.getLarguraAlma() / 10) / (perfilCalculo.getEspessuraAlma() / 10))) *
                            Math.sqrt(moduloElasticidadeKNcm2 / tensao))));
                    float areaEfetiva = areaBruta - (((perfilCalculo.getLarguraAlma() / 10) - larguraEfetiva) * (perfilCalculo.getEspessuraAlma() / 10));

//                    System.out.println("LARGURA EFETIVA " + larguraEfetiva);
//                    System.out.println("AREA EFETIVA " + areaEfetiva);

                    qa = areaEfetiva / areaBruta;
                }
                break;

            case C:
                break;
            case T:
                break;
        }

//        System.out.println("qa " + qa);
        return qa;
    }

    private float getQs(PerfilModel perfilCalculo, Aco aco, ModuloElasticidadeAco moduloElasticidadeAco) {

        float qs = 1;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez;
        Grupo grupo;

        switch (perfil) {
            case L:
                grupo = perfilCalculo.getGrupoAba();
                if (grupo.equals(Grupo.GRUPO3)) {

                    esbeltez = perfilCalculo.getEsbeltezAba();
                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = (float) (1.340 - (0.76 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                        } else {
                            qs = (float) ((0.53 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                        }
                    }
                } else {
                    if (grupo.equals(Grupo.GRUPO4)) {
//                        FALTA CALCULAR QS QUANDO O PERFIL SE ENCAIXA NO GRUPO 4 (PERFIL L)
                    }
                }
                break;
            case W:
                break;
            case H:
                grupo = perfilCalculo.getGrupoMesa();
                esbeltez = perfilCalculo.getEsbeltezMesa();
                if (grupo.equals(Grupo.GRUPO5)) {
                    float kc = CoeficienteKcGrupo5.getKc(perfilCalculo);

                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = (float) (1.415 - (0.65 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / (kc * moduloElasticidadeKNcm2))));
                        } else {
                            qs = (float) ((0.90 * moduloElasticidadeKNcm2 * kc) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                        }
                    }
                } else {
                    if (grupo.equals(Grupo.GRUPO4)) {
//                        FALTA CALCULAR QS QUANDO O PERFIL SE ENCAIXA NO GRUPO 4 (PERFIL H)
                    }
                }
                break;
            case C:
                break;
            case U:
                grupo = perfilCalculo.getGrupoAba();
                esbeltez = perfilCalculo.getEsbeltezAba();

                if (grupo.equals(Grupo.GRUPO5)) {
//                        FALTA CALCULAR QS QUANDO O PERFIL SE ENCAIXA NO GRUPO 5 (PERFIL U)
                } else {
                    if (grupo.equals(Grupo.GRUPO4)) {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = 1;
                        } else {
                            if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                                qs = (float) (1.415 - (0.74 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                            } else {
                                qs = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                            }
                        }
                    }
                }
                break;
            case T:
                break;
        }

//        System.out.println("qs " + qs);
        return qs;
    }
}
