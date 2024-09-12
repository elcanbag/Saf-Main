#ifndef GYRO_H
#define GYRO_H

#include <Adafruit_MPU6050.h>
#include <Adafruit_Sensor.h>

Adafruit_MPU6050 mpu;

void setupGyro() {
    if (!mpu.begin()) {
        Serial.println("Could not find MPU6050 sensor!");
        while (1);
    }
}

float readGyroTemperature() {
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);
    return temp.temperature;
}

sensors_event_t readGyroAcceleration() {
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);
    return a;
}

#endif // GYRO_H
