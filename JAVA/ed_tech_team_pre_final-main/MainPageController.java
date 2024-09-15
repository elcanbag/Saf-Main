package com.example.edtech_team_new;

import com.example.edtech_team_new.dao.DatesDAO;
import com.example.edtech_team_new.models.Dates;
import com.example.edtech_team_new.webSocket.SimpleWebSocketServer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;



import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;



public class MainPageController implements Initializable {

    @FXML
    private Label date;

    @FXML
    private Label distance;

    @FXML
    private Label hum;

    @FXML
    private Label internalTemp;

    @FXML
    private Label lat;

    @FXML
    private Label longg;

    @FXML
    private ImageView photo;

    @FXML
    private Label pressure;

    @FXML
    private Button refresh_btn;

    @FXML
    private Label temp;

    @FXML
    private Label time;

    @FXML
    private Label x;

    @FXML
    private Label y;

    @FXML
    private Label z;

    private String tempString;
    private SimpleWebSocketServer server;

    private boolean dateReceived = false;
    private boolean humReceived = false;
    private boolean latReceived = false;
    private boolean longgReceived = false;
    private boolean tempReceived = false;
    private boolean xReceived = false;
    private boolean yReceived = false;
    private boolean zReceived = false;
    private boolean internalTempReceived = false;
    private boolean pressureReceived = false;
    private boolean distanceReceived = false;

    DatesDAO datesDAO = new DatesDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startServer();

        refresh_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stop();
                try {
                    Thread.sleep(1000);
                    startServer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void checkIfAllDataReceived() {
        if (dateReceived && humReceived && latReceived && longgReceived && tempReceived &&
                xReceived && yReceived && zReceived && internalTempReceived && pressureReceived &&
                distanceReceived) {
            sqlDates();
        }
    }

    public void sqlDates() {
        try {
            Dates datesNew = new Dates(
                    temp.getText(),
                    hum.getText(),
                    x.getText(),
                    y.getText(),
                    z.getText(),
                    lat.getText(),
                    longg.getText(),
                    internalTemp.getText(),
                    pressure.getText(),
                    distance.getText()
            );
            datesDAO.update(datesNew);
            startServer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void startServer() {
        String host = "0.0.0.0";
        int port = 8094;

        server = new SimpleWebSocketServer(new InetSocketAddress(host, port)) {
            @Override
            public void onMessage(WebSocket conn, String message) {
                super.onMessage(conn, message);

                String[] msg = message.split(":");
                switch (msg[0]) {
                    case "date":
                        Platform.runLater(() -> date.setText(msg[1]));
                        dateReceived = true;
                        break;
                    case "hum":
                        Platform.runLater(() -> hum.setText(msg[1]));
                        humReceived = true;
                        break;
                    case "lat":
                        Platform.runLater(() -> lat.setText(msg[1]));
                        latReceived = true;
                        break;
                    case "longg":
                        Platform.runLater(() -> longg.setText(msg[1]));
                        longgReceived = true;
                        break;
                    case "temp":
                        Platform.runLater(() -> temp.setText(msg[1]));
                        tempReceived = true;
                        break;
                    case "x":
                        Platform.runLater(() -> x.setText(msg[1]));
                        xReceived = true;
                        break;
                    case "y":
                        Platform.runLater(() -> y.setText(msg[1]));
                        yReceived = true;
                        break;
                    case "z":
                        Platform.runLater(() -> z.setText(msg[1]));
                        zReceived = true;
                        break;
                    case "internalTemp":
                        Platform.runLater(() -> internalTemp.setText(msg[1]));
                        internalTempReceived = true;
                        break;
                    case "pressure":
                        Platform.runLater(() -> pressure.setText(msg[1]));
                        pressureReceived = true;
                        break;
                    case "distance":
                        Platform.runLater(() -> distance.setText(msg[1]));
                        distanceReceived = true;
                        break;
                    default:
                        System.out.println("Unknown key: " + message);
                        break;
                }

                checkIfAllDataReceived();
            }

            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                super.onOpen(conn, handshake);
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                super.onClose(conn, code, reason, remote);
            }
        };
        server.start();
    }

    public void stop() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

