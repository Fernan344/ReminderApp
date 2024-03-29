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
    private static ArrayList<Evento> eventos = new ArrayList<Evento>();

    public DB() {
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }
    
    public static Evento getEvent(int index){
        return eventos.get(index);
    }

    public static void setEventos(ArrayList<Evento> eventos) {
        DB.eventos = eventos;
    }
    
    public static void addEvent(Evento e){
        DB.eventos.add(e);        
        Collections.sort(eventos);
        ReminderApp.pagina.llenarTabla();
    }
    
    public static void updateEvent(Evento e, int index){
        DB.eventos.remove(index);
        DB.eventos.add(e);
        Collections.sort(eventos);
        ReminderApp.pagina.llenarTabla();
    }
    
    public static DefaultTableModel fillTable(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        
        model.addColumn("ID");
        model.addColumn("Evento");
        model.addColumn("Fecha Fin");
        model.addColumn("Hora Fin");
        model.addColumn("Tiempo Falta");
        model.addColumn("Notificacion");
        
        for(int i=0; i<eventos.size(); i++){
            Evento evento = eventos.get(i);
            if(evento.isState())
                model.addRow(new Object[]{i, evento.getNombre()
                    , evento.getFechaFin().toInstant().toString().split("T")[0] , (evento.getHoraFin()+":"+evento.getMinutoFin()), evento.getTiempoFaltante(), evento.isIsNotify() ? "Notificaciones":"Sin Notificaciones"
                    });
        }

        return model;
    }
    
    public static void verificacion(){
        Thread hilo = new Recordador("Recordatorio");
        hilo.start();        
        
        for(int i=0; i<DB.getEventos().size(); i++){
            eventos.get(i).setTiempoFaltante();
        }        
        ReminderApp.pagina.llenarTabla();   
        DB.saveEvent();    
    }   
    
    public static void initEvents(){
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
                
                String descripcion = "";
                try{
                    descripcion = jObj.get("descripcion").toString();
                }catch(NullPointerException e){
                    descripcion = "";
                }
                
                boolean isPeriodic = false;
                try{
                    isPeriodic = Boolean.valueOf(jObj.get("isPeriodic").toString());
                }catch(NullPointerException e){
                    isPeriodic = false;
                }
                
                int periodo = 0;
                try{
                    periodo = Integer.valueOf(jObj.get("periodo").toString());
                }catch(NullPointerException e){
                    periodo = 0;
                }
                
                Evento ev = new Evento(jObj.get("nombre").toString()
                    , new Date(jObj.get("fechaInicio").toString())
                    , new Date(jObj.get("fechaFin").toString())
                    , Boolean.valueOf(jObj.get("isNotify").toString())
                    , Boolean.valueOf(jObj.get("state").toString())
                    , Integer.parseInt(jObj.get("horaFin").toString())
                    , Integer.parseInt(jObj.get("minutoFin").toString())
                    , song
                    , periodo
                    , descripcion
                    , isPeriodic);
                addEvent(ev);
            }               
            Collections.sort(eventos);     
            ReminderApp.pagina.llenarTabla();
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex+"" +
            "\nNo se ha encontrado el archivo",
            "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
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
