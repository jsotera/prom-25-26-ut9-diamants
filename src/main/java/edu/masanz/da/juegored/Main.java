package edu.masanz.da.juegored;

import edu.masanz.da.juegored.client.service.NavigationService;
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
        stage.show();
    }

}
