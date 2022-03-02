/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import reminderapp.ReminderApp;

/**
 *
 * @author teval
 */
public class Evento implements Comparable<Evento>{
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean isNotify;
    private boolean state;
    private int horaFin;
    private int minutoFin;
    private String tiempoFaltante;
    private boolean aviso;

    public Evento(String nombre, Date fechaInicio, Date fechaFin, boolean isNotify, boolean state, int horaFin, int minutoFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.isNotify = isNotify;
        this.state = state;
        this.horaFin = horaFin;
        this.minutoFin = minutoFin;
        this.aviso = false;
        setTiempoFaltante();
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public int getMinutoFin() {
        return minutoFin;
    }

    public void setMinutoFin(int minutoFin) {
        this.minutoFin = minutoFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isIsNotify() {
        return isNotify;
    }

    public void setIsNotify(boolean isNotify) {
        this.isNotify = isNotify;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
    public String toJson(){
        return "{"
                +"\n\"nombre\": \""+this.getNombre()+"\","
                +"\n\"fechaInicio\": \""+this.getFechaInicio().toString()+"\","
                +"\n\"fechaFin\": \""+this.getFechaFin().toString()+"\","
                +"\n\"horaFin\": \""+this.getHoraFin()+"\","
                +"\n\"minutoFin\": \""+this.getMinutoFin()+"\","
                +"\n\"isNotify\": \""+this.isIsNotify()+"\","
                +"\n\"state\": \""+this.isState()+"\""
                +"\n}";
    }
    
    public String getTiempoFaltante(){
        return this.tiempoFaltante;
    }
    
    public void setTiempoFaltante(){       
        long l = this.getMilis();
        if(l<0){
            this.state=false;
            this.tiempoFaltante="0 dias, 0 horas, 0 minutos";
        }else{
            long day=l/(24*60*60*1000);
            long hour=(l/(60*60*1000)-day*24);
            long min=((l/(60*1000))-day*24*60-hour*60);
            long s=(l/1000-day*24*60*60-hour*60*60-min*60);
            this.tiempoFaltante = (day+" dias, "+hour+" horas, "+min+" minutos");
            if(this.isNotify && this.state) this.avisar(day, hour, min, s);
        }
          
    }
    
    public long getMilis(){
        try {
            String fechaLimite[] = getFechaString(this.getFechaFin().toInstant().toString()).split("-");
            String fechaActual[] = getFechaString(LocalDateTime.now().toString()).split("-");
            String horaActual[] = getTiempoActual(LocalDateTime.now().toString()).split(":");     


            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = df.parse(fechaLimite[0]+"-"+fechaLimite[1]+"-"+fechaLimite[2]+" "+horaFin+":"+minutoFin+":00");
            java.util.Date now = df.parse(fechaActual[0]+"-"+fechaActual[1]+"-"+fechaActual[2]+" "+horaActual[0]+":"+horaActual[1]+":00");
            return date.getTime()-now.getTime();
        } catch (ParseException ex) {
            System.out.println("Error GetMilis");
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } 
    }
    
    private void notificar(String mensaje){
        if(aviso==false) { 
            ReminderApp.mp3Player.play();
            this.aviso=true;
            while(JOptionPane.showConfirmDialog(null, mensaje, "Alarma", 2, 1)==-1);   
            ReminderApp.mp3Player.close();
            this.aviso=false;
        }        
    }
    
    private void avisar(long day, long hour, long min, long s){
        System.out.println(day+"--"+hour+"--"+min);
        if(day!=0){
            if((hour==0 || hour==12) && min==0)
                this.notificar("Han Pasado 12 Horas para el evento: "+this.nombre+"\nTe Queda: "+this.tiempoFaltante);
        }else{
            if(hour!=0){
                if(min==0) 
                    this.notificar("Ha Pasado 1 Hora para el evento: "+this.nombre+"\nTe Queda: "+this.tiempoFaltante);
            }else{
                if(min==15 || min==30 || min==45 || min==0) 
                    this.notificar("Ha Pasado 15 Minutos para el evento: "+this.nombre+"\nTe Queda: "+this.tiempoFaltante);
            }
        }
    }    
    
    public static String getTiempoActual(String d){
        return d.split("T")[1];
    }
    
    public static String getFechaString(String d){
        return d.split("T")[0];        
    }

    @Override
    public int compareTo(Evento o) {
        if (this.getMilis() < o.getMilis()) { 
            return -1; 
        } 
        if (this.getMilis() > o.getMilis()) {
            return 1; 
        } 
        return 0; 
    }
}
