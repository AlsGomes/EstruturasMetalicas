package br.com.als.classes.acos.model;

public class AcoModel {

    private String denominacao;

    private float tensaoEscoamento;
    private float tensaoRuptura;

    public float getTensaoEscoamento() {
        return tensaoEscoamento;
    }

    public float getTensaoRuptura() {
        return tensaoRuptura;
    }

    public String getDenominacao() {
        return denominacao;
    }

    public void setDenominacao(String denominacao) {
        this.denominacao = denominacao;
    }

    public void setTensaoEscoamento(float tensaoEscoamento) {
        this.tensaoEscoamento = tensaoEscoamento;
    }

    public void setTensaoRuptura(float tensaoRuptura) {
        this.tensaoRuptura = tensaoRuptura;
    }

    @Override
    public String toString() {
        return denominacao;
    }
}
