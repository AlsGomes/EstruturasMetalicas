package br.com.als.reports.compressao;

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
    private String x;
    private String q;
    private String qa;
    private String qs;
    private String ag;
    private String fy;
    private String comprimentoPeca;
    private String gammaA1 = "1,10";
    private String result;
    private String nomePerfil;
    private String nomeAco;
    private String moduloElasticidade;

    private String laTeX = "";

    public ReportCompressao(ResistenciaCompressao resistenciaCompressao) {
        this.r = resistenciaCompressao;
        x = String.format("%.2f", this.r.getFlambagemGlobalX());
        q = String.format("%.2f", this.r.getFlambagemLocalQ());
        qa = String.format("%.2f", this.r.getqAA());
        qs = String.format("%.2f", this.r.getqAL());
        ag = String.format("%.2f", this.r.getPerfilCalculo().getAreaBruta());
        fy = String.format("%.2f", this.r.getAco().getTensaoEscoamento() / 10);
        comprimentoPeca = String.format("%.2f", this.r.getComprimentoPeca());
        result = String.format("%.2f", this.r.getResistenciaCompressao());
        nomePerfil = r.getPerfilCalculo().getNomePerfil();
        nomeAco = r.getAco().getDenominacao();
        moduloElasticidade = r.getModuloElasticidadeAco().getDenominacaoGPa();
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
        ncRd(x, q, ag, fy, gammaA1, result);

    }

    private String ncRd() {
        return laTeX += "N_{c,rd}=\\frac{\\chi\\cdot Q\\cdot A_g\\cdot f_y}{\\gamma_{a1}}";
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
            case L:
                laTeX += "Q_a=" + qa;
                breakLine();
                esbeltez();
                breakLine();
                esbeltez(bAba, tAba, esbeltezAba);
                breakLine();
                esbeltezLim();
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

    private void esbeltezLim() {
        switch (this.r.getPerfilCalculo().getPerfil()) {
            case T:
                switch (this.r.getPerfilCalculo().getGrupoAba()) {
                    case GRUPO4:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}";
                        break;
                    case GRUPO5:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";;
                        laTeX += "0,64\\cdot\\sqrt{\\frac{E}{f_y / k_c}}";
                        break;
                    case GRUPO6:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,75\\cdot\\sqrt{\\frac{E}{f_y}}";
                        break;
                }
                break;

            case L:
                switch (this.r.getPerfilCalculo().getGrupoAba()) {
                    case GRUPO4:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}";
                        break;
                    case GRUPO3:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,45\\cdot\\sqrt{\\frac{E}{f_y}}";
                        break;
                }
                break;

            case W:
            case I:
            case H:
            case U:
                switch (this.r.getPerfilCalculo().getGrupoMesa()) {
                    case GRUPO4:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,56\\cdot\\sqrt{\\frac{E}{f_y}}";
                        break;
                    case GRUPO5:
                        laTeX += "\\left(\\frac{b}{t}\\right)_{lim1}=";
                        laTeX += "0,64\\cdot\\sqrt{\\frac{E}{f_y / k_c}}";
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
                bAba = String.format("%.2f", this.r.getPerfilCalculo().getLarguraAba());
                tAba = String.format("%.2f", this.r.getPerfilCalculo().getEspessuraAba());
                esbeltezAba = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAba());
                break;
            case I:
            case H:
            case W:
            case U:
                bAlma = String.format("%.2f", this.r.getPerfilCalculo().getLarguraAlma());
                tAlma = String.format("%.2f", this.r.getPerfilCalculo().getEspessuraAlma());
                esbeltezAlma = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezAlma());
                bMesa = String.format("%.2f", this.r.getPerfilCalculo().getLarguraMesa());
                tMesa = String.format("%.2f", this.r.getPerfilCalculo().getEspessuraMesa());
                esbeltezMesa = String.format("%.2f", this.r.getPerfilCalculo().getEsbeltezMesa());
                break;
        }
    }
}
