//Pinnen
//motor
int pinYpwm = 5;
int pinYdir = 4; //ON up, OFF down
int pinZpwm = 7;
int pinZdir = 6; //ON forward, OFF backward
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
        int splitter = inputString.indexOf(':', 11 );
        String verzending = inputString.substring(0, splitter);
        int waarde = inputString.substring(splitter + 1).toInt();
        if(verzending == "yas"){
          stuurY(waarde);
        }else if(verzending == "getpakket"){
          pakPakket(waarde);
        }
        stringComplete = false;
    }
    tellenYas();
    tellenZas();
}
