package com.example.edtech_team_new.webSocket;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;

public class SimpleWebSocketServer extends WebSocketServer {

    public SimpleWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); // Yeni bağlantıya hoş geldiniz mesajı
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message + " from " + conn.getRemoteSocketAddress());
        conn.send("Echo: " + message); // Mesajı geri gönderir (echo)
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // Bir hata olursa burada ele alınabilir
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully!");
    }

    public static void main(String[] args) {
        String host = "0.0.0.0";
        int port = 8094; // Port numarası

        SimpleWebSocketServer server = new SimpleWebSocketServer(new InetSocketAddress(host, port));
        server.start();
    }
}
