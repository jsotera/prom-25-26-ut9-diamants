package edu.masanz.da.juegored.client.model;

public class Datos {

    public static final String[] TRAMPAS = {
            "Fuego", "Fuego", "Fuego",
            "Pinchos", "Pinchos", "Pinchos",
            "Serpientes", "Serpientes", "Serpientes",
            "Piedras", "Piedras", "Piedras",
            "Arañas", "Arañas", "Arañas"
    };

    // Hay 15 cartas de tesoro con valores específicos de diamantes.
    public static final int[] TESOROS = {
            1, 2, 3, 4, 5, 5, 7, 7, 9, 11, 11, 13, 14, 15, 17
    };

    // Los "diamantes" (reliquias) en el juego original son 5 cartas especiales.
    // En las reglas estándar, las 3 primeras valen 5 puntos y las 2 últimas valen 10.
    public static final int[] RELIQUIAS = {
            5, 5, 5, 10, 10
    };

}
