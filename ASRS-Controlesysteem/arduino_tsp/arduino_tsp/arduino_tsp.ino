//Positie
int huidigeX = 0;
int huidigeY = 0;
//Pinnen
//motor
int m1s = 5;
int m1d = 4; //ON up, OFF down
int m2s = 7;
int m2d = 6; //ON forward, OFF backward
//Meters
int ldrO = 0;
int ldrV = 1;
int ledO = 13;
int ledV = 12;
//Motor speed
int m1speed = 110;
int m2speed = 110;
//Serial read
boolean stringComplete = false;
String inputString = "";

void setup() {
  Serial.begin(9600);
  pinMode(m1s, OUTPUT);
  pinMode(m2s, OUTPUT);
  pinMode(m1d, OUTPUT);
  pinMode(m2d, OUTPUT);
  pinMode(ldrO, INPUT);
  pinMode(ldrV, INPUT);
}

void serialEvent() {
  //Check if serial open is
  while (Serial.available()) {
    //Lees de bits die de java applicatie stuurt
    char inChar = (char)Serial.read(); 
    inputString += inChar;
    //Wanneer hij \n ontvangt stop die
    if (inChar == '\n') {
      stringComplete = true
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
        int waarde = inputString.substring(splitter + 1).toInt;
        if(verzending == "yas"){
          stuurY(waarde);
        }
        stringComplete = false;
    }
}
