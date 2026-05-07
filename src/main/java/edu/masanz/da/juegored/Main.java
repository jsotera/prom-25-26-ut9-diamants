package edu.masanz.da.juegored;

import edu.masanz.da.juegored.client.manager.PlayerManager;
import edu.masanz.da.juegored.client.service.NavigationService;
import edu.masanz.da.juegored.server.ServerManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        NavigationService.getInstance().setStage(stage);
        NavigationService.getInstance().navigateTo("launcher.fxml");

        stage.setTitle("- DiamantsNET -");
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            ServerManager.servidorVivo = false;
            PlayerManager.conexionAbierta = false;
        });
        stage.show();
    }

}
