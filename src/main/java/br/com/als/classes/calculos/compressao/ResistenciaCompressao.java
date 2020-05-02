package br.com.als.classes.calculos.compressao;

import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class ResistenciaCompressao {

    private float resistenciaCompressao;
    private float gamaA1 = 1.10f;

    public float getResistenciaCompressao(PerfilModel perfilCalculo, Aco aco) {

        calcularCompressao(perfilCalculo, aco);
        return resistenciaCompressao;
    }

    private void calcularCompressao(PerfilModel perfilCalculo, Aco aco) {

        resistenciaCompressao = new FlambagemLocalQ().getQ(perfilCalculo, aco);
    }
}
