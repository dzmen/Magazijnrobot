//Positie
int huidigeY = 0;
//De stippen per vak
int vakkenY[] = {0, 8, 6, 6, 7, 6};
int vakkenZ[] = {0, 8, 5, 4, 2};
//Motor speed
int ySpeed = 110;
int zSpeed = 110;
//De counters die nodig zijn om de stippen te tellen
boolean tellenY = false;
boolean tellenZ = false;
int tetellen = 0;
int getelt = 0;
//LDR waardes
int ldrYwaarde = 190;
int ldrZwaarde = 550;
//Om een count loop te voorkomen
boolean wachtenY = false;
boolean wachtenZ = false;

//Deze functie telt de gaatjes van de Y as die hij voorbij gaat
boolean tellenYas(){
  if(tellenY && analogRead(ldrY) > ldrYwaarde && wachtenY == false){
    getelt++;
    wachtenY = true;
  }else if(tellenY && analogRead(ldrY) < ldrYwaarde && wachtenY == true){
    wachtenY = false;
  }
  if(getelt < tetellen){
    return false;
  }else{
    //De counters weer resetten wanneer hij klaar is
    tellenY = false;
    resetTeller();
    return true;
  }
}

//Deze functie telt de gaatjes van de Z as die hij voorbij gaat
boolean tellenZas(){
  if(tellenZ && analogRead(ldrZ) > ldrZwaarde && wachtenZ == false){
    getelt++;
    wachtenZ = true;
  }else if(tellenZ && analogRead(ldrZ) < ldrZwaarde && wachtenZ == true){
    wachtenZ = false;
  }
  if(getelt < tetellen){
    return false;
  }else{
    //De counters weer resetten wanneer hij klaar is
    tellenZ = false;
    resetTeller();
    delay(800);
    return true;
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
    analogWrite(pinYpwm, ySpeed - 30);
  //Dit is naar boven
  }else if(yas - huidigeY > 0){
    for (int i = huidigeY + 1; i <= yas; i++) {
       stappen += vakkenY[i];
    }
    tellenY = true;
    tetellen = stappen;
    digitalWrite(pinYdir, HIGH);
    analogWrite(pinYpwm, ySpeed);
  }
  //Wachten tot hij op de juiste positie staat
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  digitalWrite(ledY, LOW);
  //De counters weer resetten wanneer hij klaar is
  huidigeY = yas;
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
  while(!tellenZas()); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  //Einde Z as naar voren
  
  //Begin Y as omhoog
  //Start de counter
  digitalWrite(ledY, HIGH);
  tellenY = true;
  //Start de motor
  digitalWrite(pinYdir, HIGH);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  tetellen = 2;
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omhoog
  
  
  //Begin Z as naar achter
  tetellen = vakkenZ[pakket];
  //Start de counter
  tellenZ = true;
  //Start de motor
  digitalWrite(pinZdir, LOW);
  analogWrite(pinZpwm, zSpeed + 30);
  //Wachten tot die op positie is
  Serial.println("Tetellen:");
  Serial.println(tetellen);
  while(!tellenZas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  digitalWrite(ledZ, LOW);
  //Einde Z as naar achter
  
  //Begin Y as omlaag
  //Start de counter
  tellenY = true;
  //Start de motor
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed - 20);
  //Wachten tot die op positie is
  tetellen = 2;
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  digitalWrite(ledY, LOW);
  //Einde Y as omlaag
  Serial.println("pakketdone");
  //JAJA, hij is eindelijk klaar :D
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
  while(!tellenZas()); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  //Einde Z as naar voren
  
  //Begin Y as omhoog
  //Start de counter
  digitalWrite(ledY, HIGH);
  tellenY = true;
  //Start de motor
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed - 20);
  //Wachten tot die op positie is
  tetellen = 8;
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  //Einde Y as omhoog
  
  
  //Begin Z as naar achter
  tetellen = vakkenZ[pakket];
  //Start de counter
  tellenZ = true;
  //Start de motor
  digitalWrite(pinZdir, LOW);
  analogWrite(pinZpwm, zSpeed + 30);
  //Wachten tot die op positie is
  Serial.println("Tetellen:");
  Serial.println(tetellen);
  while(!tellenZas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  digitalWrite(ledZ, LOW);
  //Einde Z as naar achter
  
  //Begin Y as omlaag
  //Start de counter
  tellenY = true;
  //Start de motor
  digitalWrite(pinYdir, HIGH);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  tetellen = 8;
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  digitalWrite(ledY, LOW);
  //Einde Y as omlaag
  Serial.println("dropdone");
  //JAJA, hij is eindelijk klaar :D
}

void resetTeller(){
  tetellen = 0;
  getelt = 0;
}
