package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class TelaVisualizarProdutosController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLocalizacao;

    private final ProdutoController produtoController = new ProdutoController();

    private final ArrayList<TextField> listTextFields = new ArrayList<>();

    private final Alert alert = new Alert(AlertType.NONE);
    
    @FXML
    void onCancelar(ActionEvent event) {
        MainFX.changedScreen("TelaLogin", null);
    }

    @FXML
    void abrirPaginaClientes(ActionEvent event) {
        MainFX.changedScreen("TelaVisualizarClientes", null);
    }

    @FXML
    void abrirPaginaFuncionarios(ActionEvent event) {
        MainFX.changedScreen("TelaVisualizarFuncionarios", null);
    }

    @FXML
    void abrirPaginaPedidos(ActionEvent event) {
        MainFX.changedScreen("TelaVisualizarPedidos", null);
    }

    @FXML
    void abrirPaginaProdutos(ActionEvent event) {
        MainFX.changedScreen("TelaVisualizarProdutos", null);
    }

    @FXML
    void cadastrarProduto() throws PersistenciaException {
        String nome, localizacao;
        alert.setAlertType(AlertType.NONE);

        nome = textFieldNome.getText();
        localizacao = textFieldLocalizacao.getText();
        verificarCampos();

        if (alert.getAlertType().equals(Alert.AlertType.NONE)) {
            if (produtoController.cadastrarProduto(nome, localizacao, null)) {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Produto cadastrado com sucesso.");
            } else {
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("Ocorreu um erro ao inserir o produto.");
            }
        }
        
        alert.show();
    }

    private void verificarCampos() {
        for (int i = 0; i < listTextFields.size(); i++) {
            if (listTextFields.get(i).getText().equals("") || listTextFields.get(i).getText() == null) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Atenção!");
                alert.setContentText("Preencha todo os campos.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldLocalizacao);
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
            }
        });
    }

}
