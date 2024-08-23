package br.cefetmg.gestaoentregasview.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author KEHILARY
 */
public class TelaVisualizarPedidosController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    @FXML
    void cadastrarPedido(){
        MainFX.changedScreen("TelaCadastrarPedido", null);
    }
    
}
