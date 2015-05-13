//MOTORFUNCTIES:
// Links: Draait de motor linksom
// Rechts: Draait de motor rechtsom
// Stop: Stopt de motor

// pinnen
int E1 = 4; // draairichting
int M1 = 5; // snelheid
int E2 = 7; // draairichting
int M2 = 6; // snelheid

// motorWaardes
int motorspeed = 200;

void Links(int motor){
  if(motor >1){
    digitalWrite(E2,LOW);
    analogWrite(M2,motorspeed);
  } else{
    digitalWrite(E1,LOW);
    analogWrite(M1,motorspeed);
  }
}
void Rechts(int motor){
  if(motor >1){
    digitalWrite(E2,HIGH);
    analogWrite(M2,motorspeed);
  } else{
    digitalWrite(E1,HIGH);
    analogWrite(M1,motorspeed);
  }
}
void Stop(int motor){
    if(motor >1){
    digitalWrite(E2,HIGH);
    analogWrite(M2,0);
  } else{
    digitalWrite(E1,HIGH);
    analogWrite(M1,0);
  }
}
