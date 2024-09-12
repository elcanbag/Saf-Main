#include <WiFi.h>
#include "lib/server/websocket.h"

// Wi-Fi credentials
const char* ssid = "Qwerty_asggsvsvsgz";
const char* password = "ThisLANisMyLAN";

// WebSocket server IP and port
const char* server_ip = "192.168.0.198";
const uint16_t server_port = 8094;

void setup() {
    Serial.begin(115200);

    // Connect to Wi-Fi
    connectToWiFi(ssid, password);

    // WebSocket setup
    connectWebSocket(server_ip, server_port);

    // Sensor setup
    setupBMP();
    setupGyro();
    setupHCSR04();
    setupDHT();
}

void loop() {
    // Maintain WebSocket connection
    webSocketLoop();

    // Gather and send sensor data via WebSocket
    gatherAndSendSensorData();

    delay(1000);  // Adjust delay as needed
}
