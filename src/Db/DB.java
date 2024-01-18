package Db;

import Alarma.Recordador;
import Objects.Evento;
import Utilities.Settings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import reminderapp.ReminderApp;


public class DB {
    private static ArrayList<Evento> eventos;
    private static ArrayList<Evento> retrased;

    public DB() {
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }
    
    public static ArrayList<Evento> getEventosRetrasados() {
        return retrased;
    }
    
    public static Evento getEvent(int index, String list){
        return list.equals("actuals")?eventos.get(index):retrased.get(index);
    }
    
    public static void confirmEvent(int index, String list){
        getEvent(index, list).confirmEvent();
        if(list.equals("actuals"))eventIsRetrased(getEvent(index, list), index);
    }
    
    public static void eventIsRetrased(Evento evt, int i){
        DB.eventos.remove(i);
        DB.retrased.add(evt);
        ReminderApp.retrasados.llenarTabla();
    }

    public static void setEventos(ArrayList<Evento> eventos) {
        DB.eventos = eventos;
    }
    
    public static void addEvent(Evento e){
        if(e.setTiempoFaltante()){
            DB.retrased.add(e);
            ReminderApp.retrasados.llenarTabla();
        }
        else {
            DB.eventos.add(e);
            Collections.sort(eventos);
            ReminderApp.pagina.llenarTabla();
        }        
    }
    
    public static void updateEvent(Evento e, int index, String origin){
        if(origin.equals("actuals"))
            if(e.setTiempoFaltante()) eventIsRetrased(e, index);
            else {
                DB.eventos.remove(index);
                DB.eventos.add(e);
            }
        else{
            if(e.setTiempoFaltante()){
                DB.retrased.remove(index);
                DB.retrased.add(e);
            }else{
                DB.retrased.remove(index);
                DB.eventos.add(e);               
            }
             ReminderApp.retrasados.llenarTabla();
        }
        Collections.sort(eventos);
        ReminderApp.pagina.llenarTabla();
    }
   
    public static void verificacion(){
        Thread hilo = new Recordador("Recordatorio");
        hilo.start();        
        boolean hasRetrased = false;
        for(int i=0; i<DB.getEventos().size(); i++){
            if(eventos.get(i).setTiempoFaltante()){
                eventIsRetrased(eventos.get(i), i); 
                hasRetrased = true;
            }            
        }        
        if(hasRetrased) ReminderApp.retrasados.llenarTabla();
        ReminderApp.pagina.llenarTabla();       
        DB.saveEvent();    
    }   
    
    public static boolean initEvents(){
        eventos = new ArrayList<Evento>();
        retrased = new ArrayList<Evento>();
        JSONParser parser = new JSONParser();
        try{
            File file = new File("./public/Events.json");
            
            if(!file.exists()){
                file.createNewFile();
            }            
            
            file.setWritable(true, false);
            
            Object obj=parser.parse(new FileReader(file));
            JSONArray array = (JSONArray) obj;
            
            for(Object arrObj: array){
                JSONObject jObj = (JSONObject) arrObj;
                
                String song = Settings.getDefaultSong();
                try{
                    song = jObj.get("song").toString();
                }catch(NullPointerException e){
                    song = Settings.getDefaultSong();
                }         
                
                String descripcion;
                try{
                    descripcion = jObj.get("descripcion").toString();
                }catch(NullPointerException e){
                    descripcion = "";
                }
                
                boolean isPeriodic;
                try{
                    isPeriodic = Boolean.valueOf(jObj.get("isPeriodic").toString());
                }catch(NullPointerException e){
                    isPeriodic = false;
                }
                
                int periodo;
                try{
                    periodo = Integer.valueOf(jObj.get("periodo").toString());
                }catch(NullPointerException e){
                    periodo = 0;
                }
                
                boolean state;                
                try{
                    state = Boolean.valueOf(jObj.get("state").toString());
                }catch(NullPointerException e){
                    state = true;
                }
                
                boolean confirmed;
                try{
                    confirmed = Boolean.valueOf(jObj.get("isConfirmed").toString());
                }catch(NullPointerException e){
                    confirmed = !state;
                }
                
                Evento ev = new Evento(jObj.get("nombre").toString()
                    , new Date(jObj.get("fechaInicio").toString())
                    , new Date(jObj.get("fechaFin").toString())
                    , Boolean.valueOf(jObj.get("isNotify").toString())
                    , state
                    , Integer.parseInt(jObj.get("horaFin").toString())
                    , Integer.parseInt(jObj.get("minutoFin").toString())
                    , song
                    , periodo
                    , descripcion
                    , isPeriodic
                    , confirmed);
                boolean eventRetrased = ev.setTiempoFaltante();
                if(eventRetrased) DB.retrased.add(ev);
                else DB.eventos.add(ev);                 
            }               
            Collections.sort(eventos); 
            Collections.sort(retrased);
            ReminderApp.pagina.llenarTabla();
            ReminderApp.retrasados.llenarTabla();
            return true;
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex+"" +
            "\nNo se ha encontrado el archivo",
            "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
            return false;
        } catch (ParseException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void saveEvent(){
        
        try {
            String json="[\n";
            for(int i=0; i< eventos.size(); i++){
                Evento e = eventos.get(i);
                json += e.toJson();
                
                if(i!=eventos.size()-1){
                    json+=",";
                }
            }
            if(!retrased.isEmpty())json+=",";
            
            for(int i=0; i< retrased.size(); i++){
                Evento e = retrased.get(i);
                json += e.toJson();
                
                if(i!=retrased.size()-1){
                    json+=",";
                }
            }
            
            json+="\n]";
            
            File file = new File("./public/Events.json");
            if (! file.getParentFile().exists()) {// Si el directorio principal no existe, cree el directorio principal
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {// Si ya existe, elimine el archivo anterior
                file.createNewFile();
            }

            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(json);
            write.flush();
            write.close();
        } catch (IOException ex) {
            System.out.println("ASDfasdfasdf");
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }    
    
}
