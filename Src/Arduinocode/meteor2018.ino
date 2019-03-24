#include <ESP8266WiFi.h>
#include<ESP8266WebServer.h>

ESP8266WebServer server;

#define s1 5
#define s2 16
#define s3 15

#define sObstacle A0

#define m11 12
#define m12 13
#define m21 4
#define m22 14

String web = "", web1 = "";

const char* ssid     = "Node2";
const char* password = "trskncoe";

int data = 0, p1 = 0, p2 = 0, p3 = 0, l, r, m, lt = 0, bStatus = 480;
long time1, time2;
int totaltime, flag = 0;


//1=forward
//2=backward
//3=turnleft
//4=turnright
//5=stop1
//6=left
//7=right
//8=linetracing
//9=fright
//10=fleft
//11=bright
//12=bleft

void setup() {


  web += "<html><head><title>ROBOT</title>,</head>";
  web += "<body><h1 style=\"text-align:center;\">METEOR 18</h1><h2 style=\"text-align:center;\">ROBOT CONTROL</h2><div style=\"width: 300px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"FORWARD\"><button style=\"width:290; height:140px;\">FORWARD</button></a></div>";
  web += "<div style=\"width: 900px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"LEFT\"><button style=\"width:290; height:140px;\">LEFT</button></a><a href=\"STOP\"><button style=\"width:290px; height:140px;\">STOP</button></a><a href=\"RIGHT\"><button style=\"width:290; height:140px;\">RIGHT</button></div>";//
  web += "<div style=\"width: 300px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"BACK\"><button style=\"width:290; height:140px;\">BACK</button></a></div>";
  web += "</html>;";


  web1 += "<html><head><title>HOME AUTOMATION</title>,</head>";
  web1 += "<h1 style=\"text-align:center;\">METEOR 18</h1><h2 style=\"text-align:center;\">HOME AUTOMATION</h2>";
  web1 += "<div style=\"width: 300px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"SWITCH1\"><button style=\"width:290px; height:140px;\">SWITCH 1</button></a></div>";
  web1 += "<div style=\"width: 300px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"SWITCH2\"><button style=\"width:290px; height:140px;\">SWITCH 2</button></a></div>";
  web1 += "<div style=\"width: 300px;height: 150px;margin: 0px auto 0px auto;padding: 0px auto 0px auto;\"><a href=\"SWITCH3\"><button style=\"width:290px; height:140px\">SWITCH 3</button></a></div>";
  web1 += "</html>";

  pinMode(m11, OUTPUT);
  pinMode(m12, OUTPUT);
  pinMode(m21, OUTPUT);
  pinMode(m22, OUTPUT);

  stop1();
  Serial.begin(115200);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());


  server.on("/ip", ip);

  server.on("/battery", battery);
  server.on("/genericArgs", handleGenericArgs);

  server.on("/forward", fforward);
  server.on("/backward", fbackward);
  server.on("/left", fleft);
  server.on("/right", fright);
  server.on("/stop", fstop1);

  server.on("/fleft", gfleft);
  server.on("/fright", gfright);
  server.on("/bleft", gbleft);
  server.on("/bright", gbright);

  server.on("/turnleft", fturnleft);
  server.on("/turnright", fturnright);

  server.on("/line", flinetracing);

  server.on("/switch1", fswitch1);
  server.on("/switch2", fswitch2);
  server.on("/switch3", fswitch3);
  server.on("/swichoff", fswitchoff);

  server.on("/ROBOT", []() {
    server.send(200, "text/html", web);
  });

  server.on("/FORWARD", []() {
    data = 1;
    server.send(200, "text/html", web);
    Serial.println("FORWARD");

  });


  server.on("/LEFT", []() {
    data = 3;
    server.send(200, "text/html", web);
    Serial.println("LEFT");

  });

  server.on("/RIGHT", []() {
    data = 4;
    server.send(200, "text/html", web);
    Serial.println("RIGHT");

  });
  server.on("/STOP", []() {
    data = 5;
    server.send(200, "text/html", web);
    Serial.println("STOP");

  });
  server.on("/BACK", []() {
    data = 2;
    server.send(200, "text/html", web);
    Serial.println("BACK");

  });


  server.on("/HOMEAUTO", []() {
    server.send(200, "text/html", web1);
  });

  server.on("/SWITCH1", []() {

    data = 0;
    Serial.println("port1");
    pinMode(16, OUTPUT);
    if (p1 == 0) {
      p1 = 1;
      digitalWrite(16, 1);
    }
    else {
      p1 = 0;
      digitalWrite(16, 0);
    }
    server.send(200, "text/html", web1);
  });


  server.on("/SWITCH2", []() {
    data = 0;
    Serial.println("port2");
    pinMode(5, OUTPUT);
    if (p2 == 0) {
      p2 = 1;
      digitalWrite(5, 1);
    } else {
      p2 = 0;
      digitalWrite(5, 0);
    }

    server.send(200, "text/html", web1);
  });

  server.on("/SWITCH3", []() {

    Serial.println("Switch3");
    data = 0;
    pinMode(15, OUTPUT);
    if (p3 == 0)
    { p3 = 1;
      digitalWrite(15, 1);
    } else {
      p3 = 0;
      digitalWrite(15, 0);
    }

    server.send(200, "text/html", web1);
  });
  server.begin();
}

void loop() {

  if (WiFi.status() != WL_CONNECTED) {
    stop1();
  }
  else {

    server.handleClient();
    switch (data) {
      case 1: forward(1023); break;
      case 2: backward(1023); break;
      case 3: turnleft(1023); break;
      case 4: turnright(1023); break;
      case 5: stop1(); break;
      case 6: left(1023); break;
      case 7: right(1023); break;
      case 8: linetracing(); break;
      case 9: sfright(); break;
      case 10: sfleft(); break;
      case 11: sbright(); break;
      case 12: sbleft(); break;

    }


    time2 = millis() - time1;
    if (time2 > totaltime && flag == 1) {
      Serial.println("port 3 is off");
      flag = 0;
      pinMode(15, OUTPUT);
      digitalWrite(15, 0);
      p3 = 0;
      server.send(200, "text/plain", "Switch 3 is off ");
    }
  }
}


