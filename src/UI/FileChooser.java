/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author teval
 */
public class FileChooser extends JFileChooser{

    public FileChooser() {
        super();
    }

    public File getFile() {
        LookAndFeel old = UIManager.getLookAndFeel();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Throwable ex) {
           old = null;
        }
        
        super.updateUI();
        
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);       
        
        // indica cual fue la accion de usuario sobre el jfilechooser
        int resultado = this.showOpenDialog(null);
        
        File archivo = this.getSelectedFile(); // obtiene el archivo seleccionado
        
        if(old!=null){
            try {
                UIManager.setLookAndFeel(old);
            } 
            catch (Throwable ex) {
              
            }
        }
        
        return archivo;
    }
    
}
