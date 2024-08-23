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
        try {
            stage = primaryStage;

            primaryStage.setTitle("Gestão de Entregas");
            
            telaLogin = new Scene(loaderFXML("/fxml/TelaLogin"),  1280, 720);
            
            telaVisualizarPedidos = new Scene(loaderFXML("/fxml/TelaVisualizarPedidos"),  1280, 720);
            
            telaEntregador = new Scene(loaderFXML("/fxml/TelaEntregador"),  1280, 720);
           
            telaVisualizarClientes = new Scene(loaderFXML("/fxml/TelaVisualizarClientes"), 1280, 720);
           
            telaCadastrarPedido = new Scene(loaderFXML("/fxml/TelaCadastrarPedido"),  1280, 720);
            
            telaCadastrarCliente = new Scene(loaderFXML("/fxml/TelaCadastrarCliente"), 1280, 720);
       
            telaCadastrarFuncionario = new Scene(loaderFXML("/fxml/TelaCadastrarFuncionario"),  1280, 720);
            
            telaCliente = new Scene(loaderFXML("/fxml/TelaCliente"),  1280, 720);
            
            telaVisualizarFuncionarios = new Scene(loaderFXML("/fxml/TelaVisualizarFuncionarios"),  1280, 720);

            telaVisualizarProdutos = new Scene(loaderFXML("/fxml/TelaVisualizarProdutos"), 1280, 720);

            primaryStage.setScene(telaLogin);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Parent loaderFXML(String caminho) throws IOException{
        return FXMLLoader.load(getClass().getResource(caminho + ".fxml"));
    }

    public static void changedScreen(String tela, Object userData) {
        switch (tela) {
            case "TelaLogin":
                stage.setScene(telaLogin);
                break;
              case "TelaVisualizarPedidos":
                stage.setScene(telaVisualizarPedidos);
                break;
            case "TelaCadastrarFuncionario":
                 stage.setScene(telaCadastrarFuncionario);
                 break;
            case "TelaVisualizarClientes":
                 stage.setScene(telaVisualizarClientes);  
                 break;
            case "TelaCadastrarPedido":
                stage.setScene(telaCadastrarPedido);
                break;
            case "TelaCadastrarCliente":
                stage.setScene(telaCadastrarCliente);
                break;
            case "TelaCliente":
                stage.setScene(telaCliente);
                break;
            case "TelaEntregador":
                stage.setScene(telaEntregador);
                break;
            case "TelaVisualizarFuncionarios":
                stage.setScene(telaVisualizarFuncionarios);
                break;
            case "TelaVisualizarProdutos":
                stage.setScene(telaVisualizarProdutos);
                break;
            case "Sair":
                stage.close();
                break;
        }
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

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
