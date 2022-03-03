/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reminderapp;

import Alarma.Recordador;
import Alarma.Reproductor;
import Db.DB;
import UI.FileChooser;
import UI.LookManager;
import UI.Principal;
import com.jtattoo.plaf.AbstractBorderFactory;
import com.jtattoo.plaf.AbstractIconFactory;
import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 *
 * @author teval
 */
public class ReminderApp {

    /**
     * @param args the command line arguments
     */
    
    public static Principal pagina;
    public static Reproductor  mp3Player = new Reproductor();
    
    public static void main(String[] args) {
        LookManager.setLook(new BernsteinLookAndFeel());
        
        pagina = new Principal();

        FileChooser fc = new FileChooser();
        
        File archivo = fc.getFile();

        // muestra error si es inválido
        if ((archivo == null) || (archivo.getName().equals(""))) {
            JOptionPane.showMessageDialog(null, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
        }
        
        DB.initEvents();       
        
        Thread hilo = new Recordador("Reminder");
        hilo.start();
        pagina.setVisible(true);        
    }
    
}
