package com.example.javachatroom;

import javax.sound.sampled.AudioFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class VoiceUtil {
    protected static MainPageController mainPageController;
    protected static boolean isRecording = false;
    public static void setRecording(boolean huh) { isRecording = huh; }
    public static boolean isitrecording() { return isRecording; }
    static ByteArrayOutputStream byteArrayOutputStream;
    static AudioFormat getAudioFormat(){
        float samplerate = 16000;
        int samplesize = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigend = true;
        AudioFormat format = new AudioFormat(samplerate, samplesize, channels, signed, bigend);
        return format;
    }
}
