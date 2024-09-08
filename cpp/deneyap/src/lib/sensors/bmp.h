#ifndef BMP_H
#define BMP_H

#include <SPI.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BMP085.h>

Adafruit_BMP085 bmp;

bool beginBMP()
{
    return bmp.begin();
}

float getBMPTemp()
{
    return bmp.readTemperature();
}

float getBMPPress()
{
    return bmp.readPressure();
}

#endif
