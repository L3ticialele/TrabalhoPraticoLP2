package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaVisualizarClientesController implements Initializable {
    
    @FXML
    private TableColumn<Cliente, String> colunaBairro;

    @FXML
    private TableColumn<Cliente, String> colunaLogradouro;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaQuantPedidos;

    @FXML
    private TableColumn<Cliente, String> colunaTelefone;
    
    @FXML
    private TableView<Cliente> tabelaClientes;

    private int ultimoCliente;
    
    private ArrayList<Cliente> listaClientes;
    
    private final ClienteController clienteController = new ClienteController();
    
    @FXML
    void abrirPaginaClientes(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarClientes", null);
    }
    
    @FXML
    void abrirPaginaFuncionarios(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarFuncionarios", null);
    }

    @FXML
    void abrirPaginaPedidos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarPedidos", null);
    }

    @FXML
    void abrirPaginaProdutos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarProdutos", null);
    }
    
    @FXML
    void cadastrarCliente(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaCadastrarCliente", null);
    }
    
    @FXML
    private void atualizarDados() throws PersistenciaException{
        listaClientes = clienteController.atualizaDadosCliente(ultimoCliente);
        tabelaClientes.setItems(FXCollections.observableArrayList(listaClientes));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoCliente = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));
        colunaBairro.setCellValueFactory(
                new PropertyValueFactory<>("bairro"));
        colunaLogradouro.setCellValueFactory(
                new PropertyValueFactory<>("logradouro"));
        colunaQuantPedidos.setCellValueFactory(
                new PropertyValueFactory<>("quantPedidos"));
        
        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaVisualizarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
