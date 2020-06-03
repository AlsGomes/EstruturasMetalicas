package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.model.AcoModel;
import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexof.CoeficienteKcGrupo5;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;

public class FlambagemLocalQ {

    private float qAA;
    private float qAL;
    private float larguraEfetiva;
    private float areaEfetiva;
    private float kcGrupo5;

    public float getQ(PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, AcoModel aco, ModuloElasticidadeAco moduloElasticidadeAco) {
        return getQs(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco) * getQa(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco);
    }

    private float getQa(PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, AcoModel aco, ModuloElasticidadeAco moduloElasticidadeAco) {
        float qa = 1;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez = perfilCalculo.getEsbeltezAlma();

        switch (perfil) {
            case T:
            case L:
                qa = 1;
                break;
            case W:
            case I:
            case U:
            case H:
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

                    qa = this.areaEfetiva / areaBruta;
                }
                break;
        }

        this.qAA = qa;
        return qa;
    }

    private float getQs(PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, AcoModel aco, ModuloElasticidadeAco moduloElasticidadeAco) {
        float qs = 1;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez;

        switch (perfil) {
            case L:
                esbeltez = perfilCalculo.getEsbeltezAba();

                if (grupoAba.equals(Grupo.GRUPO3) || grupoAba.equals(Grupo.GRUPO4)) {
                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoAba, moduloElasticidadeAco)) {
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
                esbeltez = perfilCalculo.getEsbeltezMesa();
                if (grupoMesa.equals(Grupo.GRUPO5)) {
                    this.kcGrupo5 = CoeficienteKcGrupo5.getKc(perfilCalculo.getLarguraAlma(), perfilCalculo.getEspessuraAlma());

                    if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                        qs = 1;
                    } else {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                            qs = (float) (1.415 - (0.65 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / (this.kcGrupo5 * moduloElasticidadeKNcm2))));
                        } else {
                            qs = (float) ((0.90 * moduloElasticidadeKNcm2 * this.kcGrupo5) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                        }
                    }
                } else {
                    if (grupoMesa.equals(Grupo.GRUPO4)) {
                        if (esbeltez < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                            qs = 1;
                        } else {
                            if (esbeltez < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                                qs = (float) (1.415 - (0.74 * esbeltez * Math.sqrt(tensaoEscoamentoKNcm2 / (moduloElasticidadeKNcm2))));
                            } else {
                                qs = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltez, 2)));
                            }
                        }
                    }
                }
                break;

            case T:
                float esbeltezAlma = perfilCalculo.getEsbeltezAlma();
                float esbeltezMesa = perfilCalculo.getEsbeltezMesa();

                float qsAlma = 1f;
                float qsMesa = 1f;

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

                if (grupoMesa.equals(Grupo.GRUPO4)) {
                    if (esbeltezMesa < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                        qsMesa = 1;
                    } else {
                        if (esbeltezMesa < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                            qsMesa = (float) (1.415 - (0.74 * esbeltezMesa * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
                        } else {
                            qsMesa = (float) ((0.69 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltezMesa, 2)));
                        }
                    }
                } else if (grupoMesa.equals(Grupo.GRUPO5)) {
                    this.kcGrupo5 = CoeficienteKcGrupo5.getKc(perfilCalculo.getLarguraAlma(), perfilCalculo.getEspessuraAlma());

                    if (esbeltezMesa < LimiteEsbeltez.getEsbeltezLim1(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                        qsMesa = 1;
                    } else {
                        if (esbeltezMesa < LimiteEsbeltez.getEsbeltezLim2(perfilCalculo, aco, grupoMesa, moduloElasticidadeAco)) {
                            qsMesa = (float) (1.415 - (0.65 * esbeltezMesa * Math.sqrt(tensaoEscoamentoKNcm2 / (this.kcGrupo5 * moduloElasticidadeKNcm2))));
                        } else {
                            qsMesa = (float) ((0.90 * moduloElasticidadeKNcm2 * this.kcGrupo5) / (tensaoEscoamentoKNcm2 * Math.pow(esbeltezMesa, 2)));
                        }
                    }
                }

                qs = Math.min(qsAlma, qsMesa);
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
