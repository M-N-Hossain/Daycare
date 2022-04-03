package com.example.daycare2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Utils {
    private static Connection connect;
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static Statement stmt;
    private static String url = "jdbc:mysql://localhost:3306/daycare";
    private static String user = null;
    private static String passWord = null;




    public static void connection() {
        user = "root";
        passWord = "Nhn@hid1122";
        try {
            connect = DriverManager.getConnection(url, user, passWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeScene(ActionEvent event, String fxml, String tittle) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Utils.class.getResource(fxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(tittle);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
    public static void changeScene3(String fxml, String tittle) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(tittle);
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScene4(String fxml, String tittle) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(tittle);
        stage.setScene(scene);
        stage.close();
    }


    public static void checkLoginUser(ActionEvent event, String userName, String password) {
        try {
            connection();
            pstmt = connect.prepareStatement("select password, userName from users where userName = ?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            while (rs.next()){
                String retrievedUserName = rs.getString("userName");
                String retrievedPassword = rs.getString("password");

                if (retrievedUserName.equals(userName) && retrievedPassword.equals(password)){
                    changeScene(event,"menu.fxml", "main menu");
                }
                else if (!retrievedUserName.equals(userName) && !retrievedPassword.equals(password)){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Wrong user input");
                    alert.show();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public static void addChildToList(String tableName, String first_Name, String last_Name, LocalDate date, String parent_ID) throws SQLException {
        connection();
        pstmt = connect.prepareStatement("INSERT INTO " + tableName + " (first_Name, last_Name, birth_Day, parent_ID)" +
                "VALUES (?,?,?,?)");
        pstmt.setString(1, first_Name);
        pstmt.setString(2, last_Name);
        pstmt.setDate(3, Date.valueOf(date));
        pstmt.setString(4, parent_ID);

        pstmt.execute();

        System.out.println(" Execute Successfully");
    }

    public static void addParentalInfo(String father_Name, String mother_Name, String address, String contact_Nr) throws SQLException {

        connection();
        pstmt = connect.prepareStatement("INSERT INTO parent_List (father_Name, mother_Name, address, contact_Nr)" +
                "VALUES (?,?,?,?)");
        pstmt.setString(1, father_Name);
        pstmt.setString(2, mother_Name);
        pstmt.setString(3, address);
        pstmt.setString(4, contact_Nr);
        pstmt.execute();

        System.out.println(" Execute Successfully");
    }

    public static void addParent(String father_Name, String mother_Name, String address, String contact_Nr) throws SQLException {
        connection();
        stmt = connect.createStatement();
        rs = stmt.executeQuery("SELECT * FROM parent_List WHERE father_Name = '"+ father_Name +"' AND mother_Name = '"+ mother_Name +
                "' AND address = '"+ address + "' AND contact_Nr = '"+ contact_Nr +"';");

        if (rs.isBeforeFirst()){
            System.out.println("Already Exist");
        }
        else {
            pstmt = connect.prepareStatement("INSERT INTO parent_List (father_Name, mother_Name, address, contact_Nr)" +
                    "VALUES (?,?,?,?)");
            pstmt.setString(1, father_Name);
            pstmt.setString(2, mother_Name);
            pstmt.setString(3, address);
            pstmt.setString(4, contact_Nr);
            pstmt.execute();
        }

    }


    //String id,String firstName,String lastName,String birthDay,String guardianId
    public static void seeChildList() throws SQLException, IOException {
//        try {
//            connect = DriverManager.getConnection(url, "root", "Nhn@hid1122");
//
//            rs = connect.createStatement().executeQuery("SELECT * FROM children");
//            while (rs.next()) {
//                observableList.add(new TableInfo(rs.getString("id"),rs.getString("firstName"),
//                        rs.getString("lastName"),rs.getString("birthday"),rs.getString("guardianId")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            idClmn.setCellValueFactory((new PropertyValueFactory<>("id")));
//            fnClmn.setCellValueFactory((new PropertyValueFactory<>("firstName")));
//            lnClmn.setCellValueFactory((new PropertyValueFactory<>("lastName")));
//            bdClmn.setCellValueFactory((new PropertyValueFactory<>("birthday")));
//            giClmn.setCellValueFactory((new PropertyValueFactory<>("guardianId")));
//            tblview.setItems(observableList);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    public static void addEmployeeToList(String firstName, String lastName, String address, String departmentID) throws SQLException {
        connection();
        pstmt = connect.prepareStatement("INSERT INTO employees (firstName, lastName, address, departmentID)" +
                "VALUES (?,?,?,?)");
        pstmt.setString(1, firstName);
        pstmt.setString(2, lastName);
        pstmt.setString(3, address);
        pstmt.setString(4, departmentID);

        pstmt.execute();
    }

    public static void makeSchedule(String employee_Id, String morning_shift, String evening_shift, LocalDate date) throws SQLException {
        connection();
        pstmt = connect.prepareStatement("INSERT INTO employee_Schedule (employee_Id, morning_shift, evening_shift, date)" +
                "VALUES (?,?,?,?)");
        pstmt.setString(1, employee_Id);
        pstmt.setString(2, morning_shift);
        pstmt.setString(3, evening_shift);
        pstmt.setDate(4, Date.valueOf(date));

        pstmt.execute();
    }

    public static void update(){
        connection();

    }

    public static String getParent_Id(String father_Name, String mother_Name, String address, String contact_Nr) throws SQLException {
        String parent_ID = "";
        connection();
        stmt = connect.createStatement();
//        rs = stmt.executeQuery("SELECT parent_ID FROM parent_List WHERE father_Name = '"+ father_Name +" ' AND mother_Name = '"+ mother_Name +
//                "' AND address = '"+ address + "' AND contact_Nr = '"+ contact_Nr +"';");
        rs = stmt.executeQuery("SELECT parent_ID FROM parent_List WHERE contact_Nr = '" + contact_Nr + "';");
        while (rs.next()){
            parent_ID = rs.getString("parent_ID");
        }
        return  parent_ID;
    }




}


