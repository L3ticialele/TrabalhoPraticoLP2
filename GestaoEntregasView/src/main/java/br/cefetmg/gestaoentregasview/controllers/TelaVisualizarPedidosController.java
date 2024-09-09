package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TableColumn<Pedido, String> colunaCpfCliente;
     
    @FXML
    private TableColumn<Pedido, String> colunaObservacoes;
    
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
    
    private final ArrayList lista = new ArrayList<>();
    
    private final ProdutoController produtoController = new ProdutoController();
    
    private final FuncionarioController funcionarioController = new FuncionarioController();
    
    
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
    void cadastrarPedido() throws PersistenciaException, IOException{
        lista.add(funcionarioController.nomeEntregadores(funcionarioController.listarFuncionarios()));
        lista.add(produtoController.nomeProdutos(produtoController.listarProdutos()));
        MainFX.changedScreen("TelaCadastrarPedido", lista);
    }
    
    @FXML
    void abrirPaginaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaLogin", null);
    }
    
    @FXML
    void atualizarDados() throws PersistenciaException{
        listaPedidos = pedidoController.atualizaDadosPedido(ultimoPedido, null);
        tabelaPedidos.setItems(FXCollections.observableArrayList(listaPedidos));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoPedido = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nomeCliente"));
        colunaEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colunaEntregador.setCellValueFactory(
                new PropertyValueFactory<>("nomeEntregador"));
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
        colunaCpfCliente.setCellValueFactory(
                new PropertyValueFactory<>("cpfCliente"));
        colunaObservacoes.setCellValueFactory(
                new PropertyValueFactory<>("observacoes"));
        try {
            atualizarDados();
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaVisualizarClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    
}
