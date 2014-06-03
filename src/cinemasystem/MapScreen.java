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
public class MapScreen extends JPanel {
    public Image img;
    
    public MapScreen(final String imgSource){
        this.img = Toolkit.getDefaultToolkit().getImage(imgSource);
    }
    
    @Override
    public void paint(Graphics g){
        //create a trapezoid
        Polygon p = new Polygon();
        p.addPoint(3, 3);
        p.addPoint(720, 3);
        p.addPoint(653, 70);
        p.addPoint(70, 70);
        //paint image with the trapezoid shape
        g.setClip(p);
        g.drawImage(img, 3, 3, 720, 70, this); 
    }
    @Override
     public Dimension getPreferredSize(){
         return new Dimension(740,70);
     }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    
    
    
}
