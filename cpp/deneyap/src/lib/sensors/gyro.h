#include <Wire.h>
#include <Adafruit_MPU6050.h>
#include <Adafruit_Sensor.h>

Adafruit_MPU6050 mpu;

bool BeginGyro()
{
    return mpu.begin();
}

float* getAcceleration()
{
    static float data[3];
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);

    data[0]=a.acceleration.x;
    data[1]=a.acceleration.y;
    data[2]=a.acceleration.z;
    return data;
}

float* getGyro()
{
    static float data[3];
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);


    data[0]=g.gyro.x;
    data[1]=g.gyro.y;
    data[2]=g.gyro.z;

    return data;
}

float getGyroTemp()
{
    sensors_event_t a, g, temp;
    mpu.getEvent(&a, &g, &temp);
    return temp.temperature;
}