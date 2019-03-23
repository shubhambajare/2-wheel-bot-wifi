void fforward() {
  server.send(200, "text/plain", "Forward");
  data = 1;
}
void fbackward() {
  server.send(200, "text/plain", "Backward");
  data = 2;
}
void fleft() {
  server.send(200, "text/plain", "Left");
  data = 6;
}
void fright() {
  server.send(200, "text/plain", "Right");
  data = 7;
}

void gfleft() {
  server.send(200, "text/plain", "Left");
  data = 10;
}
void gfright() {
  server.send(200, "text/plain", "Right");
  data = 9;
}
void gbleft() {
  server.send(200, "text/plain", "Left");
  data = 12;
}
void gbright() {
  server.send(200, "text/plain", "Right");
  data = 11;
}




void fturnleft() {
  server.send(200, "text/plain", "Turnleft");
  data = 3;
}
void fturnright() {
  server.send(200, "text/plain", "Turnright");
  data = 4;
}

void fstop1() {
  server.send(200, "text/plain", "Stop");
  data = 5;
}
void flinetracing() {
  server.send(200, "text/plain", "Linetracing");
  data = 8;
}




void fswitch1() {

  data = 0;
  Serial.println("port1");
  pinMode(16, OUTPUT);
  if (p1 == 0) {
    p1 = 1;
    digitalWrite(16, 1);
    server.send(200, "text/plain", "Switch 1 is on ");
  }
  else {
    p1 = 0;
    digitalWrite(16, 0);
    server.send(200, "text/plain", "Switch 1 is off");
  }
}
void fswitch2() {

  data = 0;
  Serial.println("port2");
  pinMode(5, OUTPUT);
  if (p2 == 0) {
    p2 = 1;
    digitalWrite(5, 1);
    server.send(200, "text/plain", "Switch 2 is on");
  } else {
    p2 = 0;
    digitalWrite(5, 0);
    server.send(200, "text/plain", "Switch 2 is off");
  }
}
void fswitch3() {

  Serial.println("Switch3");
  data = 0;
  pinMode(15, OUTPUT);
  if (p3 == 0)
  { p3 = 1;
    digitalWrite(15, 1);
    server.send(200, "text/plain", "Switch 3 is on");
  } else {
    flag = 0;
    p3 = 0;
    server.send(200, "text/plain", "Switch 3 is off");
    digitalWrite(15, 0);
  }
}
void fswitchoff() {
  pinMode(16, OUTPUT);
  pinMode(15, OUTPUT);
  pinMode(5, OUTPUT);

  digitalWrite(16, 0);
  digitalWrite(15, 0);
  digitalWrite(5, 0);

  p1 = p2 = p3 = 0;
}

void ip() {
  server.send(200, "text/plain", "connected");
}
void battery() {
  pinMode(A0, INPUT);
  if (analogRead(A0) < 50) {
    server.send(200, "text/plain", "Not connected to bot");
  }
  else if (analogRead(A0) > bStatus)
    server.send(200, "text/plain", "Battery is charged");
  else {
    server.send(200, "text/plain", "Battery Low");
  }
}

void handleGenericArgs() {
  String message = "";
  for (int i = 0; i < server.args(); i++) {
    message += server.arg(i);
  }
  //  Serial.println(message);
  // server.send(200, "text/plain", message);
  int x = message.toInt();

  if (x == 0) {
    server.send(200, "text/plain", "Please set time");
  }
  else {
    totaltime = x * 1000;
    time1 = millis();
    Serial.println(x);
    Serial.println("Switch 3 is on");
    pinMode(15, OUTPUT);
    digitalWrite(15, 1);
    flag = 1;
    p3 = 1;
    server.send(200, "text/plain", "Switch 3 is on ");
  }
}
