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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Button addChildBtn;
    @FXML
    private Button childrenListBtn;
    @FXML
    private Button employeeInfoBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private ImageView menuImg;
    @FXML
    private Button parentListBtn;
    @FXML
    private Button waitingListBtn;

   // ---------------- Adding Menu Attributes-----------------------------------
    @FXML
    private Button addChildCanclBtn;
    @FXML
    private Button addChildEnterBtn;
    @FXML
    private TextField addressTxtField;
    @FXML
    private TextField contactNrTxtField;
    @FXML
    private DatePicker dateOfBirthTxtField;
    @FXML
    private TextField fatherNameTxtField;
    @FXML
    private TextField firstNameTxtField;
    @FXML
    private TextField lastNameTxtField;
    @FXML
    private TextField motherNameTxtField;
    @FXML
    private Label addMsgLbl;
    @FXML
    private ImageView addingImg;


    Stage stage = new Stage();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            File iconFile = new File("src/main/resources/com/example/daycare2/images/daycares.jpg");
            Image icon = new Image(iconFile.toURI().toString());
            menuImg.setImage(icon);

            File addImg = new File("src/main/resources/com/example/daycare2/images/adding.png");
            Image image = new Image(addImg.toURI().toString());
            addingImg.setImage(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
//---------------------- Menu: 01 --> Add child---------------------------------------------------------------------------
        try {

            addChildBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        //Utils.changeScene3("adding.fxml","Add Menu");
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adding.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setTitle("Add Menu");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //------Adding Menu enter btn function------------------------
        try {
            addChildEnterBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.addParent(fatherNameTxtField.getText(), motherNameTxtField.getText(),
                                addressTxtField.getText(), contactNrTxtField.getText());
                        String parentID = Utils.getParent_Id(fatherNameTxtField.getText(), motherNameTxtField.getText(),
                                addressTxtField.getText(), contactNrTxtField.getText());

                        Utils.addChildToList("waiting_List", firstNameTxtField.getText(), lastNameTxtField.getText(),
                                dateOfBirthTxtField.getValue(), parentID);
                        addMsgLbl.setText("Added to the waiting list");
                        Thread.sleep(1000);
                        stage.close();


                        System.out.println("Succeed");
                    } catch (SQLException | InterruptedException  e) {
                        e.printStackTrace();
                    }



                }
            });
            addChildCanclBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage.close();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }


//---------------------- Menu: 02 --> Children List---------------------------------------------------------------------------
        try {
            childrenListBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "seeTheChildrenList.fxml", "Children List");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


//---------------------- Menu: 03 --> Waiting List---------------------------------------------------------------------------
        try {
            waitingListBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event,"seeTheWaitingList.fxml", "Waiting List");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

//---------------------- Menu: 04 --> Parent List---------------------------------------------------------------------------
        try {
            parentListBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event,"seeTheParentalInfo.fxml", "Parent List");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

//---------------------- Menu: 05 --> Employee Info---------------------------------------------------------------------------
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

//---------------------- Menu: 06 --> Log out---------------------------------------------------------------------------
        try {
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
