package br.com.als.classes.perfis.model;

import br.com.als.classes.grupos.Grupo3;
import br.com.als.interfaces.Aco;
import br.com.als.interfaces.Grupo;
import br.com.als.interfaces.Perfil;

public class L50x50x3 implements Perfil {
    private float comprimentoAba = 50.8f;
    private float areaBruta = 3.10f;
    private float pesoPorMetro = 2.46f;
    private float espessuraAba = 3.18f;
    private float inerciaXY = 7.91f;
    private float inerciaZ;
    private float raioGiracaoMin = 1.02f;
    private float esbeltez;
    private Grupo grupo = new Grupo3();
    private boolean elementoAA = false;
    private boolean elementoAL = true;

    @Override
    public float getEsbeltez() {
        return (comprimentoAba / espessuraAba);
    }

    @Override
    public Grupo getGrupo() {
        return grupo;
    }

    public float getComprimentoAba() {
        return comprimentoAba;
    }

    @Override
    public float getAreaBruta() {
        return areaBruta;
    }

    public float getPesoPorMetro() {
        return pesoPorMetro;
    }

    public float getEspessuraAba() {
        return espessuraAba;
    }

    public float getInerciaXY() {
        return inerciaXY;
    }

    public float getInerciaZ() {
        return inerciaZ;
    }

    public float getRaioGiracaoMin() {
        return raioGiracaoMin;
    }
}
