package com.divinechild.voiceControl;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioCapture {
    public static void main(String[] args) {
        try {
            // Open the microphone line
            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();

            byte[] data = new byte[1024];
            int bytesRead;

            while (true) {
                bytesRead = microphone.read(data, 0, data.length);
                if (bytesRead > 0) {
                    // Process the audio data
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
