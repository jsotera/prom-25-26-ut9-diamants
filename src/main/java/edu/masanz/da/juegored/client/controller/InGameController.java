package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.Jugador;
import edu.masanz.da.juegored.client.model.Partida;
import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.Set;

public class InGameController {


    @FXML
    private Button btnSiguienteRonda;

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

    private Partida partida;

    @FXML
    void continuar(MouseEvent event) {
        if(btnSiguienteRonda.isVisible()){
            return;
        }
        boolean saltaTrampa = partida.jugarTurno(true);
        mostrarCartaJugada();
        actualizarContadoresTrampa();
        actualizarContadoresPartida();
        actualizarEstadoJugadores();
        analizarEstadoTurno(saltaTrampa);
    }

    @FXML
    void volver(MouseEvent event) {
        if(btnSiguienteRonda.isVisible()){
            return;
        }
        boolean saltaTrampa = partida.jugarTurno(false);
        mostrarCartaJugada();
        actualizarContadoresTrampa();
        actualizarContadoresPartida();
        actualizarEstadoJugadores();
        analizarEstadoTurno(saltaTrampa);
    }

    private void analizarEstadoTurno(boolean saltaTrampa) {
        boolean jugadoresActivos = false;
        for (Jugador jugador : partida.getJugadores()) {
            if(jugador.isExplorar()){
                jugadoresActivos = true;
            }
        }

        if(saltaTrampa || !jugadoresActivos){
            btnSiguienteRonda.setVisible(true);
        }
    }

    private void mostrarCartaJugada() {
        if(partida.getCartaJugada() == null){
            cartaRecorrido.setVisible(false);
            return;
        }
        String nombreCarta = partida.getCartaJugada().getNombre()+".png";
        Image imagen = new Image(getClass().getResourceAsStream("/edu/masanz/da/juegored/imgs/individuales/"+nombreCarta));
        cartaRecorrido.setImage(imagen);
        cartaRecorrido.setVisible(true);
    }

    private void actualizarContadoresTrampa(){
        Set<Map.Entry<String, Integer>> frecuenciaTrampas = partida.getAparicionCartasTrampa().entrySet();
        for (Map.Entry<String, Integer> frecuenciaTrampa : frecuenciaTrampas) {
            String nombreTrampa = frecuenciaTrampa.getKey();
            int cantidad = frecuenciaTrampa.getValue();
            switch (nombreTrampa){
                case "trampa_01" -> txtTrampa1.setText(""+cantidad);
                case "trampa_02" -> txtTrampa2.setText(""+cantidad);
                case "trampa_03" -> txtTrampa3.setText(""+cantidad);
                case "trampa_04" -> txtTrampa4.setText(""+cantidad);
                case "trampa_05" -> txtTrampa5.setText(""+cantidad);
            }
        }
    }

    private void actualizarContadoresPartida(){
        txtNumRonda.setText(""+partida.getRonda());
        txtGemasPorJugador.setText(""+partida.getDiamantesParaCadaJugador());
        txtGemasPorElCamino.setText(""+partida.getDiamantesParaLaVueltaTotales());
        txtGemasRonda.setText(""+partida.getJugadores().get(0).getDiamantesRonda());
        txtGemasAseguradas.setText(""+partida.getJugadores().get(0).getDiamantesTotal());
    }

    private void actualizarEstadoJugadores() {
        for (Jugador jugador : partida.getJugadores()) {
            if(jugador.getNombreUsuario().equalsIgnoreCase(UserSession.getInstance().getNickname())) {
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer1);
                } else {
                    deshabilitarJugador(ivPlayer1);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player2")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer2);
                } else {
                    deshabilitarJugador(ivPlayer2);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player3")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer3);
                } else {
                    deshabilitarJugador(ivPlayer3);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player4")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer4);
                } else {
                    deshabilitarJugador(ivPlayer4);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player5")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer5);
                } else {
                    deshabilitarJugador(ivPlayer5);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player6")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer6);
                } else {
                    deshabilitarJugador(ivPlayer6);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player7")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer7);
                } else {
                    deshabilitarJugador(ivPlayer7);
                }
            } else if(jugador.getNombreUsuario().equalsIgnoreCase("player8")){
                if(jugador.isExplorar()){
                    habilitarJugador(ivPlayer8);
                } else {
                    deshabilitarJugador(ivPlayer8);
                }
            }
        }
    }

    public void deshabilitarJugador(ImageView iv){
        SepiaTone sepia = new SepiaTone();
        sepia.setLevel(0.8);
        iv.setEffect(sepia);

    }

    public void habilitarJugador(ImageView iv){
        iv.setEffect(null);
    }

    @FXML
    void siguienteRonda(ActionEvent event) {
        btnSiguienteRonda.setVisible(false);
        boolean seguiJugando = partida.prepararSiguienteRonda();
        if(seguiJugando) {
            mostrarCartaJugada();
            actualizarContadoresTrampa();
            actualizarContadoresPartida();
            actualizarEstadoJugadores();
        } else {
            FinishController controller = NavigationService.getInstance().navigateTo("finish.fxml");
            controller.setPartida(partida);
        }
    }

    @FXML
    void fueraContinuar(MouseEvent event) {
        imgContinuar.setScaleX(1);
        imgContinuar.setScaleY(1);
    }

    @FXML
    void fueraVolver(MouseEvent event) {
        imgVolver.setScaleX(1);
        imgVolver.setScaleY(1);
    }

    @FXML
    void sobreContinuar(MouseEvent event) {
        imgContinuar.setScaleX(1.05);
        imgContinuar.setScaleY(1.05);
    }

    @FXML
    void sobreVolver(MouseEvent event) {
        imgVolver.setScaleX(1.05);
        imgVolver.setScaleY(1.05);
    }

    @FXML
    void cancelar(ActionEvent event) {
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    public void initialize(){
        partida = new Partida(new Jugador(UserSession.getInstance().getNickname(), false));
        for (Jugador jugador : partida.getJugadores()) {
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
        partida.prepararSiguienteRonda();
    }

}

