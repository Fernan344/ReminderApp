/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alarma;

import Db.DB;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author teval
 */
public class Recordador extends Thread{
    private int lapso = 1;
   
    public Recordador(String parametros){
        super(parametros);
    }
    
    public void run(){
        try{
            for(int i=0; i<lapso*60; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
            
            DB.verificacion();
        }catch(Exception e){
            System.out.println("Error en el hilo De Verificacion");
            this.start();
        }
    }
}
