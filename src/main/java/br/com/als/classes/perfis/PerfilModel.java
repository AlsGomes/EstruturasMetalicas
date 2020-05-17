package br.com.als.classes.perfis;

import br.com.als.classes.anexos.anexof.Grupo;

public class PerfilModel {

    private String nomePerfil = "L76X10.7";

    private float espessuraAlma;
    private float espessuraAba = 9.52f;
    private float espessuraMesa;

    private float alturaAlma;

    private float larguraAlma;
//    private float larguraAlma = alturaAlma - (2 * espessuraMesa);

    private float larguraAba = 76.2f;
    private float larguraMesa;

    private float esbeltezAlma;
    //    private float esbeltezAlma = larguraAlma / espessuraAlma;
    private float esbeltezAba = larguraAba / espessuraAba;
    private float esbeltezMesa;
//    private float esbeltezMesa = (larguraMesa / 2) / espessuraMesa;

    private float moduloResistenciaWx = 13.60f;
    private float moduloResistenciaWy = 13.60f;

    //    private float areaBruta = (2 * ((espessuraMesa / 10) * (larguraMesa / 10))) + ((larguraAlma / 10) * (espessuraMesa / 10));
    private float areaBruta = 13.61f;
    private float pesoPorMetro = 10.7f;
    private float inerciaX = 75f;
    private float inerciaY = 75f;
    private float raioGiracaoX = 2.31f;
    private float raioGiracaoY = 2.31f;
    private float raioGiracaoMin = 1.47f;
    //    private float inerciaZ;
    private float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;

    private Grupo grupoAlma;
    private Grupo grupoAba = Grupo.GRUPO4;
    private Grupo grupoMesa;
    private Perfil perfil = Perfil.L;

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
