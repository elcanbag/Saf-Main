#ifndef TEMPERATURE_H
#define TEMPERATURE_H

#include <DHT.h>

#define DHTPIN 13
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

void setupDHT() {
    dht.begin();
}

float readHumidity() {
    return dht.readHumidity();
}

float readTemperature() {
    return dht.readTemperature();
}

#endif // TEMPERATURE_H
