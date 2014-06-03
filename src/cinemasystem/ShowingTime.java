/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.*;
import javax.swing.JPanel;



/**
 *
 * @author PC
 */
public class ShowingTime extends JPanel {

    private static final int x = 0;
    private static final int y = 0;
    private static final int width = 90;
    private static final int height = 30;
    private static final int xRound = 10;
    private static final int yRound = 10;

    private String displayText;

    public ShowingTime(final int hour, final int minute) {
        this.displayText = Integer.toString(hour)+" : "+Integer.toString(minute);
    }

    public void paint(Graphics g) {
        //draw rect
        g.setColor(MyColor.darkBlue);
        g.drawRoundRect(x, y, width, height, xRound, yRound);
        g.fillRoundRect(x, y, width, height, xRound, yRound);
        //draw string
        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.BOLD, 15));
        g.drawString(displayText, 25, 20);
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(width+10, height);
    }
    
    public String toString(){
        return displayText;
    }
}
