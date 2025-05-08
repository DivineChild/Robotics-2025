package com.divinechild;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

// import ai.picovoice.picovoice.*;

import com.divinechild.Constants.LightEmittingDiodes;
import com.divinechild.Challenges.AlphaChallenge;
import com.divinechild.Challenges.BravoChallenge;
import com.divinechild.Challenges.Challenge;
import com.divinechild.Challenges.CharlieChallenge;
import com.divinechild.drive.Drive;
import com.divinechild.lights.Lights;
import com.divinechild.lights.Light;
import com.divinechild.motors.Motor;
import com.divinechild.sensory.Sense;
import com.divinechild.sonars.Sonar;
import com.divinechild.subsystems.DriveSub;
import com.divinechild.subsystems.LightSub;
import com.divinechild.subsystems.SonarSub;

public class Main {
    private final Motor driveMotor;
    private final Motor steerMotor;

    private final Sonar frontSonar;
    private final Sonar rightSonar;
    private final Sonar backSonar;

    private final Light light1;
    private final Light light2;
    private final Light light3;

    private final DriveSub driveSub;
    private final SonarSub sonarSub;
    private final LightSub lightSub;

    public Main(IODevice arduino) {
        this.driveMotor = new Motor(arduino, Constants.DriveMotors.driveID);
        this.steerMotor = new Motor(arduino, Constants.DriveMotors.steerID);

        this.driveSub = new DriveSub(driveMotor, steerMotor);

        this.frontSonar = new Sonar(arduino, Constants.SonarSensors.frontTrigID, Constants.SonarSensors.frontEchoID);
        this.rightSonar = new Sonar(arduino, Constants.SonarSensors.frontTrigID, Constants.SonarSensors.frontEchoID);
        this.backSonar = new Sonar(arduino, Constants.SonarSensors.frontTrigID, Constants.SonarSensors.frontEchoID);

        this.sonarSub = new SonarSub(frontSonar, rightSonar, backSonar);

        this.light1 = new Light(arduino, Constants.LightEmittingDiodes.light1ID);
        this.light2 = new Light(arduino, Constants.LightEmittingDiodes.light2ID);
        this.light3 = new Light(arduino, Constants.LightEmittingDiodes.light3ID);

        this.lightSub = new LightSub(light1, light2, light3);

        Lights.setLightSub(lightSub);

        Sense.setSonarSub(sonarSub);
    }

    public static void main(String[] args) throws IllegalStateException, IOException {
        Constants.arduinoPort = args.length > 0 ? args[0]: Constants.arduinoPort;

        IODevice arduino = new FirmataDevice(Constants.arduinoPort);

        try {
            arduino.start();
            arduino.ensureInitializationIsDone();

            System.out.println(arduino.getPinsCount());

            System.out.println("Board Started, wahoo");

            Main main = new Main(arduino);

            


            AlphaChallenge alphaChallenge = new AlphaChallenge(main.driveSub, main.light1, main.light2, main.light3); 
            BravoChallenge bravoChallenge = new BravoChallenge(main.driveSub, main.light1, main.light2, main.light3); 
            CharlieChallenge charlieChallenge = new CharlieChallenge(main.driveSub, main.sonarSub, main.light1, main.light2, main.light3); 
            // DeltaChallenge alphaChallenge = new AlphaChallenge(main.driveSub, main.light1, main.light2, main.light3); 

            // Drive.movePercentOut(main.driveSub, 90);
            // Drive.movePosition(main.driveSub, 90);
            // Drive.steer(main.driveSub, Constants.Positions.STEER_CENTER_POSITION);

            // main.light1.turnOn();
            // main.light2.turnOn();
            // main.light3.turnOn();

            // alphaChallenge.run();

            // String keywordPath = "/absolute/path/to/keyword.ppn";

            // final String accessKey = "${ACCESS_KEY}"; // AccessKey obtained from [Picovoice Console](https://console.picovoice.ai/)

            // PicovoiceWakeWordCallback wakeWordCallback = () -> { };

            // String contextPath = "/absolute/path/to/context.rhn";

            // PicovoiceInferenceCallback inferenceCallback = inference -> {
            //     // `inference` exposes three getters:
            //     // (1) `getIsUnderstood()`
            //     // (2) `getIntent()`
            //     // (3) `getSlots()`
            //     // ..
            // };

            // try {
            //     Picovoice handle = new Picovoice.Builder()
            //                     .setAccessKey(accessKey)
            //                     .setKeywordPath(keywordPath)
            //                     .setWakeWordCallback(wakeWordCallback)
            //                     .setContextPath(contextPath)
            //                     .setInferenceCallback(inferenceCallback)
            //                     .build();
            // } catch (PicovoiceException e) { }

            while (true) {

                // main.light1.turnOn();
                // main.light2.turnOn();
                // main.light3.turnOn();

                Lights.turnOn1();

                System.out.println(Sense.front());
            }


                // ===== ADD PORCUPINE INITIALIZATION HERE =====
                // System.loadLibrary("pv_porcupine");
                // Porcupine porcupine = new Porcupine.Builder()
                //         .setKeywordPath("keywords/hey-robot_en_raspberry-pi.ppn")
                //         .build();

                // // Setup microphone
                // AudioFormat format = new AudioFormat(16000, 16, 1, true, false); // Porcupine expects 16kHz mono PCM
                // DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                // TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
                // microphone.open(format);
                // microphone.start();

                // System.out.println("Listening for wake word...");

                // byte[] buffer = new byte[porcupine.getFrameLength() * 2]; // 16-bit samples (2 bytes)
                // boolean wakeWordDetected = false;

                // while (!wakeWordDetected) {
                //     int bytesRead = microphone.read(buffer, 0, buffer.length);

                //     short[] pcm = new short[porcupine.getFrameLength()];
                //     for (int i = 0; i < pcm.length; i++) {
                //         pcm[i] = (short) ((buffer[2 * i] & 0xff) | (buffer[2 * i + 1] << 8));
                //     }

                //     if (porcupine.process(pcm) > 0) {
                //         System.out.println("Wake word detected!");
                //         wakeWordDetected = true;
                //     }
                // }

                // microphone.stop();
                // microphone.close();
                // porcupine.delete();

                // ==== AFTER WAKE WORD DETECTION ====

                // var myLED = arduino.getPin(7);

                // myLED.setValue(1);

                // myLED.setValue(0);

                

        } catch (Exception e) {
            System.out.println("Error connected: " + e);
        } finally {
            arduino.stop();
            System.out.println("board stopped");
        }
    }
}
