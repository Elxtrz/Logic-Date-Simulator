#include <Arduino.h>

const int ledPin = 10;

void setup() {
    pinMode(ledPin, OUTPUT);
    Serial.begin(9600);
    delay(2000); // give Arduino time to reset (java closes the port too fast)
}


void loop() {
    if (Serial.available() > 0) {
        String input = Serial.readStringUntil('\n'); // read one line
        for (int i = 0; i < input.length(); i++) {
            char c = input[i];
            if (c == '1') {
                digitalWrite(ledPin, HIGH);
            } else {
                digitalWrite(ledPin, LOW);
            }
            delay(500); // 500ms per bit
        }
    } 
}
