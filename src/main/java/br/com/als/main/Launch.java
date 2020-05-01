package br.com.als.main;

import br.com.als.classes.acos.model.AcoMR250;
import br.com.als.config.JsonWriter;
import br.com.als.interfaces.Perfil;
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

//        String pacotePerfis = "br.com.als.classes.perfis.model.";
//        String perfilSelecionado = "L50x50x3";
//        String perfilUtilizado = pacotePerfis + perfilSelecionado;
//
//        Perfil perfil = (Perfil) Class.forName(perfilUtilizado).getDeclaredConstructor().newInstance();

        JsonWriter.createJson();
    }
}
