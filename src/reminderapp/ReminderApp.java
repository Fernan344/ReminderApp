/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reminderapp;

import Alarma.Recordador;
import Db.DB;
import UI.LookManager;
import UI.Principal;
import Utilities.Settings;


/**
 *
 * @author teval
 */
public class ReminderApp {

    /**
     * @param args the command line arguments
     */
    
    public static Principal pagina;
    
    public static void main(String[] args) {
        pagina = new Principal();    
        
        Settings.cargarConfiguracion();     
        
        DB.initEvents();       
        
        Thread hilo = new Recordador("Reminder");
        hilo.start();
        pagina.setVisible(true);        
    }
    
}
