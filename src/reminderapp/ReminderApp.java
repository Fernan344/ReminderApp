/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reminderapp;

import Alarma.Recordador;
import Alarma.Reproductor;
import Db.DB;
import UI.Principal;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author teval
 */
public class ReminderApp {

    /**
     * @param args the command line arguments
     */
    
    public static Principal pagina = new Principal();
    public static Reproductor  mp3Player = new Reproductor();
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            System.out.println("error 1");
            Logger.getLogger(ReminderApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            System.out.println("error 2");
            Logger.getLogger(ReminderApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            System.out.println("error 3");
            Logger.getLogger(ReminderApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("error 4");
            Logger.getLogger(ReminderApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DB.initEvents();       
        
        Thread hilo = new Recordador("Reminder");
        hilo.start();
        pagina.setVisible(true);        
    }
    
}
