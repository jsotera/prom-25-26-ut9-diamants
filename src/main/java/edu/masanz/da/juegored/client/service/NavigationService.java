package edu.masanz.da.juegored.client.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationService {

    private static NavigationService instance;

    private Stage stage;

    private NavigationService() {}

    public static NavigationService getInstance() {
        if (instance == null) instance = new NavigationService();
        return instance;
    }

    public void setStage(Stage stage) { this.stage = stage; }
    public Stage getStage() { return stage; }

    public <T> T navigateTo(String viewFileName) {
        String path = "/edu/masanz/da/juegored/view/" + viewFileName;
        try {
            FXMLLoader loader = new FXMLLoader(NavigationService.class.getResource(path));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.sizeToScene();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        stage.close();
    }
}