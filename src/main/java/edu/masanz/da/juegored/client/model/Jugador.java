package edu.masanz.da.juegored.client.model;

public class Jugador {

    private String nombreUsuario;
    private boolean estaListo;

    private boolean explorar;
    private int diamantesTotal;
    private int diamantesRonda;

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

    public boolean isExplorar() {
        return explorar;
    }

    public void setExplorar(boolean explorar) {
        this.explorar = explorar;
    }

    public int getDiamantesTotal() {
        return diamantesTotal;
    }

    public void setDiamantesTotal(int diamantesTotal) {
        this.diamantesTotal = diamantesTotal;
    }

    public int getDiamantesRonda() {
        return diamantesRonda;
    }

    public void setDiamantesRonda(int diamantesRonda) {
        this.diamantesRonda = diamantesRonda;
    }
}
