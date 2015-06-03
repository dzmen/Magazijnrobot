//Positie
int huidigeY = 0;
//De stippen per vak
//int vakkenY[] = {0, 7, 5, 7, 7, 5};
int vakkenY[] = {0, 970, 970, 970, 970, 920};
int vakkenZ[] = {0, 2000, 1400, 1000, 500, 100};
//Motor speed
int ySpeed = 138;
int zSpeed = 190;
int yVertraging = 35;

//Hiermee sturen we de Y as
void stuurY(int yas){
  delay(100);
  int dedelay = 0;
  //Kijkt of hij naar boven of naar beneden moet
  //Dit is naar beneden
  if(yas - huidigeY < 0){
    for (int i = huidigeY; i >= yas; i--) {
       dedelay += vakkenY[i];
    }
    if(huidigeY - yas == 1){
      dedelay = dedelay - 200;
    }
    digitalWrite(pinYdir, LOW);
    analogWrite(pinYpwm, ySpeed - yVertraging);
  //Dit is naar boven
  }else if(yas - huidigeY > 0){
    for (int i = huidigeY + 1; i <= yas; i++) {
       dedelay += vakkenY[i];
    }
    digitalWrite(pinYdir, HIGH);
    analogWrite(pinYpwm, ySpeed);
  }
  delay(dedelay);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  huidigeY = yas;
  Serial.println("test1");
  delay(500);
  Serial.println("ydone");
  
}

void pakPakket(int pakket){
  //De Z as naar voren
  int dedelay = vakkenZ[pakket];
  
  //Start de motor
  digitalWrite(pinZdir, HIGH);
  analogWrite(pinZpwm, zSpeed);
  //Wachten tot die op positie is
  delay(dedelay); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  //Einde Z as naar voren
  //Start de motor
  digitalWrite(pinYdir, HIGH);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  delay(390);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omhoog
  //Begin Z as naar achter
  dedelay = vakkenZ[pakket];
  dedelay = dedelay - 100;
  //Start de motor
  digitalWrite(pinZdir, LOW);
  analogWrite(pinZpwm, zSpeed + 20);
  //Wachten tot die op positie is
  delay(dedelay);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //Einde Z as naar achter
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed - yVertraging);
  //Wachten tot die op positie is
  delay(410);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omlaag
  Serial.println("pakketdone");
  //JAJA, hij is eindelijk klaar :D
}

void dropPakket(int pakket){
    //Start de motor
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  delay(400); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  
  //De Z as naar voren
  int dedelay = vakkenZ[pakket];
  
  //Start de motor
  digitalWrite(pinZdir, HIGH);
  analogWrite(pinZpwm, zSpeed);
  //Wachten tot die op positie is
  delay(dedelay); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  //Einde Z as naar voren
  //Start de motor
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed - yVertraging);
  //Wachten tot die op positie is
  delay(1300);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omhoog
  //Begin Z as naar achter
  dedelay = vakkenZ[pakket];
  //Start de motor
  digitalWrite(pinZdir, LOW);
  analogWrite(pinZpwm, zSpeed + 20);
  //Wachten tot die op positie is
  delay(dedelay);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //Einde Z as naar achter
  digitalWrite(pinYdir, HIGH);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  delay(1300);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omlaag
  Serial.println("dropdone");
  //JAJA, hij is eindelijk klaar :D
}

