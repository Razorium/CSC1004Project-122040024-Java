package com.example.javachatroom;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.javachatroom.MainPageController.voice;

public class VoiceActions extends VoiceUtil{

    //plays the audio sent
    public static void playback(byte[] audio) throws LineUnavailableException {
        InputStream inputStream = new ByteArrayInputStream(audio);
        final AudioFormat audioFormat = getAudioFormat();
        final AudioInputStream audioInputStream = new AudioInputStream(inputStream, audioFormat, audio.length / audioFormat.getFrameSize());
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        final SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();

        Runnable player = new Runnable() {
            int buffersize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
            byte buffer[] = new byte[buffersize];
            @Override
            public void run() {
                try{
                    int counter;
                    while((counter = audioInputStream.read(buffer, 0, buffer.length)) != -1){
                        if(counter > 0){
                            sourceDataLine.write(buffer, 0, counter);
                        }
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    sourceDataLine.drain();
                    sourceDataLine.close();
                }
            }
        };
        Thread play = new Thread(player);
        play.start();
    }

    //records the audio
    public static void record() throws LineUnavailableException {
        final AudioFormat audioFormat = getAudioFormat();
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(audioFormat);
        targetDataLine.start();

        Runnable record = new Runnable() {
            int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
            byte buffer[] = new byte[bufferSize];

            byte buatdikirim[] = new byte[bufferSize];

            @Override
            public void run() {
                byteArrayOutputStream = new ByteArrayOutputStream();
                isRecording = true;
                try {
                    while (isRecording) {
                        int counter = targetDataLine.read(buffer, 0, buffer.length);
                        if (counter > 0) {
                            byteArrayOutputStream.write(buffer, 0, counter);
                        }
                    }
                } finally {
                    try{
                        byteArrayOutputStream.close();
                        byteArrayOutputStream.flush();
                        targetDataLine.close();
                        targetDataLine.flush();
                        voice(byteArrayOutputStream.toByteArray());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread recorder = new Thread(record);
        recorder.start();
    }
}
