package br.com.als.classes.calculos.compressao;

import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemLocalQ {

    public float getQ(PerfilModel perfilCalculo, Aco aco) {
        return getQs(perfilCalculo, aco) * getQa();
    }

    private float getQa() {
        return 1f;
    }

    private float getQs(PerfilModel perfilCalculo, Aco aco) {

        float qs = 1;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        Perfil perfil = perfilCalculo.getPerfil();
        float esbeltez = perfilCalculo.getEsbeltez();
        Grupo grupo = perfilCalculo.getGrupo();

        switch (perfil) {
            case L:
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
                break;
        }


        return qs;
    }
}
