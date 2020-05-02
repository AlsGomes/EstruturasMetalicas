package br.com.als.main;

import br.com.als.classes.acos.model.AcoMR250;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.config.JsonReader;
import br.com.als.config.JsonWriter;
import br.com.als.interfaces.Aco;
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

//        JsonWriter.createJson();

        Aco aco = new AcoMR250();
        PerfilModel perfilCalculo = JsonReader.read("perfis/L50.8x50.8x3.2.json");

        ResistenciaCompressao resistenciaCompressao = new ResistenciaCompressao();
        System.out.println(resistenciaCompressao.getResistenciaCompressao(perfilCalculo, aco));
    }
}
