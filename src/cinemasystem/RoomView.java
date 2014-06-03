/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class RoomView extends JPanel {

    public JPanel showAllRooms = new JPanel();
    public JButton addBtn = new JButton("Add New Room");
    public JButton delBtn = new JButton("Delete Room");
    public JButton movieBtn = new JButton("View Movies");
    public List<Room> rooms;
    public RoomModel roomModel;
    public MovieView mView;

    public RoomView(final RoomModel model, List<Room> rooms, final MovieView mView) {
        this.roomModel = model;
        this.rooms = rooms;
        this.mView = mView;
    }

    public void init() {
        System.out.println("repainting");
        //Title
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.BLACK);
        JLabel status = new JLabel("Cinema Rooms", 10);
        status.setFont(new Font("Century Gothic", Font.BOLD, 30));
        status.setForeground(MyColor.lovelyPink);
        statusPanel.add(status);

        final RoomView rv = this;

        //create a panel for dispaying rooms
        int cols;
        if (rooms.size() % 3 == 0) {
            cols = rooms.size() / 3;
        } else {
            cols = rooms.size() / 3 + 1;
        }


        showAllRooms.setLayout(new GridLayout(3, cols));
        showAllRooms.setBackground(Color.BLACK);

        for (final Room room : rooms) {

            room.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (room != null) {
                        System.out.println("Clicked on: " + room.getRoomID());
                        MapView map = new MapView(room, rv);
                        map.init();
                        setVisible(false);
                        mView.getParent().add(map);
                        SwingUtilities.windowForComponent(map.getParent()).pack();
                        System.out.println("ROOM: " + room.getRoomID());
                    }
                }
            });

            showAllRooms.add(room);
        }


        setLayout(new BorderLayout());
        add(statusPanel, BorderLayout.NORTH);
        add(showAllRooms, BorderLayout.CENTER);

        add(statusPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(addBtn);
        bottomPanel.add(delBtn);
        bottomPanel.add(movieBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
//        pack();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);


        movieBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mView.rooms = rooms;
                mView.setVisible(true);
                getParent().add(mView);
                SwingUtilities.windowForComponent(getParent()).pack();
            }
        });

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RoomAdd rav = new RoomAdd(rooms, roomModel, rv);
                rav.init();
            }
        });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RoomDelete delRoom = new RoomDelete(rooms, roomModel, rv);
                try {
                    delRoom.init();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(MovieView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
