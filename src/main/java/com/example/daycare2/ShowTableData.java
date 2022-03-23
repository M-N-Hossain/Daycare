package com.example.daycare2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ShowTableData implements Initializable {

    private static Connection connect;
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Statement stmt;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String user = null;
    private static String passWord = null;

    //Children Table Attributes
    @FXML
    private Button childListBackBtn;
    @FXML
    private TableColumn<User, String> bdClmn;
    @FXML
    private TableColumn<User, String> fnClmn;
    @FXML
    private TableColumn<User, String> giClmn;
    @FXML
    private TableColumn<User, String> idClmn;
    @FXML
    private TableColumn<User, String> lnClmn;
    @FXML
    private TableView<User> tblview;
    ObservableList<User> childrenList = FXCollections.observableArrayList();

    // Waiting list table attributes
    @FXML
    private Button waitingListBackBtn;
    @FXML
    private TableColumn<User, String> waitingFnClmn;
    @FXML
    private TableColumn<User, String> waitingListBdClmn;
    @FXML
    private TableColumn<User, String> waitingListGiClmn;
    @FXML
    private TableColumn<User, String> waitingListIdClmn;
    @FXML
    private TableColumn<User, String> waitingListLnClmn;
    @FXML
    private TableView<User> waitngListTblview;
    ObservableList<User> waitingList = FXCollections.observableArrayList();

    // Parent list attributes
    @FXML
    private TableColumn<User, String> parentListAddressClmn;
    @FXML
    private Button parentListBackBtn;
    @FXML
    private TableColumn<User, String> parentListFnClmn;
    @FXML
    private TableColumn<User, String> parentListIdClmn;
    @FXML
    private TableColumn<User, String> parentListPNClmn;
    @FXML
    private TableView<User> parentListTblview;
    @FXML
    private TableColumn<User, String> parentListLnClmn;
    ObservableList<User> parentList = FXCollections.observableArrayList();

    // Employee list attributes
    @FXML
    private TableColumn<User, String> employeeListAddClmn;
    @FXML
    private Button employeeListBackBtn;
    @FXML
    private TableColumn<User, String> employeeListDiClmn;
    @FXML
    private TableColumn<User, String> employeeListFnClmn;
    @FXML
    private TableColumn<User, String> employeeListIdClmn;
    @FXML
    private TableColumn<User, String> employeeListLnClmn;
    @FXML
    private TableView<User> employeeListTblview;
    ObservableList<User> employeeList = FXCollections.observableArrayList();

    // Schedule List Attributes
    @FXML
    private TableColumn<User, String> scheduleListEIClmn;
    @FXML
    private Button scheduleListBackBtn;
    @FXML
    private TableColumn<User, String> scheduleListDClmn;
    @FXML
    private TableColumn<User, String> scheduleListESClmn;
    @FXML
    private TableColumn<User, String> scheduleListMSClmn;
    @FXML
    private TableView<User> scheduleListTblview;
    ObservableList<User> scheduleList = FXCollections.observableArrayList();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
// -------------------Children list functionalist-----------------------------------------------------------
        try {
            connect = DriverManager.getConnection(url, "root", "Nhn@hid1122");

            rs = connect.createStatement().executeQuery("SELECT * FROM children");
            while (rs.next()) {
                childrenList.add(new User(rs.getString("id"),rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("birthday"),rs.getString("guardianId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            idClmn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            fnClmn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            lnClmn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            bdClmn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            giClmn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
            tblview.setItems(childrenList);

        } catch (Exception e) {

        }
        try {
            childListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "childrenMenu.fxml", "Children Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


//-----------------------Waiting list functionalities-----------------------------------------------------------
        try {
            rs = connect.createStatement().executeQuery("SELECT * FROM waiting_list");
            while (rs.next()) {
                waitingList.add(new User(rs.getString("waiting_ID"),rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("birthday"),rs.getString("guardianId")));
            }
            waitingListIdClmn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            waitingFnClmn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            waitingListLnClmn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            waitingListBdClmn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            waitingListGiClmn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
            waitngListTblview.setItems(waitingList);

            waitingListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "childrenMenu.fxml", "Children Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

//-------------------- parent list functionalist--------------------------------------------------
        try {
            rs = connect.createStatement().executeQuery("SELECT * FROM parental_info");
            while (rs.next()) {
                parentList.add(new User(rs.getString("parentId"),rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("phone_number"),rs.getString("address")));
            }
            parentListIdClmn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            parentListFnClmn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            parentListLnClmn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            parentListPNClmn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            parentListAddressClmn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
            parentListTblview.setItems(parentList);

            parentListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "parentalInfo.fxml", "Parent Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


//------------------------ Employee list functionalist--------------------------------------------------
        try {
            rs = connect.createStatement().executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                employeeList.add(new User(rs.getString("empID"),rs.getString("firstName"),
                        rs.getString("lastName"),rs.getString("address"),rs.getString("departmentID")));
            }
            employeeListIdClmn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            employeeListFnClmn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            employeeListLnClmn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            employeeListAddClmn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            employeeListDiClmn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
            employeeListTblview.setItems(employeeList);

            employeeListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "employeeMenu.fxml", "Employee Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

//------------------------ Schedule list functionalist--------------------------------------------------
        try {
            rs = connect.createStatement().executeQuery("SELECT * FROM employee_schedule");
            while (rs.next()) {
                scheduleList.add(new User(rs.getString("empID"),rs.getString("morning_shift"),
                        rs.getString("evening_shift"),rs.getString("date")," "));
            }
            scheduleListEIClmn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            scheduleListMSClmn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            scheduleListESClmn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            scheduleListDClmn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            scheduleListTblview.setItems(scheduleList);

            scheduleListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "employeeMenu.fxml", "Employee Menu");
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
