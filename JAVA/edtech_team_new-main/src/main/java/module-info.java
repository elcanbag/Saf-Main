module com.example.edtech_team_new {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires Java.WebSocket;


    opens com.example.edtech_team_new to javafx.fxml;
    exports com.example.edtech_team_new;

}