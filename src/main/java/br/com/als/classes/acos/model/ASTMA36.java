package br.com.als.classes.acos.model;

import br.com.als.interfaces.Aco;

public class ASTMA36 implements Aco {

    private String denominacao = "ASTM A36";

    private float tensaoEscoamento = 250f;
    private float tensaoRuptura = 400f;

    @Override
    public float getTensaoEscoamento() {
        return tensaoEscoamento;
    }

    @Override
    public float getTensaoRuptura() {
        return tensaoRuptura;
    }

    @Override
    public String getDenominacao() {
        return denominacao;
    }


    @Override
    public String toString() {
        return "AcoMR250{" +
                "denominacao='" + denominacao + '\'' +
                ", tensaoEscoamento=" + tensaoEscoamento +
                ", tensaoRuptura=" + tensaoRuptura +
                '}';
    }
}