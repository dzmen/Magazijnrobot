boolean stringComplete = false;
String inputString = "";
int led=13;

void setup(){
    Serial.begin(9600);
    pinMode(led,OUTPUT);
    Serial.println("Hi, What's Your Name");
    Links(2);
}

void serialEvent() {
  while (Serial.available()) {
    char inChar = (char)Serial.read(); 
    inputString += inChar;
    if (inChar == '\n') {
      stringComplete = true;
      inputString = inputString.substring(0, inputString.length()-1);
    }
   Serial.flush();
    //end of while()
   }
    //end of serialEvent
}

void loop(){
   if(stringComplete){
         digitalWrite(led,HIGH);
         String name = "Hello "+ inputString; 
         inputString = "";
         Serial.println(name);
         stringComplete = false;
    }
}
