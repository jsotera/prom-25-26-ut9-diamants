package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.manager.PlayerManager;
import edu.masanz.da.juegored.client.model.Jugador;
import edu.masanz.da.juegored.client.service.NavigationService;
import edu.masanz.da.juegored.server.ClientHandler;
import edu.masanz.da.juegored.server.ServerManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

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
        new Thread(() -> {
            while(PlayerManager.conexionAbierta){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("hilo initialize WAITTING ROOM muerto");
            Platform.runLater(() -> {
                try {
                    cancelar(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }).start();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colReady.setCellValueFactory(new PropertyValueFactory<>("estaListo"));

        jugadores = FXCollections.observableArrayList(

        );

        tvPlayers.setItems(jugadores);

        new Thread(() -> {
            while(PlayerManager.conexionAbierta){
                jugadores.clear();
                jugadores.addAll(PlayerManager.jugadores);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        PlayerManager.txtAreaChat = txtAreaChat;
    }

    @FXML
    void cancelar(ActionEvent event) {
        ServerManager.servidorVivo = false;
        PlayerManager.conexionAbierta = false;
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    @FXML
    void enviar(ActionEvent event) {
        System.out.println("volando voy");
        String msg = txtUserChat.getText();
        if(!msg.isEmpty()){
            PlayerManager.msg = msg;
        }
        txtUserChat.clear();
    }

    @FXML
    void estoyListo(ActionEvent event) {

    }

}