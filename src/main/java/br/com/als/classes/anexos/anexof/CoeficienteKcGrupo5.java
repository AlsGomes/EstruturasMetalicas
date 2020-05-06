package br.com.als.classes.anexos.anexof;

import br.com.als.classes.perfis.PerfilModel;

public class CoeficienteKcGrupo5 {

    public static float getKc(PerfilModel perfilCalculo) {

        if (!perfilCalculo.getGrupoMesa().equals(Grupo.GRUPO5)) {
            System.out.println("O grupo não requer o coeficiente kc");
            return 0;
        }

        return (float) (4 / Math.sqrt(perfilCalculo.getLarguraAlma() / perfilCalculo.getEspessuraAlma()));
    }
}
