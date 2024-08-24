package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasview.MainFX;
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

public class TelaVisualizarFuncionariosController implements Initializable {
    
    @FXML
    private TableColumn<Funcionario, String> colunaTelefone;

    @FXML
    private TableColumn<Funcionario, String> colunaTipo;

    @FXML
    private TableColumn<Funcionario, String> colunaNome;
    
    @FXML
    private TableView<Funcionario> tabelaFuncionarios;
    
    private int ultimoFuncionario;
    
    private ArrayList<Funcionario> listaFuncionarios;
    
    private final FuncionarioController funcionarioController = new FuncionarioController();
    
    @FXML
    private void atualizarDados() throws PersistenciaException{
        listaFuncionarios = funcionarioController.atualizaDadosCliente(ultimoFuncionario);
        tabelaFuncionarios.setItems(FXCollections.observableArrayList(listaFuncionarios));
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
    void cadastrarFuncionario(){
        MainFX.changedScreen("TelaCadastrarFuncionario", null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoFuncionario = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colunaTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));
        colunaTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipoPerfil"));
        
        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaVisualizarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
