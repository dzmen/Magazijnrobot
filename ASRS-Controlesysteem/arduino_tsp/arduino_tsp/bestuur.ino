//Positie
int huidigeY = 0;
//De stippen per vak
int vakkenY[] = {0, 6, 7, 6, 5, 6};
int vakkenZ[] = {0, 9, 7, 5, 1, 5};
//Motor speed
int ySpeed = 110;
int zSpeed = 110;
//De counters die nodig zijn om de stippen te tellen
boolean tellenY = false;
boolean tellenZ = false;
int tetellen = 0;
int getelt = 0;
//LDR waardes
int ldrYwaarde = 170;
int ldrZwaarde = 450;
//Om een count loop te voorkomen
boolean wachtenY = false;
boolean wachtenZ = false;

//Deze functie telt de gaatjes van de Y as die hij voorbij gaat
void tellenYas(){
  if(tellenY && digitalRead(ldrY) > ldrYwaarde && wachtenY == false){
    getelt++;
    wachtenY = true;
  }else if(tellenY && digitalRead(ldrY) < ldrYwaarde && wachtenY == true){
    wachtenY = false;
  }
}

//Deze functie telt de gaatjes van de Z as die hij voorbij gaat
void tellenZas(){
  if(tellenZ && digitalRead(ldrZ) > ldrZwaarde && wachtenZ == false){
    getelt++;
    wachtenZ = true;
  }else if(tellenZ && digitalRead(ldrZ) < ldrZwaarde && wachtenZ == true){
    wachtenZ = false;
  }
}

//Hiermee sturen we de Y as
void stuurY(int yas){
  int stappen = 0;
  digitalWrite(ledY, HIGH);
  //Kijkt of hij naar boven of naar beneden moet
  //Dit is naar beneden
  if(yas - huidigeY < 0){
    for (int i = huidigeY; i > yas; i--) {
       stappen += vakkenY[i];
    }
    tellenY = true;
    tetellen = stappen;
    digitalWrite(pinYdir, LOW);
    analogWrite(pinYpwm, ySpeed);
  //Dit is naar boven
  }else if(yas - huidigeY > 0){
    for (int i = huidigeY; i <= yas; i++) {
       stappen += vakkenY[i];
    }
    tellenY = true;
    tetellen = stappen;
    digitalWrite(pinYdir, HIGH);
    analogWrite(pinYpwm, ySpeed);
  }
  //Wachten tot hij op de juiste positie staat
  while(getelt <= tetellen);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  digitalWrite(ledY, LOW);
  //De counters weer resetten wanneer hij klaar is
  huidigeY = yas;
  tellenY = false;
  resetTeller();
  Serial.println("ydone");
}

void pakPakket(int pakket){
  //De Z as naar voren
  tetellen = vakkenZ[pakket];
  //Start de counter
  digitalWrite(ledZ, HIGH);
  tellenZ = true;
  //Start de motor
  digitalWrite(pinZdir, HIGH);
  analogWrite(pinZpwm, zSpeed);
  //Wachten tot die op positie is
  while(getelt <= tetellen);
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  tellenZ = false;
  resetTeller();
  //Einde Z as naar voren
  
  
  //Begin Y as omhoog
  //Start de counter
  digitalWrite(ledY, HIGH);
  tellenZ = true;
  //Start de motor
  digitalWrite(pinYdir, HIGH);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  while(getelt <= 2);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  tellenY = false;
  resetTeller();
  //Einde Y as omhoog
  
  
  //Begin Z as naar achter
  tetellen = vakkenZ[pakket];
  //Start de counter
  tellenZ = true;
  //Start de motor
  digitalWrite(pinZdir, LOW);
  analogWrite(pinZpwm, zSpeed);
  //Wachten tot die op positie is
  while(getelt <= tetellen);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  tellenZ = false;
  digitalWrite(ledZ, LOW);
  resetTeller();
  //Einde Z as naar achter
  
  //Begin Y as omlaag
  //Start de counter
  tellenZ = true;
  //Start de motor
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  while(getelt <= 2);
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  tellenY = false;
  digitalWrite(ledY, LOW);
  resetTeller();
  //Einde Y as omlaag
  Serial.println("pakketdone");
  //JAJA, hij is eindelijk klaar :D
}

void resetTeller(){
  tetellen = 0;
  getelt = 0;
}
