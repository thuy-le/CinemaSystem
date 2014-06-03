/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;



/**
 *
 * @author PC
 */
public class MovieAdd extends JFrame implements Observer{
    
    public List<Movie> movies;
    public List<MovieCategory> movieCats;
    public MovieModel model;
    public MovieView parentPanel;
    public JTextField txt1;
    public JTextField txt2;
    public JTextField txt5;
    public JComboBox roomList;
    public JComboBox catList;
    
    public List<Room> rooms;
    
    
    public MovieAdd(List<Movie> m, final MovieModel model, final MovieView parentPanel, List<Room> rooms, List<MovieCategory> movieCats){
        this.movies = m;
        this.model = model;
        this.parentPanel = parentPanel;
        this.rooms = rooms;
        this.movieCats = movieCats;
    }
    
    public void init(){
        model.addObserver(this);
        MovieController controller = new MovieController(model, this);
        
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(6,1)){
            @Override
             public Dimension getPreferredSize(){
                return new Dimension(500, 240);
    }
        };
        
        JPanel l1 = new JPanel(new GridLayout(1,3));
        JLabel label1 = new JLabel("Name: ");
        txt1 = new JTextField(20);
        JLabel des1 = new JLabel("");
        l1.add(label1);
        l1.add(txt1);
        l1.add(des1);
        leftPanel.add(l1);
        
        JPanel l2 = new JPanel(new GridLayout(1,3));
        JLabel label2 = new JLabel("Duration: ");
        txt2 = new JTextField(20);
        JLabel des2 = new JLabel("(Minutes)");
        l2.add(label2);
        l2.add(txt2);
        l2.add(des2);
        leftPanel.add(l2);
        
        JPanel l5 = new JPanel(new GridLayout(1,3));
        JLabel label5 = new JLabel("Picture: ");
        txt5 = new JTextField(20);
        JLabel des5 = new JLabel("");
        l5.add(label5);
        l5.add(txt5);
        l5.add(des5);
        leftPanel.add(l5);
        
        Vector myCats = new Vector();
        for (MovieCategory myCat : movieCats) {
            myCats.add(new Item(myCat.getCategoryID(), myCat.getCategoryName()));
        }
        
        JPanel l6 = new JPanel(new GridLayout(1,3));
        JLabel label6 = new JLabel("Category: ");
        catList = new JComboBox(myCats);
        JLabel des6 = new JLabel("");
        l6.add(label6);
        l6.add(catList);
        l6.add(des6);
        leftPanel.add(l6);
        
        
        Vector myRooms = new Vector();
        for (Room r : rooms) {
            myRooms.add(new Item(r.getRoomID(), r.toString()));
        }
        
        JPanel l8 = new JPanel(new GridLayout(1,3));
        JLabel label8 = new JLabel("Room: ");
        roomList = new JComboBox(myRooms);
        JLabel des8 = new JLabel("");
        l8.add(label8);
        l8.add(roomList);
        l8.add(des8);
        leftPanel.add(l8);
        
        JPanel l7 = new JPanel(new GridLayout(1,1));
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(controller);
        l7.add(new JLabel());
        l7.add(addBtn);
        l7.add(new JLabel());
       
        leftPanel.add(l7);
        
        add(leftPanel, BorderLayout.CENTER);
        
        setSize(leftPanel.getPreferredSize());
        setTitle("Add New Movie");
    }

    @Override
    public void update(Observable o, Object arg) {
        movies = (ArrayList<Movie>) arg;
        repaint();
    }
    
}
