package com.divinechild.Challenges;

import java.util.Timer;
import java.util.TimerTask;

import com.divinechild.drive.Drive;
import com.divinechild.lights.Light;
import com.divinechild.subsystems.DriveSub;
import com.divinechild.subsystems.SonarSub;

public class CharlieChallenge extends Challenge {

    private final SonarSub sonarSub;
    boolean run = true;
    
    public CharlieChallenge(DriveSub driveSub, SonarSub sonarSub, Light light1, Light light2, Light light3) {
        super(driveSub, light1, light2, light3);
        this.sonarSub = sonarSub;
    }

    @Override
    public void run() throws InterruptedException {
        Thread.startVirtualThread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        run = false;
                    }
                }, 30 * 1000);
            }
        });

        System.out.println("Running Charlie");

        while (run) {

            double distanceCM = sonarSub.getDistanceFront();

            if (distanceCM < 150 && distanceCM != -1) {
                halt();
            } else {
                move(20);
            }

        }

        run = true;
    }
}
