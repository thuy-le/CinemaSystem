/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class TimeView extends JPanel {

    private Movie movie = null;
    private MovieView mv;
    private Room room;

    public TimeView(final Movie movie, final MovieView mv, final Room room) {
        this.movie = movie;
        this.mv = mv;
        this.room = room;
    }

    public void init() {

        setVisible(true);
        setBackground(Color.WHITE);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.WHITE);
        JLabel status = new JLabel("Showing time for " + movie.getName());
        status.setFont(new Font("Century Gothic", Font.BOLD, 30));
        status.setForeground(MyColor.middleBlue);
        statusPanel.add(status);
        //statusPanel.setPreferredSize(new Dimension(500, 70));

        JPanel leftPanel = new JPanel(new GridLayout(movie.getPeriod().size(), 1));
        JPanel rightPanel = new JPanel(new GridLayout(movie.getShowingTime().size() / 4 + 1, 4));
        rightPanel.setBackground(Color.WHITE);

        final ButtonGroup bg = new ButtonGroup();

        System.out.println(movie.getShowingTime().size());

        final TimeView tv = this;

        int index = 0;
        for (String period : movie.getPeriod()) {
            JRadioButton rBtn = new JRadioButton(period);
            JLabel label = new JLabel(period);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.setBackground(Color.WHITE);
            if (index == 0) {
                rBtn.setSelected(true);
            }
            index++;
            bg.add(rBtn);
            p.add(rBtn);
            leftPanel.add(p);
        }

        for (final ShowingTime showingTime : movie.getShowingTime()) {
            rightPanel.add(showingTime);
            showingTime.setCursor(new Cursor(Cursor.HAND_CURSOR));

            showingTime.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    Movie myMovie;
                    if (movie.getName() != null) {
                        JRadioButton r = getRadioButton(bg); 
                        myMovie = movie;
                        final MapView seatMapView = new MapView(myMovie, showingTime, tv, mv, room, r.getText());
                        System.out.println("Here: " + movie.getName());
                        seatMapView.init();
                        setVisible(false);
                        mv.getParent().remove(seatMapView);
                        mv.getParent().add(seatMapView);
                        SwingUtilities.windowForComponent(mv.getParent()).pack();
                    }
                }
            });

        }

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        add(statusPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(p, BorderLayout.CENTER);

        //back button settings
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        JButton backBtn = new JButton("Back");
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        backBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                movie = new Movie();
                setVisible(false);
                mv.setVisible(true);
                getParent().add(mv);
                SwingUtilities.windowForComponent(mv.getParent()).pack();
            }
        });

//        pack();
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieView getMv() {
        return mv;
    }

    public void setMv(MovieView mv) {
        this.mv = mv;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public JRadioButton getRadioButton(ButtonGroup g) {
        for (Enumeration e = g.getElements(); e.hasMoreElements();) {
            JRadioButton jrb = (JRadioButton) e.nextElement();
            if (jrb.getModel() == g.getSelection()) {
                return jrb;
            }
        }
        return null;
    }
}
