package com.divinechild.Challenges;

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

    public void move(int speed) {
        Drive.movePercentOut(driveSub, speed - Constants.Positions.DRIVE_ZERO_SPEED);
    }

    public void move(int speed, double seconds) throws InterruptedException {
        Drive.movePercentOut(driveSub, speed - Constants.Positions.DRIVE_ZERO_SPEED);
        hold(seconds);
        Drive.movePercentOut(driveSub, 0 - Constants.Positions.DRIVE_ZERO_SPEED);
    }

    public void turn(int degrees) {
        Drive.steer(driveSub, degrees - Constants.Positions.STEER_CENTER_POSITION);
    }

    public void turn(int degrees, double seconds) throws InterruptedException {
        Drive.steer(driveSub, degrees - Constants.Positions.STEER_CENTER_POSITION);
        hold(seconds);
        Drive.steer(driveSub, Constants.Positions.STEER_CENTER_POSITION);
    }

    public void halt() {
        move(0);
    }

    public void center() {
        turn(0);
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

    public void flashLights(int numTimes, double flashPeriod) throws InterruptedException {
        for (int i = 0; i < numTimes; i++) {
            turnLightsOn();
            hold(flashPeriod * 0.5);
            turnLightsOff();
            hold(flashPeriod * 0.5);
        }
    }

    public void start() {
        halt();
        center();
        isRunning = true;
    }

    public void finish() {
        halt();
        center();
        isRunning = false;
    }

    public void alpha() throws InterruptedException {
        start();

        finish();

        
    }
}
