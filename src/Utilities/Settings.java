/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import UI.LookManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author teval
 */
public class Settings {
    private static String interfaz="Default";
    private static String defaultSong = "./public/Song.mp3";
    private static boolean notificaciones = false;
    private static String [] UIs = new String[] {"Default", "Oscuro", "Amarillo", "Azul", "Mint", "Aluminio", "Noire"};
    
    public static void cargarConfiguracion(){
        JSONParser parser = new JSONParser();
        try{
            File file = new File("./system/Config.json");
            File dir = new File("./system");
            
            if(!dir.exists()){
                dir.mkdir();
            }
            
            if(!file.exists()){
                file.createNewFile();
                createNewSetting();
                return;
            }           
            
            file.setWritable(true, false);
            
            Object obj=parser.parse(new FileReader(file));
            JSONObject jObj = (JSONObject) obj;
            
            interfaz = jObj.get("interfaz").toString();
            defaultSong = "./public/Song.mp3";
            
            LookManager.turnLook(interfaz);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex+"" +
            "\nNo se ha encontrado el archivo",
            "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo cargar la configuracion");
        }
    }
    
    public static void setConfiguration(){
        
    }
    
    public static boolean getNotificaciones(){
        return notificaciones;
    }
    
    public static void turnNotificaciones(){
        notificaciones = notificaciones ? false : true;
    }
    
    public static String getDefaultSong(){
        return defaultSong;
    }
    
    private static void createNewSetting(){       
        String jsonConfig = "{"
                +"\n\"interfaz\": \""+interfaz+"\","
                +"\n\"defaultSong\": \""+defaultSong+"\""
                +"\n}";        
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("./system/Config.json");
            fos.write(jsonConfig.getBytes());
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String[] getInterfaces(){
        return UIs;
    }
    
    public static void setUi(String ui){
        interfaz = ui;
        LookManager.turnLook(interfaz);
        createNewSetting();
    }
    
    public static String getUi(){
        return interfaz;
    }
}
