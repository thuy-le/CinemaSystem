/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author PC
 */
public class MovieController implements ActionListener {

    private MovieAdd addMovieView;
    private MovieDelete delMovieView;
    private MovieModel model;

    public MovieController(final MovieModel model, final MovieAdd addMovieView) {
        this.model = model;
        this.addMovieView = addMovieView;
    }

    public MovieController(final MovieModel model, final MovieDelete delMovieView) {
        this.model = model;
        this.delMovieView = delMovieView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {

            JButton b = (JButton) e.getSource();
            if (b.getText().equalsIgnoreCase("add")) {
                Boolean valid = false;
                Boolean isEmpty = true;
                int duration = 0;

                if (addMovieView.txt1.getText().trim().equals("") || addMovieView.txt2.getText().trim().equals("") || addMovieView.txt5.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(addMovieView.getParent(), "Movie name, duration and picture should not be empty");
                } else {
                    isEmpty = false;
                    try {
                        duration = Integer.parseInt(addMovieView.txt2.getText());
                        valid = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addMovieView.getParent(), "Please input a number for duration");
                    }
                }




                if (valid && !isEmpty) {

                    int id = 0;
                    for (Movie movie : addMovieView.movies) {
                        if (movie.getMovieID() > id) {
                            id = movie.getMovieID();
                        }
                    }
                    id++;

                    List<ShowingTime> sTimes = new ArrayList<ShowingTime>();
                    ShowingTime showingTime = new ShowingTime(10, 20);
                    sTimes.add(showingTime);
                    ShowingTime showingTime1 = new ShowingTime(11, 30);
                    sTimes.add(showingTime1);
                    ShowingTime showingTime2 = new ShowingTime(1, 10);
                    sTimes.add(showingTime2);

                    Calendar cal = Calendar.getInstance();
                    List<String> period = new ArrayList<String>();
                    for (int j = 0; j < 7; j++) {
                        Format f = new SimpleDateFormat("EEEE - dd MMMM yyyy");
                        cal.add(Calendar.DATE, +1);
                        Date date = cal.getTime();
                        period.add(f.format(date));
                    }

                    Room room = null;
                    int rID = ((Item) addMovieView.roomList.getSelectedItem()).getId();
                    for (Room myRoom : addMovieView.rooms) {
                        if (myRoom.getRoomID() == rID) {
                            room = myRoom;
                        }
                    }

                    MovieCategory mCat = null;
                    int mID = ((Item) addMovieView.catList.getSelectedItem()).getId();
                    for (MovieCategory movieCat : addMovieView.movieCats) {
                        if (movieCat.getCategoryID() == mID) {
                            mCat = movieCat;
                        }
                    }

                    final Movie movie = new Movie(id, addMovieView.txt1.getText(), duration, sTimes, period, addMovieView.txt5.getText(), mCat, room);
                    addMovieView.movies.add(movie);
                    model.addMovie(addMovieView.movies);


                    System.out.println("Bat Dau");
                    for (Movie m : addMovieView.movies) {
                        System.out.println(m.getName());
                        System.out.println(m.getMovieID());
                    }

                    addMovieView.setVisible(false);

                    int cols;
                    if (addMovieView.movies.size() % 2 == 0) {
                        cols = addMovieView.movies.size() / 2;
                    } else {
                        cols = addMovieView.movies.size() / 2 + 1;
                    }

                    try {
                        movie.addMouseListener(new MouseAdapter() {

                            final Movie mMovie = (Movie) movie.clone();

                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (addMovieView.parentPanel.role != 0) {
                                    addMovieView.parentPanel.setVisible(false);
                                    TimeView tv = new TimeView(mMovie, addMovieView.parentPanel, mMovie.getRoom());
                                    tv.init();
                                    System.out.println("MOVIE: " + movie.getName());
                                    addMovieView.parentPanel.getParent().add(tv);
                                    SwingUtilities.windowForComponent(tv.getParent()).pack();
                                } else {
                                    JOptionPane.showMessageDialog(addMovieView.parentPanel.getParent(), "Please login to book the movie");
                                }
                            }
                        });
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(MovieController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    addMovieView.parentPanel.showAllMovie.setLayout(new GridLayout(2, cols));
                    addMovieView.parentPanel.showAllMovie.add(movie);
                    addMovieView.parentPanel.revalidate();
                    addMovieView.parentPanel.repaint();
                    SwingUtilities.windowForComponent(addMovieView.parentPanel.getParent()).pack();
                }

            } else if (b.getText().equalsIgnoreCase("delete")) {
                System.out.println("PASSED: " + delMovieView.getId());

                Movie myM = null;
                for (Movie movie : delMovieView.movies) {
                    if (movie.getMovieID() == delMovieView.getId()) {
                        myM = movie;
                    }
                }

                delMovieView.cb.revalidate();
                delMovieView.cb.repaint();

                delMovieView.mainPanel.revalidate();
                delMovieView.mainPanel.repaint();
                delMovieView.revalidate();
                delMovieView.repaint();
                delMovieView.setVisible(false);


                delMovieView.parentPanel.movies.remove(myM);
                int cols;
                if (delMovieView.parentPanel.movies.size() % 2 == 0) {
                    cols = delMovieView.parentPanel.movies.size() / 2;
                } else {
                    cols = delMovieView.parentPanel.movies.size() / 2 + 1;
                }


                delMovieView.parentPanel.showAllMovie.remove(myM);
                delMovieView.parentPanel.showAllMovie.setLayout(new GridLayout(2, cols));
                delMovieView.parentPanel.revalidate();
                delMovieView.parentPanel.repaint();
                SwingUtilities.windowForComponent(delMovieView.parentPanel.getParent()).pack();
            }

        }
    }
}
