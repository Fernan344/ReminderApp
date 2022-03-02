/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alarma;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class Reproductor {

    
    private final String mp3FileToPlay;
    private Player jlPlayer;

    public Reproductor() {
        this.mp3FileToPlay = "./public/Song.mp3";
    }

    public void play() {
        try {
            FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }

        new Thread() {
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();


    }
    
    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
    
}
