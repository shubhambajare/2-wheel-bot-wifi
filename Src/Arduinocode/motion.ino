
void forward(int a) {
  analogWrite(m11, a);
  digitalWrite(m12, 0);
  analogWrite(m21, a);
  digitalWrite(m22, 0);

  Serial.println ("forward");
}

void backward(int a) {
  analogWrite(m11, 1023 - a);
  digitalWrite(m12, 1);
  analogWrite(m21, 1023 - a);
  digitalWrite(m22, 1);
  Serial.println("backward");
}

void turnleft(int a) {
  analogWrite(m11, a);
  digitalWrite(m12, 0);
  analogWrite(m21, 0);
  digitalWrite(m22, 0);
  Serial.println("turnleft");
}
void turnright(int a) {
  analogWrite(m11, 0);
  digitalWrite(m12, 0);
  analogWrite(m21, a);
  digitalWrite(m22, 0);
  Serial.println("turnright");
}
void stop1() {
  digitalWrite(m11, 0);
  digitalWrite(m12, 0);
  digitalWrite(m21, 0);
  digitalWrite(m22, 0);
  Serial.println("stop");
}
void left(int a) {
  analogWrite(m11, a);
  digitalWrite(m12, 0);
  analogWrite(m21, 1023 - a);
  digitalWrite(m22, 1);
  Serial.println("left");
}
void right(int a) {
  analogWrite(m11, 1023 - a);
  digitalWrite(m12, 1);
  analogWrite(m21, a);
  digitalWrite(m22, 0);
  Serial.println("right");
}
void linetracing() {
  pinMode(s1, INPUT);
  pinMode(s2, INPUT);
  pinMode(s3, INPUT);

  l = digitalRead(s1);
  m = digitalRead(s2);
  r = digitalRead(s3);

  if (l == 1 && m == 1 && r == 1) {
    forward(800);
  }
  if (l == 1  && r == 0) {
    turnleft(800);
    lt = 0;
  }
  if (l == 0 && m == 1 && r == 0) {
    forward(800);
  }
  if (l == 0  && r == 1) {
    turnright(800);
    lt = 1;
  }
  if (l == 0 && m == 0 && r == 0) {
    if (lt == 0)
      left(800);
    else right(800);
  }
  Serial.println("linetracing");
}
void sfright() {
  analogWrite(m11, 1023);
  digitalWrite(m12, 0);
  analogWrite(m21, 800);
  digitalWrite(m22, 0);
}
void sfleft() {
  analogWrite(m11, 800);
  digitalWrite(m12, 0);
  analogWrite(m21, 1023);
  digitalWrite(m22, 0);

}
void sbright() {
  analogWrite(m11, 400);
  digitalWrite(m12, 1);
  analogWrite(m21, 0);
  digitalWrite(m22, 1);

}
void sbleft() {
   analogWrite(m11, 0);
  digitalWrite(m12, 1);
  analogWrite(m21, 400);
  digitalWrite(m22, 1);
  
  }

