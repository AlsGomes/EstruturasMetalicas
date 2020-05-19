package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexof.CoeficienteKcGrupo5;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemLocalQ {

    private float qAA;
    private float qAL;
    private float larguraEfetiva;
    private float areaEfetiva;
    private float kcGrupo5;

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
            case T:
            case L:
                qa = 1;
                break;
            case W:
            case I:
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
                    this.larguraEfetiva = (float) (1.92 * (perfilCalculo.getEspessuraAlma() / 10) * Math.sqrt(moduloElasticidadeKNcm2 / tensao)
                            * (1 - ((coeficienteCa / ((perfilCalculo.getLarguraAlma() / 10) / (perfilCalculo.getEspessuraAlma() / 10))) *
                            Math.sqrt(moduloElasticidadeKNcm2 / tensao))));
                    this.areaEfetiva = areaBruta - (((perfilCalculo.getLarguraAlma() / 10) - this.larguraEfetiva) * (perfilCalculo.getEspessuraAlma() / 10));

//                    System.out.println("LARGURA EFETIVA " + larguraEfetiva);
//                    System.out.println("AREA EFETIVA " + areaEfetiva);

                    qa = this.areaEfetiva / areaBruta;
                }
                break;
        }

//        System.out.println("qa " + qa);
        this.qAA = qa;
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
                esbeltez = perfilCalculo.getEsbeltezAba();

                if (grupo.equals(Grupo.GRUPO3) || grupo.equals(Grupo.GRUPO4)) {
                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = (float) (1.340 - (0.76 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                        } else {
                            qs = (float) ((0.53 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                        }
                    }
                }
                break;

            case W:
            case I:
            case H:
            case U:
                grupo = perfilCalculo.getGrupoMesa();
                esbeltez = perfilCalculo.getEsbeltezMesa();
                if (grupo.equals(Grupo.GRUPO5)) {
                    this.kcGrupo5 = CoeficienteKcGrupo5.getKc(perfilCalculo);

                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = (float) (1.415 - (0.65 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / (this.kcGrupo5 * moduloElasticidadeKNcm2))));
                        } else {
                            qs = (float) ((0.90 * moduloElasticidadeKNcm2 * this.kcGrupo5) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                        }
                    }
                } else {
                    if (grupo.equals(Grupo.GRUPO4)) {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                            qs = 1;
                        } else {
                            if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupo, moduloElasticidadeAco)) {
                                qs = (float) (1.415 - (0.74 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / (moduloElasticidadeKNcm2))));
                            } else {
                                qs = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                            }
                        }
                    }
                }
                break;

            case T:
                Grupo grupoAba = perfilCalculo.getGrupoAba();
                Grupo grupoAlma = perfilCalculo.getGrupoAlma();

                float esbeltezAlma = perfilCalculo.getEsbeltezAlma();
                float esbeltezAba = perfilCalculo.getEsbeltezAba();

                float qsAlma = 1f;
                float qsAba = 1f;

                if (grupoAlma.equals(Grupo.GRUPO6)) {
                    if (esbeltezAlma < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoAlma, moduloElasticidadeAco)) {
                        qsAlma = 1;
                    } else {
                        if (esbeltezAlma < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoAlma, moduloElasticidadeAco)) {
                            qsAlma = (float) (1.908 - (1.22 * esbeltezAlma * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                        } else {
                            qsAlma = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltezAlma, 2)));
                        }
                    }
                }

                if (grupoAba.equals(Grupo.GRUPO4)) {
                    if (esbeltezAba < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
                        qsAba = 1;
                    } else {
                        if (esbeltezAba < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
                            qsAba = (float) (1.415 - (0.74 * esbeltezAba * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                        } else {
                            qsAba = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltezAba, 2)));
                        }
                    }
                } else if (grupoAba.equals(Grupo.GRUPO5)) {
                    this.kcGrupo5 = CoeficienteKcGrupo5.getKc(perfilCalculo);

                    if (esbeltezAba < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
                        qsAba = 1;
                    } else {
                        if (esbeltezAba < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
                            qsAba = (float) (1.415 - (0.65 * esbeltezAba * Math.sqrt(tensaoEscoamentoKNcm2 / (this.kcGrupo5 * moduloElasticidadeKNcm2))));
                        } else {
                            qsAba = (float) ((0.90 * moduloElasticidadeKNcm2 * this.kcGrupo5) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltezAba, 2)));
                        }
                    }
                }

                qs = Math.min(qsAlma, qsAba);
                break;
        }

//        System.out.println("qs " + qs);
        this.qAL = qs;
        return qs;
    }

    public float getqAA() {
        return qAA;
    }

    public float getqAL() {
        return qAL;
    }

    public float getLarguraEfetiva() {
        return larguraEfetiva;
    }

    public float getAreaEfetiva() {
        return areaEfetiva;
    }

    public float getKcGrupo5() {
        return kcGrupo5;
    }
}
