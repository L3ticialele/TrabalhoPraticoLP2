package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasdao.dao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IFuncionarioDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasview.MainFX;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxTipo;

    @FXML
    private TextField textFieldConfirmar;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldSenha;

    @FXML
    private TextField textFieldTelefone;

    private final String[] tipos = {"Administrador", "Atendente", "Entregador"};

    private Funcionario funcionario;

    private final ArrayList<TextField> listTextFields = new ArrayList<>();

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
    void cadastrarFuncionario(ActionEvent event) throws PersistenciaException {
        alert = new Alert(AlertType.NONE);
        String nome, senha, telefone, tipo, confirmarSenha;
        verificarCampos();
        if (!alert.getContentText().equals("Preencha todo os campos.")) {
            verificarSenha();
        }
        if (!alert.getAlertType().equals(AlertType.WARNING)) {
            FuncionarioController funcionarioController = new FuncionarioController();
            nome = textFieldNome.getText();
            senha = textFieldSenha.getText();
            telefone = textFieldTelefone.getText();
            tipo = choiceBoxTipo.getValue();
            confirmarSenha = textFieldConfirmar.getText();
            if(funcionarioController.cadastrarFuncionario(nome, senha, telefone, null, senha)){
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Funcionário cadastrado com sucesso!");
            abrirPaginaFuncionarios(event);
            }else{
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("Ocorreu um erro ao cadastrar o funcionário.");
            }
        }
        alert.show();
    }

    private void verificarSenha() {
        if (!textFieldSenha.getText().equals(textFieldConfirmar.getText())) {
            alert.setAlertType(AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setContentText("Confirme sua senha.");
            textFieldSenha.setText(null);
            textFieldConfirmar.setText(null);
        }

    }

    private void verificarCampos() {
        for (int i = 0; i < listTextFields.size(); i++) {
            if (listTextFields.get(i).getText().equals("") || listTextFields.get(i).getText() == null) {
                alert.setAlertType(AlertType.WARNING);
                alert.setTitle("Atenção!");
                alert.setContentText("Preencha todo os campos.");
            }
        }
        if (choiceBoxTipo.getValue() == null) {
            alert.setAlertType(AlertType.WARNING);
            alert.setTitle("Atenção!");
            alert.setContentText("Preencha todo os campos.");
        }
    }

    @FXML
    void onCancelar(ActionEvent event) {
        MainFX.changedScreen("Sair", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
           }
       });
        choiceBoxTipo.getItems().addAll(tipos);
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldTelefone);
        listTextFields.add(textFieldSenha);
        listTextFields.add(textFieldConfirmar);
    }

}
