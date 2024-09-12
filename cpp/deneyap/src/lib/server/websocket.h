#ifndef WEBSOCKET_H
#define WEBSOCKET_H

#include <ArduinoWebsockets.h>
#include "lib/sensors/bmp.h"
#include "lib/sensors/gyro.h"
#include "lib/sensors/hc_sr04.h"
#include "lib/sensors/temperature.h"

using namespace websockets;

// WebSocket client
WebsocketsClient webSocket;

void sendWebSocketMessage(String key, String value) {
    if (webSocket.available()) {
        String message = key + ":" + value;
        webSocket.send(message);
    }
}

void connectToWiFi(const char* ssid, const char* password) {
    if (WiFi.status() == WL_CONNECTED) {
        Serial.println("Wi-Fi already connected");
        return;
    }
    
    WiFi.begin(ssid, password);
    uint8_t attempts = 0;
    while (WiFi.status() != WL_CONNECTED && attempts < 10) {
        delay(500);  // Shorter retry interval
        Serial.println("Connecting to WiFi...");
        attempts++;
    }

    if (WiFi.status() == WL_CONNECTED) {
        Serial.println("Connected to WiFi");
    } else {
        Serial.println("Failed to connect to WiFi");
    }
}

void connectWebSocket(const char* server_ip, uint16_t server_port) {
    String ws_server_url = String("ws://") + server_ip + ":" + String(server_port) + "/";
    if (webSocket.connect(ws_server_url)) {
        Serial.println("WebSocket connected!");
    } else {
        Serial.println("WebSocket connection failed!");
    }
}

void gatherAndSendSensorData() {
    // Get sensor data
    float humidity = readHumidity();
    float temperature = readTemperature();
    float pressure = readPressure();
    float distance = getDistance();
    float internalTemp = readGyroTemperature();
    sensors_event_t a = readGyroAcceleration();

    // Send sensor data as individual messages via WebSocket
    sendWebSocketMessage("date", "10/02/2024");
    sendWebSocketMessage("hum", String(humidity, 2));
    sendWebSocketMessage("lat", "42.6448998");
    sendWebSocketMessage("longg", "42.6448998");
    sendWebSocketMessage("temp", String(temperature, 2));
    sendWebSocketMessage("x", String(a.acceleration.x, 2));
    sendWebSocketMessage("y", String(a.acceleration.y, 2));
    sendWebSocketMessage("z", String(a.acceleration.z, 2));
    sendWebSocketMessage("internalTemp", String(internalTemp, 2));
    sendWebSocketMessage("pressure", String(pressure, 2));
    sendWebSocketMessage("distance", String(distance, 2));
}

void webSocketLoop() {
    webSocket.poll();
}

#endif // WEBSOCKET_H
