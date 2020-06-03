package br.com.als.ui.calculos.compressao;

import br.com.als.classes.acos.model.AcoModel;
import br.com.als.classes.acos.moduloelasticidade.ModuloElasticidadeAco;
import br.com.als.classes.anexos.anexoe.CoeficienteFlambagem;
import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.calculos.compressao.ResistenciaCompressao;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.classes.suporte.SmartImageView;
import br.com.als.config.JsonReader;
import br.com.als.reports.compressao.ReportCompressao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.ResourceBundle;

public class CalculoCompressaoController implements Initializable {

    @FXML
    public VBox vboxContainer;
    @FXML
    public VBox vboxGrupos;
    @FXML
    public WebView webV;

    @FXML
    private JFXComboBox<AcoModel> cboAco;

    @FXML
    private JFXComboBox<PerfilModel> cboPerfil;

    @FXML
    private JFXComboBox<CoeficienteFlambagem> cboVinculo;

    @FXML
    private JFXTextField txtComprimento;

    @FXML
    private JFXComboBox<ModuloElasticidadeAco> cboModuloElasticidade;

    @FXML
    private JFXButton btnOk;

    @FXML
    private Pane imgReport;

    DoubleValidator doubleValidator = new DoubleValidator();

    JFXComboBox<Grupo> cboGrupoAlma = new JFXComboBox<>();
    JFXComboBox<Grupo> cboGrupoMesa = new JFXComboBox<>();
    JFXComboBox<Grupo> cboGrupoAba = new JFXComboBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFields();
        automateFields();
        startWebViewer();
    }

    private void automateFields() {
        cboPerfil.setOnAction(event -> {
            Perfil perfil = cboPerfil.getSelectionModel().getSelectedItem().getPerfil();
            vboxGrupos.getChildren().clear();
            if (perfil != null) {
                switch (perfil) {
                    case L:
                        vboxGrupos.getChildren().addAll(cboGrupoAba);

                        cboGrupoAba.getItems().clear();
                        cboGrupoAba.getItems().addAll(cboPerfil.getSelectionModel().getSelectedItem().getGrupoAba());
                        if (cboGrupoAba.getItems().size() == 1) {
                            cboGrupoAba.getSelectionModel().selectFirst();
                        }
                        break;
                    case I:
                    case H:
                    case W:
                    case T:
                    case U:
                        vboxGrupos.getChildren().addAll(cboGrupoAlma, cboGrupoMesa);

                        cboGrupoAlma.getItems().clear();
                        cboGrupoAlma.getItems().addAll(cboPerfil.getSelectionModel().getSelectedItem().getGrupoAlma());
                        if (cboGrupoAlma.getItems().size() == 1) {
                            cboGrupoAlma.getSelectionModel().selectFirst();
                        }

                        cboGrupoMesa.getItems().clear();
                        cboGrupoMesa.getItems().addAll(cboPerfil.getSelectionModel().getSelectedItem().getGrupoMesa());
                        if (cboGrupoMesa.getItems().size() == 1) {
                            cboGrupoMesa.getSelectionModel().selectFirst();
                        }
                        break;
                }
            }
        });

        txtComprimento.getValidators().addAll(doubleValidator);

        btnOk.setOnAction(event -> {
            if (verificacoes()) {
                AcoModel aco = cboAco.getSelectionModel().getSelectedItem();
                PerfilModel perfil = cboPerfil.getSelectionModel().getSelectedItem();
                CoeficienteFlambagem vinculo = cboVinculo.getSelectionModel().getSelectedItem();
                float comprimentoPeca = Float.parseFloat(txtComprimento.getText());
                ModuloElasticidadeAco moduloElasticidadeAco = cboModuloElasticidade.getSelectionModel().getSelectedItem();
                Grupo grupoAba = cboGrupoAba.getSelectionModel().getSelectedItem();
                Grupo grupoAlma = cboGrupoAlma.getSelectionModel().getSelectedItem();
                Grupo grupoMesa = cboGrupoMesa.getSelectionModel().getSelectedItem();

                ResistenciaCompressao resistenciaCompressao;
                ReportCompressao reportCompressao;

                switch (perfil.getPerfil()) {
                    case L:
                        resistenciaCompressao = new ResistenciaCompressao(
                                //314.72f,
                                perfil
                                , grupoAba
                                , null
                                , null
                                , aco
                                , vinculo
                                , comprimentoPeca
                                , moduloElasticidadeAco
                        );
                        reportCompressao = new ReportCompressao(resistenciaCompressao);
                        try {
                            reportCompressao.exportarRelatorio();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        imgReport.setImage(new Image(ReportCompressao.PATH_REPORTS));
                        break;
                    case I:
                    case H:
                    case W:
                    case T:
                    case U:
                        resistenciaCompressao = new ResistenciaCompressao(
                                //314.72f,
                                perfil
                                , null
                                , grupoAlma
                                , grupoMesa
                                , aco
                                , vinculo
                                , comprimentoPeca
                                , moduloElasticidadeAco
                        );
                        reportCompressao = new ReportCompressao(resistenciaCompressao);
                        try {
                            reportCompressao.exportarRelatorio();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgReport.getChildren().addAll(new SmartImageView().getImageView("Compressao.png"));
//                        imgReport.setImage(new Image("Compressao.png"));
                        break;
                }
            }
        });
    }

    private boolean verificacoes() {

        if (cboAco.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        if (cboPerfil.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        if (cboVinculo.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        if (!txtComprimento.validate()) {
            return false;
        }

        if (cboModuloElasticidade.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        Perfil perfil = cboPerfil.getSelectionModel().getSelectedItem().getPerfil();
        switch (perfil) {
            case L:
                if (cboGrupoAba.getSelectionModel().getSelectedItem() == null) {
                    return false;
                }
                break;
            case I:
            case H:
            case W:
            case T:
            case U:
                if (cboGrupoAlma.getSelectionModel().getSelectedItem() == null || cboGrupoMesa.getSelectionModel().getSelectedItem() == null) {
                    return false;
                }
                break;

            default:
                return false;
        }

        return true;
    }

    private void initFields() {
        doubleValidator.setMessage("Somente Numeros Racionais");

        cboAco.getItems().clear();
        File diretorioAcos = new File("acos");
        for (File aco : Objects.requireNonNull(diretorioAcos.listFiles())) {
            try {
                AcoModel acoModel = JsonReader.readAco(aco.getPath());
                cboAco.getItems().addAll(acoModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cboPerfil.getItems().clear();
        File diretorioPerfis = new File("perfis");
        for (File perfil : Objects.requireNonNull(diretorioPerfis.listFiles())) {
            try {
                PerfilModel perfilModel = JsonReader.readPerfil(perfil.getPath());
                cboPerfil.getItems().addAll(perfilModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cboVinculo.getItems().clear();
        cboVinculo.getItems().addAll(CoeficienteFlambagem.values());

        cboModuloElasticidade.getItems().clear();
        cboModuloElasticidade.getItems().addAll(ModuloElasticidadeAco.values());

        cboGrupoAba.setPromptText("Grupo da Aba");
        cboGrupoAlma.setPromptText("Grupo da Alma");
        cboGrupoMesa.setPromptText("Grupo da Mesa");

        cboGrupoAba.setPadding(new Insets(5, 5, 5, 5));
        cboGrupoAlma.setPadding(new Insets(5, 5, 5, 5));
        cboGrupoMesa.setPadding(new Insets(5, 5, 5, 5));

        cboGrupoAba.setPrefWidth(cboAco.getPrefWidth());
        cboGrupoAlma.setPrefWidth(cboAco.getPrefWidth());
        cboGrupoMesa.setPrefWidth(cboAco.getPrefWidth());

        cboGrupoAba.setLabelFloat(true);
        cboGrupoAlma.setLabelFloat(true);
        cboGrupoMesa.setLabelFloat(true);
    }

    private void startWebViewer() {
        WebEngine engine = webV.getEngine();
        String url = getClass().getResource("/web/viewer.html").toExternalForm();

        // connect CSS styles to customize pdf.js appearance
//        engine.setUserStyleSheetLocation(getClass().getResource("/web.css").toExternalForm());

        engine.setJavaScriptEnabled(true);
        engine.load(url);

        engine.getLoadWorker()
                .stateProperty()
                .addListener((observable, oldValue, newValue) -> {
                    // to debug JS code by showing console.log() calls in IDE console
//                    JSObject window = (JSObject) engine.executeScript("window");
//                    window.setMember("java", new JSLogListener());
//                    engine.executeScript("console.log = function(message){ java.log(message); };");

                    // this pdf file will be opened on application startup
                    if (newValue == Worker.State.SUCCEEDED) {
                        try {
                            // readFileToByteArray() comes from commons-io library
                            byte[] data = Files.readAllBytes((new File("relatorios/Compressao.pdf").toPath()));
                            String base64 = Base64.getEncoder().encodeToString(data);
                            // call JS function from Java code
                            engine.executeScript("openFileFromBase64('" + base64 + "')");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
