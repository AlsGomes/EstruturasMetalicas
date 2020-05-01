package br.com.als.classes.grupos;

import br.com.als.interfaces.Aco;
import br.com.als.interfaces.Grupo;

public class Grupo3 implements Grupo {

    @Override
    public float getEsbeltezLim1(Aco aco) {

        float esbeltez;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        esbeltez = (float) (0.45 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));

        return esbeltez;
    }

    @Override
    public float getEsbeltezLim2(Aco aco) {

        float esbeltez;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        esbeltez = (float) (0.91 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));

        return esbeltez;
    }
}
