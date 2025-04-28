package com.divinechild;

import ai.picovoice.porcupine.Porcupine;
import ai.picovoice.porcupine.PorcupineException;

import javax.sound.sampled.*;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.firmata.FirmataDevice;

import com.divinechild.motors.Motor;
import com.divinechild.sonars.Sonar;
import com.divinechild.subsystems.DriveSub;
import com.divinechild.subsystems.SonarSub;

public class Main {
    private final Motor driveMotor;
    private final Motor steerMotor;

    private final Sonar frontSonar;
    private final Sonar rightSonar;
    private final Sonar backSonar;

    private final DriveSub driveSub;
    private final SonarSub sonarSub;

    public Main(IODevice arduino) {
        this.driveMotor = new Motor(arduino, Constants.DriveMotors.driveID);
        this.steerMotor = new Motor(arduino, Constants.DriveMotors.steerID);
        this.frontSonar = new Sonar('F');
        this.rightSonar = new Sonar('R');
        this.backSonar = new Sonar('B');
        
        this.driveSub = new DriveSub(driveMotor, steerMotor);
        this.sonarSub = new SonarSub(frontSonar, rightSonar, backSonar);
    }


    public static void main(String[] args) throws IllegalStateException, IOException {
        IODevice arduino = new FirmataDevice(Constants.arduinoPort);

        try {
            arduino.start();
            System.out.println("Board Started, wahoo");

            arduino.ensureInitializationIsDone();

            // ===== ADD PORCUPINE INITIALIZATION HERE =====
            System.loadLibrary("pv_porcupine");
            Porcupine porcupine = new Porcupine.Builder()
                    .setKeywordPath("keywords/hey-robot_en_raspberry-pi.ppn")
                    .build();

            // Setup microphone
            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);  // Porcupine expects 16kHz mono PCM
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();

            System.out.println("Listening for wake word...");

            byte[] buffer = new byte[porcupine.getFrameLength() * 2]; // 16-bit samples (2 bytes)
            boolean wakeWordDetected = false;

            while (!wakeWordDetected) {
                int bytesRead = microphone.read(buffer, 0, buffer.length);

                short[] pcm = new short[porcupine.getFrameLength()];
                for (int i = 0; i < pcm.length; i++) {
                    pcm[i] = (short) ((buffer[2 * i] & 0xff) | (buffer[2 * i + 1] << 8));
                }

                if (porcupine.process(pcm) > 0) {
                    System.out.println("Wake word detected!");
                    wakeWordDetected = true;
                }
            }

            microphone.stop();
            microphone.close();
            porcupine.delete();

            // ==== AFTER WAKE WORD DETECTION ====

            var myLED = arduino.getPin(7);

            myLED.setValue(1);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Sleep error.... someone wasn't tired");
            }
            myLED.setValue(0);

        } catch (Exception e) {
            System.out.println("Error connected: " + e);
        } finally {
            arduino.stop();
            System.out.println("board stopped");
        }

    }
}