//Positie
int huidigeX = 0;
int huidigeDoos = 1;
//Motor speed
int xSpeed = 240;
int bSpeed = 190;
//De counters die nodig zijn om de stippen te tellen
int vakkenX[] = {0, 670, 620, 610, 610, 610};
//BPP motor wachttijd
int bWachten = 1800;
//Om een count loop te voorkomen
boolean wachtenX = false;

//Hiermee sturen we de Y as
void stuurX(int xas){
  delay(100);
  int dedelay = 0;
  //Kijkt of hij naar boven of naar beneden moet
  //Dit is naar beneden
  if(xas - huidigeX < 0){
    for (int i = huidigeX; i >= xas; i--) {
       dedelay = vakkenX[i];
       digitalWrite(pinXdir, LOW);
       analogWrite(pinXpwm, xSpeed - 42);
       delay(dedelay);
       //Schakel de motor en lampje van Y as uit
       analogWrite(pinXpwm, 0);
       delay(1200);
    }
  //Dit is naar boven
  }else if(xas - huidigeX > 0){
    for (int i = huidigeX + 1; i <= xas; i++) {
       dedelay = vakkenX[i];
       digitalWrite(pinXdir, HIGH);
       analogWrite(pinXpwm, xSpeed); 
       delay(dedelay);
       //Schakel de motor en lampje van Y as uit
       analogWrite(pinXpwm, 0);
       delay(1200);
    }

  }
  //De counters weer resetten wanneer hij klaar is
  huidigeX = xas;
  Serial.println("test1");
  delay(500);
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
    delay(100);
    Serial.println("zetdone");
    huidigeDoos = doos;
  }
} 
