//Positie
int huidigeX = 0;
int huidigeDoos = 1;
//Motor speed
int xSpeed = 210;
int bSpeed = 150;
//De counters die nodig zijn om de stippen te tellen
int tetellen = 0;
int getelt = 0;
//BPP motor wachttijd
int bWachten = 1000;
//Om een count loop te voorkomen
boolean wachtenX = false;

//Deze functie telt de gaatjes van de X as die hij voorbij gaat
boolean tellenXas(){
  if(analogRead(ldrX) > ldrXwaarde && wachtenX == false){
    getelt++;
    wachtenX = true;
  }else if(analogRead(ldrX) < ldrXwaarde && wachtenX == true){
    wachtenX = false;
  }
  Serial.println(getelt);
  if(getelt >= tetellen){
    //De counters weer resetten wanneer hij klaar is
    resetTeller();
    return true;
  }else{
    return false;
  }
}


//Hiermee sturen we de X as
void stuurX(int xas){
  digitalWrite(ledX, HIGH);
  //Kijkt of hij naar boven of naar beneden moet
  //Dit is naar beneden
  if(xas - huidigeX < 0){
    tetellen = huidigeX - xas;
    digitalWrite(pinXdir, LOW);
    analogWrite(pinXpwm, xSpeed - 20);
  //Dit is naar boven
  }else if(xas - huidigeX > 0){
    tetellen = xas - huidigeX;
    digitalWrite(pinXdir, HIGH);
    analogWrite(pinXpwm, xSpeed);
  }
  Serial.print("Te tellen: ");
  Serial.print(tetellen);
  delay(500);
  //Wachten tot hij op de juiste positie staat
  while(!tellenXas());
  //Schakel de motor en lampje van X as uit
  analogWrite(pinXpwm, 0);
  digitalWrite(ledX, LOW);
  //De counters weer resetten wanneer hij klaar is
  huidigeX = xas;
  Serial.println("xdone");
}

void zetDoos(int doos){
  if(doos == 1 && huidigeDoos != 1){
    digitalWrite(pinBdir, HIGH);
  }else if(doos == 2 && huidigeDoos != 2){
    digitalWrite(pinBdir, LOW);
  }
  if(huidigeDoos != doos){
    analogWrite(pinBpwm, bSpeed);
    delay(bWachten);
    analogWrite(pinBpwm, 0);
    Serial.print("Doos gezet");
    huidigeDoos = doos;
  }
}  

void resetTeller(){
  tetellen = 0;
  getelt = 0;
}
