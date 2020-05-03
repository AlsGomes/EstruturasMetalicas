package br.com.als.classes.calculos.compressao;

import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class ResistenciaCompressao {

    private float resistenciaCompressao;
    private float gamaA1 = 1.10f;

    public float getResistenciaCompressao(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca) {

        calcularCompressao(perfilCalculo, aco, vinculo, comprimentoPeca);
        return resistenciaCompressao;
    }

    private void calcularCompressao(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca) {

        float q = new FlambagemLocalQ().getQ(perfilCalculo, aco);
        float x = new FlambagemGlobalX().getX(perfilCalculo, aco, vinculo, comprimentoPeca);
        float areaBruta = perfilCalculo.getAreaBruta();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        resistenciaCompressao = q * x * areaBruta * tensaoEscoamentoKNcm2 / gamaA1;
    }
}
