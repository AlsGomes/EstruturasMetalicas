package br.com.als.ui.cadastro.perfis;

import br.com.als.classes.anexos.anexof.Grupo;
import br.com.als.classes.perfis.ElementoPerfil;
import br.com.als.classes.perfis.Perfil;
import br.com.als.classes.perfis.PerfilModel;
import br.com.als.config.JsonWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.validation.Validator;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroPerfisController implements Initializable {

    @FXML
    public JFXButton btn_Cadastrar;
    @FXML
    public JFXComboBox<Perfil> cboPerfis;
    @FXML
    public VBox vboxContainer;

    JFXTextField txtNomePerfil = new JFXTextField();
    JFXTextField txtEspessuraAba = new JFXTextField();
    JFXTextField txtLarguraAba = new JFXTextField();
    JFXTextField txtEsbeltezAba = new JFXTextField();
    JFXTextField txtModuloResistenciaWx = new JFXTextField();
    JFXTextField txtModuloResistenciaWy = new JFXTextField();
    JFXTextField txtAreaBruta = new JFXTextField();
    JFXTextField txtPesoPorMetro = new JFXTextField();
    JFXTextField txtInerciaX = new JFXTextField();
    JFXTextField txtInerciaY = new JFXTextField();
    JFXTextField txtInerciaZ = new JFXTextField();
    JFXTextField txtRaioGiracaoX = new JFXTextField();
    JFXTextField txtRaioGiracaoY = new JFXTextField();
    JFXTextField txtRaioGiracaoZ = new JFXTextField();
    JFXTextField txtEspessuraAlma = new JFXTextField();
    JFXTextField txtEspessuraMesa = new JFXTextField();
    JFXTextField txtLarguraAlma = new JFXTextField();
    JFXTextField txtLarguraMesa = new JFXTextField();
    JFXTextField txtEsbeltezAlma = new JFXTextField();
    JFXTextField txtEsbeltezMesa = new JFXTextField();

    JFXTextField textFieldsPerfilL[] = new JFXTextField[]{
            txtNomePerfil,
            txtEspessuraAba,
            txtLarguraAba,
            txtEsbeltezAba,
            txtModuloResistenciaWx,
            txtModuloResistenciaWy,
            txtAreaBruta,
            txtPesoPorMetro,
            txtInerciaX,
            txtInerciaY,
            txtInerciaZ,
            txtRaioGiracaoX,
            txtRaioGiracaoY,
            txtRaioGiracaoZ,
    };

    JFXTextField textFieldsPerfilIHW[] = new JFXTextField[]{
            txtNomePerfil,
            txtEspessuraAlma,
            txtEspessuraMesa,
            txtLarguraAlma,
            txtLarguraMesa,
            txtEsbeltezAlma,
            txtEsbeltezMesa,
            txtModuloResistenciaWx,
            txtModuloResistenciaWy,
            txtAreaBruta,
            txtPesoPorMetro,
            txtInerciaX,
            txtInerciaY,
            txtInerciaZ,
            txtRaioGiracaoX,
            txtRaioGiracaoY,
            txtRaioGiracaoZ,
    };

    DoubleValidator doubleValidator = new DoubleValidator();
    RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setFieldValues();
        automatizarCampos();
        loadPerfis();
    }

    private void setFieldValues() {
        txtNomePerfil.setPromptText("Nome Perfil");
        txtEspessuraAba.setPromptText("Espessura Aba (mm)");
        txtEspessuraAlma.setPromptText("Espessura Alma (mm)");
        txtEspessuraMesa.setPromptText("Espessura Mesa (mm)");
        txtLarguraAba.setPromptText("Largura Aba (mm)");
        txtLarguraAlma.setPromptText("Largura Alma (mm)");
        txtLarguraMesa.setPromptText("Largura Mesa (mm)");
        txtEsbeltezAba.setPromptText("Esbeltez da Aba");
        txtEsbeltezAlma.setPromptText("Esbeltez da Alma");
        txtEsbeltezMesa.setPromptText("Esbeltez da Mesa");
        txtAreaBruta.setPromptText("Area Bruta (cm2)");
        txtPesoPorMetro.setPromptText("Peso por Metro (kg/m)");
        txtModuloResistenciaWx.setPromptText("Modulo de Resistencia Wx (cm3)");
        txtModuloResistenciaWy.setPromptText("Modulo de Resistencia Wy (cm3)");
        txtInerciaX.setPromptText("Inercia X (cm4)");
        txtInerciaY.setPromptText("Inercia Y (cm4)");
        txtInerciaZ.setPromptText("Inercia Z (cm4)");
        txtRaioGiracaoX.setPromptText("Raio de Giracao x (cm)");
        txtRaioGiracaoY.setPromptText("Raio de Giracao z (cm)");
        txtRaioGiracaoZ.setPromptText("Raio de Giracao Minimo (cm)");

        txtNomePerfil.setLabelFloat(true);
        txtEspessuraAba.setLabelFloat(true);
        txtEspessuraAlma.setLabelFloat(true);
        txtEspessuraMesa.setLabelFloat(true);
        txtLarguraAba.setLabelFloat(true);
        txtLarguraAlma.setLabelFloat(true);
        txtLarguraMesa.setLabelFloat(true);
        txtEsbeltezAba.setLabelFloat(true);
        txtEsbeltezAlma.setLabelFloat(true);
        txtEsbeltezMesa.setLabelFloat(true);
        txtModuloResistenciaWx.setLabelFloat(true);
        txtModuloResistenciaWy.setLabelFloat(true);
        txtAreaBruta.setLabelFloat(true);
        txtPesoPorMetro.setLabelFloat(true);
        txtInerciaX.setLabelFloat(true);
        txtInerciaY.setLabelFloat(true);
        txtInerciaZ.setLabelFloat(true);
        txtRaioGiracaoX.setLabelFloat(true);
        txtRaioGiracaoY.setLabelFloat(true);
        txtRaioGiracaoZ.setLabelFloat(true);

        txtNomePerfil.getValidators().addAll(requiredFieldValidator);
        txtEspessuraAba.getValidators().addAll(doubleValidator);
        txtEspessuraAlma.getValidators().addAll(doubleValidator);
        txtEspessuraMesa.getValidators().addAll(doubleValidator);
        txtLarguraAba.getValidators().addAll(doubleValidator);
        txtLarguraAlma.getValidators().addAll(doubleValidator);
        txtLarguraMesa.getValidators().addAll(doubleValidator);
        txtEsbeltezAba.getValidators().addAll(doubleValidator);
        txtEsbeltezAlma.getValidators().addAll(doubleValidator);
        txtEsbeltezMesa.getValidators().addAll(doubleValidator);
        txtModuloResistenciaWx.getValidators().addAll(doubleValidator);
        txtModuloResistenciaWy.getValidators().addAll(doubleValidator);
        txtAreaBruta.getValidators().addAll(doubleValidator);
        txtPesoPorMetro.getValidators().addAll(doubleValidator);
        txtInerciaX.getValidators().addAll(doubleValidator);
        txtInerciaY.getValidators().addAll(doubleValidator);
        txtInerciaZ.getValidators().addAll(doubleValidator);
        txtRaioGiracaoX.getValidators().addAll(doubleValidator);
        txtRaioGiracaoY.getValidators().addAll(doubleValidator);
        txtRaioGiracaoZ.getValidators().addAll(doubleValidator);
    }

    private void automatizarCampos() {
        btn_Cadastrar.setOnAction(event -> {
            if (verificacoes()) {
                inserirPerfil();
            } else {
                System.out.println("Não Pode Cadastrar");
            }
        });

        txtEspessuraAba.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraAba.validate() && txtLarguraAba.validate()) {
                    float largura = Float.parseFloat(txtLarguraAba.getText()) / 10;
                    float espessura = Float.parseFloat(txtEspessuraAba.getText()) / 10;
                    txtEsbeltezAba.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessura, largura, ElementoPerfil.ABA)));
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaL(espessura, largura)));
                }
            }
        });

        txtEspessuraAlma.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate()) {
                    float largura = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessura = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    txtEsbeltezAlma.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessura, largura, ElementoPerfil.ALMA)));
                }

                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate() && txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraAlma = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessuraAlma = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaIHWU(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, espessuraAlma, larguraAlma)));
                }
            }
        });

        txtEspessuraMesa.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtEsbeltezMesa.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, ElementoPerfil.MESA)));
                }

                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate() && txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraAlma = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessuraAlma = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaIHWU(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, espessuraAlma, larguraAlma)));
                }
            }
        });

        txtLarguraAba.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraAba.validate() && txtLarguraAba.validate()) {
                    float largura = Float.parseFloat(txtLarguraAba.getText()) / 10;
                    float espessura = Float.parseFloat(txtEspessuraAba.getText()) / 10;
                    txtEsbeltezAba.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessura, largura, ElementoPerfil.ABA)));
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaL(espessura, largura)));
                }
            }
        });

        txtLarguraAlma.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate()) {
                    float larguraAlma = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessuraAlma = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    txtEsbeltezAlma.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessuraAlma, larguraAlma, ElementoPerfil.ALMA)));
                }

                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate() && txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraAlma = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessuraAlma = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaIHWU(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, espessuraAlma, larguraAlma)));
                }
            }
        });

        txtLarguraMesa.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtEsbeltezMesa.setText(Float.toString(calcEsbeltez(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, ElementoPerfil.MESA)));
                }

                if (txtEspessuraAlma.validate() && txtLarguraAlma.validate() && txtEspessuraMesa.validate() && txtLarguraMesa.validate()) {
                    float larguraAlma = Float.parseFloat(txtLarguraAlma.getText()) / 10;
                    float espessuraAlma = Float.parseFloat(txtEspessuraAlma.getText()) / 10;
                    float larguraMesa = Float.parseFloat(txtLarguraMesa.getText()) / 10;
                    float espessuraMesa = Float.parseFloat(txtEspessuraMesa.getText()) / 10;
                    txtAreaBruta.setText(Float.toString(calcAreaBrutaIHWU(cboPerfis.getSelectionModel().getSelectedItem(), espessuraMesa, larguraMesa, espessuraAlma, larguraAlma)));
                }
            }
        });

        txtModuloResistenciaWx.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtModuloResistenciaWx.validate()) {
                    txtModuloResistenciaWy.setText(txtModuloResistenciaWx.getText());
                }
            }
        });

        txtModuloResistenciaWy.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtModuloResistenciaWy.validate()) {
                    txtModuloResistenciaWx.setText(txtModuloResistenciaWy.getText());
                }
            }
        });

        txtRaioGiracaoX.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtRaioGiracaoX.validate()) {
                    txtRaioGiracaoY.setText(txtRaioGiracaoX.getText());
                }
            }
        });

        txtRaioGiracaoY.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtRaioGiracaoY.validate()) {
                    txtRaioGiracaoX.setText(txtRaioGiracaoY.getText());
                }
            }
        });

        txtInerciaX.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtInerciaX.validate()) {
                    txtInerciaY.setText(txtInerciaX.getText());
                }

                if (txtInerciaX.validate() && txtAreaBruta.validate()) {
                    float areaBruta = Float.parseFloat(txtAreaBruta.getText());
                    float inerciaX = Float.parseFloat(txtInerciaX.getText());
                    float raioGiracaoX = (float) Math.sqrt(inerciaX / areaBruta);
                    txtRaioGiracaoX.setText(Float.toString(raioGiracaoX));
                }
            }
        });

        txtInerciaY.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtInerciaY.validate()) {
                    txtInerciaX.setText(txtInerciaY.getText());
                }

                if (txtInerciaY.validate() && txtAreaBruta.validate()) {
                    float areaBruta = Float.parseFloat(txtAreaBruta.getText());
                    float inerciaY = Float.parseFloat(txtInerciaY.getText());
                    float raioGiracaoY = (float) Math.sqrt(inerciaY / areaBruta);
                    txtRaioGiracaoY.setText(Float.toString(raioGiracaoY));
                }
            }
        });

        txtRaioGiracaoZ.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (txtRaioGiracaoZ.validate() && txtAreaBruta.validate()) {
                    float raioGiracaoMin = Float.parseFloat(txtRaioGiracaoZ.getText());
                    float areaBruta = Float.parseFloat(txtAreaBruta.getText());
                    float inerciaZ = (float) Math.pow(raioGiracaoMin, 2) * areaBruta;
                    txtInerciaZ.setText(Float.toString(inerciaZ));
                }
            }
        });
    }

    private float calcAreaBrutaL(float espessura, float largura) {
        float areaBruta = (largura * espessura) + ((largura - espessura) * espessura);
        return areaBruta;
    }

    private float calcAreaBrutaIHWU(Perfil perfil, float espessuraMesa, float larguraMesa, float espessuraAlma, float larguraAlma) {
        float areaBruta = 0;

        switch (perfil) {
            case I:
            case H:
            case W:
                areaBruta = ((larguraMesa * espessuraMesa) * 2) + (larguraAlma * espessuraAlma);
                break;
            case U:
                areaBruta = (((larguraMesa - espessuraAlma) * espessuraMesa) * 2) + (larguraAlma * espessuraAlma);
                break;
        }

        return areaBruta;
    }

    private float calcEsbeltez(Perfil perfil, float espessura, float largura, ElementoPerfil elemento) {
        float esbeltez = 0;

        switch (perfil) {
            case I:
            case H:
            case W:
            case T:
                switch (elemento) {
                    case ALMA:
                        esbeltez = largura / espessura;
                        break;
                    case MESA:
                        esbeltez = (largura / 2) / espessura;
                        break;
                }
                break;
            case U:
                switch (elemento) {
                    case ALMA:
                    case MESA:
                        esbeltez = largura / espessura;
                        break;
                }
                break;
            case L:
                switch (elemento) {
                    case ABA:
                        esbeltez = largura / espessura;
                        break;
                }
                break;
        }

        return esbeltez;
    }

    private void inserirPerfil() {
        PerfilModel perfilModel = new PerfilModel();

        switch (cboPerfis.getSelectionModel().getSelectedItem()) {
            case L:
                perfilModel.setNomePerfil(txtNomePerfil.getText());
                perfilModel.setEspessuraAba(Float.parseFloat(txtEsbeltezAba.getText()));
                perfilModel.setLarguraAba(Float.parseFloat(txtLarguraAba.getText()));
                perfilModel.setEsbeltezAba(Float.parseFloat(txtEsbeltezAba.getText()));
                perfilModel.setModuloResistenciaWx(Float.parseFloat(txtModuloResistenciaWx.getText()));
                perfilModel.setModuloResistenciaWy(Float.parseFloat(txtModuloResistenciaWy.getText()));
                perfilModel.setAreaBruta(Float.parseFloat(txtAreaBruta.getText()));
                perfilModel.setPesoPorMetro(Float.parseFloat(txtPesoPorMetro.getText()));
                perfilModel.setInerciaX(Float.parseFloat(txtInerciaX.getText()));
                perfilModel.setInerciaY(Float.parseFloat(txtInerciaY.getText()));
                perfilModel.setInerciaZ(Float.parseFloat(txtInerciaZ.getText()));
                perfilModel.setRaioGiracaoX(Float.parseFloat(txtRaioGiracaoX.getText()));
                perfilModel.setRaioGiracaoY(Float.parseFloat(txtRaioGiracaoY.getText()));
                perfilModel.setRaioGiracaoMin(Float.parseFloat(txtRaioGiracaoZ.getText()));
                perfilModel.setGrupoAba(cboGrupo.getSelectionModel().getSelectedItem());
                perfilModel.setPerfil(Perfil.L);

                JsonWriter jsonWriter = new JsonWriter();
                jsonWriter.createJson(perfilModel);
                break;

            case I:
            case W:
            case H:
                break;
        }
    }

    private void loadPerfis() {

        cboPerfis.getItems().clear();

        for (Perfil perfil : Perfil.values()) {
            cboPerfis.getItems().add(perfil);
        }

        cboPerfis.setOnAction(event -> {
            if (cboPerfis.getSelectionModel().getSelectedItem() != null) {
                loadFields(cboPerfis.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void loadFields(Perfil perfil) {

        doubleValidator.setMessage("Somente numeros racionais.");
        requiredFieldValidator.setMessage("Campo obrigatorio.");
        vboxContainer.getChildren().clear();

        switch (perfil) {
            case I:
            case W:
            case H:
                break;

            case L:
                for (JFXTextField field : textFieldsPerfilL) {
                    field.setPrefWidth(200d);
                }

                HBox hBoxMedidas = new HBox(txtNomePerfil, txtEspessuraAba, txtLarguraAba, txtPesoPorMetro, txtEsbeltezAba, txtAreaBruta);
                hBoxMedidas.setSpacing(20d);

                HBox hBoxModuloResistencia = new HBox(txtModuloResistenciaWx, txtModuloResistenciaWy);
                hBoxModuloResistencia.setSpacing(20d);

                HBox hBoxInercia = new HBox(txtInerciaX, txtInerciaY, txtInerciaZ);
                hBoxInercia.setSpacing(20d);

                HBox hBoxRaioGiracao = new HBox(txtRaioGiracaoX, txtRaioGiracaoY, txtRaioGiracaoZ);
                hBoxRaioGiracao.setSpacing(20d);

                vboxContainer.getChildren().addAll(hBoxMedidas, hBoxModuloResistencia, hBoxInercia, hBoxRaioGiracao);
                break;

        }
    }

    private boolean verificacoes() {
        if (cboPerfis.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        switch (cboPerfis.getSelectionModel().getSelectedItem()) {
            case L:
                for (JFXTextField field : textFieldsPerfilL) {
                    if (!field.validate()) {
                        return false;
                    }
                }
                break;

            case I:
            case H:
            case W:
                for (JFXTextField field : textFieldsPerfilIHW) {
                    if (!field.validate()) {
                        return false;
                    }
                }
                break;
        }

        return true;
    }
}
