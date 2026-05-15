package edu.masanz.da.juegored.client.model;

public abstract class Bot extends Jugador {

    public Bot(String nombreUsuario, boolean estaListo) {
        super(nombreUsuario, estaListo);
    }

    public abstract boolean tomarDecision(Partida partida);
}
