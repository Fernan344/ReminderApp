/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.LinkedList;

/**
 *
 * @author teval
 */
public class LocalStorage {
    private static LinkedList<Storage> storage;

    public static void addStorage(String nombre, Storage.tipos tipo, Object objeto){
        boolean added = false;
        for(int i=0; i<storage.size(); i++){
            if(storage.get(i).getNombre().equals(nombre)){
                storage.set(i, new Storage(nombre, tipo, objeto));
                added=true;
            }
        }
        if(!added){
            storage.add(new Storage(nombre, tipo, objeto));
        }
    }  
    
    public static Storage getStorage(String nombre){
        for(Storage st: storage){
            if(st.getNombre().equals(nombre)){
                return st;
            }
        }
        return null;
    }
}
