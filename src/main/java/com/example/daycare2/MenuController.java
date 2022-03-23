package com.example.daycare2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button logOutBtn;
    @FXML
    private Button childInfoBtn;
    @FXML
    private Button employeeInfoBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            childInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "childrenMenu.fxml", "Children Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            employeeInfoBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "employeeMenu.fxml", "Employee menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            logOutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "logIn.fxml", "log in");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
