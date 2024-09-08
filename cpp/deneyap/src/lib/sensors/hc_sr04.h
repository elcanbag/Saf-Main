#ifndef HC_SR04_H
#define HC_SR04_H

#include <Arduino.h>

#define TRIG_PIN D0
#define ECHO_PIN D1

void setupHCSR04() {
    pinMode(TRIG_PIN, OUTPUT);
    pinMode(ECHO_PIN, INPUT);
}

long getDistance() {

    digitalWrite(TRIG_PIN, LOW);
    delayMicroseconds(2);
    
    
    digitalWrite(TRIG_PIN, HIGH);
    delayMicroseconds(10);
    digitalWrite(TRIG_PIN, LOW);
    

    long duration = pulseIn(ECHO_PIN, HIGH);
    

    long distance = duration * 0.034 / 2;
    
    return distance;
}

#endif
