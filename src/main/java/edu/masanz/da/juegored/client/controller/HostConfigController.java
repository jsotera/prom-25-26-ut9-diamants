package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.manager.PlayerManager;
import edu.masanz.da.juegored.client.model.Sala;
import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import edu.masanz.da.juegored.server.ServerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.InetAddress;

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
    private Text txtError;

    @FXML
    public void initialize() {

    }

    @FXML
    void cancelarHostConfig(ActionEvent event) {
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    @FXML
    void crearSala(ActionEvent event) {
        if(txtSala==null || txtSala.getText().isEmpty() || numJugadores==null || numJugadores.getText().isEmpty()){
            txtError.setVisible(true);
            return;
        }
        int puerto = (int)(Math.random()*20000)+10000; // 10000 - 29999
        String nombre = txtSala.getText();
        int jugadores = 1;
        int maxJugadores = Integer.parseInt(numJugadores.getText());
        Sala sala = null;
        try {
            sala = new Sala(InetAddress.getLocalHost().getHostAddress(), puerto, nombre, jugadores, maxJugadores);
            ServerManager.lanzarServidor(puerto, nombre, maxJugadores);
        } catch (Exception e) {
            sala = null;
        }
        if(sala!=null){
            UserSession.getInstance().setSala(sala);
            PlayerManager.startClient(sala);
            NavigationService.getInstance().navigateTo("waiting.fxml");
        } else {
            // habra ocurrido algun error
            System.out.println("Ha ocurrido un error creando la sala");
        }
    }

}

