#ifndef HC_SR04_H
#define HC_SR04_H

#define TRIG_PIN 18
#define ECHO_PIN 19

#include <Arduino.h>

void setupHCSR04() {
    pinMode(TRIG_PIN, OUTPUT);
    pinMode(ECHO_PIN, INPUT);
}

float getDistance() {
    digitalWrite(TRIG_PIN, LOW);
    delayMicroseconds(2);
    digitalWrite(TRIG_PIN, HIGH);
    delayMicroseconds(10);
    digitalWrite(TRIG_PIN, LOW);

    long duration = pulseIn(ECHO_PIN, HIGH);
    return duration * 0.034 / 2;  // Convert to cm
}

#endif // HC_SR04_H
