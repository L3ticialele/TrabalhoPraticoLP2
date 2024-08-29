
package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaEntregadorController implements Initializable {
    
    @FXML
    private TableColumn<Pedido, String> colunaData;

    @FXML
    private TableColumn<Pedido, String> colunaEndereco;

    @FXML
    private TableColumn<Pedido, String> colunaNome;

    @FXML
    private TableColumn<Pedido, String> colunaSituacao;

    @FXML
    private TableColumn<Pedido, String> colunaTelefone;

    @FXML
    private TableView<Pedido> tabelaEntregas;
    
    private ArrayList<Pedido> listaPedidos;
    
    private int ultimoPedido;
    
    private final PedidoController pedidoController = new PedidoController();
    
    private Funcionario entregador;
    
    @FXML
    private void atualizarDados() throws PersistenciaException{
        listaPedidos = pedidoController.atualizaDadosPedido(ultimoPedido, entregador);
        tabelaEntregas.setItems(FXCollections.observableArrayList(listaPedidos));
    }
    @FXML
    void abrirPaginaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaLogin", null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultimoPedido = 0;
        
        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nomeCliente"));
        colunaEndereco.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        colunaData.setCellValueFactory(
                new PropertyValueFactory<>("data"));
        colunaSituacao.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        colunaTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefoneCliente"));
        
        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
            if(viewData.getClass().equals(Funcionario.class)){
                entregador = (Funcionario) viewData;
            }
        });
    } 
    
}
