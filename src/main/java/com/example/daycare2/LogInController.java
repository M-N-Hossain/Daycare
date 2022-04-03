package com.example.daycare2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private ImageView logInIcon;
    @FXML
    private ImageView logInImg2;
    @FXML
    protected Label logInMsgLbl;
    @FXML
    private Button logInBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private TextField usrTextField;
    @FXML
    private PasswordField passTextField;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File iconFile = new File("src/main/resources/com/example/daycare2/images/logInIcon.png");
        Image icon = new Image(iconFile.toURI().toString());
        logInIcon.setImage(icon);

        File loginImg = new File("src/main/resources/com/example/daycare2/images/logInImg2.png");
        Image img = new Image(loginImg.toURI().toString());
        logInImg2.setImage(img);

        logInMsgLbl.setText(" ");
        logInBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Utils.checkLoginUser(event, usrTextField.getText(), passTextField.getText());
                logInMsgLbl.setText("Invalid user and password, Try again");
                usrTextField.setText("");
                passTextField.setText("");
            }
        });

        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Utils.changeScene(event, "signUp.fxml", "Sign up");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}