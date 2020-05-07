package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemGlobalX {

    public float getX(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPecaCm, ModuloElasticidadeAco moduloElasticidadeAco) {

        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;
        float le = vinculo.getCoeficienteFlambagem() * comprimentoPecaCm;

        float momentoInercia;
        if (perfilCalculo.getPerfil().equals(Perfil.L)) {
            momentoInercia = perfilCalculo.getInerciaZ();
        } else {
            momentoInercia = Math.min(perfilCalculo.getInerciaX(), perfilCalculo.getInerciaY());
        }

        float cargaCriticaEuler = (float) ((Math.pow(Math.PI, 2) * moduloElasticidadeKNcm2 * momentoInercia) / Math.pow(le, 2));
        float q = new FlambagemLocalQ().getQ(perfilCalculo, aco, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        float indiceEsbeltez = (float) Math.sqrt((q * areaBruta * tensaoEscoamentoKNcm2) / (cargaCriticaEuler));

        float x;
        if (indiceEsbeltez <= 1.5) {
            x = (float) Math.pow(0.658, Math.pow(indiceEsbeltez, 2));
        } else {
            x = (float) (0.877 / Math.pow(indiceEsbeltez, 2));
        }

        return x;
    }

    public float getX(float momentoInercia, PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPecaCm, ModuloElasticidadeAco moduloElasticidadeAco) {

        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;
        float le = vinculo.getCoeficienteFlambagem() * comprimentoPecaCm;

        float cargaCriticaEuler = (float) ((Math.pow(Math.PI, 2) * moduloElasticidadeKNcm2 * momentoInercia) / Math.pow(le, 2));
        float q = new FlambagemLocalQ().getQ(perfilCalculo, aco, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        float indiceEsbeltez = (float) Math.sqrt((q * areaBruta * tensaoEscoamentoKNcm2) / (cargaCriticaEuler));

//        System.out.println("FLAMBAGEM LOCAL Q " + q);
//        System.out.println("CARGA CRITICA DE EULER " + cargaCriticaEuler);
//        System.out.println("INDICE DE ESBELTEZ " + indiceEsbeltez);

        float x;
        if (indiceEsbeltez <= 1.5) {
            x = (float) Math.pow(0.658, Math.pow(indiceEsbeltez, 2));
        } else {
            x = (float) (0.877 / Math.pow(indiceEsbeltez, 2));
        }

        return x;
    }
}
