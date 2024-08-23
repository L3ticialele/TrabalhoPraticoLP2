package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregasdao.dao.PedidoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IPedidoDAO;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class CadastrarPedidoController {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private TextField textFieldQuantidade;
    @FXML
    private TextField textFieldValorUnitario;
    @FXML
    private TextField textFieldValorTotal;
    @FXML
    private TextField textFieldMarca;
    @FXML
    private TextField textFieldFormaPagamento;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextArea textAreaObservacoes;

    private final ArrayList<TextField> listTextField = new ArrayList<>();

    private Pedido pedido;

    private Alert alert;

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
    public void salvarPedido() throws PersistenciaException {
        alert = new Alert(AlertType.NONE);
        String nomeProduto, endereco, marca, formaPagamento, observacoes;
        int quantidade;
        double valorUnitario, valorTotal;
        Date data = new Date();
        verificarCampos();
        if (!alert.getAlertType().equals(AlertType.WARNING)) {
            IPedidoDAO pedidoDAO = new PedidoDAO();
            nomeProduto = comboBoxProduto.getSelectionModel().getSelectedItem();
            endereco = textFieldEndereco.getText();
            quantidade = Integer.parseInt(textFieldQuantidade.getText());
            valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());
            valorTotal = Double.parseDouble(textFieldValorTotal.getText());
            marca = textFieldMarca.getText();
            formaPagamento = textFieldFormaPagamento.getText();
            observacoes = textAreaObservacoes.getText();
            
            pedido = new Pedido(data, valorTotal, "EMPREPARACAO", null,  marca,  quantidade, valorUnitario, formaPagamento, endereco, null);
            pedidoDAO.inserir(pedido);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Pedido cadastrado com sucesso! ");
            MainFX.changedScreen("TelaVisualizarPedidos", null);
        }

        alert.show(); 
    }

    private void verificarCampos() {
        for (int i = 0; i < listTextField.size(); i++) {
            if (listTextField.get(i).getText() == null || listTextField.get(i).getText().equals("")) {
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("Preencha todos os campos.");
                alert.setTitle("Atenção!");
            }
        }
        if (textAreaObservacoes.getText() == null || textAreaObservacoes.getText().equals("")) {
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Preencha todos os campos.");
            alert.setTitle("Atenção!");
        }
    }

    private void limparCampos() {
        comboBoxProduto.setValue(null);
        textFieldQuantidade.setText(null);
        textFieldValorUnitario.setText(null);
        textFieldValorTotal.setText(null);
        textFieldMarca.setText(null);
        textFieldFormaPagamento.setText(null);
        textFieldEndereco.setText(null);
        textAreaObservacoes.setText(null);
    }

    @FXML
    private void onCancelar() {
        MainFX.changedScreen("Sair", null);

    }
    
    @FXML
    private void initialize() {
        comboBoxProduto.setItems(FXCollections.observableArrayList("Produto 1", "Produto 2", "Produto 3"));
        listTextField.add(textFieldQuantidade);
        listTextField.add(textFieldValorUnitario);
        listTextField.add(textFieldValorTotal);
        listTextField.add(textFieldMarca);
        listTextField.add(textFieldFormaPagamento);
        listTextField.add(textFieldEndereco);
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
           }
       });
    }
}
