package br.com.als.classes.perfis;

import br.com.als.classes.anexos.anexof.Grupo;

public class PerfilModel {

    private String nomePerfil;

    private float espessuraAlma;
    private float espessuraAba;
    private float espessuraMesa;

    private float alturaAlma;

    private float larguraAlma;
//    private float larguraAlma = alturaAlma - (2 * espessuraMesa);

    private float larguraAba;
    private float larguraMesa;

    private float esbeltezAlma;
    //    private float esbeltezAlma = larguraAlma / espessuraAlma;
    private float esbeltezAba;
    private float esbeltezMesa;
//    private float esbeltezMesa = (larguraMesa / 2) / espessuraMesa;

    private float moduloResistenciaWx;
    private float moduloResistenciaWy;

    //    private float areaBruta = (2 * ((espessuraMesa / 10) * (larguraMesa / 10))) + ((larguraAlma / 10) * (espessuraMesa / 10));
    private float areaBruta;
    private float pesoPorMetro;
    private float inerciaX;
    private float inerciaY;
    private float raioGiracaoX;
    private float raioGiracaoY;
    private float raioGiracaoMin;
    private float inerciaZ;
//    private float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;

    private Grupo grupoAlma;
    private Grupo grupoAba;
    private Grupo grupoMesa;
    private Perfil perfil;

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

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public void setEspessuraAlma(float espessuraAlma) {
        this.espessuraAlma = espessuraAlma;
    }

    public void setEspessuraAba(float espessuraAba) {
        this.espessuraAba = espessuraAba;
    }

    public void setEspessuraMesa(float espessuraMesa) {
        this.espessuraMesa = espessuraMesa;
    }

    public void setAlturaAlma(float alturaAlma) {
        this.alturaAlma = alturaAlma;
    }

    public void setLarguraAlma(float larguraAlma) {
        this.larguraAlma = larguraAlma;
    }

    public void setLarguraAba(float larguraAba) {
        this.larguraAba = larguraAba;
    }

    public void setLarguraMesa(float larguraMesa) {
        this.larguraMesa = larguraMesa;
    }

    public void setEsbeltezAlma(float esbeltezAlma) {
        this.esbeltezAlma = esbeltezAlma;
    }

    public void setEsbeltezAba(float esbeltezAba) {
        this.esbeltezAba = esbeltezAba;
    }

    public void setEsbeltezMesa(float esbeltezMesa) {
        this.esbeltezMesa = esbeltezMesa;
    }

    public void setModuloResistenciaWx(float moduloResistenciaWx) {
        this.moduloResistenciaWx = moduloResistenciaWx;
    }

    public void setModuloResistenciaWy(float moduloResistenciaWy) {
        this.moduloResistenciaWy = moduloResistenciaWy;
    }

    public void setAreaBruta(float areaBruta) {
        this.areaBruta = areaBruta;
    }

    public void setPesoPorMetro(float pesoPorMetro) {
        this.pesoPorMetro = pesoPorMetro;
    }

    public void setInerciaX(float inerciaX) {
        this.inerciaX = inerciaX;
    }

    public void setInerciaY(float inerciaY) {
        this.inerciaY = inerciaY;
    }

    public void setRaioGiracaoX(float raioGiracaoX) {
        this.raioGiracaoX = raioGiracaoX;
    }

    public void setRaioGiracaoY(float raioGiracaoY) {
        this.raioGiracaoY = raioGiracaoY;
    }

    public void setRaioGiracaoMin(float raioGiracaoMin) {
        this.raioGiracaoMin = raioGiracaoMin;
    }

    public void setInerciaZ(float inerciaZ) {
        this.inerciaZ = inerciaZ;
    }

    public void setGrupoAlma(Grupo grupoAlma) {
        this.grupoAlma = grupoAlma;
    }

    public void setGrupoAba(Grupo grupoAba) {
        this.grupoAba = grupoAba;
    }

    public void setGrupoMesa(Grupo grupoMesa) {
        this.grupoMesa = grupoMesa;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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
                ", moduloResistenciaWx=" + moduloResistenciaWx +
                ", moduloResistenciaWy=" + moduloResistenciaWy +
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
