/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package reminderapp;

import Alarma.Recordador;
import Db.DB;
import UI.Pages.AllEvents;
import UI.Pages.Main;
import UI.Pages.NonConfirmatedEvents;
import Utilities.Settings;

/**
 *
 * @author teval
 */

public class ReminderApp {

    /**
     * @param args the command line arguments
     */
    
    public static AllEvents pagina;
    public static NonConfirmatedEvents retrasados;
    public static Main principal;
    
    public static void main(String[] args) {
        pagina = new AllEvents();    
        retrasados = new NonConfirmatedEvents();
        principal = new Main();
        principal.setVisible(true);
        
        Settings.cargarConfiguracion();     
        
        DB.initEvents();       
        
        Thread hilo = new Recordador("Reminder");
        hilo.start();
        pagina.setVisible(true);       
    }
    
}
