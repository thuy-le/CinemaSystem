/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;


import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Movie extends JPanel implements Cloneable{

    private static final int x = 15;
    private static final int y = 15;
    private static final int width = 200;
    private static final int height = 300;
    
    private int movieID;
    private String name;
    private int duration;
    private List<ShowingTime> showingTime;
    private List<String> period;
    private String picture;
    private MovieCategory movieCategory;
    private Room room;

    Movie(){}
    
    public Movie(final int movieID, final String name, final int duration, final List<ShowingTime> showingTime, final List<String> period, final String picture, final MovieCategory movieCategory, final Room room) {
        this.movieID = movieID;
        this.name = name;
        this.movieCategory = movieCategory;
        this.duration = duration;
        this.showingTime = showingTime;
        this.period = period;
        this.picture = picture;
        this.room = room;
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public void paint(Graphics g){
        //draw poster
        Image img = Toolkit.getDefaultToolkit().getImage(picture);
        g.drawImage(img, x, y, width, height, this);
        //draw movie name
        Font font = new Font("Century Gothic", Font.BOLD, 15);
        g.setColor(MyColor.darkGrey);
        g.setFont(font);
        g.drawString(name, x, height+35);
        g.drawString("( " + movieCategory.getCategoryName() + " - " + duration + " )", x, height+55);
        g.dispose();
    }
    
    
    
    public Dimension getPreferredSize(){
        return new Dimension(width+40, height+60);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPeriod() {
        return period;
    }

    public void setPeriod(List<String> period) {
        this.period = period;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ShowingTime> getShowingTime() {
        return showingTime;
    }

    public void setShowingTime(List<ShowingTime> showingTime) {
        this.showingTime = showingTime;
    }

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    
}
