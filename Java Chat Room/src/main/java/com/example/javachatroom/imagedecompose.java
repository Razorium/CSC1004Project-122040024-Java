package com.example.javachatroom;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.io.Serializable;

public class imagedecompose implements Serializable {
    public static int[][] convert(Image image){
        int height = (int) image.getHeight();
        int width = (int) image.getWidth();
        int[][] hasil = new int[width][height];

        PixelReader pixelReader = image.getPixelReader();
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                hasil[i][j] = pixelReader.getArgb(i, j);
            }
        }
        return hasil;
    }

    public static Image getImage(int [][] imageawal){
        int width = imageawal.length;
        int height = imageawal[0].length;
        WritableImage image = new WritableImage(width, height);

        PixelWriter writer = image.getPixelWriter();
        for(int i = 0; i<width; i++){
            for(int j = 0; j<height; j++){
                writer.setArgb(i, j, imageawal[i][j]);
            }
        }

        return image;
    }
}