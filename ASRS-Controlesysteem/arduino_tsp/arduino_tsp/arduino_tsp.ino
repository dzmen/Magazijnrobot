//Positie
int x = 1;
int y = 1;
//Ontvangen positie
int ox = 0;
int oy - y;
//Pinnen
int m1s = 5;
int m1d = 4;
int m2s = 7;
int m2d = 6;
//Motor speed
int m1speed = 90;
int m2speed = 90;
//Serial read
boolean stringComplete = false;
String inputString = "";

void setup() {
  Serial.begin(9600);
  pinMode(m1s, OUTPUT);
  pinMode(m2s, OUTPUT);
  pinMode(m1d, OUTPUT);
  pinMode(m2d, OUTPUT);
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
  // put your main code here, to run repeatedly:

}
