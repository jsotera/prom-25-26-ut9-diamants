package edu.masanz.da.juegored;

import edu.masanz.da.juegored.service.NavigationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        NavigationService.getInstance().setStage(stage);
        NavigationService.getInstance().navigateTo("launcher.fxml");

        stage.setTitle("- Ejemplo de aplicación JavaFX -");
        stage.setResizable(false);
        stage.show();
    }

}
