package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.model.Jugador;
import edu.masanz.da.juegored.client.model.Partida;
import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.List;

public class FinishController {

    @FXML
    private VBox vbPuntuaciones;

    @FXML
    void volver(ActionEvent event) {
        FinishController controller = NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    public void setPartida(Partida partida) {
        List<Jugador> jugadores = partida.getJugadores();
        Collections.sort(jugadores, (j1, j2) -> {
            return j2.getDiamantesTotal() - j1.getDiamantesTotal();
        });
        for (int i = 0; i < jugadores.size(); i++) {
            Text puntuacionJugador = new Text();
            puntuacionJugador.setText(jugadores.get(i).getNombreUsuario()+": "+jugadores.get(i).getDiamantesTotal());
            vbPuntuaciones.getChildren().add(puntuacionJugador);
        }
    }
}