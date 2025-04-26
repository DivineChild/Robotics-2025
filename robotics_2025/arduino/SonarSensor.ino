const int trigPin = 9;
const int echoPin = 10;

struct Sonar {
  int trigPin;
  int echoPin;
};

Sonar frontSonar = { 6, 7 };
Sonar rightSonar = { 8, 9 };
Sonar backSonar = { 10, 11 };

Sonar sonars[] = { frontSonar, rightSonar, backSonar };

void setup() {
  Serial.begin(9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  for (int i = 0; i < 3; i++) {
    sonars[]
  }
}

void loop() {
  if (Serial.available()) {
    char cmd = Serial.read();
    if (cmd == 'F' || cmd == 'R' || cmd == 'B') {
      Sonar sonar;

      if (cmd == 'F') sonar = sonars[0] // 'F' for front distance
      else if (cmd == 'R') sonar = sonars[1] // 'R' for right distance
      else if (cmd == 'B') sonar = sonars[0] // 'B' for back distance
      
      Serial.println(getFilteredDistance(sonar));
    }
  }
}

float getFilteredDistance(Sonar sonar) {
  float distances[3];
  for (int i = 0; i < 3; i++) {
    distances[i] = readDistance(sonar);
  }
  return median(distances[0], distances[1], distances[2])
}

float readSonar(Sonar sonar) {
  digitalWrite(sonar.trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(sonar.trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(sonar.trigPin, LOW);

  long duration = pulseIn(sonar.echoPin, HIGH);
  return (duration / 1000000.0) * 343.0 / 2.0; // distance in meters
}

float median(int a, int b, int c) {
  if ((a >= b && a <= c) || (a >= c && a <= b)) return a;
  if ((b >= a && b <= c) || (b >= c && b <= a)) return b;
  return c;
}

