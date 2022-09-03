/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Components;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

/**
 *
 * @author teval
 */
public class LabelSplit extends JLabel{
    private boolean gradient;

    public boolean isGradient() {
        return gradient;
    }

    public void setGradient(boolean gradient) {
        this.gradient = gradient;
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        if(gradient){
            g2.setPaint(new GradientPaint(0, 0, new Color(115, 115, 115), getWidth(), 0, new Color(115, 115, 115, 0)));
        }else g2.setColor(new Color(115, 115, 115));
        g2.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
        g2.dispose();
        super.paint(grphcs);
    }   
    
}
