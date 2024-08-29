package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Pedido;
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

public class TelaClienteController implements Initializable {

    @FXML
    private TableColumn<Pedido, String> colunaData;

    @FXML
    private TableColumn<Pedido, String> colunaEndereco;

    @FXML
    private TableColumn<Pedido, String> colunaEntregador;

    @FXML
    private TableColumn<Pedido, String> colunaMarca;

    @FXML
    private TableColumn<Pedido, String> colunaPagamento;

    @FXML
    private TableColumn<Pedido, String> colunaProduto;

    @FXML
    private TableColumn<Pedido, String> colunaQuantidade;

    @FXML
    private TableColumn<Pedido, String> colunaTelefone;

    @FXML
    private TableColumn<Pedido, String> colunaValorTotal;

    @FXML
    private TableColumn<Pedido, String> colunaValorUnitario;

    @FXML
    private TableColumn<Pedido, String> colunaObservacoes;

    @FXML
    private TableView<Pedido> tabelaPedidos;
    
    private final PedidoController pedidoController = new PedidoController();
    
    private ArrayList<Pedido> listaPedidos;
    
    private int ultimoPedido;
    
    private Cliente cliente;

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
    void abrirPaginaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaLogin", null);
  
    }

    @FXML
    private void atualizarDados() throws PersistenciaException{
        listaPedidos = pedidoController.atualizaDadosPedido(ultimoPedido, cliente);
        tabelaPedidos.setItems(FXCollections.observableArrayList(listaPedidos));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoPedido = 0;

        colunaEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colunaMarca.setCellValueFactory(
                new PropertyValueFactory<>("marca"));
        colunaProduto.setCellValueFactory(
                new PropertyValueFactory<>("nomeProduto"));
        colunaObservacoes.setCellValueFactory(
                new PropertyValueFactory<>("observacoes"));
        colunaPagamento.setCellValueFactory(
                new PropertyValueFactory<>("pagamento"));
        colunaValorTotal.setCellValueFactory(
                new PropertyValueFactory<>("valorTotal"));
        colunaValorUnitario.setCellValueFactory(
                new PropertyValueFactory<>("valorUnitario"));
        colunaQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        colunaData.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        colunaEntregador.setCellValueFactory(
                new PropertyValueFactory<>("nomeEntregador"));
        colunaTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefoneCliente"));

        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if(viewData.getClass().equals(Cliente.class)){
                cliente = (Cliente) viewData;
            }
        });
    }
    
    

}