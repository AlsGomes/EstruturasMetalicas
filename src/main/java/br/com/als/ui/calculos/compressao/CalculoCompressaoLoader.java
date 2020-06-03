package br.com.als.ui.calculos.compressao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculoCompressaoLoader extends Application {

    public static final String URL_FXML = "/br/com/als/ui/calculos/compressao/CalculoCompressaoUI.fxml";

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(URL_FXML));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
