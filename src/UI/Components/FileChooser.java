/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Components;

import UI.Pages.Configurations;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    
    public void copyExternalData(String ruta){        
        try {

            File archivo = this.getFile();

            // muestra error si es inválido
            if ((archivo == null) || (archivo.getName().equals(""))) {
                JOptionPane.showMessageDialog(null, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
            }else{
                this.deepCopy(ruta, archivo);
            }
        } catch (IOException ex) {
            Logger.getLogger(Configurations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deepCopy(String ruta, File archivo) throws IOException{
        Path temp = Files.copy
        (Paths.get(archivo.getPath()),
            Paths.get(ruta), StandardCopyOption.REPLACE_EXISTING);

        if(temp != null){
            JOptionPane.showMessageDialog(null,  "El Archivo Se Modifico Correctamente", "Succes", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "El Archivo No Se Ha Podido Extraer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
