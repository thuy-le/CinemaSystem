/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class CinemaSystem extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    
    ArrayList<JLabel> labels = new ArrayList<JLabel>();
    ArrayList<ShowingTime> showingTimes = new ArrayList<ShowingTime>();
    List<Room> rooms = new ArrayList<Room>();
    RoomModel roomModel = new RoomModel();
    List<Movie> movies = new ArrayList<Movie>();
    MovieModel model = new MovieModel();
    List<ShowingTime> sTimes = new ArrayList<ShowingTime>();
    ShowingTime showingTime = new ShowingTime(10, 20);
    List<String> period = new ArrayList<String>();
    List<MovieCategory> movieCats = new ArrayList<MovieCategory>();
    MovieView movie = new MovieView(movies, model, rooms, roomModel, movieCats);
    
    CinemaSystem(){
        init();
        pack();
        setTitle("Movie Booking System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    public void init(){
        int i = 0;
        for (; i < 2; i++) {
            Room r = new Room(i + 1, 50 + i);
            rooms.add(r);
        }
        for (; i < 5; i++) {
            Room r = new Room(i + 1, 100 + i);
            rooms.add(r);
        }
        for (; i < 7; i++) {
            Room r = new Room(i + 1, 150 + i);
            rooms.add(r);
        }

        
        
        sTimes.add(showingTime);
        ShowingTime showingTime1 = new ShowingTime(11, 30);
        sTimes.add(showingTime1);
        ShowingTime showingTime2 = new ShowingTime(1, 10);
        sTimes.add(showingTime2);

        Calendar cal = Calendar.getInstance();
 
        for (int j = 0; j < 7; j++) {
            Format f = new SimpleDateFormat("EEEE - dd MMMM yyyy");
            cal.add(Calendar.DATE, +1);
            Date date = cal.getTime();
            period.add(f.format(date));
        }
        
        

        
        MovieCategory cat = new MovieCategory(1, "Drama");
        movieCats.add(cat);
        MovieCategory cat1 = new MovieCategory(2, "Horror");
        movieCats.add(cat1);
        MovieCategory cat2 = new MovieCategory(3, "Cartoon");
        movieCats.add(cat2);
        MovieCategory cat3 = new MovieCategory(4, "Action");
        movieCats.add(cat3);
        MovieCategory cat4 = new MovieCategory(5, "Comedy");
        movieCats.add(cat4);

        String alice = "images/poster/alice.jpg";
        String clash = "images/poster/clash.jpg";
        String friday = "images/poster/friday.jpg";
        String lasthouse = "images/poster/lasthouse.jpg";
        String no4 = "images/poster/no4.jpg";
        String stepup = "images/poster/stepup.jpg";
        String zombie = "images/poster/zombie.jpg";

        Movie movie1 = new Movie(1, "Alice in Wonder Land", 115, sTimes, period, alice, movieCats.get(2), rooms.get(1));
        movies.add(movie1);
        Movie movie2 = new Movie(2, "Clash", 95, sTimes, period, clash, movieCats.get(3), rooms.get(2));
        movies.add(movie2);
        Movie movie3 = new Movie(3, "Friday 13th", 150, sTimes, period, friday, movieCats.get(1), rooms.get(3));
        movies.add(movie3);
        Movie movie4 = new Movie(4, "The Last House on The Left", 120, sTimes, period, lasthouse, movieCats.get(1), rooms.get(4));
        movies.add(movie4);
        Movie movie5 = new Movie(5, "I'm Number 4", 150, sTimes, period, no4, movieCats.get(3), rooms.get(5));
        movies.add(movie5);
        Movie movie6 = new Movie(6, "Step Up 3D", 55, sTimes, period, stepup, movieCats.get(2), rooms.get(0));
        movies.add(movie6);
        Movie movie7 = new Movie(7, "Zombieland", 75, sTimes, period, zombie, movieCats.get(4), rooms.get(6));
        movies.add(movie7);


        File myFile = new File("files/myFile2.txt");
        boolean isExist = myFile.exists();
        if (!isExist) {
        } else {
            myFile.delete();
        }

        
        movie.init();

        add(movie);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                new CinemaSystem();  
            }  
        });  

    }
}
