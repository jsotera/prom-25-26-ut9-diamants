package edu.masanz.da.juegored.client.controller;

import edu.masanz.da.juegored.client.manager.PlayerManager;
import edu.masanz.da.juegored.client.model.Sala;
import edu.masanz.da.juegored.client.model.UserSession;
import edu.masanz.da.juegored.client.service.NavigationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import static edu.masanz.da.juegored.core.Consts.PORT_UDP;

public class LobbyController {

    private Sala salaSeleccionada;

    @FXML
    private Button btnUnirme;
    @FXML
    private TableView<Sala> tvSalas;
    @FXML
    private TableColumn<Sala, String> colNombre;
    @FXML
    private TableColumn<Sala, Integer> colJugadores;
    @FXML
    private TableColumn<Sala, Integer> colHost;
    @FXML
    private TableColumn<Sala, Integer> colPuerto;

    @FXML
    void cancelar(ActionEvent event) {
        PlayerManager.buscarServidores = false;
        NavigationService.getInstance().navigateTo("launcher.fxml");
    }

    @FXML
    void unirme(ActionEvent event) {
        if(salaSeleccionada!=null){
            System.out.println("Sala seleccionada: " + salaSeleccionada.getNombre());
            PlayerManager.buscarServidores = false;
            UserSession.getInstance().setSala(salaSeleccionada);
            PlayerManager.startClient(salaSeleccionada);
            NavigationService.getInstance().navigateTo("waiting.fxml");
        }
    }

    public void initialize(){
        PlayerManager.buscarServidores = true;

        colHost.setCellValueFactory(new PropertyValueFactory<>("host"));
        colPuerto.setCellValueFactory(new PropertyValueFactory<>("puerto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colJugadores.setCellValueFactory(new PropertyValueFactory<>("jugadoresInfo"));

        // 2. Crear la lista de datos
        ObservableList<Sala> datos = FXCollections.observableArrayList(

        );

        // 3. Cargar los datos en la tabla
        tvSalas.setItems(datos);

        tvSalas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                salaSeleccionada = newValue;
                btnUnirme.setVisible(true);
            }
        });

        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT_UDP)) {
                // Es importante poner un timeout para que no se bloquee siempre
                socket.setSoTimeout(5000);
                byte[] buffer = new byte[1024];

                System.out.println("Buscando servidores en la red...");

                while (PlayerManager.buscarServidores) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    try {
                        socket.receive(packet);
                        String data = new String(packet.getData(), 0, packet.getLength());

                        System.out.println("Servidor encontrado: " + data);
                        String[] dataInfo = data.split(";");
                        Sala sala = new Sala(dataInfo[0], Integer.parseInt(dataInfo[1]), dataInfo[2], Integer.parseInt(dataInfo[3]), Integer.parseInt(dataInfo[4]));
                        if(!datos.contains(sala)){
                            datos.add(sala);
                        }

                    } catch (SocketTimeoutException e) {
                        datos.clear();
                        System.out.println("No se encontraron más servidores.");
                    }
                }
                System.out.println("hilo muerto");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //datos.add(new Sala("123.123.123.123", 4, "Hola Mundo", 2, 4));

    }

}
