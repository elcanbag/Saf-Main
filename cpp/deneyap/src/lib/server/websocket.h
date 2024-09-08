#ifndef WEBSOCKET_H
#define WEBSOCKET_H

#include <ArduinoWebsockets.h>
#include "lib/sensors/temperature.h"
#include "lib/sensors/gyro.h"
#include "lib/sensors/bmp.h"
#include "lib/sensors/hc_sr04.h"

using namespace websockets;

WebsocketsClient client;

void setupWebSocket(const char* ssid, const char* password, const char* serverUrl) {
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) {
        delay(500);
        Serial.print(".");
    }
    Serial.println("WiFi Connected!");

    client.connect(serverUrl);
    Serial.println("WebSocket Connection Established!");

    BeginDHT();
    BeginGyro();
    beginBMP();
    setupHCSR04();
}

void sendData() {
    if (client.available()) {
        float temp = getTemp();
        float internalTemp = getGyroTemp();
        float pressure = getBMPPress() / 100.0;
        float hum = getHum();
        long distance = getDistance();

        float* gyroData = getGyro();
        float x = gyroData[0];
        float y = gyroData[1];
        float z = gyroData[2];

        client.send("date:10/02/2024");
        client.send("hum:" + String(hum));
        client.send("lat:42.6448998");
        client.send("longg:42.6448998");
        client.send("temp:" + String(temp));
        client.send("x:" + String(x));
        client.send("y:" + String(y));
        client.send("z:" + String(z));
        client.send("internalTemp:" + String(internalTemp));
        client.send("pressure:" + String(pressure));
        client.send("distance:" + String(distance) + " cm");
    }
}

void managePingPong() {
    static unsigned long lastPingTime = 0;
    if (millis() - lastPingTime > 10000) {
        client.ping();
        Serial.println("Ping sent");
        lastPingTime = millis();
    }

    client.poll();
}

#endif