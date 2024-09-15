package com.example.edtech_team_new;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class CameraWebSocketServer extends WebSocketServer {

    private int imageCounter = 0;

    public CameraWebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        try {
            // Save the binary data (image) to a file
            String fileName = "image_" + (imageCounter++) + ".jpg";
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] imageData = new byte[message.remaining()];
            message.get(imageData);
            fos.write(imageData);
            fos.close();
            System.out.println("Saved image as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    public static void main(String[] args) {
        int port = 9090;  // Specify the port you want to use
        InetSocketAddress address = new InetSocketAddress(port);
        CameraWebSocketServer server = new CameraWebSocketServer(address);
        server.start();
        System.out.println("WebSocket server started on port: " + port);
    }
}
