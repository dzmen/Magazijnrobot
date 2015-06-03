//Pinnen
//motor
int pinYpwm = 5;
int pinYdir = 4; //ON up, OFF down
int pinZpwm = 6;
int pinZdir = 7; //ON forward, OFF backward
//Serial read
boolean stringComplete = false;
String inputString = "";

void setup() {
  Serial.begin(9600);
  pinMode(pinYpwm, OUTPUT);
  pinMode(pinYdir, OUTPUT);
  pinMode(pinZpwm, OUTPUT);
  pinMode(pinZdir, OUTPUT);
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
/*  
     if (Serial.available()) 
   {
       int c = Serial.read();
       switch (c)
     {
       case '1':
         stuurY(1);
         break;                    // send command one time
       case '2':
         stuurY(2);
         break;
       case '3':
         stuurY(3);
	 break;
      case '4':
         stuurY(4);
         break;
      case '5':
         stuurY(5);
         break;
      case '6':
         dropPakket(3);
         break;
      case '7':
         dropPakket(2);
         break;
      case '8':
         dropPakket(1);
         break;
     }
   }
*/   
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
        if(verzending == "drop"){
          dropPakket(waarde);
        }
        inputString = "";
        stringComplete = false;
    }
         
}
