package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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

    private String linha;

    private String[] palavras;

    private ArrayList<Pedido> pedidos = new ArrayList<>();

    private int ultimaLinhaLida;

    private Pedido pedido;
    
    private Cliente cliente;

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
    public void atualizarDados() throws PersistenciaException {
        PedidoController pedidoController = new PedidoController();
        pedidoController.listarPedidos(cliente);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimaLinhaLida = 0;

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
                new PropertyValueFactory<>("entregador"));
        colunaTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));

      //  atualizarDados();
    }
    
    

}