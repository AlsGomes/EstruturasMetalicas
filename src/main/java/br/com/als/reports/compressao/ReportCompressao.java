package br.com.als.reports.compressao;

import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.calculos.compressao.LimiteEsbeltez;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;
import br.com.als.classes.perfis.Perfil;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ReportCompressao implements Report {

    public static final String PATH_REPORTS = "relatorios/Compressão.png";

    private ResistenciaCompressao r;
    private String bMesa;
    private String bMesaMetade;
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
    private String larguraEfetiva;
    private String areaEfetiva;
    private String areaBruta;
    private String kcGrupo5;

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
        areaEfetiva = String.format("%.2f cm^2", this.r.getAreaEfetiva());
        larguraEfetiva = String.format("%.2f cm", this.r.getLarguraEfetiva());
        areaBruta = String.format("%.2f cm^2", this.r.getPerfilCalculo().getAreaBruta());
        kcGrupo5 = String.format("%.2f", this.r.getKcGrupo5());
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
        breakLine();
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case T:
                laTeX += "Q=Q_s";
                esbeltez();
                breakLine();
                esbeltez(bMesaMetade, tMesa, esbeltezMesa);
                breakLine();
                esbeltezLimMesa(moduloElasticidade, fy);

                esbeltez();
                breakLine();
                esbeltez(bAlma, tAlma, esbeltezAlma);
                breakLine();
                esbeltezLimAlma(moduloElasticidade, fy);

                laTeX += String.format("Q=%s", q);
                break;
            case L:
                laTeX += "Q=Q_s";
                breakLine();
                esbeltez();
                breakLine();
                esbeltez(bAba, tAba, esbeltezAba);
                breakLine();
                esbeltezLimAba(moduloElasticidade, fy);
                breakLine();
                break;
            case I:
            case H:
            case W:
                laTeX += "Q=Q_a Q_s";
                esbeltez();
                breakLine();
                esbeltez(bAlma, tAlma, esbeltezAlma);
                breakLine();
                esbeltezLimAlma(moduloElasticidade, fy);

                esbeltez();
                breakLine();
                esbeltez(bMesaMetade, tMesa, esbeltezMesa);
                breakLine();
                esbeltezLimMesa(moduloElasticidade, fy);

                laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, q);
                break;
            case U:
                laTeX += "Q=Q_a Q_s";
                esbeltez();
                breakLine();
                esbeltez(bAlma, tAlma, esbeltezAlma);
                breakLine();
                esbeltezLimAlma(moduloElasticidade, fy);

                esbeltez();
                breakLine();
                esbeltez(bMesa, tMesa, esbeltezMesa);
                breakLine();
                esbeltezLimMesa(moduloElasticidade, fy);

                laTeX += String.format("Q=%s\\cdot %s=%s", qa, qs, q);
                break;
        }
        breakLine();

        return laTeX;
    }

    private void esbeltezLimAba(String e, String fy) {
        String resultStringLim1;
        String resultStringLim2;
        float resultValorLim1;
        float resultValorLim2;
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case L:
                switch (this.r.getGrupoAba()) {
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
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
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
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
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
                            breakLine();
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
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
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
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
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
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
                            breakLine();
                            laTeX += String.format("Q=%s", String.format("%.2f", this.r.getFlambagemLocalQ()));
                        }
                        break;
                }
                break;
        }
    }

    private void esbeltezLimMesa(String e, String fy) {
        String resultStringLim1;
        String resultStringLim2;
        float resultValorLim1;
        float resultValorLim2;
        String bUtilizado = (this.r.getPerfilCalculo().getPerfil().equals(Perfil.U) ? bMesa : bMesaMetade);
        switch (this.r.getGrupoMesa()) {
            case GRUPO4:
                resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO4, this.r.getModuloElasticidadeAco());
                resultStringLim1 = String.format("%.2f", resultValorLim1);
                resultStringLim2 = String.format("%.2f", resultValorLim2);
                laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}=";
                laTeX += String.format("0,56\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                breakLine();
                if (this.r.getPerfilCalculo().getEsbeltezMesa() < resultValorLim1) {
                    laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                    breakLine();
                } else if (this.r.getPerfilCalculo().getEsbeltezMesa() < resultValorLim2) {
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                    laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                    laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                    breakLine();
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                    breakLine();
                    laTeX += "Q_s=1,415-0,74\\frac{b}{t}\\sqrt{\\frac{f_y}{E}}";
                    breakLine();
                    laTeX += String.format("Q_s=1,415-0,74\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s}}=%s", bUtilizado, tMesa, fy, e, qs);
                    breakLine();
                } else {
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                    laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                    laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                    breakLine();
                    laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                    breakLine();
                    laTeX += "Q_s=\\frac{0,69E}{f_y\\left(\\frac{b}{t}\\right)^2}";
                    breakLine();
                    laTeX += String.format("Q_s=\\frac{0,69%s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, fy, bMesa, tMesa, qs);
                }
                break;

            case GRUPO5:
                resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO5, this.r.getModuloElasticidadeAco());
                resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO5, this.r.getModuloElasticidadeAco());
                resultStringLim1 = String.format("%.2f", resultValorLim1);
                resultStringLim2 = String.format("%.2f", resultValorLim2);
                laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                laTeX += "0,64\\cdot\\sqrt{\\frac{E}{f_y/k_c}}";
                breakLine();
                laTeX += "k_c=\\frac{4}{\\sqrt{h/t_w}}";
                breakLine();
                laTeX += String.format("k_c=\\frac{4}{\\sqrt{%s/%s}}=%s", bAlma, tAlma, kcGrupo5);
                breakLine();
                laTeX += String.format("0,64\\cdot\\sqrt{\\frac{%s}{%s/%s}}=%s", e, fy, kcGrupo5, resultStringLim1);
                breakLine();
                if (this.r.getPerfilCalculo().getEsbeltezMesa() < resultValorLim1) {
                    laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                    breakLine();
                } else if (this.r.getPerfilCalculo().getEsbeltezMesa() < resultValorLim2) {
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                    laTeX += "1,17\\sqrt{\\frac{E}{f_y/k_c}}=";
                    laTeX += String.format("1,17\\cdot\\sqrt{\\frac{%s}{%s/%s}}=%s", e, fy, kcGrupo5, resultStringLim2);
                    breakLine();
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                    breakLine();
                    laTeX += "Q_s=1,415-0,65\\frac{b}{t}\\sqrt{\\frac{f_y}{k_c\\cdot E}}";
                    breakLine();
                    laTeX += String.format("Q_s=1,415-0,65\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s\\cdot %s}}=%s", bUtilizado, tMesa, fy, kcGrupo5, e, qs);
                    breakLine();
                } else {
                    laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                    laTeX += "1,17\\cdot\\sqrt{\\frac{E}{f_y/k_c}}=";
                    laTeX += String.format("1,17\\cdot\\sqrt{\\frac{%s}{%s/%s}}=%s", e, fy, kcGrupo5, resultStringLim2);
                    breakLine();
                    laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                    breakLine();
                    laTeX += "Q_s=\\frac{0,90E k_c}{f_y\\left(\\frac{b}{t}\\right)^2}";
                    breakLine();
                    laTeX += String.format("Q_s=\\frac{0,90\\cdot %s\\cdot %s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, kcGrupo5, fy, bMesa, tMesa, qs);
                    breakLine();
                }
                break;
        }
    }

    private void esbeltezLimAlma(String e, String fy) {
        String resultStringLim1;
        String resultStringLim2;
        float resultValorLim1;
        float resultValorLim2;
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case T:
                switch (this.r.getGrupoAlma()) {
                    case GRUPO6:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO6, this.r.getModuloElasticidadeAco());
                        resultValorLim2 = LimiteEsbeltez.getEsbeltezLim2(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO6, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        resultStringLim2 = String.format("%.2f", resultValorLim2);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,75\\cdot\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("0,75\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAlma() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_s=%s", qs);
                            breakLine();
                        } else if (this.r.getPerfilCalculo().getEsbeltezAlma() < resultValorLim2) {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}<\\lambda <\\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=1,908-1,22\\frac{b}{t}\\sqrt{\\frac{f_y}{E}}";
                            breakLine();
                            laTeX += String.format("Q_s=1,908-1,22\\cdot\\frac{%s}{%s}\\cdot\\sqrt{\\frac{%s}{%s}}=%s", bAlma, tAlma, fy, e, qs);
                            breakLine();
                        } else {
                            laTeX += "\\left(\\frac{b}{t}\\right)_{lim2}=";
                            laTeX += "1,03\\cdot\\sqrt{\\frac{E}{f_y}}=";
                            laTeX += String.format("1,03\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim2);
                            breakLine();
                            laTeX += "\\lambda > \\left(\\frac{b}{t}\\right)_{lim2}";
                            breakLine();
                            laTeX += "Q_s=\\frac{0,69E}{f_y\\left(\\frac{b}{t}\\right)^2}";
                            breakLine();
                            laTeX += String.format("Q_s=\\frac{0,69%s}{%s\\cdot\\left(\\frac{%s}{%s}\\right)^2}=%s", e, fy, bAlma, tAlma, qs);
                            breakLine();
                        }
                        break;
                }
                break;

            case W:
            case I:
            case H:
            case U:
                switch (this.r.getGrupoAlma()) {
                    case GRUPO1:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO1, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "1,40\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("1,40\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAlma() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_a=%s", qa);
                            breakLine();
                        } else {
                            laTeX += "Q_a=\\frac{A_{ef}}{A_g}";
                            breakLine();
                            laTeX += "b_{ef}=1,92t\\sqrt{\\frac{E}{\\sigma}}\\left[1-\\frac{c_a}{b/t}\\sqrt{\\frac{E}{\\sigma}}\\right]\\leq b";
                            breakLine();
                            laTeX += String.format("\\sigma=f_y=%s", fy);
                            breakLine();
                            laTeX += "c_a=0,34";
                            breakLine();
                            laTeX += String.format("b_{ef}=1,92\\cdot %s\\sqrt{\\frac{%s}{%s}}\\left[1-\\frac{%s}{%s/%s}\\sqrt{\\frac{%s}{%s}}\\right]\\leq %s=%s", tAlma, e, fy, "0,34", bAlma, tAlma, e, fy, bAlma, larguraEfetiva);
                            breakLine();
                            laTeX += "A_{ef}=A_g-(b-b_{ef})t";
                            breakLine();
                            laTeX += String.format("A_{ef}=%s-(%s-%s)%s=%s", areaBruta, bAlma, larguraEfetiva, tAlma, areaEfetiva);
                            breakLine();
                            laTeX += String.format("Q_a=\\frac{%s}{%s}=%s", areaEfetiva, areaBruta, qa);
                            breakLine();
                        }

                        break;
                    case GRUPO2:
                        resultValorLim1 = LimiteEsbeltez.getEsbeltezLim1(this.r.getPerfilCalculo(), this.r.getAco(), Grupo.GRUPO2, this.r.getModuloElasticidadeAco());
                        resultStringLim1 = String.format("%.2f", resultValorLim1);
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "1,49\\sqrt{\\frac{E}{f_y}}=";
                        laTeX += String.format("1,49\\cdot\\sqrt{\\frac{%s}{%s}}=%s", e, fy, resultStringLim1);
                        breakLine();
                        if (this.r.getPerfilCalculo().getEsbeltezAlma() < resultValorLim1) {
                            laTeX += String.format("\\lambda <\\left(\\frac{b}{t}\\right)_{lim1}\\therefore Q_a=%s", qa);
                            breakLine();
                        } else {
                            laTeX += "Q_a=\\frac{A_{ef}}{A_g}";
                            breakLine();
                            laTeX += "b_{ef}=1,92t\\sqrt{\\frac{E}{\\sigma}}\\left[1-\\frac{c_a}{b/t}\\sqrt{\\frac{E}{\\sigma}}\\right]\\leq b";
                            breakLine();
                            laTeX += String.format("\\sigma=f_y=%s", fy);
                            breakLine();
                            laTeX += "c_a=0,34";
                            breakLine();
                            laTeX += String.format("b_{ef}=1,92\\cdot %s\\sqrt{\\frac{%s}{%s}}\\left[1-\\frac{%s}{%s/%s}\\sqrt{\\frac{%s}{%s}}\\right]\\leq %s=%s", tAlma, e, fy, "0,34", bAlma, tAlma, e, fy, bAlma, larguraEfetiva);
                            breakLine();
                            laTeX += "A_{ef}=A_g-(b-b_{ef})t";
                            breakLine();
                            laTeX += String.format("A_{ef}=%s-(%s-%s)%s=%s", areaBruta, bAlma, larguraEfetiva, tAlma, areaEfetiva);
                            breakLine();
                            laTeX += String.format("Q_a=\\frac{%s}{%s}=%s", areaEfetiva, areaBruta, qa);
                            breakLine();
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
                bAba = String.format("%.2fcm", this.r.getPerfilCalculo().getLarguraAba() / 10);
                tAba = String.format("%.2fcm", this.r.getPerfilCalculo().getEspessuraAba() / 10);
                esbeltezAba = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAba());
                break;
            case I:
            case H:
            case W:
            case U:
                bAlma = String.format("%.2fcm", this.r.getPerfilCalculo().getLarguraAlma() / 10);
                tAlma = String.format("%.2fcm", this.r.getPerfilCalculo().getEspessuraAlma() / 10);
                esbeltezAlma = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAlma());

                bMesa = String.format("%.2fcm", this.r.getPerfilCalculo().getLarguraMesa() / 10);
                bMesaMetade = String.format("%.2fcm", (this.r.getPerfilCalculo().getLarguraMesa() / 2) / 10);
                tMesa = String.format("%.2fcm", this.r.getPerfilCalculo().getEspessuraMesa() / 10);
                esbeltezMesa = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezMesa());
                break;
        }
    }
}
