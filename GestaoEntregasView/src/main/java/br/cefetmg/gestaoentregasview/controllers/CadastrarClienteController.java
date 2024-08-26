package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.ValidaCampos;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarClienteController implements Initializable {
    
    @FXML
    private Label labelSenhaForte;

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

    private final Alert alert = new Alert(Alert.AlertType.NONE);

    private final ClienteController clienteController = new ClienteController();
    
    private final ValidaCampos validador = new ValidaCampos();

    @FXML
    private void cadastrarCliente(ActionEvent event) throws PersistenciaException {
        alert.setAlertType(Alert.AlertType.NONE);
        String nome, telefone, cnpj, cpf, logradouro, bairro, senha;
        verificarCampos();
        if (!alert.getContentText().equals("Preencha todo os campos.")){
            validarCampos();
        }
        if (!alert.getAlertType().equals(Alert.AlertType.WARNING)) {
            nome = textFieldNome.getText();
            cnpj = textFieldCnpj.getText();
            telefone = textFieldTelefone.getText();
            cpf = textFieldCpf.getText();
            logradouro = textFieldLogradouro.getText();
            bairro = textFieldBairro.getText();
            senha = textFieldSenha.getText();
            if (clienteController.cadastrarCliente(nome, logradouro, bairro, cnpj, cpf, null, senha, telefone)) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Cliente cadastrado com sucesso! ");
                abrirPaginaClientes(event);
                setToNull();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Ocorreu um erro ao cadastrar o cliente.");
            }
        }
        alert.show();
    }

    @FXML
    void onCancelar(ActionEvent event) {
        MainFX.changedScreen("TelaLogin", null);
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
    
    private void validarCampos() throws PersistenciaException{
        alert.setAlertType(Alert.AlertType.WARNING);
        labelSenhaForte.setText(null);
        if (!textFieldSenha.getText().equals(textFieldConfirmar.getText())) {
            alert.setContentText("Confirme sua senha.");
        }
        else if (!validador.senhaForte(textFieldSenha.getText())) {
            alert.setContentText("Senha fraca.");
            labelSenhaForte.setText("*Senha forte:  deve conter pelo menos 1 caractere, 1 letra maiúscula, 1 número e 6 digitos.");
        }
        else if (!validador.validarTelefone(textFieldTelefone.getText())) {
                alert.setContentText("Número de telefone inválido.");
            }
        else if(!validador.isCPF(textFieldCpf.getText())){
                alert.setContentText("CPF inválido.");
        }
        else if(!validador.verificaExistenciaCPF(textFieldCpf.getText())){
            alert.setContentText("Já existe um usuário cadastrado com esse CPF.");
        }
        else if(!validador.isCNPJ(textFieldCnpj.getText())){
            alert.setContentText("CNPJ inválido.");
        }
        else if(!validador.validaNome(textFieldNome.getText())){
            alert.setContentText("Nome inválido.");
        }
        else{
            alert.setAlertType(Alert.AlertType.NONE);
        }
        
    }
    
    private void setToNull(){
        textFieldNome.setText(null);
        textFieldBairro.setText(null);
        textFieldLogradouro.setText(null);
        textFieldCpf.setText(null);
        textFieldSenha.setText(null);
        textFieldConfirmar.setText(null);
        textFieldTelefone.setText(null);
        textFieldCnpj.setText(null);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldTelefone);
        listTextFields.add(textFieldBairro);
        listTextFields.add(textFieldLogradouro);
        listTextFields.add(textFieldCnpj);
        listTextFields.add(textFieldCpf);
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
    }

}
