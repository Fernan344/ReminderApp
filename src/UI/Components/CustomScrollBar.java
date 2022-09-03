/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;
/**
 *
 * @author teval
 */
public class CustomScrollBar extends JScrollBar{
    public CustomScrollBar() {
        setUI(new ScrollBarModern());
        setPreferredSize(new Dimension(5, 5));
        setForeground(new Color(63, 109, 217, 100));
        setOpaque(false);
    }
}
