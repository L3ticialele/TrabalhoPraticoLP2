/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.gestaoentregasview.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author letic
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField textFieldTelefone;
    
    @FXML
    private TextField textFieldSenha;
    
    @FXML
    private void entrar(ActionEvent event){
        
        String telefone, senha;
        telefone = textFieldTelefone.getText();
        senha = textFieldSenha.getText();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
