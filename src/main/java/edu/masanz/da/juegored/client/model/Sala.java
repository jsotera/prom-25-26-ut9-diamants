package edu.masanz.da.juegored.client.model;

import java.util.Objects;

public class Sala {

    private String host;
    private int puerto;
    private String nombre;
    private int jugadores;
    private int maxJugadores;
    private String jugadoresInfo;

    public Sala(String host, int puerto, String nombre, int jugadores, int maxJugadores) {
        this.host = host;
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.maxJugadores = maxJugadores;
        this.puerto = puerto;
        jugadoresInfo = jugadores + "/" + maxJugadores;
    }

    public String getJugadoresInfo() {
        jugadoresInfo = jugadores + "/" + maxJugadores;
        return jugadoresInfo;
    }

    public String getHost() {
        return host;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJugadores() {
        return jugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public int getPuerto() {
        return puerto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sala sala = (Sala) o;
        return puerto == sala.puerto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(puerto);
    }
}