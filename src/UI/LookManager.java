/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import reminderapp.ReminderApp;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author teval
 */
public class LookManager {
    
    private static final LookAndFeel defaults = javax.swing.UIManager.getLookAndFeel();

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
    
    public static void turnLook(String tema){
        if(tema.equals("Default"))setLook(new McWinLookAndFeel());
        else if(tema.equals("Oscuro")) setLook(new HiFiLookAndFeel());
        else if(tema.equals("Amarillo")) setLook(new BernsteinLookAndFeel());
        else if(tema.equals("Azul")) setLook("UpperEssential.UpperEssentialLookAndFeel");
        else if(tema.equals("Mint")) setLook(new MintLookAndFeel());
        else if(tema.equals("Aluminio")) setLook(new AluminiumLookAndFeel());
        else if(tema.equals("Noire")) setLook(new NoireLookAndFeel());
        
        ReminderApp.pagina.dispose();
        ReminderApp.pagina=new Principal();
        ReminderApp.pagina.setVisible(true);
    }
}
