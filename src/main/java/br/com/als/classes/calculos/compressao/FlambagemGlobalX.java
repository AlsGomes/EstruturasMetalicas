package br.com.als.classes.calculos.compressao;

import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.interfaces.Aco;

public class FlambagemGlobalX {

    private float inerciaUtilizada;
    private float cargaCriticaEuler;
    private float lambda0;

    public float getInerciaUtilizada() {
        return inerciaUtilizada;
    }

    public float getCargaCriticaEuler() {
        return cargaCriticaEuler;
    }

    public float getLambda0() {
        return lambda0;
    }

    public float getX(PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPecaCm, ModuloElasticidadeAco moduloElasticidadeAco) {

        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;
        float le = vinculo.getCoeficienteFlambagem() * comprimentoPecaCm;

        float momentoInercia;
        if (perfilCalculo.getPerfil().equals(Perfil.L)) {
            momentoInercia = perfilCalculo.getInerciaZ();
        } else {
            momentoInercia = Math.min(perfilCalculo.getInerciaX(), perfilCalculo.getInerciaY());
        }
        this.inerciaUtilizada = momentoInercia;

        this.cargaCriticaEuler = (float) ((Math.pow(Math.PI, 2) * moduloElasticidadeKNcm2 * momentoInercia) / Math.pow(le, 2));
        float q = new FlambagemLocalQ().getQ(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        this.lambda0 = (float) Math.sqrt((q * areaBruta * tensaoEscoamentoKNcm2) / (this.cargaCriticaEuler));

        float x;
        if (this.lambda0 <= 1.5) {
            x = (float) Math.pow(0.658, Math.pow(this.lambda0, 2));
        } else {
            x = (float) (0.877 / Math.pow(this.lambda0, 2));
        }

        return x;
    }

    public float getX(float momentoInercia, PerfilModel perfilCalculo, Grupo grupoAba, Grupo grupoAlma, Grupo grupoMesa, Aco aco, CoeficienteFlambagem vinculo, float comprimentoPecaCm, ModuloElasticidadeAco moduloElasticidadeAco) {

        float moduloElasticidadeKNcm2 = moduloElasticidadeAco.getModuloElasticidadeKNcm2();
        float tensaoEscoamentoKNcm2 = aco.getTensaoEscoamento() / 10;
        float le = vinculo.getCoeficienteFlambagem() * comprimentoPecaCm;

        this.inerciaUtilizada = momentoInercia;
        this.cargaCriticaEuler = (float) ((Math.pow(Math.PI, 2) * moduloElasticidadeKNcm2 * momentoInercia) / Math.pow(le, 2));
        float q = new FlambagemLocalQ().getQ(perfilCalculo, grupoAba, grupoAlma, grupoMesa, aco, moduloElasticidadeAco);
        float areaBruta = perfilCalculo.getAreaBruta();
        this.lambda0 = (float) Math.sqrt((q * areaBruta * tensaoEscoamentoKNcm2) / (this.cargaCriticaEuler));

        float x;
        if (this.lambda0 <= 1.5) {
            x = (float) Math.pow(0.658, Math.pow(this.lambda0, 2));
        } else {
            x = (float) (0.877 / Math.pow(this.lambda0, 2));
        }

        return x;
    }
}
