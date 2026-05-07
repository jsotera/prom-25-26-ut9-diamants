package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LauncherController {

    @FXML
    private TextField txtNickname;

    @FXML
    void crearSala(ActionEvent event) {
        String nickname = txtNickname.getText();

        if(nickname.isEmpty()){
            return;
        }

        UserSession.getInstance().setNickname(nickname);
        NavigationService.getInstance().navigateTo("hostconfig.fxml");
    }

    @FXML
    void unirSala(ActionEvent event) {
        String nickname = txtNickname.getText();

        if(nickname.isEmpty()){
            return;
        }

        UserSession.getInstance().setNickname(nickname);
        NavigationService.getInstance().navigateTo("lobby.fxml");
    }

    @FXML
    void jugarSolo(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
        NavigationService.getInstance().close();
    }
}
