//Positie
int huidigeY = 0;
//De stippen per vak
int vakkenY[] = {0, 7, 7, 8, 7, 5};
int vakkenZ[] = {0, 13, 11, 9, 7, 5};
//Motor speed
int ySpeed = 110;
int zSpeed = 200;
int tetellen = 0;
int getelt = 0;
//LDR waardes
int ldrYwaarde = 200;
int ldrZwaarde = 580;
//Om een count loop te voorkomen
boolean wachtenY = false;
boolean wachtenZ = false;

//Deze functie telt de gaatjes van de Y as die hij voorbij gaat
boolean tellenYas(){
  if(analogRead(ldrY) < ldrYwaarde && wachtenY == false){
    getelt++;
    wachtenY = true;
  }else if(analogRead(ldrY) > ldrYwaarde && wachtenY == true){
    wachtenY = false;
  }
  if(getelt == tetellen){
    //De counters weer resetten wanneer hij klaar is
    resetTeller();
    return true;
  }else{
    Serial.println(getelt);
    return false;
  }
}

//Deze functie telt de gaatjes van de Z as die hij voorbij gaat
boolean tellenZas(){
  if(analogRead(ldrZ) < ldrZwaarde && wachtenZ == false){
    getelt++;
    wachtenZ = true;
  }else if(analogRead(ldrZ) > ldrZwaarde && wachtenZ == true){
    wachtenZ = false;
  }
  if(getelt == tetellen){
    //De counters weer resetten wanneer hij klaar is
    resetTeller();
    return true;      
  }else{
    Serial.println(getelt);
    return false;
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
    tetellen = stappen;
    digitalWrite(pinYdir, LOW);
    analogWrite(pinYpwm, ySpeed - 30);
  //Dit is naar boven
  }else if(yas - huidigeY > 0){
    for (int i = huidigeY + 1; i <= yas; i++) {
       stappen += vakkenY[i];
    }
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
  //Haal er 1 af als je waarde 1 is
  tetellen = tetellen - 1;
  
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
  
  digitalWrite(pinYdir, LOW);
  analogWrite(pinYpwm, ySpeed);
  //Wachten tot die op positie is
  tetellen = 1;
  while(!tellenYas());
  //Schakel de motor en lampje van Y as uit
  analogWrite(pinYpwm, 0);
  digitalWrite(ledY, LOW);
  //Einde Y as omlaag
  Serial.println("pakketdone");
  //JAJA, hij is eindelijk klaar :D
}

void dropPakket(int pakket){
  //De Z as naar voren
  tetellen = vakkenZ[pakket];
  //Start de counter
  digitalWrite(ledZ, HIGH);
  
  //Start de motor
  digitalWrite(pinZdir, HIGH);
  analogWrite(pinZpwm, zSpeed);
  //Wachten tot die op positie is
  while(!tellenZas()); 
    //Schakel de motor en lampje van Y as uit
  analogWrite(pinZpwm, 0);
  //De counters weer resetten wanneer hij klaar is
  //Einde Z as naar voren
  
  //Begin Y as omlaag
  //Start de counter
  digitalWrite(ledY, HIGH);
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
