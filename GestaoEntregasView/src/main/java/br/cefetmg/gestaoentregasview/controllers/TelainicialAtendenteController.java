package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregasentidades.Usuario;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class TelainicialAtendenteController implements Initializable {
  @FXML
    private Button ButaoAtendenete;

    @FXML
    private Button ButaoEntregador;

    @FXML
    private Button ButaoPedido;

    @FXML
    private Button ButaoPerfil;

    @FXML
    private Button ButaoSobre;

    @FXML
    private Button butaoCliente;
    
    @FXML
    private Button voltar;
    private Usuario usuario;
    
    
    @FXML
    void BotaoVoltar(ActionEvent event){
       MainFX.changedScreen("Login", usuario);
    }
    @FXML
    void TelaAA(ActionEvent event) {
        MainFX.changedScreen("TelaCadastroAtendente", usuario);
    }

    @FXML
    void TelaAC(ActionEvent event) {
        MainFX.changedScreen("telaCadastroCliente", usuario);

    }

    @FXML
    void TelaAE(ActionEvent event) {
        MainFX.changedScreen("TelaCadastroEntregador", usuario);

    }

    @FXML
    void TelaAPE(ActionEvent event) {
        MainFX.changedScreen("TelaCadastroPedido", usuario);

    }

    @FXML
    void TelaAPF(ActionEvent event) {
        MainFX.changedScreen("TelaPerfilAtendente", usuario);

    }

    @FXML
    void TelaAS(ActionEvent event) {
        MainFX.changedScreen("TelaCadastroSobre", usuario);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
