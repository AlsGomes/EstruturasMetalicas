package br.com.als.classes.perfis.model;

import br.com.als.classes.grupos.Grupo;

public class PerfilModel {

    private float comprimentoAba = 50.8f;
    private float areaBruta = 3.10f;
    private float pesoPorMetro = 2.46f;
    private float espessuraAba = 3.18f;
    private float inerciaXY = 7.91f;
    private float inerciaZ;
    private float raioGiracaoMin = 1.02f;
    private float esbeltez;

    private boolean elementoAA = false;
    private boolean elementoAL = true;

    private String grupo = Grupo.GRUPO3;


}
