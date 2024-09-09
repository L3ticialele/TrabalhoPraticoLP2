package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Produto;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaVisualizarProdutosController implements Initializable {

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldLocalizacao;
    
    @FXML
    private TableColumn<Produto, String> colunaNome;
    
    @FXML
    private TableColumn<Produto, String> colunaLocalizacao;
    
    @FXML
    private TableView<Produto> tabelaProdutos;

    private final ProdutoController produtoController = new ProdutoController();

    private final ArrayList<TextField> listTextFields = new ArrayList<>();

    private final Alert alert = new Alert(AlertType.NONE);
    
    private int ultimoProduto;

    private ArrayList<Produto> listaProdutos;
    
    @FXML
    void abrirPaginaClientes(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarClientes", null);
    }

    @FXML
    void abrirPaginaFuncionarios(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarFuncionarios", null);
    }

    @FXML
    void abrirPaginaPedidos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarPedidos", null);
    }

    @FXML
    void abrirPaginaProdutos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarProdutos", null);
    }
    
    @FXML
    void abrirPaginaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaLogin", null);
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
        atualizarDados();
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
    
    @FXML
    void atualizarDados() throws PersistenciaException{
        listaProdutos = produtoController.atualizaDadosProduto(ultimoProduto);
        tabelaProdutos.setItems(FXCollections.observableArrayList(listaProdutos));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoProduto = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colunaLocalizacao.setCellValueFactory(
                new PropertyValueFactory<>("localizacao"));
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldLocalizacao);
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
        
        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaVisualizarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
