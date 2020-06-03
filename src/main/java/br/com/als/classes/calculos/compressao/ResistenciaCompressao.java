package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.model.AcoModel;
import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.PerfilModel;

public class ResistenciaCompressao {

    private float resistenciaCompressao;
    private float flambagemLocalQ;
    private float qAA;
    private float qAL;
    private float larguraEfetiva;
    private float areaEfetiva;
    private float flambagemGlobalX;
    private float inerciaUtilizada;
    private PerfilModel perfilCalculo;
    private AcoModel aco;
    private CoeficienteFlambagem vinculo;
    private float comprimentoPeca;
    private ModuloElasticidadeAco moduloElasticidadeAco;
    private float cargaCriticaEuler;
    private float lambda0;
    private float kcGrupo5;
    private Grupo grupoAba;
    private Grupo grupoMesa;
    private Grupo grupoAlma;

    private static final float GAMA_A1 = 1.10f;

    public ResistenciaCompressao(PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, AcoModel aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        this.perfilCalculo = perfilCalculo;
        this.aco = aco;
        this.vinculo = vinculo;
        this.comprimentoPeca = comprimentoPeca;
        this.moduloElasticidadeAco = moduloElasticidadeAco;
        FlambagemLocalQ fQ = new FlambagemLocalQ();
        this.flambagemLocalQ = fQ.getQ(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco);
        this.qAA = fQ.getqAA();
        this.qAL = fQ.getqAL();
        this.areaEfetiva = fQ.getAreaEfetiva();
        this.larguraEfetiva = fQ.getLarguraEfetiva();
        this.kcGrupo5 = fQ.getKcGrupo5();
        FlambagemGlobalX fX = new FlambagemGlobalX();
        this.flambagemGlobalX = fX.getX(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        this.inerciaUtilizada = fX.getInerciaUtilizada();
        this.cargaCriticaEuler = fX.getCargaCriticaEuler();
        this.lambda0 = fX.getLambda0();
        this.grupoAba = grupoAba;
        this.grupoAlma = grupoAlma;
        this.grupoMesa = grupoMesa;
        calcularCompressao();
    }

    public ResistenciaCompressao(float momentoInercia, PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, AcoModel aco, CoeficienteFlambagem vinculo, float comprimentoPeca, ModuloElasticidadeAco moduloElasticidadeAco) {
        this.perfilCalculo = perfilCalculo;
        this.aco = aco;
        this.vinculo = vinculo;
        this.comprimentoPeca = comprimentoPeca;
        this.moduloElasticidadeAco = moduloElasticidadeAco;
        FlambagemLocalQ fQ = new FlambagemLocalQ();
        this.flambagemLocalQ = fQ.getQ(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco);
        this.qAA = fQ.getqAA();
        this.qAL = fQ.getqAL();
        this.areaEfetiva = fQ.getAreaEfetiva();
        this.larguraEfetiva = fQ.getLarguraEfetiva();
        this.kcGrupo5 = fQ.getKcGrupo5();
        FlambagemGlobalX fX = new FlambagemGlobalX();
        this.flambagemGlobalX = fX.getX(momentoInercia, perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, vinculo, comprimentoPeca, moduloElasticidadeAco);
        this.inerciaUtilizada = momentoInercia;
        this.cargaCriticaEuler = fX.getCargaCriticaEuler();
        this.lambda0 = fX.getLambda0();
        this.grupoAba = grupoAba;
        this.grupoAlma = grupoAlma;
        this.grupoMesa = grupoMesa;
        calcularCompressao();
    }

    public Grupo getGrupoAba() {
        return grupoAba;
    }

    public Grupo getGrupoMesa() {
        return grupoMesa;
    }

    public Grupo getGrupoAlma() {
        return grupoAlma;
    }

    public float getKcGrupo5() {
        return kcGrupo5;
    }

    public float getLarguraEfetiva() {
        return larguraEfetiva;
    }

    public float getAreaEfetiva() {
        return areaEfetiva;
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

    public AcoModel getAco() {
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
