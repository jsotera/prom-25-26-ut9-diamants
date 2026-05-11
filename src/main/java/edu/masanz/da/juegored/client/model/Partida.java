package edu.masanz.da.juegored.client.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Partida {

    private List<Carta> baraja;
    private List<Jugador> jugadores;
    private int ronda;
    private static final int RONDAS_MAX = 5;

    public Partida(Jugador jugadorHumano) {
        // TODO 01: Inicializar y poblar valores
        poblarBaja();
        this.jugadores = new ArrayList<>();
        this.jugadores.add(jugadorHumano);
        int numBots = (int) (Math.random()*6)+2; // 2 -- 7
        // empiezo en 2, porque las imagenes tienen nombre de player1, player2, etc. y el primer bot sera player2
        // por eso hago este bucle de manera tan "rara"
        for (int i = 2; i <= numBots+1; i++) {
            Jugador bot = new Jugador("player"+i, false);
            this.jugadores.add(bot);
        }
        this.ronda = 1;
    }

    private void poblarBaja() {
        this.baraja = new ArrayList<>();
        for (int idTrampa : Datos.TRAMPAS) {
            Carta trampa = new Carta("trampa_0"+idTrampa, 0, false, true);
            this.baraja.add(trampa);
        }
        for (int idDiamante : Datos.DIAMANTES) {
            String srtDiamante = idDiamante<=9 ? "0"+idDiamante : ""+idDiamante;
            Carta diamante = new Carta("diamante_"+srtDiamante, idDiamante, false, false);
            this.baraja.add(diamante);
        }
    }

    @Override
    public String toString() {
        return "Partida{" +
                "baraja=" + baraja +
                ", jugadores=" + jugadores +
                ", ronda=" + ronda +
                '}';
    }

    public static void main(String[] args) {
        Partida p = new Partida(new Jugador("Javi", false));
        System.out.println(p);
    }
}
