package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class ResistenciaCompressao {

    private float resistenciaCompressao;
    private float flambagemLocalQ;
    private float qAA;
    private float qAL;
    private float flambagemGlobalX;
    private float inerciaUtilizada;
    private PerfilModel perfilCalculo;
    private Aco aco;
    private CoeficienteFlambagem vinculo;
    private float comprimentoPeca;
    private ModuloElasticidadeAco moduloElasticidadeAco;
    private float cargaCriticaEuler;
    private float lambda0;

    private static final float GAMA_A1 = 1.10f;

    public ResistenciaCompressao(PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        this.perfilCalculo = perfilCalculo;
        this.aco = aco;
        this.vinculo = vinculo;
        this.comprimentoPeca = comprimentoPeca;
        this.moduloElasticidadeAco = moduloElasticidadeAco;
        FlambagemLocalQ fQ = new FlambagemLocalQ();
        this.flambagemLocalQ = fQ.getQ(perfilCalculo, aco, moduloElasticidadeAco);
        this.qAA = fQ.getqAA();
        this.qAL = fQ.getqAL();
        FlambagemGlobalX fX = new FlambagemGlobalX();
        this.flambagemGlobalX = fX.getX(perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        this.inerciaUtilizada = fX.getInerciaUtilizada();
        this.cargaCriticaEuler = fX.getCargaCriticaEuler();
        this.lambda0 = fX.getLambda0();
        calcularCompressao();
    }

    public ResistenciaCompressao(float momentoInercia, PerfilModel perfilCalculo, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        this.perfilCalculo = perfilCalculo;
        this.aco = aco;
        this.vinculo = vinculo;
        this.comprimentoPeca = comprimentoPeca;
        this.moduloElasticidadeAco = moduloElasticidadeAco;
        FlambagemLocalQ f = new FlambagemLocalQ();
        this.flambagemLocalQ = f.getQ(perfilCalculo, aco, moduloElasticidadeAco);
        this.qAA = f.getqAA();
        this.qAL = f.getqAL();
        FlambagemGlobalX fX = new FlambagemGlobalX();
        this.flambagemGlobalX = fX.getX(momentoInercia, perfilCalculo, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        this.inerciaUtilizada = momentoInercia;
        this.cargaCriticaEuler = fX.getCargaCriticaEuler();
        this.lambda0 = fX.getLambda0();
        calcularCompressao();
    }

    public float getLambda0() {
        return lambda0;
    }

    public float getCargaCriticaEuler() {
        return cargaCriticaEuler;
    }

    public float getInerciaUtilizada() {
        return inerciaUtilizada;
    }

    public float getFlambagemLocalQ() {
        return this.flambagemLocalQ;
    }

    public float getFlambagemGlobalX() {
        return this.flambagemGlobalX;
    }

    public PerfilModel getPerfilCalculo() {
        return this.perfilCalculo;
    }

    public Aco getAco() {
        return this.aco;
    }

    public CoeficienteFlambagem getVinculo() {
        return this.vinculo;
    }

    public float getComprimentoPeca() {
        return this.comprimentoPeca;
    }

    public ModuloElasticidadeAco getModuloElasticidadeAco() {
        return this.moduloElasticidadeAco;
    }

    public float getResistenciaCompressao() {
        return this.resistenciaCompressao;
    }

    public float getqAA() {
        return qAA;
    }

    public float getqAL() {
        return qAL;
    }

    private void calcularCompressao() {
        float q = getFlambagemLocalQ();
        float x = getFlambagemGlobalX();
        float areaBruta = getPerfilCalculo().getAreaBruta();
        float tensaoEscoamentoKNcm2 = getAco().getTensaoEscoamento() / 10;

        this.resistenciaCompressao = q * x * areaBruta * tensaoEscoamentoKNcm2 / GAMA_A1;
    }
}
