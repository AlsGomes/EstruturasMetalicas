package br.com.als.classes.acos.moduloelasticidade;

public enum ModuloElasticidadeAco {

    GPa200(200f, 200000f, 200000000f, 20000f),
    GPa205(205f, 205000f, 205000000f, 20500f);

    private float moduloElasticidadeGPa;
    private float moduloElasticidadeMPa;
    private float moduloElasticidadeKPa;
    private float moduloElasticidadeKNcm2;

    ModuloElasticidadeAco(float moduloElasticidadeGPa, float moduloElasticidadeMPa, float moduloElasticidadeKPa, float moduloElasticidadeKNcm2) {
        this.moduloElasticidadeGPa = moduloElasticidadeGPa;
        this.moduloElasticidadeMPa = moduloElasticidadeMPa;
        this.moduloElasticidadeKPa = moduloElasticidadeKPa;
        this.moduloElasticidadeKNcm2 = moduloElasticidadeKNcm2;
    }

    public float getModuloElasticidadeGPa() {
        return moduloElasticidadeGPa;
    }

    public float getModuloElasticidadeMPa() {
        return moduloElasticidadeMPa;
    }

    public float getModuloElasticidadeKPa() {
        return moduloElasticidadeKPa;
    }

    public float getModuloElasticidadeKNcm2() {
        return moduloElasticidadeKNcm2;
    }
}
