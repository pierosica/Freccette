package org.ilpider.games.freccette.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class WelcomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private HBox hbox;

    @FXML
    private TextField txtNome1;

    @FXML
    private TextField txtNome2;
    
    @FXML
    private TextField txtNome3;

    @FXML
    private TextField txtNome4;
    
    @FXML
    void doNuovaPartita(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert txtNome2 != null : "fx:id=\"txtNome2\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert txtNome1 != null : "fx:id=\"txtNome1\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert txtNome4 != null : "fx:id=\"txtNome4\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert txtNome3 != null : "fx:id=\"txtNome3\" was not injected: check your FXML file 'Welcome.fxml'.";

        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        txtNome1.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                hbox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
    }
}
