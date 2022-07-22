/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author teval
 */
public class Storage {
    private String nombre;
    private tipos tipo;
    private Object storage;

    public Storage(String nombre, tipos tipo, Object storage) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.storage = storage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tipos getTipo() {
        return tipo;
    }

    public void setTipo(tipos tipo) {
        this.tipo = tipo;
    }

    public Object getStorage() {
        return storage;
    }

    public void setStorage(Object storage) {
        this.storage = storage;
    }

    public enum tipos {
        String,
        Int,
        Double,
        Boolean,
        Evento
    }
    
}
