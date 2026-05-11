package edu.masanz.da.juegored.client.model;

import java.util.*;

public class Partida {

    private HashMap<String, Integer> aparicionCartasTrampa;
    private List<Carta> baraja;
    private List<Jugador> jugadores;
    private int ronda;
    private int diamantesParaLaVueltaTotales;
    private static final int RONDAS_MAX = 5;

    public Partida(Jugador jugadorHumano) {
        // TODO 01: Inicializar y poblar valores
        poblarBaja();
        this.jugadores = new ArrayList<>();
        // el jugador.get(0) siempre sera el jugador humano
        this.jugadores.add(jugadorHumano);
        int numBots = (int) (Math.random()*6)+2; // 2 -- 7
        // empiezo en 2, porque las imagenes tienen nombre de player1, player2, etc. y el primer bot sera player2
        // por eso hago este bucle de manera tan "rara"
        for (int i = 2; i <= numBots+1; i++) {
            Jugador bot = new Jugador("player"+i, false);
            this.jugadores.add(bot);
        }
        this.ronda = 1;
        this.diamantesParaLaVueltaTotales = 0;
    }

    private void poblarBaja() {
        this.baraja = new ArrayList<>();
        this.aparicionCartasTrampa = new HashMap<>();

        for (int idTrampa : Datos.TRAMPAS) {
            Carta trampa = new Carta("trampa_0"+idTrampa, 0, false, true);
            this.baraja.add(trampa);
            this.aparicionCartasTrampa.put(trampa.getNombre(), 0);
        }
        for (int idDiamante : Datos.DIAMANTES) {
            String srtDiamante = idDiamante<=9 ? "0"+idDiamante : ""+idDiamante;
            Carta diamante = new Carta("diamante_"+srtDiamante, idDiamante, false, false);
            this.baraja.add(diamante);
        }

    }

    public void jugarRonda(){
        poblarBaja();
        Collections.shuffle(baraja);
        for (Jugador jugador : jugadores) {
            jugador.setExplorar(true);
            jugador.setDiamantesRonda(0);
        }
        diamantesParaLaVueltaTotales = 0;
        boolean trampaActivada = false;
        boolean jugadoresActivos = true;
        while(!trampaActivada && jugadoresActivos){

            trampaActivada = jugarTurno();

            jugadoresActivos = false;
            for (Jugador jugador : jugadores) {
                if(jugador.isExplorar()){
                    jugadoresActivos = true;
                }
            }
        }
    }

    /**
     * Intenta jugar un turno y dice si salta una trampa
     * @return si salta trampa, true, sino false
     */
    private boolean jugarTurno() {
        int jugadoresActivos = 0;
        List<Jugador> jugadoresQueVuelven = new ArrayList<>();
        // DECISION HUMANA
        jugadoresActivos = jugadoresActivos + decisionHumana(jugadoresQueVuelven);
        // DECISION DE BOTS
        jugadoresActivos = jugadoresActivos + decisionBots(jugadoresQueVuelven);
        // dar las gemas a aquellos jugadores que se vuelven al campamento
        repartirGemasDeVuelta(jugadoresQueVuelven);
        // si no quedan jugadores, finaliza el turno
        if(jugadoresActivos<=0){
            return false;
        }
        // Sacamos carta de la baraja y la analizamos
        Carta carta = baraja.remove(0);
        System.out.println(carta);
        // Analizamos si es una trampa
        boolean explota = analizarTrampa(carta);
        if(!carta.isEsTrampa()){
            analizarDiamantes(carta, jugadoresActivos);
        }
        return explota;
    }

    private void analizarDiamantes(Carta carta, int jugadoresActivos) {
        int diamantes = carta.getDiamantes();
        int diamantesParaCadaJugador = diamantes / jugadoresActivos;
        int diamantesParaLaVuelta = diamantes % jugadoresActivos;
        System.out.println("Cada jugador se lleva: "+diamantesParaCadaJugador);
        diamantesParaLaVueltaTotales = diamantesParaLaVueltaTotales + diamantesParaLaVuelta;
        for (Jugador jugador : jugadores) {
            if(jugador.isExplorar()){
                jugador.setDiamantesRonda(jugador.getDiamantesRonda()+diamantesParaCadaJugador);
            }
        }
    }

    private boolean analizarTrampa(Carta carta) {
        if(carta.isEsTrampa()){
            int numDeEsaCartaTrampa = aparicionCartasTrampa.get(carta.getNombre());
            numDeEsaCartaTrampa++;
            aparicionCartasTrampa.put(carta.getNombre(), numDeEsaCartaTrampa);
            // explota cuando han salido 2 trampas iguales
            if(numDeEsaCartaTrampa >= 2){
                return true;
            }
        }
        return false;
    }

    private void repartirGemasDeVuelta(List<Jugador> jugadoresQueVuelven) {
        if(jugadoresQueVuelven.size()>0) {
            int diamantesParaCadaJugador = diamantesParaLaVueltaTotales / jugadoresQueVuelven.size();
            diamantesParaLaVueltaTotales = diamantesParaLaVueltaTotales % jugadoresQueVuelven.size();
            for (Jugador jugador : jugadoresQueVuelven) {
                jugador.setDiamantesRonda(jugador.getDiamantesRonda() + diamantesParaCadaJugador);
                jugador.setDiamantesTotal(jugador.getDiamantesTotal() + jugador.getDiamantesRonda());
            }
        }
    }

    /**
     * @param jugadoresQueVuelven donde incluyo los jugadores que vuelven
     * @return devuelvo la cantidad de jugadores que continuan
     */
    private int decisionBots(List<Jugador> jugadoresQueVuelven) {
        int botsActivos = 0;
        for (int i = 1; i < jugadores.size(); i++) {
            Jugador bot = jugadores.get(i);
            if(bot.isExplorar()) {
                if (Math.random() < 0.75) {
                    bot.setExplorar(true);
                    botsActivos++;
                } else {
                    bot.setExplorar(false);
                    jugadoresQueVuelven.add(bot);
                }
            }
        }
        return botsActivos;
    }

    /**
     * @param jugadoresQueVuelven donde incluyo los jugadores que vuelven
     * @return devuelvo la cantidad de jugadores que continuan
     */
    private int decisionHumana(List<Jugador> jugadoresQueVuelven) {
        if(jugadores.get(0).isExplorar()) {
            Scanner teclado = new Scanner(System.in);
            System.out.print("¿Quieres continuar? (vas " + jugadores.get(0).getDiamantesRonda() + ") [S/n]: ");
            String decision = teclado.nextLine();
            if (decision.equalsIgnoreCase("n")) {
                jugadores.get(0).setExplorar(false);
                jugadoresQueVuelven.add(jugadores.get(0));
            } else {
                jugadores.get(0).setExplorar(true);
                return 1;
            }
        }
        return 0;
    }

    private void mostrarResultados() {

        Collections.sort(jugadores, (j1, j2) -> {
            return j2.getDiamantesTotal() - j1.getDiamantesTotal();
        });
        System.out.printf("%10s %10s\n", "JUGADOR", "GEMAS");
        System.out.println("*".repeat(22));
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.printf("%10s %10d\n", jugadores.get(i).getNombreUsuario(), jugadores.get(i).getDiamantesTotal());
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
        //System.out.println(p);
        for (int i = 0; i < 2; i++) {
            p.jugarRonda();
        }
        p.mostrarResultados();
    }
}
