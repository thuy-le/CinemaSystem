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
public class Room extends JPanel implements Cloneable{

    private static final int x = 20;
    private static final int y = 20;
    private static final int width = 150;
    private static final int height = 150;
    private static final int xRound = 20;
    private static final int yRound = 20;
    
    private int roomID;
    private int noOfSeats;
    
    Room() {
    }
    
    public Room(final int roomID, final int noOfSeats) {
        this.roomID = roomID;
        this.noOfSeats = noOfSeats;
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void paint(Graphics g) {
        //draw room
        if (noOfSeats <50) {
            g.setColor(MyColor.youngGreen);
        }        
        else if (noOfSeats >= 50 && noOfSeats<100) {
            g.setColor(MyColor.yellow);
        } else if (noOfSeats >= 100 && noOfSeats < 150) {
            g.setColor(MyColor.purple);
        } else {
            g.setColor(MyColor.greenBlue);
        }
        
        g.drawRoundRect(x, y, width, height, xRound, yRound);
        g.fillRoundRect(x, y, width, height, xRound, yRound);
        //draw room name
        Font font = new Font("Century Gothic", Font.BOLD, 24);
        g.setColor(MyColor.darkBlue);
        g.setFont(font);
        g.drawString("Room No." + Integer.toString(roomID), x+6, y+40);
        
        Font font2 = new Font("Century Gothic", Font.BOLD, 18);
        g.setColor(MyColor.darkGrey);
        g.setFont(font2);
        g.drawString(Integer.toString(noOfSeats) + " Seats", x+20, y+80);
        g.dispose();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(width + 40, height + 40);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public String toString(){
        return "Room No." + roomID;
    }

    public String getNoOfSeats2(){
        return Integer.toString(noOfSeats);
    }
    
    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
    
}
