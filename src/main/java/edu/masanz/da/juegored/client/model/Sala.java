package edu.masanz.da.juegored.client.model;

public class Sala {
    private String nombre;
    private int jugadores;

    public Sala(String nombre, int jugadores) {
        this.nombre = nombre;
        this.jugadores = jugadores;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJugadores() {
        return jugadores;
    }
}