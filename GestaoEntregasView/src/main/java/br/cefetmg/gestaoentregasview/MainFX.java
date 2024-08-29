package br.cefetmg.gestaoentregasview;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

    private static Stage stage;
    private static Scene telaLogin;
    private static Scene telaVisualizarPedidos;
    private static Scene telaEntregador;
    private static Scene telaVisualizarClientes;
    private static Scene telaCadastrarPedido;
    private static Scene telaCadastrarCliente;
    private static Scene telaCadastrarFuncionario;
    private static Scene telaCliente;
    private static Scene telaVisualizarFuncionarios;
    private static Scene telaVisualizarProdutos;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        telaLogin = new Scene(loaderFXML("/fxml/TelaLogin"),  1280, 720);
        primaryStage.setScene(telaLogin);
        primaryStage.show();
    }
    
    public static Parent loaderFXML(String caminho) throws IOException{
        return FXMLLoader.load(MainFX.class.getResource(caminho + ".fxml"));
    }

    public static void changedScreen(String tela, Object userData) throws IOException {
        switch (tela) {
            case "TelaLogin":
                telaLogin = new Scene(loaderFXML("/fxml/TelaLogin"),  1280, 720);
                stage.setScene(telaLogin);
                break;
              case "TelaVisualizarPedidos":
                telaVisualizarPedidos = new Scene(loaderFXML("/fxml/TelaVisualizarPedidos"),  1280, 720);
                stage.setScene(telaVisualizarPedidos);
                break;
            case "TelaCadastrarFuncionario":
                 telaCadastrarFuncionario = new Scene(loaderFXML("/fxml/TelaCadastrarFuncionario"),  1280, 720);
                 stage.setScene(telaCadastrarFuncionario);
                 break;
            case "TelaVisualizarClientes":
                 telaVisualizarClientes = new Scene(loaderFXML("/fxml/TelaVisualizarClientes"), 1280, 720);
                 stage.setScene(telaVisualizarClientes);  
                 break;
            case "TelaCadastrarPedido":
                telaCadastrarPedido = new Scene(new MainFX().loaderFXML("/fxml/TelaCadastrarPedido"),  1280, 720);
                stage.setScene(telaCadastrarPedido);
                break;
            case "TelaCadastrarCliente":
                telaCadastrarCliente = new Scene(loaderFXML("/fxml/TelaCadastrarCliente"), 1280, 720);
                stage.setScene(telaCadastrarCliente);
                break;
            case "TelaCliente":
                telaCliente = new Scene(loaderFXML("/fxml/TelaCliente"),  1280, 720);
                stage.setScene(telaCliente);
                break;
            case "TelaEntregador":
                telaEntregador = new Scene(loaderFXML("/fxml/TelaEntregador"),  1280, 720);
                stage.setScene(telaEntregador);
                break;
            case "TelaVisualizarFuncionarios":
                telaVisualizarFuncionarios = new Scene(loaderFXML("/fxml/TelaVisualizarFuncionarios"),  1280, 720);
                stage.setScene(telaVisualizarFuncionarios);
                break;
            case "TelaVisualizarProdutos":
                telaVisualizarProdutos = new Scene(loaderFXML("/fxml/TelaVisualizarProdutos"), 1280, 720);
                stage.setScene(telaVisualizarProdutos);
                break;
            case "Sair":
                stage.close();
                break;
        }
    }

    private static final ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {

        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, userData);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
