package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.UsuarioController;
import br.cefetmg.gestaoentregasentidades.Usuario;
import br.cefetmg.gestaoentregasdao.dao.UsuarioDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private Button BotaoEntrar;

    @FXML
    private TextField CampoSenha;

    @FXML
    private TextField CampoTelefone;

    @FXML
    private Label msg;

    UsuarioController usuarioController = new UsuarioController();
    
    private Usuario user;

    public void BotaoEntrar(ActionEvent e) throws PersistenciaException {
        String senha = CampoSenha.getText();
        String telefone = CampoTelefone.getText();
        user = usuarioController.login(telefone, senha);
        if (CampoSenha.getText().isBlank() == true || CampoTelefone.getText().isBlank() == true) {
            msg.setText("Preencha todos os campos!");
        } else if (user!=null) {
            MainFX.changedScreen(usuarioController.direcionarTela(user), user);
        } else {
            msg.setText("Telefone e/ou senha incorreto!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainFX.addOnChangeScreenListener(new MainFX.OnChangeScreen(){
           @Override
           public void onScreenChanged(String newString, Object viewData){
           }
       });
    }

}
