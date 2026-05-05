package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class HostConfigController {

    @FXML
    private TextField txtSala;

    @FXML
    private TextField numJugadores;

    @FXML
    private RadioButton rbtPrivate;

    @FXML
    private RadioButton rbtPublic;

    @FXML
    private ToggleGroup tipo;

    @FXML
    public void initialize() {

    }

    @FXML
    void cancelarHostConfig(ActionEvent event) {

        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    @FXML
    void crearSala(ActionEvent event) {

    }

}

