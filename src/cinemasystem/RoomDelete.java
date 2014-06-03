/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author PC
 */
public class RoomDelete extends JFrame implements Observer {

    public List<Room> rooms;
    private int id;
    private RoomModel model;
    public RoomView parentPanel;
    public JComboBox cb;
    public JPanel mainPanel = new JPanel();

    public RoomDelete(final List<Room> rooms, final RoomModel model, final RoomView parentPanel) {
        this.rooms = rooms;
        this.model = model;
        this.parentPanel = parentPanel;
    }

    public void init() throws CloneNotSupportedException {
        model.addObserver(this);

        setLayout(new BorderLayout());

        Vector myRooms = new Vector();
        for (Room r : rooms) {
            myRooms.addElement(new Item(r.getRoomID(), "Room No." + Integer.toString(r.getRoomID())));
        }

        //(myMovies);
        cb = new JComboBox(myRooms);
        cb.setSelectedIndex(0);

        Item currentRoom = (Item) cb.getSelectedItem();
        id = currentRoom.getId();
        System.out.println(id);

        //List<Movie> myMs = (Movie) movies.c;

        for (Room r : rooms) {
            if (r.getRoomID() == id) {
                Room rCopy = (Room) r.clone();
                mainPanel.add(rCopy);
            }
        }

        cb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                Item cMovie = (Item) jcb.getSelectedItem();
                id = cMovie.getId();
                System.out.println(id);
                for (Room r : rooms) {
                    if (r.getRoomID() == id) {
                        Room rC = null;
                        try {
                            rC = (Room)r.clone();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(MovieDelete.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(r.getName());
                        mainPanel.removeAll();
                        mainPanel.add(rC);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        mainPanel.getParent().revalidate();
                        mainPanel.getParent().repaint();
                    }
                }
            }
        });



        RoomController controller = new RoomController(model, this);
        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(controller);

        mainPanel.setBackground(Color.BLACK);
        setBackground(Color.BLACK);
        add(cb, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(delBtn, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        rooms = (ArrayList<Room>) arg;
        repaint();
    }

    public JComboBox getCb() {
        return cb;
    }

    public void setCb(JComboBox cb) {
        this.cb = cb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public RoomModel getModel() {
        return model;
    }

    public void setModel(RoomModel model) {
        this.model = model;
    }

    public RoomView getParentPanel() {
        return parentPanel;
    }

    public void setParentPanel(RoomView parentPanel) {
        this.parentPanel = parentPanel;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    

}