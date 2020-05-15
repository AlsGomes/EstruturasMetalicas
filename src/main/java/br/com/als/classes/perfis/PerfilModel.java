package br.com.als.classes.perfis;

import br.com.als.classes.anexos.anexof.Grupo;

public class PerfilModel {

    private String nomePerfil = "W310x38.7";

    private float espessuraAlma = 5.8f;
    private float espessuraAba;
    private float espessuraMesa = 9.7f;

    private float alturaAlma = 310f;

    private float larguraAlma = alturaAlma - (2 * espessuraMesa);
    private float larguraAba;
    private float larguraMesa = 165f;

    private float esbeltezAlma = larguraAlma / espessuraAlma;
    private float esbeltezAba;
    private float esbeltezMesa = (larguraMesa / 2) / espessuraMesa;

    private float moduloResistenciaWx = 553.6f;
    private float moduloResistenciaWy = 88.1f;

    private float areaBruta = (2 * ((espessuraMesa / 10) * (larguraMesa / 10))) + ((larguraAlma / 10) * (espessuraMesa / 10));
    private float pesoPorMetro = 38.7f;
    private float inerciaX = 8581f;
    private float inerciaY = 727f;
    private float raioGiracaoX;
    private float raioGiracaoY;
    private float raioGiracaoMin;
    private float inerciaZ;
//    private float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;

    private Grupo grupoAlma = Grupo.GRUPO2;
    private Grupo grupoAba;
    private Grupo grupoMesa = Grupo.GRUPO4;
    private Perfil perfil = Perfil.W;

    public String getNomePerfil() {
        return nomePerfil;
    }

    public float getEspessuraAlma() {
        return espessuraAlma;
    }

    public float getEspessuraAba() {
        return espessuraAba;
    }

    public float getEspessuraMesa() {
        return espessuraMesa;
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

    public float getLarguraMesa() {
        return larguraMesa;
    }

    public float getEsbeltezAlma() {
        return esbeltezAlma;
    }

    public float getEsbeltezAba() {
        return esbeltezAba;
    }

    public float getEsbeltezMesa() {
        return esbeltezMesa;
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

    public Grupo getGrupoMesa() {
        return grupoMesa;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public float getModuloResistenciaWx() {
        return moduloResistenciaWx;
    }

    public float getModuloResistenciaWy() {
        return moduloResistenciaWy;
    }

    @Override
    public String toString() {
        return "PerfilModel{" +
                "nomePerfil='" + nomePerfil + '\'' +
                ", espessuraAlma=" + espessuraAlma +
                ", espessuraAba=" + espessuraAba +
                ", espessuraMesa=" + espessuraMesa +
                ", alturaAlma=" + alturaAlma +
                ", larguraAlma=" + larguraAlma +
                ", larguraAba=" + larguraAba +
                ", larguraMesa=" + larguraMesa +
                ", esbeltezAlma=" + esbeltezAlma +
                ", esbeltezAba=" + esbeltezAba +
                ", esbeltezMesa=" + esbeltezMesa +
                ", areaBruta=" + areaBruta +
                ", pesoPorMetro=" + pesoPorMetro +
                ", inerciaX=" + inerciaX +
                ", inerciaY=" + inerciaY +
                ", raioGiracaoX=" + raioGiracaoX +
                ", raioGiracaoY=" + raioGiracaoY +
                ", raioGiracaoMin=" + raioGiracaoMin +
                ", inerciaZ=" + inerciaZ +
                ", grupoAlma=" + grupoAlma +
                ", grupoAba=" + grupoAba +
                ", grupoMesa=" + grupoMesa +
                ", perfil=" + perfil +
                '}';
    }
}
