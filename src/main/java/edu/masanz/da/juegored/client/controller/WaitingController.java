package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.Jugador;
import edu.masanz.da.juegored.client.service.NavigationService;
import edu.masanz.da.juegored.server.ServerManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WaitingController {

    @FXML
    private Button btnEstoyListo;

    @FXML
    private TableColumn<Jugador, String> colNombre;

    @FXML
    private TableColumn<Jugador, Boolean> colReady;

    @FXML
    private TableView<Jugador> tvPlayers;

    @FXML
    private TextArea txtAreaChat;

    @FXML
    private TextField txtUserChat;

    private ObservableList<Jugador> jugadores;

    public void initialize(){

    }

    @FXML
    void cancelar(ActionEvent event) {
        ServerManager.servidorVivo = false;
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    @FXML
    void enviar(ActionEvent event) {
        System.out.println("volando voy");
    }

    @FXML
    void estoyListo(ActionEvent event) {

    }

}