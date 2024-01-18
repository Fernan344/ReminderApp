/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Components;

import com.raven.table.TableCustom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author teval
 */
public class CustomTableHeader extends DefaultTableCellRenderer{

    public CustomTableHeader() {
        setPreferredSize(new Dimension(0, 35));
        setBackground(new Color(60,60,60));
        setForeground(new Color(200,200,200));
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs); 
        grphcs.setColor(new Color(100, 100, 100));
        grphcs.drawLine(0, getHeight()-1, getWidth(), getWidth()-1);
    }     
}
