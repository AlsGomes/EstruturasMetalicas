package br.com.als.classes.perfis;

import br.com.als.classes.anexos.anexof.Grupo;

public class PerfilModel {

    private String nomePerfil = "U152x15.6";

    private float espessuraAlma = 7.98f;
    private float espessuraAba = 8.71f;

    private float alturaAlma = 152.40f;

    private float larguraAlma = alturaAlma - (2 * espessuraAba);
    private float larguraAba = 51.66f;

    private float esbeltezAlma = larguraAlma / espessuraAlma;
    private float esbeltezAba = larguraAba / espessuraAba;


    private float areaBruta = 19.9f;
    private float pesoPorMetro = 15.60f;
    private float inerciaX = 632f;
    private float inerciaY = 36f;
    private float raioGiracaoX = 5.63f;
    private float raioGiracaoY = 1.34f;
    private float raioGiracaoMin = 1.34f;
    private float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;

    private Grupo grupoAlma = Grupo.GRUPO2;
    private Grupo grupoAba = Grupo.GRUPO4;
    private Perfil perfil = Perfil.U;

    public String getNomePerfil() {
        return nomePerfil;
    }

    public float getEspessuraAlma() {
        return espessuraAlma;
    }

    public float getEspessuraAba() {
        return espessuraAba;
    }

    public float getAlturaAlma() {
        return alturaAlma;
    }

    public float getLarguraAlma() {
        return larguraAlma;
    }

    public float getLarguraAba() {
        return larguraAba;
    }

    public float getEsbeltezAlma() {
        return esbeltezAlma;
    }

    public float getEsbeltezAba() {
        return esbeltezAba;
    }

    public float getAreaBruta() {
        return areaBruta;
    }

    public float getPesoPorMetro() {
        return pesoPorMetro;
    }

    public float getInerciaX() {
        return inerciaX;
    }

    public float getInerciaY() {
        return inerciaY;
    }

    public float getRaioGiracaoX() {
        return raioGiracaoX;
    }

    public float getRaioGiracaoY() {
        return raioGiracaoY;
    }

    public float getRaioGiracaoMin() {
        return raioGiracaoMin;
    }

    public float getInerciaZ() {
        return inerciaZ;
    }

    public Grupo getGrupoAlma() {
        return grupoAlma;
    }

    public Grupo getGrupoAba() {
        return grupoAba;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
