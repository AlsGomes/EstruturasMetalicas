package br.com.als.main;

import br.com.als.classes.acos.model.*;
import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.config.JsonReader;
import br.com.als.reports.compressao.ReportCompressao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launch extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new StackPane(new Label("Testing")), 500, 500);

        stage.setScene(scene);
        stage.show();

        String pathAcos = "acos/%s.json";
        AcoModel aco = JsonReader.readAco(String.format(pathAcos, "ASTMA36"));

        String pathPerfis = "perfis/%s.json";
        PerfilModel perfilCalculo = JsonReader.readPerfil(String.format(pathPerfis, "CS H 292mm x 240mm"));

        ResistenciaCompressao resistenciaCompressao = new ResistenciaCompressao(
                //314.72f,
                perfilCalculo
                , null
                , perfilCalculo.getGrupoAlma()[0]
                , perfilCalculo.getGrupoMesa()[1]
                , aco
                , CoeficienteFlambagem.K_RECOMENDADO_D_DUPLO_APOIO
                , 650f
                , ModuloElasticidadeAco.GPa205
        );

        ReportCompressao reportCompressao = new ReportCompressao(resistenciaCompressao);
        reportCompressao.exportarRelatorio();
    }
}
