package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregasentidades.Usuario;
import br.cefetmg.gestaoentregasdao.dao.UsuarioDAO;
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

    private Usuario user;

    

    public void BotaoEntrar(ActionEvent e) {
        String senha = CampoSenha.getText();
        String telefone = CampoTelefone.getText();
        if (CampoSenha.getText().isBlank() == true || CampoTelefone.getText().isBlank() == true) {
            msg.setText("Preencha todos os campos!");
        } else if (validarLogin(telefone, senha)) {
            UsuarioDAO aux = new UsuarioDAO();
            String tela = aux.tipo(user);
            MainFX.changedScreen(tela, user);
        } else {
            msg.setText("Telefone e/ou senha incorreto!");
        }
    }

    public boolean validarLogin(String telefone, String senha) {
        Usuario usuario = new Usuario();
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.validarLogin(usuario)) {
            user = usuarioDAO.procurarPorTelefone(telefone);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
