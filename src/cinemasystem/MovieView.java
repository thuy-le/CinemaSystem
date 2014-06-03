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
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class MovieView extends JPanel implements Observer {

    private MovieModel movieModel;
    public List<Movie> movies = new ArrayList<Movie>();
    private JButton loginBtn = new JButton("Login");
    private JButton registerBtn = new JButton("Register");
    private JButton addBtn = new JButton("Add New Movie");
    private JButton delBtn = new JButton("Delete Movie");
    private JButton roomBtn = new JButton("View Rooms");
    private RoomModel roomModel;
    private List<MovieCategory> movieCats;
    public String username = null;
    public int role = 0;
    public JPanel showAllMovie = new JPanel();
    public List<Room> rooms = new ArrayList<Room>();
    
    public MovieView(List<Movie> movies, MovieModel mModel, List<Room> rooms, RoomModel roomModel, List<MovieCategory> movieCats) {
        this.movies = movies;
        this.movieModel = mModel;
        this.rooms = rooms;
        this.roomModel = roomModel;
        this.movieCats = movieCats;
    }

    public void init() {
        System.out.println("repainting");

        movieModel.addObserver(this);
        //Title
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(Color.WHITE);
        JLabel status = new JLabel("Please Choose A Movie", 10);
        status.setFont(new Font("Century Gothic", Font.BOLD, 30));
        status.setForeground(MyColor.darkBlue2);
        statusPanel.add(status);
        //create a panel for dispaying movies

        showAllMovie.setLayout(new GridLayout(2, movies.size() / 2));
        showAllMovie.setBackground(Color.WHITE);
        //add movie to movie list

        final MovieView mv = this;

        //add movies on the list to movie panel
        for (final Movie movie : movies) {
            showAllMovie.add(movie);

            movie.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (role != 0) {
                        setVisible(false);
                        TimeView tv = new TimeView(movie, mv, movie.getRoom());
                        tv.init();
                        System.out.println("MOVIE: " + movie.getName());
                        getParent().add(tv);
                        SwingUtilities.windowForComponent(getParent()).pack();
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Please login to book the movie");
                    }
                }
               
            });
            //System.out.println(movie.getName());
        }

        setLayout(new BorderLayout());
        add(statusPanel, BorderLayout.NORTH);
        add(showAllMovie, BorderLayout.CENTER);

        add(statusPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(loginBtn);
        bottomPanel.add(registerBtn);
        bottomPanel.add(addBtn);
        bottomPanel.add(delBtn);
        bottomPanel.add(roomBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);


        
        loginBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login log = new Login(mv);
                log.init();
                getParent().add(log);
                SwingUtilities.windowForComponent(getParent()).pack();
            }
        });
        
        registerBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Register reg = new Register(mv);
                reg.init();
                getParent().add(reg);
                SwingUtilities.windowForComponent(getParent()).pack();
            }
        });

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (role == 2) {
                    MovieAdd mav = new MovieAdd(movies, movieModel, mv, rooms, movieCats);
                    mav.init();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "You do not have permission to do this action");
                }
            }
        });

        roomBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (role == 2) {
                    setVisible(false);
                    RoomView rv = new RoomView(roomModel, rooms, mv);
                    rv.init();
                    getParent().add(rv);
                    SwingUtilities.windowForComponent(rv.getParent()).pack();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "You do not have permission to do this action");
                }
            }
        });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (role == 2) {
                    MovieDelete delMovie = new MovieDelete(movies, movieModel, mv);
                    try {
                        delMovie.init();
                    } catch (CloneNotSupportedException ex) {
                        Logger.getLogger(MovieView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(getParent(), "You do not have permission to do this action");
                }
            }
        });
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(JButton addBtn) {
        this.addBtn = addBtn;
    }

    public JButton getDelBtn() {
        return delBtn;
    }

    public void setDelBtn(JButton delBtn) {
        this.delBtn = delBtn;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public void setLoginBtn(JButton loginBtn) {
        this.loginBtn = loginBtn;
    }

    public MovieModel getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public JButton getRegisterBtn() {
        return registerBtn;
    }

    public void setRegisterBtn(JButton registerBtn) {
        this.registerBtn = registerBtn;
    }

    public JPanel getShowAllMovie() {
        return showAllMovie;
    }

    public void setShowAllMovie(JPanel showAllMovie) {
        this.showAllMovie = showAllMovie;
    }

    @Override
    public void update(Observable o, Object arg) {
        movies = (ArrayList<Movie>) arg;
        repaint();
    }
}
