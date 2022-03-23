package com.example.daycare2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeMenuController implements Initializable{
    @FXML
    private Button addEmployeeToListBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button makeScheduleBtn;
    @FXML
    private Button seeTheEmployeeToListLBtn;
    @FXML
    private Button seeTheScheduleBtn;

    //Add employee to employee list attribute
    @FXML
    private TextField employeeAddressTxtFld;
    @FXML
    private TextField employeeDepartmentIDTxtFld;
    @FXML
    private Button addEmployeeCanclBtn;
    @FXML
    private Button addEmployeeEnterBtn;
    @FXML
    private TextField employeeFirstNameTxtFld;
    @FXML
    private TextField employeeLastNameTxtFld;

    // Make Schedule Attributes
    @FXML
    private Button addScheduleBtn;
    @FXML
    private Button mSCanclBtn;
    @FXML
    private DatePicker mSDTxtFld;
    @FXML
    private TextField mSEMIDTxtFld;
    @FXML
    private ChoiceBox<String> shiftChoiceBox;
    private String[] shift = {"Morning","Evening"};



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
// ---------------------Employee Menu list 3 : See employee list----------------------------------------------
            seeTheScheduleBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event,"seeTheSchedule.fxml", "Schedule");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
// ---------------------Employee Menu list 2 : Make Schedule----------------------------------------------
            makeScheduleBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "makeSchedule.fxml", "MakeSchedule menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

// ---------------------Employee Menu list 3 : See employee list----------------------------------------------
            seeTheEmployeeToListLBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event,"seeTheEmployeeList.fxml","Employee Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

// -------------------Employee Menu list 4 : Add new employee to list--------------------------------------
            addEmployeeToListBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "addEmployeeToList.fxml", "AddEmployeeToList Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            backBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "menu.fxml", "Main Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


// ---------------------make schedule button actions------------------------------------------------------
        try {
            addScheduleBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String workingShift = shiftChoiceBox.getValue();
                    String noShift = "No Shift";
                    if (workingShift.equalsIgnoreCase("morning")) {
                        try {
                            Utils.makeSchedule(mSEMIDTxtFld.getText(), workingShift, noShift, mSDTxtFld.getValue());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Utils.makeSchedule(mSEMIDTxtFld.getText(), noShift, workingShift, mSDTxtFld.getValue());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Execute SuccessFul");

                }
            });
            mSCanclBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "employeeMenu.fxml", "employee menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


// ------------------add employee list button actions---------------------------------------------
        try {
            addEmployeeEnterBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.addEmployeeToList(employeeFirstNameTxtFld.getText(), employeeLastNameTxtFld.getText(),
                                employeeAddressTxtFld.getText(), employeeDepartmentIDTxtFld.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Execution done");
                }
            });
            addEmployeeCanclBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event,"employeeMenu.fxml", "employee Menu");
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
