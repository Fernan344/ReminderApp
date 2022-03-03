/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import reminderapp.ReminderApp;

/**
 *
 * @author teval
 */
public class LookManager {

    public LookManager() {
    }
    
    public static void setLook(String Look){
        try {
            UIManager.setLookAndFeel(Look);
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
    }
    
    public static void setLook(LookAndFeel Look){
        try {
            UIManager.setLookAndFeel(Look);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("error 4");
            Logger.getLogger(ReminderApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
