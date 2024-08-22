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
    private static Scene telaInicialAtendente;
    private static Scene telaInicialEntregador;
    private static Scene telaInicialCliente;
    private static Scene telaCadastrarPedido;
    private static Scene telaCadastrarCliente;
    private static Scene telaCadastrarEntregador;
    private static Scene telaCadastrarAtendente;
    private static Scene telaPerfilAtendente;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            stage = primaryStage;

            primaryStage.setTitle("Gestão de Entregas");
            
            Parent loaderTelaLogin = FXMLLoader.load(getClass().getResource("/fxml/TelaLogin.fxml"));
            telaLogin = new Scene(loaderTelaLogin,  1280, 720);
            
            Parent loaderTelaInicialAtendente = FXMLLoader.load(getClass().getResource("/fxml/TelaInicialAtendente.fxml"));
            telaInicialAtendente = new Scene(loaderTelaInicialAtendente,  1280, 720);
            
            //Parent loaderTelaInicialEntregador = FXMLLoader.load(getClass().getResource("/fxml/TelaInicialEntregador.fxml"));
            //telaInicialEntregador = new Scene(loaderTelaInicialEntregador,  1280, 720);
            
            //Parent loaderTelaInicialCliente = FXMLLoader.load(getClass().getResource("/fxml/TelaInicialCliente.fxml"));
            //telaInicialCliente = new Scene(loaderTelaInicialCliente, 1280, 720);
            
            Parent loaderTelaCadastrarPedido = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarPedido.fxml"));
            telaCadastrarPedido = new Scene(loaderTelaCadastrarPedido,  1280, 720);
            
            Parent loaderTelaCadastrarCliente = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarCliente.fxml"));
            telaCadastrarCliente = new Scene(loaderTelaCadastrarCliente, 1280, 720);
       
            //Parent loaderTelaCadastrarEntregador = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarEntregador.fxml"));
            //telaCadastrarEntregador = new Scene(loaderTelaCadastrarEntregador,  1280, 720);
            
            //Parent loaderTelaCadastrarAtendente = FXMLLoader.load(getClass().getResource("/fxml/TelaCadastrarAtendente.fxml"));
            //telaCadastrarAtendente = new Scene(loaderTelaCadastrarAtendente,  1280, 720);
            
            //Parent loaderTelaPerfilAtendente = FXMLLoader.load(getClass().getResource("/fxml/TelaPerfilAtendente.fxml"));
            //telaPerfilAtendente = new Scene(loaderTelaPerfilAtendente,  1280, 720);


            primaryStage.setScene(telaLogin);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changedScreen(String tela, Object userData) {
        switch (tela) {
            case "TelaLogin":
                stage.setScene(telaLogin);
                break;
              case "TelaInicialAtendente":
                stage.setScene(telaInicialAtendente);
                break;
            case "TelaInicialEntregador":
                 stage.setScene(telaInicialEntregador);
                 break;
            case "TelaInicialCliente":
                 stage.setScene(telaInicialCliente);  
                 break;
            case "TelaCadastrarPedido":
                stage.setScene(telaCadastrarPedido);
                break;
            case "TelaCadastrarCliente":
                stage.setScene(telaCadastrarCliente);
                break;
            case "TelaCadastrarEntregador":
                stage.setScene(telaCadastrarEntregador);
                break;
            case "TelaCadastrarAtendente":
                stage.setScene(telaCadastrarAtendente);
                break;
            case "TelaPerfilAtendenete":
                stage.setScene(telaPerfilAtendente);
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
