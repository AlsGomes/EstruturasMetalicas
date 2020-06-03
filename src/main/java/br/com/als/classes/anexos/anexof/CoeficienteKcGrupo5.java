package br.com.als.classes.anexos.anexof;

public class CoeficienteKcGrupo5 {

    public static float getKc(float larguraAlma, float espessuraAlma) {

        return (float) (4 / Math.sqrt(larguraAlma / espessuraAlma));
    }
}
