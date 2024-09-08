#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>



//Sensor pin
#define DHTPIN 13 
//Sensor type
#define DHTTYPE    DHT11   
DHT_Unified dht(DHTPIN, DHTTYPE);

//For DHT Sensor to start working
void BeginDHT(){
    dht.begin();
}

//For Temperature
int getTemp(){
    sensors_event_t event;
    dht.temperature().getEvent(&event);
    return event.temperature;
}

//For Humidity
int getHum(){
    sensors_event_t event;
    dht.humidity().getEvent(&event);
    return event.relative_humidity;
}