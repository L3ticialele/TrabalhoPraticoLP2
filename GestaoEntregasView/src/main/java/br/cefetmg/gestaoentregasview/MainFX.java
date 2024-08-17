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
    private static Scene telaCadastrarFuncionario;
    private static Scene telaCadastrarCliente;
    private static Scene telaCadastrarPedido;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            stage = primaryStage;

            primaryStage.setTitle("Gestão de Entregas");
            
            Parent loaderTelaCadastrarFuncionario = FXMLLoader.load(getClass().getResource("/fxml/CadastrarFuncionario.fxml"));
            telaCadastrarFuncionario = new Scene(loaderTelaCadastrarFuncionario, 423, 330);
            
            Parent loaderTelaCadastrarCliente = FXMLLoader.load(getClass().getResource("/fxml/CadastrarCliente.fxml"));
            telaCadastrarCliente = new Scene(loaderTelaCadastrarCliente, 423, 330);
            
            Parent loaderTelaCadastrarPedido = FXMLLoader.load(getClass().getResource("/fxml/CadastrarPedido.fxml"));
            telaCadastrarPedido = new Scene(loaderTelaCadastrarPedido,  523, 396);

            primaryStage.setScene(telaCadastrarFuncionario);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changedScreen(String tela, Object userData) {
        switch (tela) {
            case "CadastrarFuncionario":
                stage.setScene(telaCadastrarFuncionario);
            case "CadastrarCliente":
                stage.setScene(telaCadastrarCliente);
            case "CadastrarPedido":
                stage.setScene(telaCadastrarPedido);
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
