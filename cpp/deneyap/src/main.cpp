#include <Arduino.h>
#include <WiFi.h>
#include "lib/server/websocket.h"

const char* ssid = "Qwerty_asggsvsvsgz";
const char* password = "ThisLANisMyLAN";

const char* serverIp = "192.168.0.188";
const int serverPort = 8094;
const char* serverPath = "/";

void setup() {
    Serial.begin(115200);

    String serverUrl = "ws://" + String(serverIp) + ":" + String(serverPort) + String(serverPath);
    setupWebSocket(ssid, password, serverUrl.c_str());
}


void loop() {
    sendData();
    managePingPong();
    delay(2000);
}
