#ifndef BMP_H
#define BMP_H

#include <Adafruit_BMP085.h>

// BMP180 sensor setup
Adafruit_BMP085 bmp;

void setupBMP() {
    if (!bmp.begin()) {
        Serial.println("Could not find BMP180 sensor!");
        while (1);
    }
}

float readPressure() {
    return bmp.readPressure();
}

#endif // BMP_H
