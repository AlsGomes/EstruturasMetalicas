package br.com.als.classes.acos.model;

import br.com.als.interfaces.Aco;

public class AcoMR250 implements Aco {
    private float tensaoEscoamento = 250f;
    private float tensaoRuptura = 400f;
    private float moduloElasticidade = 200f;

    @Override
    public float getTensaoEscoamento() {
        return tensaoEscoamento;
    }

    @Override
    public float getTensaoRuptura() {
        return tensaoRuptura;
    }

    @Override
    public float getModuloElasticidade() {
        return moduloElasticidade;
    }

    @Override
    public String toString() {
        return "AcoMR250{" +
                "tensaoEscoamento=" + tensaoEscoamento +
                ", tensaoRuptura=" + tensaoRuptura +
                ", moduloElasticidade=" + moduloElasticidade +
                '}';
    }
}
