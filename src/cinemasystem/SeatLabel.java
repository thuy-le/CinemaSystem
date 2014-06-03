/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author PC
 */
public class SeatLabel extends JPanel{
    private String c;
    private int fontSize;
    private int x;
    private int y;
    
    public SeatLabel(final String c, final int fontSize, final int x, final int y){
        this.c = c;
        this.fontSize = fontSize;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        Font font = new Font("Century Gothic", Font.BOLD, fontSize);
        g.setFont(font);
        g.drawString(c, x, y);
    }
    
    @Override
    public Dimension getPreferredSize(){
         return new Dimension(40,40);
     }
    
    
}
