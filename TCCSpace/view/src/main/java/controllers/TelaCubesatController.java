package controllers;

import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.view.MainFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TelaCubesatController implements Initializable {

    @FXML
    private Button botaoDados;

    @FXML
    private Button botaoCubesat;

    @FXML
    private ScrollBar scroll;

    @FXML
    private Button botaoEquipe;

    @FXML
    private Button botaoExplorar;

    @FXML
    AnchorPane panelListaCubesat;

    @FXML
    private Label label;

    private UsuarioDTO usuario;

    @FXML
    private VBox vBoxListarCubesats;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoCadastrarCubesat;

    @FXML
    private ImageView iconeExplorar;

    @FXML
    private ImageView iconeEquipes;

    @FXML
    private Button botaoEditarCubesat;

    private CubeSatDTO cubesat;

    @FXML
    void apresentarTelaDados(ActionEvent event) {
        MainFX.changedScreen("Gui3d", usuario);
    }

    @FXML
    void apresentarTelaEditarCubesat(ActionEvent event, CubeSatDTO cubesat) {
        MainFX.changedScreen("Editar Cubesat", cubesat);
    }

    @FXML
    void explorarToPourple(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorarLilas.png"));
    }

    @FXML
    void explorarToWhite(MouseEvent event) {
        botaoExplorar.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeExplorar.setImage(new Image("file:src/main/resources/images/iconeExplorar.png"));
    }

    @FXML
    void equipesToPourple(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: #8C52FF;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipesLilas.png"));
    }

    @FXML
    void equipesToWhite(MouseEvent event) {
        botaoEquipe.setStyle("-fx-text-fill: white;"
                + "-fx-background-color: 0;");
        iconeEquipes.setImage(new Image("file:src/main/resources/images/iconeEquipes.png"));
    }

    @FXML
    void telaCadastrarCubesat(ActionEvent event) {
        MainFX.changedScreen("Cadastrar Cubesat", usuario);
    }

    @FXML
    void apresentaTelaCubesat(ActionEvent event) {
        MainFX.changedScreen("Cubesat", usuario);
    }

    @FXML
    void apresentaTelaEquipe(ActionEvent event) {
        MainFX.changedScreen("Equipes", usuario);
    }

    @FXML
    void apresentaTelaExplorar(ActionEvent event) {
        MainFX.changedScreen("Explorar", usuario);
    }

    @FXML
    void apresentarTelaInicial(ActionEvent event) {
        MainFX.changedScreen("Tela Inicial", usuario);
    }

    void listarCubes() {
        vBoxListarCubesats = new VBox();
        vBoxListarCubesats.setLayoutX(5);
        vBoxListarCubesats.setLayoutY(5);
        vBoxListarCubesats.setPrefWidth(157);
        vBoxListarCubesats.setPrefHeight(199);
        vBoxListarCubesats.setSpacing(10);
        panelListaCubesat.getChildren().add(vBoxListarCubesats);
        List<CubeSatDTO> cubes = usuario.getCubeSat();
        if (!cubes.isEmpty()) {
            for (int i = 0, j = 0; i < cubes.size(); i++, j += 49) {
                cubesat = cubes.get(i);
                System.out.println(cubes.get(i).getNome());
                Button botao = new Button();
                botao.setId(cubes.get(i).getNome());
                botao.setAlignment(Pos.CENTER);
                botao.setLayoutX(38);
                if (i == 0) {
                    botao.setLayoutX(6);
                }
                botao.setLayoutY(j);
                botao.setMnemonicParsing(false);
                botao.setPrefHeight(40);
                botao.setPrefWidth(147);
                botao.setStyle("-fx-background-color: 0; -fx-border-color: #8c52ff; -fx-border-radius: 2px;");
                botao.setText(cubes.get(i).getNome());
                botao.setTextFill(Color.WHITE);
                botao.setOnAction(event -> {
                    apresentarTelaEditarCubesat(event, cubesat);
                });
                vBoxListarCubesats.getChildren().add(botao);
            }
        }
        scroll.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                vBoxListarCubesats.setLayoutY(-new_val.doubleValue());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
                if (viewData.getClass().equals(UsuarioDTO.class)) {
                    usuario = (UsuarioDTO) viewData;
                    if (newString.equals("Cubesat")) {
                        listarCubes();
                    }
                }
                else if(viewData.getClass().equals(CubeSatDTO.class)){
                    cubesat = (CubeSatDTO)viewData;
                }
            }
        });

    }

}
