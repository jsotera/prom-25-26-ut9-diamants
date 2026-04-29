package edu.masanz.da.juegored.controller;

import edu.masanz.da.juegored.model.Sala;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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
    void cancelar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/masanz/da/juegored/launcher.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void unirme(ActionEvent event) {
        if(salaSeleccionada!=null){
            System.out.println("Sala seleccionada: " + salaSeleccionada.getNombre());
        }
    }

    public void initialize(){
        System.out.println("Cargando cosas!");

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colJugadores.setCellValueFactory(new PropertyValueFactory<>("jugadores"));

        // 2. Crear la lista de datos
        ObservableList<Sala> datos = FXCollections.observableArrayList(
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Sala Pro", 4),
                new Sala("Principiantes", 2),
                new Sala("Torneo Semanal", 8)
        );

        // 3. Cargar los datos en la tabla
        tvSalas.setItems(datos);

        tvSalas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                salaSeleccionada = newValue;
                btnUnirme.setVisible(true);
            }
        });


    }

}
