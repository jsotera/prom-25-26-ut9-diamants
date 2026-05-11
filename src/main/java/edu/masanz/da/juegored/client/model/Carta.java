package edu.masanz.da.juegored.client.model;

public class Carta {

    private String nombre;
    private int diamantes;
    private boolean esReliquia;
    private boolean esTrampa;

    public Carta(String nombre, int diamantes, boolean esReliquia, boolean esTrampa) {
        this.nombre = nombre;
        this.diamantes = diamantes;
        this.esReliquia = esReliquia;
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

    public boolean isEsReliquia() {
        return esReliquia;
    }

    public void setEsReliquia(boolean esReliquia) {
        this.esReliquia = esReliquia;
    }

    public boolean isEsTrampa() {
        return esTrampa;
    }

    public void setEsTrampa(boolean esTrampa) {
        this.esTrampa = esTrampa;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nombre='" + nombre + '\'' +
                ", diamantes=" + diamantes +
                ", esReliquia=" + esReliquia +
                ", esTrampa=" + esTrampa +
                '}';
    }
}
