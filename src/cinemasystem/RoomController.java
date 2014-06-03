/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class RoomController implements ActionListener {

    private RoomAdd addRoomView;
    private RoomDelete delRoomView;
    private RoomModel model;

    public RoomController(final RoomModel model, final RoomAdd addRoomView) {
        this.model = model;
        this.addRoomView = addRoomView;
    }

    public RoomController(final RoomModel model, final RoomDelete delRoomView) {
        this.model = model;
        this.delRoomView = delRoomView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            
            JButton b = (JButton) e.getSource();
            if (b.getText().equalsIgnoreCase("add")) {
                int id = 0;
                for (Room r : addRoomView.rooms) {
                    if (r.getRoomID() > id) {
                        id = r.getRoomID();
                    }
                }
                id++;

                Room r = new Room(id, Integer.parseInt(addRoomView.txt1.getText()));
                addRoomView.rooms.add(r);
                model.addRoom(addRoomView.rooms);

                addRoomView.setVisible(false);

                addRoomView.parentPanel.rooms = addRoomView.rooms;
                
                for (Room room : addRoomView.parentPanel.rooms) {
                    System.out.println(room.getRoomID());
                }
                
                addRoomView.parentPanel.init();
                addRoomView.parentPanel.revalidate();
                addRoomView.parentPanel.repaint();
                SwingUtilities.windowForComponent(addRoomView.parentPanel).pack();
//                addRoomView.parentPanel.pack();
                
            }
            
            
            else if (b.getText().equalsIgnoreCase("delete")) {
                System.out.println("PASSED: " + delRoomView.getId());

                Room myM = null;
                for (Room r : delRoomView.rooms) {
                    if (r.getRoomID() == delRoomView.getId()) {
                        myM = r;
                    }
                }

                delRoomView.cb.revalidate();
                delRoomView.cb.repaint();

                delRoomView.mainPanel.revalidate();
                delRoomView.mainPanel.repaint();
                delRoomView.revalidate();
                delRoomView.repaint();
                delRoomView.setVisible(false);


                delRoomView.parentPanel.rooms.remove(myM);
                int cols;
                if (delRoomView.parentPanel.rooms.size() % 3 == 0) {
                    cols = delRoomView.parentPanel.rooms.size() / 3;
                } else {
                    cols = delRoomView.parentPanel.rooms.size() / 3 + 1;
                }
                
                List<Movie> delM = new ArrayList<Movie>();
                for (Movie m : delRoomView.parentPanel.mView.movies) {
                    if(m.getRoom().getRoomID() == myM.getRoomID()){
                        delM.add(m);
                    }
                }
                
                for (Movie movie : delM) {
                    delRoomView.parentPanel.mView.movies.remove(movie);
                    delRoomView.parentPanel.mView.showAllMovie.remove(movie);
                }
                
                
                delRoomView.parentPanel.showAllRooms.remove(myM);
                delRoomView.parentPanel.showAllRooms.setLayout(new GridLayout(3, cols));
                delRoomView.parentPanel.revalidate();
                delRoomView.parentPanel.repaint();
                SwingUtilities.windowForComponent(delRoomView.parentPanel).pack();
            }
            
        }
    }
}
