package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.UsuarioController;
import br.cefetmg.gestaoentregasentidades.Usuario;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private TextField campoSenha;

    @FXML
    private TextField campoCpf;

    @FXML
    private Label msg;

    UsuarioController usuarioController = new UsuarioController();
    
    private Usuario user;

    public void BotaoEntrar(ActionEvent e) throws PersistenciaException {
        String senha = campoSenha.getText();
        String cpf = campoCpf.getText();
        user = usuarioController.login(cpf, senha);
        if (campoSenha.getText().isBlank() == true || campoCpf.getText().isBlank() == true) {
            msg.setText("Preencha todos os campos!");
        } else if (user!=null) {
            MainFX.changedScreen(usuarioController.direcionarTela(user), user);
        } else {
            msg.setText("Telefone e/ou senha incorreto!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
    }

}
