/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
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
public class MovieDelete extends JFrame implements Observer {

    public List<Movie> movies;
    private int id;
    private MovieModel model;
    public MovieView parentPanel;
    public JComboBox cb;
    public final JPanel mainPanel = new JPanel();

    public MovieDelete(final List<Movie> movies, final MovieModel model, final MovieView parentPanel) {
        this.movies = movies;
        this.model = model;
        this.parentPanel = parentPanel;
    }

    public void init() throws CloneNotSupportedException {
        model.addObserver(this);

        setLayout(new BorderLayout());

        Vector myMovies = new Vector();
        for (Movie m : movies) {
            myMovies.addElement(new Item(m.getMovieID(), m.getName()));
        }

        //(myMovies);
        cb = new JComboBox(myMovies);
        cb.setSelectedIndex(0);

        Item currentMovie = (Item) cb.getSelectedItem();
        id = currentMovie.getId();
        System.out.println(id);

        //List<Movie> myMs = (Movie) movies.c;

        for (Movie m : movies) {
            if (m.getMovieID() == id) {
                Movie mCopy = (Movie) m.clone();
                mainPanel.add(mCopy);
            }
        }

        cb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb = (JComboBox) e.getSource();
                Item cMovie = (Item) jcb.getSelectedItem();
                id = cMovie.getId();
                System.out.println(id);
                for (Movie m : movies) {
                    if (m.getMovieID() == id) {
                        Movie mC = null;
                        try {
                            mC = (Movie)m.clone();
                        } catch (CloneNotSupportedException ex) {
                            Logger.getLogger(MovieDelete.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(m.getName());
                        mainPanel.removeAll();
                        mainPanel.add(mC);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        mainPanel.getParent().revalidate();
                        mainPanel.getParent().repaint();
                    }
                }
            }
        });



        MovieController controller = new MovieController(model, this);
        JButton delBtn = new JButton("Delete");
        delBtn.addActionListener(controller);

        add(cb, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(delBtn, BorderLayout.SOUTH);

        pack();
        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void update(Observable o, Object arg) {
        movies = (ArrayList<Movie>) arg;
        repaint();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MovieModel getModel() {
        return model;
    }

    public void setModel(MovieModel model) {
        this.model = model;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieView getParentPanel() {
        return parentPanel;
    }

    public void setParentPanel(MovieView parentPanel) {
        this.parentPanel = parentPanel;
    }
}
