package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexof.CoeficienteKcGrupo5;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class LimiteEsbeltez {

    public static float getEsbeltezLim1(PerfilModel perfilCalculo, Aco aco, Grupo grupo, ModuloElasticidadeAco moduloElasticidadeAco) {

        float esbeltez = 0;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        switch (grupo) {
            case GRUPO1:
                esbeltez = (float) (1.40 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO2:
                esbeltez = (float) (1.49 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO3:
                esbeltez = (float) (0.45 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO4:
                esbeltez = (float) (0.56 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO5:
                float kc = CoeficienteKcGrupo5.getKc(perfilCalculo);
                if (kc < 0.35 || kc > 0.76) {
                    System.out.println(String.format("Verificar coeficiente kc do Grupo5. Valor %s", kc));
                }
                esbeltez = (float) (0.64 * Math.sqrt(moduloElasticidadeKNcm2 / (tensaoEscoamentoKNcm2 / kc)));
                break;
            case GRUPO6:
                esbeltez = (float) (0.75 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
        }

        return esbeltez;
    }

    public static float getEsbeltezLim2(PerfilModel perfilCalculo, Aco aco, Grupo grupo, ModuloElasticidadeAco moduloElasticidadeAco) {

        float esbeltez = 0;
        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        switch (grupo) {
            //                FALTA CALCULAR GRUPO 1
            case GRUPO1:
                esbeltez = (float) (1.40 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            //                FALTA CALCULAR GRUPO 2
            case GRUPO2:
                esbeltez = (float) (1.49 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO3:
                esbeltez = (float) (0.91 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO4:
            case GRUPO6:
                esbeltez = (float) (1.03 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));
                break;
            case GRUPO5:
                float kc = CoeficienteKcGrupo5.getKc(perfilCalculo);
                if (kc < 0.35 || kc > 0.76) {
                    System.out.println(String.format("Verificar coeficiente kc do Grupo5. Valor %s", kc));
                }
                esbeltez = (float) (1.17 * Math.sqrt(moduloElasticidadeKNcm2 / (tensaoEscoamentoKNcm2 / kc)));
                break;
        }

        return esbeltez;
    }
}
