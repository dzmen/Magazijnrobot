//Pinnen
//motor
int pinXpwm = 5;
int pinXdir = 4; //ON up, OFF down
int pinBpwm = 6;
int pinBdir = 7; //ON forward, OFF backward
//Meters
int ldrX = 0;
int ledX = 13;
//Serial read
boolean stringComplete = false;
String inputString = "";

void setup() {
  Serial.begin(9600);
  pinMode(pinXpwm, OUTPUT);
  pinMode(pinXdir, OUTPUT);
  pinMode(pinBpwm, OUTPUT);
  pinMode(pinBdir, OUTPUT);
  pinMode(ledX, OUTPUT);
  pinMode(ldrX, INPUT);
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
        Serial.println("BPP: String received");
        int splitter = inputString.indexOf(':');
        String verzending = inputString.substring(0, splitter);
        int waarde = inputString.substring(splitter + 1).toInt();
        Serial.println(inputString);
        if(verzending == "xas"){
          Serial.println("We gaan de y sturen");
          stuurX(waarde);
        }
        if(verzending == "zetdoos"){
          Serial.println("We gaan het pakket ophalen");
          zetDoos(waarde);
        }
        inputString = "";
        stringComplete = false;
    }
}
