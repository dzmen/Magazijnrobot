//Pinnen
//motor
int pinYpwm = 5;
int pinYdir = 4; //ON up, OFF down
int pinZpwm = 6;
int pinZdir = 7; //ON forward, OFF backward
//Meters
int ldrY = 0;
int ldrZ = 1;
int ledY = 13;
int ledZ = 12;
//Serial read
boolean stringComplete = false;
String inputString = "";

void setup() {
  Serial.begin(9600);
  pinMode(pinYpwm, OUTPUT);
  pinMode(pinYdir, OUTPUT);
  pinMode(pinZpwm, OUTPUT);
  pinMode(pinZdir, OUTPUT);
  pinMode(ledY, OUTPUT);
  pinMode(ledZ, OUTPUT);
  pinMode(ldrY, INPUT);
  pinMode(ldrZ, INPUT);
}

void serialEvent() {
  //Check if serial open is
  while (Serial.available()) {
    //Lees de bits die de java applicatie stuurt
    char inChar = (char)Serial.read(); 
    inputString += inChar;
    //Wanneer hij \n ontvangt stop die
    if (inChar == '\n') {
      stringComplete = true;
      //Hij haalt \n eraf zodat je de juiste string krijgt
      inputString = inputString.substring(0, inputString.length()-1);
    }
    //Haalt de serial leeg
   Serial.flush();
   }
}

void loop() {
     if(stringComplete){
        Serial.println("TSP: String received");
        int splitter = inputString.indexOf(':');
        String verzending = inputString.substring(0, splitter);
        int waarde = inputString.substring(splitter + 1).toInt();
        Serial.println(inputString);
        if(verzending == "yas"){
          Serial.println("We gaan de y sturen");
          stuurY(waarde);
        }
        if(verzending == "krijgpakket"){
          Serial.println("We gaan het pakket ophalen");
          pakPakket(waarde);
        }
        if(verzending == "droppakket"){
          dropPakket(waarde);
        }
        inputString = "";
        stringComplete = false;
    }
}
