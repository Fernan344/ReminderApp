/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alarma;

import Db.DB;
import Utilities.Settings;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author teval
 */
public class SettingsVerify extends Thread{
    private int lapso = 1;
   
    public SettingsVerify(String parametros, int lapso){
        super(parametros);
        this.lapso = lapso;
    }
    
    public void run(){
        try{
            for(int i=0; i<lapso*60; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("i: "+i);
            }            
            Settings.turnSilenciar();
        }catch(Exception e){
        }
    }
}
