package edu.masanz.da.juegored.client.model;

public class BotRandom extends Bot {

    public BotRandom(String nombreUsuario, boolean estaListo) {
        super(nombreUsuario, estaListo);
    }

    public boolean tomarDecision(Partida partida){
        if (Math.random() < 0.75) {
            return true;
        } else {
            return false;
        }
    }
}
