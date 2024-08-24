package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.ValidaCampos;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
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
import javafx.scene.control.Label;

public class CadastrarFuncionarioController implements Initializable {
    
    @FXML
    private Label labelSenhaForte;

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

    @FXML
    private TextField textFieldCpf;

    private final String[] tipos = {"Administrador", "Atendente", "Entregador"};

    private Funcionario funcionario;

    private final ArrayList<TextField> listTextFields = new ArrayList<>();

    private final Alert alert = new Alert(AlertType.NONE);

    private final ValidaCampos validador = new ValidaCampos();

    private @FXML
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
        alert.setAlertType(AlertType.NONE);
        String nome, senha, telefone, tipo, confirmarSenha, cpf;
        verificarCampos();
        if (!alert.getContentText().equals("Preencha todo os campos.")) {
            validarCampos();
        }
        if (!alert.getAlertType().equals(AlertType.WARNING)) {
            FuncionarioController funcionarioController = new FuncionarioController();
            nome = textFieldNome.getText();
            senha = textFieldSenha.getText();
            telefone = textFieldTelefone.getText();
            tipo = choiceBoxTipo.getValue();
            confirmarSenha = textFieldConfirmar.getText();
            cpf = textFieldCpf.getText();
            if (funcionarioController.cadastrarFuncionario(nome, senha, telefone, null, tipo, cpf)) {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Funcionário cadastrado com sucesso!");
                abrirPaginaFuncionarios(event);
                setToNull();
            } else {
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("Ocorreu um erro ao cadastrar o funcionário.");
            }
        }
        alert.show();
    }

    private void setToNull() {
        choiceBoxTipo.setValue(null);
        textFieldNome.setText(null);
        textFieldCpf.setText(null);
        textFieldSenha.setText(null);
        textFieldConfirmar.setText(null);
        textFieldTelefone.setText(null);
    }

    private void validarCampos() throws PersistenciaException {
        alert.setAlertType(Alert.AlertType.WARNING);
        labelSenhaForte.setText(null);
        if (!textFieldSenha.getText().equals(textFieldConfirmar.getText())) {
            alert.setContentText("Confirme sua senha.");
        } else if (!validador.senhaForte(textFieldSenha.getText())) {
            alert.setContentText("Senha fraca.");
            labelSenhaForte.setText("*Senha forte:  deve conter pelo menos 1 caractere, 1 letra maiúscula, 1 número e 6 digitos.");
        } else if (!validador.validarTelefone(textFieldTelefone.getText())) {
            alert.setContentText("Número de telefone inválido.");
        } else if (!validador.isCPF(textFieldCpf.getText())) {
            alert.setContentText("CPF inválido.");
        } else if (!validador.verificaExistenciaCPF(textFieldCpf.getText())) {
            alert.setContentText("Já existe um usuário cadastrado com esse CPF.");
        } else if (!validador.validaNome(textFieldNome.getText())) {
            alert.setContentText("Nome inválido.");
        } else {
            alert.setAlertType(Alert.AlertType.NONE);
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
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
            }
        });
        choiceBoxTipo.getItems().addAll(tipos);
        listTextFields.add(textFieldNome);
        listTextFields.add(textFieldTelefone);
        listTextFields.add(textFieldSenha);
        listTextFields.add(textFieldConfirmar);
    }

}
