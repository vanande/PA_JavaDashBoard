module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.dashboard to javafx.fxml;
    exports com.dashboard;
}