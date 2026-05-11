package edu.masanz.da.juegored.client.model;

public class Carta {

    private String nombre;
    private int diamantes;
    private boolean esTesoro;
    private boolean esTrampa;

    public Carta(String nombre, int diamantes, boolean esTesoro, boolean esTrampa) {
        this.nombre = nombre;
        this.diamantes = diamantes;
        this.esTesoro = esTesoro;
        this.esTrampa = esTrampa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDiamantes() {
        return diamantes;
    }

    public void setDiamantes(int diamantes) {
        this.diamantes = diamantes;
    }

    public boolean isEsTesoro() {
        return esTesoro;
    }

    public void setEsTesoro(boolean esTesoro) {
        this.esTesoro = esTesoro;
    }

    public boolean isEsTrampa() {
        return esTrampa;
    }

    public void setEsTrampa(boolean esTrampa) {
        this.esTrampa = esTrampa;
    }
}
