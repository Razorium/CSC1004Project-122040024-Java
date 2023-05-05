package com.example.javachatroom;

import java.io.Serializable;

public class Message implements Serializable {
    private String username;
    private String text;
    private String type;
    private byte[] voice;

    public void addVoice(byte[] wah){
        this.voice = wah;
    }
    public void setType(String wew) { this.type = wew; }
    public byte[] returnvoice() { return voice; }

    public String getType() { return type; }

    public Message(String username){
        this.username = username;
    }

    public String returntext(){
        return text;
    }

    public void addtext(String text){
        this.text = text;
    }

    public String returnusername(){
        return username;
    }
}
