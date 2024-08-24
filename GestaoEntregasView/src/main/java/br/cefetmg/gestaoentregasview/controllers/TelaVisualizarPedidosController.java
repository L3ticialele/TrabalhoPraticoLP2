package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Pedido;
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

public class TelaVisualizarPedidosController implements Initializable {
    
    @FXML
    private TableColumn<Pedido, String> colunaEndereco;
    
    @FXML
    private TableColumn<Pedido, String> colunaEntregador;
    
    @FXML
    private TableColumn<Pedido, String> colunaMarca;
    
    @FXML
    private TableColumn<Pedido, String> colunaNome;
    
    @FXML
    private TableColumn<Pedido, String> colunaPagamento;
    
    @FXML
    private TableColumn<Pedido, String> colunaQuantidade;
    
    @FXML
    private TableColumn<Pedido, String> colunaValorTotal;
    
    @FXML
    private TableColumn<Pedido, String> colunaValorUnitario;
    
    @FXML
    private TableColumn<Pedido, String> colunaStatus;
    
    @FXML
    private TableView<Pedido> tabelaPedidos;
    
    private int ultimoPedido;
    
    private ArrayList<Pedido> listaPedidos;
    
    private final PedidoController pedidoController = new PedidoController();
    
    
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
    
    @FXML
    void atualizarDados() throws PersistenciaException{
        listaPedidos = pedidoController.atualizaDadosCliente(ultimoPedido);
        tabelaPedidos.setItems(FXCollections.observableArrayList(listaPedidos));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoPedido = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colunaEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colunaEntregador.setCellValueFactory(
                new PropertyValueFactory<>("entregador"));
        colunaMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca"));
        colunaPagamento.setCellValueFactory(
                new PropertyValueFactory<>("pagamento"));
        colunaQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        colunaValorTotal.setCellValueFactory(
                new PropertyValueFactory<>("valorTotal"));
        colunaValorUnitario.setCellValueFactory(
                new PropertyValueFactory<>("valorUnitario"));
        colunaStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaVisualizarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    
}
