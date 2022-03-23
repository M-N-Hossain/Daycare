package com.example.daycare2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private static Statement stmt = null;
    private static ResultSet rs = null;
    private static Connection connect = null;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String usr = "root";
    private static String pass = "Nhn@hid1122";

    @FXML
    private TextField usrTextField;
    @FXML
    private PasswordField passTextField;
    @FXML
    private Label signUpMsgLbl;
    @FXML
    private ImageView signUpIcon;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button logInBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File iconFile = new File("src/main/resources/com/example/daycare2/images/logInIcon.png");
        Image icon = new Image(iconFile.toURI().toString());
        signUpIcon.setImage(icon);

        connection();
        signUpMsgLbl.setText("");
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = usrTextField.getText();
                String password = passTextField.getText();
                if (usrTextField.getText().isEmpty() && passTextField.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Give input");
                    alert.show();
                    System.out.println("Give Input");
                }
                else {
                    try {
                        insert(userName, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Query execute successfully");
                    signUpMsgLbl.setText("you are signed up!");
                }
            }
        });


        logInBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Utils.changeScene(event,"logIn.fxml", "Log in");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void connection(){
        try {
            connect = DriverManager.getConnection(url,usr,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String userName, String password) throws SQLException {
        String query = "Insert into users (userName, password) values ("+ "'"+
                userName +"'" + ", '" + password + "');";
        stmt = connect.createStatement();
        stmt.executeUpdate(query);
    }
}