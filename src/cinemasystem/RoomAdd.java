/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;



/**
 *
 * @author PC
 */
public class RoomAdd extends JFrame implements Observer{
    
    public List<Room> rooms;
    public RoomModel model;
    public RoomView parentPanel;
    public JTextField txt1;
    public JTextField txt2;
    
    public RoomAdd(List<Room> r, final RoomModel model, final RoomView parentPanel){
        this.rooms = r;
        this.model = model;
        this.parentPanel = parentPanel;
    }
    
    public void init(){
        model.addObserver(this);
        RoomController controller = new RoomController(model, this);
        
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(2,1)){
            @Override
             public Dimension getPreferredSize(){
                return new Dimension(350, 100);
    }
        };
        
        JPanel l1 = new JPanel(new GridLayout(1,2));
        JLabel label1 = new JLabel("Number of Seats: ");
        txt1 = new JTextField(20);
        l1.add(label1);
        l1.add(txt1);
        leftPanel.add(l1);
               
        JPanel l7 = new JPanel(new GridLayout(1,2));
        l7.add(new JLabel());
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(controller);
        
        l7.add(addBtn);
       
        leftPanel.add(l7);
        
        add(leftPanel, BorderLayout.CENTER);
        
        setSize(leftPanel.getPreferredSize());
        setTitle("Add New Room");
    }

    @Override
    public void update(Observable o, Object arg) {
        rooms = (ArrayList<Room>) arg;
        repaint();
    }
    
}
