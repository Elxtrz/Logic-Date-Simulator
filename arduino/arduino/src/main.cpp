#include <Arduino.h>

// Pins
const int inputPins[] = {11, 12, 10}; // Input LEDs A, B, C
const int numInputs = sizeof(inputPins)/sizeof(inputPins[0]);
const int outputPin = 9;               // Output LED

// Correct/Wrong LEDs
const int greenPin = A1; // correct
const int redPin   = A0; // wrong

// 7-segment pins mapping (A-G)
const int segMapping[7] = {
    8, // A
    3, // B
    4, // C
    6, // D
    7, // E
    2, // F
    5  // G
};

// Digit patterns 0-9 (common-cathode)
const byte digits[10][7] = {
    {1,1,0,1,1,1,1}, // 0
    {0,0,0,0,0,1,1}, // 1
    {1,0,1,1,1,1,0}, // 2
    {1,0,1,1,0,1,1}, // 3
    {0,1,1,0,0,1,1}, // 4
    {1,1,1,1,0,0,1}, // 5
    {1,1,1,1,1,0,1}, // 6
    {1,0,0,0,0,1,1}, // 7
    {1,1,1,1,1,1,1}, // 8
    {1,1,1,0,0,1,1}  // 9
};

// Turn off all 7-segment LEDs
void clearSegments() {
    for(int i=0;i<7;i++) digitalWrite(segMapping[i], LOW);
}

// Light segments by letters (A-G)
void lightSegments(String segs) {
    clearSegments();
    for(unsigned int i=0;i<segs.length();i++){
        char c = segs[i];
        if(c>='A' && c<='G')
            digitalWrite(segMapping[c-'A'], HIGH);
    }
}

void setup() {
    // Input LEDs
    for(int i=0;i<numInputs;i++){
        pinMode(inputPins[i], OUTPUT);
        digitalWrite(inputPins[i], LOW);
    }

    // Output LED
    pinMode(outputPin, OUTPUT);
    digitalWrite(outputPin, LOW);

    // Correct/Wrong LEDs
    pinMode(greenPin, OUTPUT);
    pinMode(redPin, OUTPUT);
    digitalWrite(greenPin, LOW);
    digitalWrite(redPin, LOW);

    // 7-segment
    for(int i=0;i<7;i++){
        pinMode(segMapping[i], OUTPUT);
        digitalWrite(segMapping[i], LOW);
    }

    Serial.begin(9600);
    delay(2000); // wait for Serial
}

void loop() {
    if(Serial.available()>0){
        String input = Serial.readStringUntil('\n');

        // Parse "R~A~B~C~#~Y~O"
        String fields[7];
        int fieldIndex=0;
        String temp="";
        for(unsigned int i=0;i<input.length();i++){
            char c = input[i];
            if(c=='~'){
                if(fieldIndex<7){
                    fields[fieldIndex++] = temp;
                    temp="";
                }
            } else 
                temp += c;
        }
        if(fieldIndex<7) 
          fields[fieldIndex] = temp;

        // Reset
        if(fields[0]=="1"){
            for(int i=0;i<numInputs;i++) digitalWrite(inputPins[i], LOW);
            digitalWrite(outputPin, LOW);
            clearSegments();
            digitalWrite(greenPin, LOW);
            digitalWrite(redPin, LOW);
        } else {
            // Input LEDs
            for(int i=0;i<numInputs;i++)
                digitalWrite(inputPins[i], fields[i+1].toInt()==1 ? HIGH : LOW);
            

            // Output LED
            digitalWrite(outputPin, fields[6].toInt()==1 ? HIGH : LOW);

            // 7-segment display
            String seg = fields[4];
            if(seg=="X")
                clearSegments();

            else if(seg.length()==1 && seg[0]>='0' && seg[0]<='9'){ // digit
                int num = seg.toInt();

                for(int i=0;i<7;i++)
                    digitalWrite(segMapping[i], digits[num][i]?HIGH:LOW);
              
            } else
                lightSegments(seg); // letters A-G or multiple letters
            

            // Correct / Wrong LEDs
            if(fields[5] == "1"){  // correct
                digitalWrite(greenPin, HIGH);
                digitalWrite(redPin, LOW);

            } else if(fields[5] == "0"){ // wrong
                digitalWrite(greenPin, LOW);
                digitalWrite(redPin, HIGH);

            } else if(fields[5] == "2"){  // off
                digitalWrite(greenPin, LOW);
                digitalWrite(redPin, LOW);

            }
        }
    }
}
