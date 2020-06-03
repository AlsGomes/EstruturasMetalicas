package br.com.als.classes.anexos.anexoe;

public enum CoeficienteFlambagem {

    K_RECOMENDADO_A_DUPLO_ENGASTE(0.65f),
    K_RECOMENDADO_B_ENGASTE_E_APOIO(0.8f),
    K_RECOMENDADO_C_ENGASTE_E_ROTACAO_IMPEDIDA_COM_TRANSLACAO_LIVRE(1.2f),
    K_RECOMENDADO_D_DUPLO_APOIO(1.0f),
    K_RECOMENDADO_E_ENGASTE_E_LIVRE(2.1f),
    K_RECOMENDADO_F_APOIO_E_ROTACAO_IMPEDIDA_COM_TRANSLACAO_LIVRE(2.0f),

    K_TEORICO_A_DUPLO_ENGASTE(0.5f),
    K_TEORICO_B_ENGASTE_E_APOIO(0.7f),
    K_TEORICO_C_ENGASTE_E_ROTACAO_IMPEDIDA_COM_TRANSLACAO_LIVRE(1.0f),
    K_TEORICO_D_DUPLO_APOIO(1.0f),
    K_TEORICO_E_ENGASTE_E_LIVRE(2.0f);

    float coeficienteFlambagem;

    CoeficienteFlambagem(float coeficienteFlambagem) {
        this.coeficienteFlambagem = coeficienteFlambagem;
    }

    public float getCoeficienteFlambagem() {
        return coeficienteFlambagem;
    }
    }
