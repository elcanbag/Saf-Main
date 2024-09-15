package com.example.edtech_team_new;

import com.example.edtech_team_new.dao.DatesDAO;
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
import java.util.ResourceBundle;



public class MainPageController implements Initializable {




    @FXML
    private Label date;

    @FXML
    public Label hum;

    @FXML
    private Label lat;

    @FXML
    private Label longg;

    @FXML
    private ImageView photo;

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




    private void startServer() {
        String host = "0.0.0.0";
        int port = 8094;

        server = new SimpleWebSocketServer(new InetSocketAddress(host, port)) {
            @Override
            public void onMessage(WebSocket conn, String message) {
                super.onMessage(conn, message);

                String[] msg=message.split(":");


                switch (msg[0]) {
                    case "date":
                        Platform.runLater(()-> date.setText(msg[1]));

                        break;
                    case "hum":
                        Platform.runLater(()-> hum.setText(msg[1]));
                        break;
                    case "lat":
                        Platform.runLater(()-> lat.setText(msg[1]));
                        break;
                    case "longg":
                        Platform.runLater(()-> longg.setText(msg[1]));
                        break;
                    case "temp":
                        Platform.runLater(()-> temp.setText(msg[1]));
                        break;
                    case "x":
                        Platform.runLater(()-> x.setText(msg[1]));
                        break;
                    case "y":
                        Platform.runLater(()-> y.setText(msg[1]));
                        break;
                    case "z":
                        Platform.runLater(()-> z.setText(msg[1]));
                        break;
                    default:
                        System.out.println("Unknown key: " + message);
                        break;
                }

                DatesDAO datesDAO = new DatesDAO();
                datesDAO.update(lat.getText());
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
        //new Thread(server::start).start();

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
