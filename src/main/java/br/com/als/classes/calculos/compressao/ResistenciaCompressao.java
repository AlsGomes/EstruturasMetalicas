package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class ResistenciaCompressao {

    private float resistenciaCompressao;
    private float gamaA1 = 1.10f;

    public float getResistenciaCompressao(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        calcularCompressao(perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        return resistenciaCompressao;
    }

    private void calcularCompressao(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {

        float q = new FlambagemLocalQ().getQ(perfilCalculo, aco, moduloElasticidadeAco);
        float x = new FlambagemGlobalX().getX(perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        resistenciaCompressao = q * x * areaBruta * tensaoEscoamentoKNcm2 / gamaA1;
    }

    public float getResistenciaCompressao(float momentoInercia, PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        calcularCompressao(momentoInercia, perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        return resistenciaCompressao;
    }

    private void calcularCompressao(float momentoInercia, PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {

        float q = new FlambagemLocalQ().getQ(perfilCalculo, aco, moduloElasticidadeAco);
//        System.out.println("Qa x Qs = " + q);
        float x = new FlambagemGlobalX().getX(momentoInercia, perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        resistenciaCompressao = q * x * areaBruta * tensaoEscoamentoKNcm2 / gamaA1;
    }
}
