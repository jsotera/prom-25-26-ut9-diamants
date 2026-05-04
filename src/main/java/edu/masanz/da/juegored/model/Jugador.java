package edu.masanz.da.juegored.model;

public class Jugador {

    private String nombreUsuario;
    private boolean estaListo;

    public Jugador(String nombreUsuario, boolean estaListo) {
        this.nombreUsuario = nombreUsuario;
        this.estaListo = estaListo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isEstaListo() {
        return estaListo;
    }

    public void setEstaListo(boolean estaListo) {
        this.estaListo = estaListo;
    }
}
