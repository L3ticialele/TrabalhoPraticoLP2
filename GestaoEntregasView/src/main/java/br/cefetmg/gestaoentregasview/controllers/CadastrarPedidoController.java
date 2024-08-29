package br.cefetmg.gestaoentregasview.controllers;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasview.MainFX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class CadastrarPedidoController {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private ComboBox<String> comboBoxEntregadores;
    @FXML
    private TextField textFieldQuantidade;
    @FXML
    private TextField textFieldValorUnitario;
    @FXML
    private TextField textFieldValorTotal;
    @FXML
    private TextField textFieldMarca;
    @FXML
    private TextField textFieldFormaPagamento;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextArea textAreaObservacoes;
    @FXML
    private TextField textFieldCpfCliente;

    private List<String> listaFuncionarios;

    private final ArrayList<TextField> listTextField = new ArrayList<>();

    private final PedidoController pedidoController = new PedidoController();

    private final ClienteController clienteController = new ClienteController();

    private final FuncionarioController funcionarioController = new FuncionarioController();

    private final ProdutoController produtoController = new ProdutoController();

    private Pedido pedido;

    private Alert alert;

    private Cliente cliente;

    private Funcionario entregador;

    private ArrayList<String> listaProdutos;

    private ArrayList<Object> lista;

    @FXML
    void abrirPaginaClientes(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarClientes", null);
        limparCampos();
    }

    @FXML
    void abrirPaginaFuncionarios(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarFuncionarios", null);
        limparCampos();
    }

    @FXML
    void abrirPaginaPedidos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarPedidos", null);
        limparCampos();
    }

    @FXML
    void abrirPaginaProdutos(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaVisualizarProdutos", null);
        limparCampos();
    }
    
    @FXML
    void abrirPaginaLogin(ActionEvent event) throws IOException {
        MainFX.changedScreen("TelaLogin", null);
        limparCampos();
    }

    @FXML
    public void salvarPedido() throws PersistenciaException, IOException {
        alert = new Alert(AlertType.NONE);
        String nomeProduto, endereco, marca, formaPagamento, observacoes, cpf;
        int quantidade;
        double valorUnitario, valorTotal;
        Date data = new Date();
        verificarCampos();
        cliente = clienteController.buscarClientePorCpf(textFieldCpfCliente.getText());
        if (!alert.getAlertType().equals(AlertType.WARNING)) {
            nomeProduto = comboBoxProduto.getSelectionModel().getSelectedItem();
            endereco = textFieldEndereco.getText();
            quantidade = Integer.parseInt(textFieldQuantidade.getText());
            valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());
            valorTotal = Double.parseDouble(textFieldValorTotal.getText());
            marca = textFieldMarca.getText();
            formaPagamento = textFieldFormaPagamento.getText();
            observacoes = textAreaObservacoes.getText();
            cpf = textFieldCpfCliente.getText();
            entregador = funcionarioController.buscarFuncionarioPorNome(comboBoxEntregadores.getValue());
            if (pedidoController.cadastrarPedido(data, valorTotal, "EMPREPARACAO", cliente, marca, quantidade, valorUnitario, formaPagamento, endereco, entregador, observacoes)) {
                alert.setAlertType(AlertType.INFORMATION);
                alert.setContentText("Pedido cadastrado com sucesso! ");
                MainFX.changedScreen("TelaVisualizarPedidos", null);
                limparCampos();
            } else {
                alert.setAlertType(AlertType.ERROR);
                alert.setContentText("Ocorreu um erro ao cadastrar o pedido.");
            }
        }

        alert.show();
    }

    private void verificarCampos() {
        for (int i = 0; i < listTextField.size(); i++) {
            if (listTextField.get(i).getText() == null || listTextField.get(i).getText().equals("")){
                alert.setAlertType(AlertType.WARNING);
                alert.setContentText("Preencha todos os campos.");
                alert.setTitle("Atenção!");
            }
        }
        if (!alert.getAlertType().equals(AlertType.WARNING) && cliente == null) {
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Cliente não encontrado.");
            alert.setTitle("Atenção!");
        }

    }

    private void limparCampos() {
        comboBoxProduto.setValue(null);
        textFieldQuantidade.setText(null);
        textFieldValorUnitario.setText(null);
        textFieldValorTotal.setText(null);
        textFieldMarca.setText(null);
        textFieldFormaPagamento.setText(null);
        textFieldEndereco.setText(null);
        textAreaObservacoes.setText(null);
    }

    @FXML
    private void onCancelar() throws IOException {
        MainFX.changedScreen("Sair", null);

    }

    private void iniciaComboBox() {
        try {
            listaFuncionarios = funcionarioController.nomeEntregadores(funcionarioController.listarFuncionarios());
            listaProdutos = produtoController.nomeProdutos(produtoController.listarProdutos());
        } catch (PersistenciaException ex) {
            Logger.getLogger(CadastrarPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        comboBoxProduto.setItems(FXCollections.observableArrayList(listaProdutos));
        comboBoxEntregadores.setItems(FXCollections.observableArrayList(listaFuncionarios));
         Platform.runLater(()->{
            comboBoxProduto.requestFocus();
        });
         System.out.println("Carregou produtos...");
        
    }

    @FXML
    private void initialize() {

        iniciaComboBox();
        listTextField.add(textFieldQuantidade);
        listTextField.add(textFieldValorUnitario);
        listTextField.add(textFieldValorTotal);
        listTextField.add(textFieldMarca);
        listTextField.add(textFieldFormaPagamento);
        listTextField.add(textFieldEndereco);

        // Adiciona ChangeListeners para atualizar o valor total
        textFieldQuantidade.textProperty().addListener((observable, oldValue, newValue) -> atualizarValorTotal());
        textFieldValorUnitario.textProperty().addListener((observable, oldValue, newValue) -> atualizarValorTotal());

        MainFX.addOnChangeScreenListener((String newString, Object viewData) -> {
        });
    }

// Método para atualizar o valor total com base na quantidade e valor unitário
    private void atualizarValorTotal() {
        try {
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());
            double valorTotal = quantidade * valorUnitario;
            textFieldValorTotal.setText(String.valueOf(valorTotal));
        } catch (NumberFormatException e) {
            textFieldValorTotal.setText(""); // Limpa o campo se houver erro na conversão
        }
    }
}
