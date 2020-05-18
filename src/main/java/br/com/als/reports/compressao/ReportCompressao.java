package br.com.als.reports.compressao;

import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.calculos.compressao.LimiteEsbeltez;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReportCompressao implements Report {

    public static final String PATH_REPORTS = "relatorios/Compressão.png";

    private ResistenciaCompressao r;
    private String bMesa;
    private String tMesa;
    private String esbeltezMesa;
    private String bAba;
    private String tAba;
    private String esbeltezAba;
    private String bAlma;
    private String tAlma;
    private String esbeltezAlma;
    private String inerciaUtilizada;
    private String x;
    private String q;
    private String qa;
    private String qs;
    private String ag;
    private String fy;
    private String k;
    private String comprimentoPeca;
    private String gammaA1 = "1,10";
    private String result;
    private String nomePerfil;
    private String nomeAco;
    private String moduloElasticidade;
    private String cargaCriticaEuler;
    private String lambda0;

    private String laTeX = "";

    public ReportCompressao(ResistenciaCompressao resistenciaCompressao) {
        this.r = resistenciaCompressao;
        x = String.format("%.2f", this.r.getFlambagemGlobalX());
        q = String.format("%.2f", this.r.getFlambagemLocalQ());
        qa = String.format("%.2f", this.r.getqAA());
        qs = String.format("%.2f", this.r.getqAL());
        ag = String.format("%.2fcm^2", this.r.getPerfilCalculo().getAreaBruta());
        fy = String.format("%.2f kN/cm^2", this.r.getAco().getTensaoEscoamento() / 10);
        comprimentoPeca = String.format("%.2f cm", this.r.getComprimentoPeca());
        result = String.format("%.2f kN", this.r.getResistenciaCompressao());
        nomePerfil = r.getPerfilCalculo().getNomePerfil();
        nomeAco = r.getAco().getDenominacao();
        moduloElasticidade = String.format("%.2f kN/cm^2", this.r.getModuloElasticidadeAco().getModuloElasticidadeKNcm2());
        k = String.format("%.2f", this.r.getVinculo().getCoeficienteFlambagem());
        inerciaUtilizada = String.format("%.2f cm^4", this.r.getInerciaUtilizada());
        cargaCriticaEuler = String.format("%.2f kN", this.r.getCargaCriticaEuler());
        lambda0 = String.format("%.2f", this.r.getLambda0());
        setEsbeltez();
    }

    public ResistenciaCompressao getResistenciaCompressao() {
        return r;
    }

    @Override
    public File exportarRelatorio() throws IOException {
        makeLaTeX();

        Insets insets = new Insets(5, 5, 5, 5);
        render(laTeX, insets, 30, PATH_REPORTS);

        return new File(PATH_REPORTS);
    }

    private void makeLaTeX() {
        ncRd();
        breakLine();
        qaXqs();
        flambGlob();
        ncRd(x, q, ag, fy, gammaA1, result);
        System.out.println(laTeX);
    }

    private void flambGlob() {
        laTeX += "\\chi(\\lambda_0)\\rightarrow\\lambda_0(N_e)";
        breakLine();
        laTeX += "N_e=\\frac{\\pi^2 EI}{(KL)^2}";
        breakLine();
        laTeX += String.format("N_e=\\frac{\\pi^2\\cdot %s\\cdot %s}{(%s\\cdot %s)^2}=%s", moduloElasticidade, inerciaUtilizada, k, comprimentoPeca, cargaCriticaEuler);
        breakLine();
        laTeX += "\\lambda_0=\\sqrt{\\frac{Q A_g f_y}{N_e}}=";
        laTeX += String.format("\\sqrt{\\frac{%s\\cdot %s\\cdot %s}{%s}}=%s", q, ag, fy, cargaCriticaEuler, lambda0);
        breakLine();
        if (this.r.getLambda0() <= 1.5) {
            laTeX += "\\lambda_0\\leq1,5\\therefore\\chi=0,658^{\\lambda_0}^2";
            breakLine();
            laTeX += String.format("\\chi=0,658^{%s}^2=%s", lambda0, x);
        } else {
            laTeX += "\\lambda_0 >1,5\\therefore\\chi=\\frac{0,877}{{\\lambda_0}^2}";
            breakLine();
            laTeX += String.format("\\chi=\\frac{0,877}{{%s}^2}=%s", lambda0, x);
        }
        breakLine();
    }

    private String ncRd() {
        return laTeX += "N_{c,rd}=\\frac{\\chi Q A_g f_y}{\\gamma_{a1}}";
    }

    private String ncRd(String x, String q, String ag, String fy, String gammaA1, String result) {
        return laTeX += String.format("N_{c,rd}=\\frac{%s\\cdot %s\\cdot %s\\cdot %s}{%s}=%s", x, q, ag, fy, gammaA1, result);
    }

    private String breakLine() {
        return laTeX += "\\\\";
    }

    private String qaXqs() {
        laTeX += "Q=Q_a\\cdot Q_s";
        breakLine();
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case T:
                laTeX += "Q_a=" + qa;
                breakLine();
                esbeltez();
                breakLine();
                esbeltez(bMesa, tMesa, esbeltezMesa);
                breakLine();
                esbeltezLim(moduloElasticidade, fy);
                breakLine();
                break;
            case L:
                laTeX += "Q_a=" + qa;
                breakLine();
                esbeltez();
                breakLine();
                esbeltez(bAba, tAba, esbeltezAba);
                breakLine();
                esbeltezLim(moduloElasticidade, fy);
                breakLine();
                break;
            case I:
            case H:
            case W:
            case U:
                break;
        }

        return laTeX;
    }

    private void esbeltezLim(String e, String fy) {
        String resultStringLim1;
        String resultStringLim2;
        float resultValorLim1;
        float resultValorLim2;
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case T:
                switch (this.r.getPerfilCalculo().getGrupoAba()) {
                    case GRUPO4:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                        resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        resultStringLim2 = String.format("%.2f", resultValorLim2);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim2) {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=1,415-0,74\\frac{b}{t}\\sqrt{\\frac{f_y}{E}}";
                            breakLine();
                            laTeX += String.format("Q_s=1,415-0,74\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s}}=%s", bAba, tAba, fy, e, qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=\\frac{0,69E}{f_y\\left(\\frac{b}{t}\\right)^2}";
                            breakLine();
                            laTeX += String.format("Q_s=\\frac{0,69%s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, fy, bAba, tAba, qs);
                        }
                        break;
                    case GRUPO5:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO5, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,64\\cdot\\sqrt{\\frac{E}{f_y / k_c}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        }


                        break;
                    case GRUPO6:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO6, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,75\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        }

                        break;
                }
                break;

            case L:
                switch (this.r.getPerfilCalculo().getGrupoAba()) {
                    case GRUPO4:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                        resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        resultStringLim2 = String.format("%.2f", resultValorLim2);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim2) {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=1,415-0,74\\frac{b}{t}\\sqrt{\\frac{f_y}{E}}";
                            breakLine();
                            laTeX += String.format("Q_s=1,415-0,74\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s}}=%s", bAba, tAba, fy, e, qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=\\frac{0,69E}{f_y\\left(\\frac{b}{t}\\right)^2}";
                            breakLine();
                            laTeX += String.format("Q_s=\\frac{0,69%s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, fy, bAba, tAba, qs);
                        }

                        break;
                    case GRUPO3:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO3, this.r.getModuloElasticidadeAco());
                        resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO3, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        resultStringLim2 = String.format("%.2f", resultValorLim2);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,45\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,45\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim2) {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "0,91\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("0,91\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=1,340-0,76\\frac{b}{t}\\sqrt{\\frac{f_y}{E}}";
                            breakLine();
                            laTeX += String.format("Q_s=1,340-0,76\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s}}=%s", bAba, tAba, fy, e, qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        } else {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "0,91\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("0,91\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=\\frac{0,53E}{f_y\\left(\\frac{b}{t}\\right)^2}";
                            breakLine();
                            laTeX += String.format("Q_s=\\frac{0,53%s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, fy, bAba, tAba, qs);
                        }
                        break;
                }
                break;

            case W:
            case I:
            case H:
            case U:
                switch (this.r.getPerfilCalculo().getGrupoMesa()) {
                    case GRUPO4:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        }

                        break;
                    case GRUPO5:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO5, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,64\\cdot\\sqrt{\\frac{E}{f_y / k_c}}=";
                        laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAba() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                            laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, String.format("%.2f", this.r.getFlambagemLocalQ()));
                        }

                        break;
                }
                break;
        }
    }

    private String esbeltez() {
        return laTeX += "\\lambda =\\frac{b}{t}";
    }

    private String esbeltez(String b, String t, String result) {
        return laTeX += String.format("\\lambda =\\frac{%s}{%s}=%s", b, t, result);
    }

    private void setEsbeltez() {
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case L:
            case T:
                bAba = String.format("%.2fmm", this.r.getPerfilCalculo().getLarguraAba());
                tAba = String.format("%.2fmm", this.r.getPerfilCalculo().getEspessuraAba());
                esbeltezAba = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAba());
                break;
            case I:
            case H:
            case W:
            case U:
                bAlma = String.format("%.2fmm", this.r.getPerfilCalculo().getLarguraAlma());
                tAlma = String.format("%.2fmm", this.r.getPerfilCalculo().getEspessuraAlma());
                esbeltezAlma = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAlma());
                bMesa = String.format("%.2fmm", this.r.getPerfilCalculo().getLarguraMesa());
                tMesa = String.format("%.2fmm", this.r.getPerfilCalculo().getEspessuraMesa());
                esbeltezMesa = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezMesa());
                break;
        }
    }
}
