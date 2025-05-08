package com.divinechild.Challenges;

import java.util.Timer;

import com.divinechild.Constants;
import com.divinechild.drive.Drive;
import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;

public class Challenge {
    private final DriveSub driveSub;
    // private final SonarSub sonarSub;
    private final Light light1;
    private final Light light2;
    private final Light light3;

    public boolean isRunning = false;
    
    public Challenge(DriveSub driveSub, Light light1, Light light2, Light light3) {
        this.driveSub = driveSub;
        this.light1 = light1;
        this.light2 = light2;
        this.light3 = light3;
    }

    public long toMillis(double seconds) {
        return (long)(seconds * 1000);
    }

    public void hold(double seconds) throws InterruptedException {
        Thread.sleep(toMillis(seconds));
    }

    public void move(int speed) throws InterruptedException {
        // Drive.moveSpeed += Constants.deltaTime * (speed > Drive.moveSpeed ? -1 : 1);

        // int deltaSpeed = speed - (int)Drive.moveSpeed;

        while (Drive.moveSpeed != speed) {
            if (Drive.moveSpeed < speed) {
                Drive.moveSpeed++;
            } else if (Drive.moveSpeed > speed) {
                Drive.moveSpeed--;
            }

            Drive.movePercentOut(driveSub, Drive.moveSpeed - Constants.Positions.DRIVE_ZERO_SPEED);

            hold(0.02);
        }

        Drive.movePercentOut(driveSub, speed - Constants.Positions.DRIVE_ZERO_SPEED);
    }

    public void move(int speed, double seconds) throws InterruptedException {
        // Drive.moveSpeed += Constants.deltaTime * (speed > Drive.moveSpeed ? -1 : 1);

        move(speed);
        hold(seconds);
        move(0);
    }

    public void turn(int degrees) throws InterruptedException {
        while (Drive.steerAngle != degrees) {
            if (Drive.steerAngle < degrees) {
                Drive.steerAngle++;
            } else if (Drive.steerAngle > degrees) {
                Drive.steerAngle--;
            }

            Drive.steer(driveSub, Drive.steerAngle - Constants.Positions.STEER_CENTER_POSITION);

            hold(0.02);
        }

        Drive.steer(driveSub, degrees - Constants.Positions.STEER_CENTER_POSITION);
    }

    public void turn(int degrees, double seconds) throws InterruptedException {
        turn(degrees);
        hold(seconds);
        turn(0);
    }

    public void halt() throws InterruptedException {
        move(0);
    }

    public void center() throws InterruptedException {
        turn(0);
    }

    public void forceStop() {
        Drive.movePercentOut(driveSub, Constants.Positions.DRIVE_ZERO_SPEED);
    }

    public void turnLightsOn() {
        light1.turnOn();
        light2.turnOn();
        light3.turnOn();
    }

    public void turnLightsOff() {
        light1.turnOff();
        light2.turnOff();
        light3.turnOff();
    }

    public void setLightsBrightness(int brightness) {
        light1.setBrightness(brightness);
        light2.setBrightness(brightness);
        light3.setBrightness(brightness);
    }

    public void flashLights(int numTimes, double flashPeriod) throws InterruptedException {
        for (int i = 0; i < numTimes; i++) {
            turnLightsOn();
            hold(flashPeriod * 0.5);
            turnLightsOff();
            hold(flashPeriod * 0.5);
        }
    }

    public void start() throws InterruptedException {
        halt();
        center();
        isRunning = true;
    }


    public void finish() throws InterruptedException {
        halt();
        center();
        flashLights(3, 1);
        isRunning = false;
    }

    public void testSteer() throws InterruptedException {
        start();

        turn(20, 2);
        hold(1);
        turn(-20, 2);

        finish();        
    }

    public void testMove() throws InterruptedException {
        start();

        move(10, 2);
        hold(1);
        move(-10, 2);

        finish();        
    }

    public void run() throws InterruptedException, Throwable {}
}
