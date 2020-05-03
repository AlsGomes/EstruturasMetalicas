package br.com.als.classes.perfis;

import br.com.als.classes.anexos.anexof.Grupo;

public class PerfilModel {

    private String nomePerfil = "L50.8x50.8x3.2";
    private float comprimentoAba = 50.8f;
    private float areaBruta = 3.10f;
    private float pesoPorMetro = 2.46f;
    private float espessuraAba = 3.18f;
    private float inerciaX = 7.91f;
    private float inerciaY = 7.91f;
    private float raioGiracaoMin = 1.02f;
    private float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;
    private float esbeltez = comprimentoAba / espessuraAba;

    private boolean elementoAA = false;
    private boolean elementoAL = true;

    private Grupo grupo = Grupo.GRUPO3;
    private Perfil perfil = Perfil.L;

    public String getNomePerfil() {
        return nomePerfil;
    }

    public float getComprimentoAba() {
        return comprimentoAba;
    }

    public float getAreaBruta() {
        return areaBruta;
    }

    public float getPesoPorMetro() {
        return pesoPorMetro;
    }

    public float getEspessuraAba() {
        return espessuraAba;
    }

    public float getInerciaX() {
        return inerciaX;
    }

    public float getInerciaY() {
        return inerciaY;
    }

    public float getInerciaZ() {
        return inerciaZ;
    }

    public float getRaioGiracaoMin() {
        return raioGiracaoMin;
    }

    public float getEsbeltez() {
        return esbeltez;
    }

    public boolean isElementoAA() {
        return elementoAA;
    }

    public boolean isElementoAL() {
        return elementoAL;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
