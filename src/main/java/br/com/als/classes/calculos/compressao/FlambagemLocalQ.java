package br.com.als.classes.calculos.compressao;

import br.com.als.interfaces.Aco;
import br.com.als.interfaces.Perfil;

public class FlambagemLocalQ {

    public float getQ(Perfil perfil, Aco aco) {
        return getQs(perfil, aco) * getQa();
    }

    private float getQa() {
        return 1f;
    }

    private float getQs(Perfil perfil, Aco aco) {
        float qs;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        if (perfil.getEsbeltez() < perfil.getGrupo().getEsbeltezLim1(aco)) {
            qs = 1;
        } else {
            if (perfil.getEsbeltez() < perfil.getGrupo().getEsbeltezLim2(aco)) {
                qs = (float) (1.340 - (0.76 * perfil.getEsbeltez() * Math.sqrt(tensaoEscoamentoKNcm2 / moduloElasticidadeKNcm2)));
            } else {
                qs = (float) ((0.53 * moduloElasticidadeKNcm2) / (tensaoEscoamentoKNcm2 * Math.pow(perfil.getEsbeltez(), 2)));
            }
        }

        return qs;
    }
}
