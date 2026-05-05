module edu.masanz.da.juegored {
        requires javafx.controls;
        requires javafx.fxml;

        opens edu.masanz.da.juegored to javafx.fxml;
        opens edu.masanz.da.juegored.client.model to javafx.base;
        opens edu.masanz.da.juegored.client.controller to javafx.fxml;

        exports edu.masanz.da.juegored;
        exports edu.masanz.da.juegored.client.controller;
}