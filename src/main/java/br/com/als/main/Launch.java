package br.com.als.main;

import br.com.als.classes.acos.model.ASTMA36;
import br.com.als.classes.acos.model.G35;
import br.com.als.classes.acos.model.MR250;
import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.config.JsonReader;
import br.com.als.config.JsonWriter;
import br.com.als.interfaces.Aco;
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

//        PerfilModel perfilCalculo = new PerfilModel();
//        JsonWriter.createJson(perfilCalculo);

        Aco aco = new MR250();
        String path = "perfis/%s.json";
        PerfilModel perfilCalculo = JsonReader.read(String.format(path, "L76x10.7"));

        ResistenciaCompressao resistenciaCompressao = new ResistenciaCompressao(
                314.72f
                ,perfilCalculo
                , aco
                , CoeficienteFlambagem.K_RECOMENDADO_D_DUPLO_APOIO
                , 250f
                , ModuloElasticidadeAco.GPa200
        );

        ReportCompressao reportCompressao = new ReportCompressao(resistenciaCompressao);
        reportCompressao.exportarRelatorio();

//        System.out.println(resistenciaCompressao.getResistenciaCompressao());
    }
}
