/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Models;

import com.raven.table.model.TableRowData;

/**
 *
 * @author teval
 */
public class ModelStaff extends TableRowData{
    
    private String id;
    private String evento;
    private String fechaFin;
    private String horaFin;
    private String tiempo;
    private String notificacion;

    public ModelStaff() {
    }

    public ModelStaff(String id, String evento, String fechaFin, String horaFin, String tiempo, String notificacion) {
        this.id = id;
        this.evento = evento;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.tiempo = tiempo;
        this.notificacion = notificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }
    
        

    @Override
    public Object[] toTableRow() {
        return new Object[]{id, evento, fechaFin, horaFin, tiempo, notificacion};
    }
    
    
}
