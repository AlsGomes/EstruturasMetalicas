package br.com.als.classes.grupos;

import br.com.als.interfaces.Aco;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    /*MANDAR AS CONSTANTES PARA UM ENUMERADO, TROCAR O NOME DA CLASSE PARA LimiteEsbeltez E TROCAR O PACOTE DELA PARA
    * calculos.compressao*/

    public static final String GRUPO1 = "Grupo1";
    public static final String GRUPO2 = "Grupo2";
    public static final String GRUPO3 = "Grupo3";
    public static final String GRUPO4 = "Grupo4";
    public static final String GRUPO5 = "Grupo5";
    public static final String GRUPO6 = "Grupo6";

    private String[] grupos = {GRUPO1, GRUPO2, GRUPO3, GRUPO4, GRUPO5, GRUPO6};

    private List<String> listaGrupos = new ArrayList<>();

    public float getEsbeltezLim1(Aco aco, String grupo) {

        float esbeltez;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        esbeltez = (float) (0.45 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));

        return esbeltez;
    }

    public float getEsbeltezLim2(Aco aco) {

        float esbeltez;
        float moduloElasticidadeKNcm2 = aco.getModuloElasticidade() * 100;
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;

        esbeltez = (float) (0.91 * Math.sqrt(moduloElasticidadeKNcm2 / tensaoEscoamentoKNcm2));

        return esbeltez;
    }
}
