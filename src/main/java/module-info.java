module com.example.daycare2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.daycare2 to javafx.fxml;
    exports com.example.daycare2;
}