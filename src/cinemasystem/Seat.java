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
public class Seat extends JPanel implements Cloneable{
     
    private String seatNo;
    public SeatCategory seatCategory;
    
    public Seat(final String seatNo, final int categoryID){
        this.seatNo = seatNo;
        this.seatCategory = new SeatCategory(categoryID);
    }
    
    @Override
    public void paint(Graphics g){
        //draw seat background
        Image img = Toolkit.getDefaultToolkit().getImage(seatCategory.getImageSrc());
        g.drawImage(img, 0, 0, 40, 40, this);
        //draw seat number
        Font font = new Font("Century Gothic", Font.BOLD, 15);
        g.setColor(Color.BLACK);
        g.setFont(font);
        String sn = seatNo.replaceAll("\\D"," ");
        sn = sn.trim();
        g.drawString(sn, 13, 23);
        g.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(45, 45);
    }
    
    public String toString(){
        return seatNo;
    }
    
    public void setCategory(int catID){
        this.seatCategory = new SeatCategory(catID);
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
}
