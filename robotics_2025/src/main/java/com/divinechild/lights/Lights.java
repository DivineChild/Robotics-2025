package com.divinechild.lights;

import org.firmata4j.IODevice;

import com.divinechild.subsystems.LightSub;

public class Lights {

    private static LightSub lightSub;

    public Lights() {

    }

    public static void setLightSub(LightSub ls) {
        lightSub = ls;
    }

    public static void turnOn1() {
        lightSub.light1.turnOn();
    }

    public static void turnOn2() {
        lightSub.light2.turnOn();
    }

    public static void turnOn3() {
        lightSub.light3.turnOn();
    }

    public static void turnOff1() {
        lightSub.light1.turnOff();
    }

    public static void turnOff2() {
        lightSub.light2.turnOff();
    }

    public static void turnOff3() {
        lightSub.light3.turnOff();
    }

    public void setBrightness(Light light, int brightness) {
        light.setBrightness(brightness);
    }
}
