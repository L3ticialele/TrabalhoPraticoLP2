package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author letic
 */
public class CadastrarClienteController implements Initializable {

    @FXML
    private TextField textFieldBairro;

    @FXML
    private TextField textFieldCnpj;

    @FXML
    private TextField textFieldCpf;

    @FXML
    private TextField textFieldLogradouro;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private TextField textFieldConfirmar;

    @FXML
    private TextField textFieldTelefone;

    private final ArrayList<TextField> listTextFields = new ArrayList<>();

    private Alert alert;

    private Cliente cliente;

    @FXML
    void cadastrarCliente(ActionEvent event) {
        alert = new Alert(Alert.AlertType.NONE);
        String nome, telefone, cnpj, cpf, logradouro, bairro, senha;
        verificarCampos();
        if (!alert.getContentText().equals("Preencha todo os campos.")) {
            verificarSenha();
        }
        if (!alert.getAlertType().equals(Alert.AlertType.WARNING)) {
            nome = textFieldNome.getText();
            cnpj = textFieldCnpj.getText();
            telefone = textFieldTelefone.getText();
            cpf = textFieldCpf.getText();
            logradouro = textFieldLogradouro.getText();
            bairro = textFieldBairro.getText();
            senha = textFieldSenha.getText();
            cliente = new Cliente(nome, logradouro, bairro, cnpj, cpf, null, senha, telefone);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Funcionário cadastrado com sucesso! ");
            onCancelar(event);
        }
        alert.show();
    }

    @FXML
    void onCancelar(ActionEvent event) {
        MainFX.changedScreen("Sair", null);
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

    private void verificarSenha() {
        if (!textFieldSenha.getText().equals(textFieldConfirmar.getText())) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setContentText("Confirme sua senha.");
            textFieldSenha.setText(null);
            textFieldConfirmar.setText(null);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldTelefone);
        listTextFields.add(textFieldBairro);
        listTextFields.add(textFieldLogradouro);
        listTextFields.add(textFieldCnpj);
        listTextFields.add(textFieldCpf);
    }

}
