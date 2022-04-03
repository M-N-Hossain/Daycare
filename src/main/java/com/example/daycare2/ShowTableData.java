package com.example.daycare2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ShowTableData implements Initializable {

    private static Connection connect;
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Statement stmt;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String user = null;
    private static String passWord = null;


    @FXML
    private TableColumn<User, String> fourthColumn;
    @FXML
    private TableColumn<User, String> secondColumn;
    @FXML
    private TableColumn<User, String> fifthColumn;
    @FXML
    private TableColumn<User, String> firstColumn;
    @FXML
    private TableColumn<User, String> thirdColumn;
    @FXML
    private TableView<User> tblview;

    @FXML
    private TextField firstTextField;
    @FXML
    private TextField secondTextField;
    @FXML
    private TextField thirdTextField;
    @FXML
    private TextField fourthTextField;
    @FXML
    private TextField fifthTextField;

    // parent Label attributes
    @FXML
    private Label fatherLBL;
    @FXML
    private Label motherLBL;
    @FXML
    private Label addressLBL;
    @FXML
    private Label phoneNrLBL;


    //Children Table Attributes
    @FXML
    private Button csDeleteBtn;
    @FXML
    private Button childListBackBtn;
    @FXML
    private Button csUpdateBTn;
    ObservableList<User> childrenList = FXCollections.observableArrayList();

    // Waiting list table attributes
    @FXML
    private Button waitingListBackBtn;
    @FXML
    private Button wsBackBtn;
    @FXML
    private Button wsDeleteBtn;
    @FXML
    private Button wsTransferBtn;
    @FXML
    private Button wsUpdateBTn;

    @FXML
    private TableView<User> waitingListTblView;
    ObservableList<User> waitingList = FXCollections.observableArrayList();

    // Parent list attributes
    @FXML
    private Button psDeleteBtn;

    @FXML
    private Button psUpdateBTn;
    @FXML
    private Button psBackBtn;

    @FXML
    private TableView<User> parentTblView;
    ObservableList<User> parentList = FXCollections.observableArrayList();

    // Employee list attributes

    @FXML
    private Button employeeListBackBtn;
    @FXML
    private TableView<User> employeeListTblview;
    ObservableList<User> employeeList = FXCollections.observableArrayList();

    // Schedule List Attributes
    @FXML
    private Button scheduleListBackBtn;

    private TableView<User> scheduleListTblview;
    ObservableList<User> scheduleList = FXCollections.observableArrayList();





    int index = -1;
    public void  getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            index = tblview.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            }

            firstTextField.setText(secondColumn.getCellData(index).toString());
            secondTextField.setText(thirdColumn.getCellData(index).toString());
            thirdTextField.setText(fourthColumn.getCellData(index).toString());
            fourthTextField.setText(fifthColumn.getCellData(index).toString());


            connect = DriverManager.getConnection(url, "root", "Nhn@hid1122");
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT p.father_Name, p.mother_Name, p.address, p.contact_Nr FROM parent_List p, admit_Children_List a WHERE p.parent_Id = " + fourthTextField.getText() +";");
                    //+ fourthTextField.getText() + " = a." +fourthTextField.getText() + ";");
            if (rs.next()){
                fatherLBL.setText(rs.getString("father_Name"));
                motherLBL.setText(rs.getString("mother_Name"));
                addressLBL.setText(rs.getString("address"));
                phoneNrLBL.setText(rs.getString("contact_Nr"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getSelected2(MouseEvent mouseEvent) {
        try {
            index = waitingListTblView.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            }
            firstTextField.setText(secondColumn.getCellData(index).toString());
            secondTextField.setText(thirdColumn.getCellData(index).toString());
            thirdTextField.setText(fourthColumn.getCellData(index).toString());
            fourthTextField.setText(fifthColumn.getCellData(index).toString());

            connect = DriverManager.getConnection(url, "root", "Nhn@hid1122");
            stmt = connect.createStatement();
            rs = stmt.executeQuery("SELECT p.father_Name, p.mother_Name, p.address, p.contact_Nr FROM parent_List p, waiting_List a WHERE p.parent_Id = " + fourthTextField.getText() +";");
            //+ fourthTextField.getText() + " = a." +fourthTextField.getText() + ";");
            if (rs.next()){
                fatherLBL.setText(rs.getString("father_Name"));
                motherLBL.setText(rs.getString("mother_Name"));
                addressLBL.setText(rs.getString("address"));
                phoneNrLBL.setText(rs.getString("contact_Nr"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSelected3(MouseEvent mouseEvent) {
        try {
            index = parentTblView.getSelectionModel().getSelectedIndex();
            if (index <= -1) {
                return;
            }
            firstTextField.setText(secondColumn.getCellData(index).toString());
            secondTextField.setText(thirdColumn.getCellData(index).toString());
            thirdTextField.setText(fourthColumn.getCellData(index).toString());
            fourthTextField.setText(fifthColumn.getCellData(index).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updateTable(ObservableList observableList, String tableName, TableView tableView) throws SQLException {
        observableList.clear();
        rs = connect.createStatement().executeQuery("SELECT * FROM " + tableName);
        while (rs.next()){
            observableList.add(new User(rs.getString(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5)));
        }
        tableView.setItems(observableList);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
// -------------------Children list functionalist----------------------------------------------------------
        try {
            fatherLBL.setText("");
            motherLBL.setText("");
            addressLBL.setText("");
            phoneNrLBL.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            connect = DriverManager.getConnection(url, "root", "Nhn@hid1122");

            rs = connect.createStatement().executeQuery("SELECT * FROM admit_Children_List");
            while (rs.next()) {
                childrenList.add(new User(rs.getString("id"),rs.getString("first_Name"),
                        rs.getString("last_Name"),rs.getString("birth_Day"),rs.getString("parent_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            firstColumn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            secondColumn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            thirdColumn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            fourthColumn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            fifthColumn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
            tblview.setItems(childrenList);


        } catch (Exception e) {

        }
        try {
            childListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "menu.fxml", "Menu");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        csDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stmt = connect.createStatement();
                    stmt.execute("DELETE FROM admit_Children_List WHERE id IN ( SELECT id FROM (" +
                            " SELECT id FROM admit_Children_List WHERE first_Name = '" + firstTextField.getText() +"' " +
                            "AND last_Name = '"+ secondTextField.getText() + "' AND birth_Day = '"+ thirdTextField.getText() + "' AND parent_Id = '" +
                            fourthTextField.getText() + "') AS id);");

                    firstTextField.setText("");
                    secondTextField.setText("");
                    thirdTextField.setText("");
                    fourthTextField.setText("");
//                    fifthTextField.setText("");
                    updateTable(childrenList, "admit_Children_List",tblview);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        csUpdateBTn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    stmt = connect.createStatement();
                    stmt.execute("UPDATE admit_Children_List SET first_Name = '"+ firstTextField.getText()+"', " +
                            "last_Name = '"+ secondTextField.getText()+"', " +
                            "birth_Day = '"+thirdTextField.getText()+"', " + "parent_ID = '"+fourthTextField.getText()+"' " +
                            "WHERE id IN ( SELECT id FROM ( SELECT id FROM admit_Children_List WHERE first_Name = '"+firstTextField.getText()+"' " +
                            "AND last_Name = '"+secondTextField.getText()+"' " +
                            "AND birth_Day = '"+thirdTextField.getText()+"' " +
                            "AND parent_Id = '"+fourthTextField.getText()+"') AS id);");

                    updateTable(childrenList, "admit_Children_List", tblview);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });



        } catch (Exception e) {
            e.printStackTrace();
        }




//-----------------------Waiting list functionalities-----------------------------------------------------------
        try {
            rs = connect.createStatement().executeQuery("SELECT * FROM waiting_List");
            while (rs.next()) {
                waitingList.add(new User(rs.getString("waiting_ID"),rs.getString("first_Name"),
                        rs.getString("last_Name"),rs.getString("birth_Day"),rs.getString("parent_ID")));
            }
            firstColumn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            secondColumn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            thirdColumn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            fourthColumn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            fifthColumn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));

            waitingListTblView.setItems(waitingList);



            wsTransferBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.addChildToList("admit_Children_List",firstTextField.getText(),secondTextField.getText(),
                                LocalDate.parse(thirdTextField.getText()),fourthTextField.getText());

                        stmt = connect.createStatement();
                        stmt.execute("DELETE FROM waiting_list WHERE waiting_ID IN ( SELECT waiting_Id FROM (" +
                                " SELECT waiting_ID FROM waiting_List WHERE first_Name = '" + firstTextField.getText() + "' " +
                                "AND last_Name = '" + secondTextField.getText() + "' AND birth_Day = '" + thirdTextField.getText() + "' AND parent_Id = '" +
                                fourthTextField.getText() + "') AS waititng_ID);");

                        firstTextField.setText("");
                        secondTextField.setText("");
                        thirdTextField.setText("");
                        fourthTextField.setText("");
                        updateTable(waitingList, "waiting_List", waitingListTblView);


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }
            });

            try {
                wsUpdateBTn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        try {
                            stmt = connect.createStatement();
                            stmt.execute("UPDATE waiting_List SET first_Name = '"+ firstTextField.getText()+"', " +
                                    "last_Name = '"+ secondTextField.getText()+"', " +
                                    "birth_Day = '"+thirdTextField.getText()+"', " + "parent_ID = '"+fourthTextField.getText()+"' " +
                                    "WHERE waiting_ID IN ( SELECT waiting_ID FROM ( SELECT waiting_ID FROM waiting_List WHERE first_Name = '"+firstTextField.getText()+"' " +
                                    "AND last_Name = '"+secondTextField.getText()+"' " +
                                    "AND birth_Day = '"+thirdTextField.getText()+"' " +
                                    "AND parent_Id = '"+fourthTextField.getText()+"') AS waiting_ID);");

                            updateTable(waitingList, "waiting_List", waitingListTblView);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                wsDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            stmt = connect.createStatement();
                            stmt.execute("DELETE FROM waiting_list WHERE waiting_ID IN ( SELECT waiting_Id FROM (" +
                                    " SELECT waiting_ID FROM waiting_List WHERE first_Name = '" + firstTextField.getText() + "' " +
                                    "AND last_Name = '" + secondTextField.getText() + "' AND birth_Day = '" + thirdTextField.getText() + "' AND parent_Id = '" +
                                    fourthTextField.getText() + "') AS waititng_ID);");

                            firstTextField.setText("");
                            secondTextField.setText("");
                            thirdTextField.setText("");
                            fourthTextField.setText("");
                            updateTable(waitingList, "waiting_List", waitingListTblView);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


            waitingListBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "menu.fxml", "Menu");
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
            rs = connect.createStatement().executeQuery("SELECT * FROM parent_List");
            while (rs.next()) {
                parentList.add(new User(rs.getString("parent_ID"),rs.getString("father_Name"),
                        rs.getString("mother_Name"),rs.getString("address"),rs.getString("contact_Nr")));
            }
            firstColumn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            secondColumn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            thirdColumn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            fourthColumn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            fifthColumn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));

            parentTblView.setItems(parentList);

            try {
                psDeleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            stmt = connect.createStatement();
                            stmt.execute("DELETE FROM parent_List WHERE parent_ID IN ( SELECT parent_Id FROM (" +
                                    " SELECT parent_ID FROM parent_List WHERE father_Name = '" + firstTextField.getText() + "' " +
                                    "AND mother_Name = '" + secondTextField.getText() + "' AND address = '" + thirdTextField.getText() + "' AND contact_Nr = '" +
                                    fourthTextField.getText() + "') AS parent_ID) ;");

                            firstTextField.setText("");
                            secondTextField.setText("");
                            thirdTextField.setText("");
                            fourthTextField.setText("");
                            updateTable(waitingList, "parent_List", parentTblView);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }



            psBackBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Utils.changeScene(event, "menu.fxml", "Menu");
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
            firstColumn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            secondColumn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            thirdColumn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            fourthColumn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
            fifthColumn.setCellValueFactory((new PropertyValueFactory<>("fifthColumn")));
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
            firstColumn.setCellValueFactory((new PropertyValueFactory<>("firstColumn")));
            secondColumn.setCellValueFactory((new PropertyValueFactory<>("secondColumn")));
            thirdColumn.setCellValueFactory((new PropertyValueFactory<>("thirdColumn")));
            fourthColumn.setCellValueFactory((new PropertyValueFactory<>("fourthColumn")));
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
