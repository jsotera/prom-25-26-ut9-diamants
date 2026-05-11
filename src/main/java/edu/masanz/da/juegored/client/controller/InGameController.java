package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.Jugador;
import edu.masanz.da.juegored.client.model.Partida;
import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class InGameController {

    @FXML
    private ImageView cartaRecorrido;

    @FXML
    private ImageView imgContinuar;

    @FXML
    private ImageView imgVolver;

    @FXML
    private ImageView ivPlayer1;

    @FXML
    private ImageView ivPlayer2;

    @FXML
    private ImageView ivPlayer3;

    @FXML
    private ImageView ivPlayer4;

    @FXML
    private ImageView ivPlayer5;

    @FXML
    private ImageView ivPlayer6;

    @FXML
    private ImageView ivPlayer7;

    @FXML
    private ImageView ivPlayer8;

    @FXML
    private Text txtNumRonda;

    @FXML
    private Text txtGemasAseguradas;

    @FXML
    private Text txtGemasPorElCamino;

    @FXML
    private Text txtGemasPorJugador;

    @FXML
    private Text txtGemasRonda;

    @FXML
    private Text txtTrampa1;

    @FXML
    private Text txtTrampa2;

    @FXML
    private Text txtTrampa3;

    @FXML
    private Text txtTrampa4;

    @FXML
    private Text txtTrampa5;

    @FXML
    void continuar(MouseEvent event) {

    }

    @FXML
    void fueraContinuar(MouseEvent event) {
        System.out.println("raton fuera de continuar");
        imgContinuar.setScaleX(1);
    }

    @FXML
    void fueraVolver(MouseEvent event) {
        System.out.println("raton fuera de volver");
    }

    @FXML
    void sobreContinuar(MouseEvent event) {
        System.out.println("raton encima de continuar");
        imgContinuar.setScaleX(2);
    }

    @FXML
    void sobreVolver(MouseEvent event) {
        System.out.println("raton encima de volver");
    }

    @FXML
    void volver(MouseEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    public void initialize(){
        Partida p = new Partida(new Jugador(UserSession.getInstance().getNickname(), false));
        for (Jugador jugador : p.getJugadores()) {
            if(jugador.getNombreUsuario().equalsIgnoreCase("player2")){
                ivPlayer2.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player3")){
                ivPlayer3.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player4")){
                ivPlayer4.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player5")){
                ivPlayer5.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player6")){
                ivPlayer6.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player7")){
                ivPlayer7.setVisible(true);
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player8")){
                ivPlayer8.setVisible(true);
            }
        }

    }

}

