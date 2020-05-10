package br.com.als.classes.acos.model;

import br.com.als.interfaces.Aco;

public class G35 implements Aco {

    private String denominacao = "G-35";

    private float tensaoEscoamento = 345f;
    private float tensaoRuptura = 450f;

    @Override
    public String getDenominacao() {
        return denominacao;
    }

    @Override
    public float getTensaoEscoamento() {
        return tensaoEscoamento;
    }

    @Override
    public float getTensaoRuptura() {
        return tensaoRuptura;
    }

    @Override
    public String toString() {
        return "G35{" +
                "denominacao='" + denominacao + '\'' +
                ", tensaoEscoamento=" + tensaoEscoamento +
                ", tensaoRuptura=" + tensaoRuptura +
                '}';
    }
}
