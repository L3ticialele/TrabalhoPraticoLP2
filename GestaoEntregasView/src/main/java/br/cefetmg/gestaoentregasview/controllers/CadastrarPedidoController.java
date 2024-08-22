package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.util.ArrayList;
import javafx.collections.FXCollections;
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
    public void salvarPedido() {
        alert = new Alert(AlertType.NONE);
        String nomeProduto, endereco, quantidade, valorUnitario, valorTotal, marca, formaPagamento, observacoes;
        verificarCampos();
        if (!alert.getAlertType().equals(AlertType.WARNING)) {
            nomeProduto = comboBoxProduto.getSelectionModel().getSelectedItem();
            endereco = textFieldEndereco.getText();
            quantidade = textFieldQuantidade.getText();
            valorUnitario = textFieldValorUnitario.getText();
            valorTotal = textFieldValorTotal.getText();
            marca = textFieldMarca.getText();
            formaPagamento = textFieldFormaPagamento.getText();
            observacoes = textAreaObservacoes.getText();
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Pedido cadastrado com sucesso! ");
        }

        alert.show(); //exibe a mensagem
        limparCampos();
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
