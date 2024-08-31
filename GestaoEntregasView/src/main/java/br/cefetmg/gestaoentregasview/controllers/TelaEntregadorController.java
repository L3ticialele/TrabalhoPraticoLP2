package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.EntregadorController;
import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField dataFim;

    @FXML
    private TextField dataInicio;

    @FXML
    private TableView<Pedido> tabelaEntregas;

    private ArrayList<Pedido> listaPedidos;

    private int ultimoPedido;

    private final PedidoController pedidoController = new PedidoController();

    private final EntregadorController entregadorController = new EntregadorController();

    private Funcionario entregador;

    private Alert alert;

    @FXML
    private void atualizarDados() throws PersistenciaException {
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
            if (viewData.getClass().equals(Funcionario.class)) {
                entregador = (Funcionario) viewData;
            }
        });
    }

    public void gerarRelatorio() throws IOException, PersistenciaException {
        alert = new Alert(Alert.AlertType.NONE);
        String inicio = dataInicio.getText();
        String fim = dataFim.getText();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
        LocalDate dataInicio = LocalDate.parse(inicio, formato);
        LocalDate dataFim = LocalDate.parse(fim, formato);
        LocalDate dataAtual = LocalDate.now();
        
        
        if (dataInicio.isAfter(dataAtual)) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("As datas não podem ser futuras.");
            alert.show();
        }
        else if (dataInicio.isAfter(dataFim)) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("A data de início não pode ser maior do que a final.");
            alert.show();
        }
        else {
            ultimoPedido = 0;
            listaPedidos = pedidoController.atualizaDadosPedido(ultimoPedido, entregador);
            entregadorController.gerarRelatorio(inicio, fim, listaPedidos, entregador);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Verifique seu relatório no Exlorador de arquivos.");
            alert.show();
        }}
        catch(DateTimeParseException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Arrume o formato das datas. Use o formato dd/mm/aaaa.");
            alert.show();
        }
    }
}
