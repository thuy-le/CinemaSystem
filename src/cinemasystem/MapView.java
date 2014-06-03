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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class MapView extends JPanel {

    private Movie movie = null;
    private String period = null;
    private ShowingTime sTime = null;
    private TimeView tv;
    private MovieView mv;
    private Room room;
    private List<Seat> seats = new ArrayList<Seat>();
    public List<Seat> chosenSeats = new ArrayList<Seat>();
    private RoomView rv;

    public MapView(final Movie movie, final ShowingTime sTime, final TimeView tv, final MovieView mv, final Room room, final String period) {
        this.movie = movie;
        this.sTime = sTime;
        this.tv = tv;
        this.mv = mv;
        seats = new ArrayList<Seat>();
        this.room = room;
        this.period = period;
    }

    public MapView(final Room room, final RoomView rv) {
        this.room = room;
        this.rv = rv;
    }

    public void init() {
        final MapView map = this;
        setVisible(false);
        //TOP PANEL: CONTAINS A REFERENCE OF THE CINEMA SCREEN  AND CHOSEN SEAT(s) STATUS
        JPanel screenPanel = new JPanel();
        screenPanel.setBackground(Color.BLACK);
        MapScreen cs = new MapScreen("images/poligonIMG.png");
        screenPanel.add(cs);
        //displaying the status of chosen seat(s)
        JPanel statusPanel = new JPanel();
        statusPanel.setBackground(new Color(0, 0, 0));
        final JLabel status = new JLabel(room.toString(), 10);
        status.setFont(new Font("Century Gothic", Font.BOLD, 30));
        status.setForeground(MyColor.lightBlue);
        statusPanel.add(status);
        //add components to top 
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(screenPanel, BorderLayout.CENTER);
        topPanel.add(statusPanel, BorderLayout.SOUTH);

        //CENTRAL PANEL: CONTAINS SEATS
        JPanel seatPanel = new JPanel(new BorderLayout());
        JPanel leftLabelPanel = new JPanel();
        JPanel rightLabelPanel = new JPanel();
        //create centre seats

        //SIDE PANELS: CONTAINS SEAT'S LABEL

        JPanel centreSeat = new JPanel();
        centreSeat.setBackground(Color.BLACK);

        int seatNumber = 0;
        seatNumber += room.getNoOfSeats();

        int rows;
        int cols;
        if (seatNumber < 50) {
            cols = 6;
        } else if (seatNumber >= 50 && seatNumber < 100) {
            cols = 8;
        } else if (seatNumber >= 100 && seatNumber < 150) {
            cols = 14;
        } else {
            cols = 20;
        }

        int leftOut = seatNumber % cols;
        System.out.println("DU: " + Integer.toString(seatNumber % cols));
        System.out.println("COLS: " + Integer.toString(cols));
        if (leftOut == 0) {
            rows = seatNumber / cols;
        } else {
            rows = seatNumber / cols + 1;
        }
        System.out.println("COLS: " + Integer.toString(rows));


        leftLabelPanel.setLayout(new GridLayout(rows, 1));
        rightLabelPanel.setLayout(new GridLayout(rows, 1));
        rightLabelPanel.setBackground(Color.BLACK);
        leftLabelPanel.setBackground(Color.BLACK);

        int seatCount = 0;
        char c = 'A';
        for (int i = 0; i < rows; i++) {
            SeatLabel lp = new SeatLabel(Character.toString(c), 18, 10, 25);
            leftLabelPanel.add(lp);
            SeatLabel lp2 = new SeatLabel(Character.toString(c), 18, 10, 25);
            rightLabelPanel.add(lp2);

            JPanel p = new JPanel();
            p.setBackground(Color.BLACK);

            if (cols == 6) {
                p.setLayout(new GridLayout(1, 6));
            } else if (cols == 14) {
                p.setLayout(new GridLayout(1, 14));
            } else {
                p.setLayout(new GridLayout(1, 20));
            }

            centreSeat.setLayout(new GridLayout(rows, cols));

            for (int j = 0; j < cols; j++) {
                int category = 1;
                if (cols == 6) {
                    if ((j > 0 && j < 5) && (i <= rows / 2 + 1 && i >= rows / 2 - 1)) {
                        category = 2;
                    } else if (i == (rows - 1)) {
                        category = 3;
                    } else {
                        category = 1;
                    }
                }

                if (cols == 8) {
                    if ((j > 1 && j < 6) && (i <= rows / 2 + 2 && i >= rows / 2 - 2)) {
                        category = 2;
                    } else if (i == (rows - 1)) {
                        category = 3;
                    } else {
                        category = 1;
                    }
                }

                if (cols == 14) {
                    if ((j > 3 && j < 10) && (i <= rows / 2 + 2 && i >= rows / 2 - 2)) {
                        category = 2;
                    } else if (i == (rows - 1)) {
                        category = 3;
                    } else {
                        category = 1;
                    }

                }

                if (cols == 20) {
                    if ((j > 3 && j < 16) && (i <= rows / 2 + 2 && i >= rows / 2 - 2)) {
                        category = 2;
                    } else if (i == (rows - 1)) {
                        category = 3;
                    } else {
                        category = 1;
                    }
                }

                Seat cp = new Seat(c + (Integer.toString(j + 1)), category);
                p.add(cp);
                centreSeat.add(p);
                seats.add(cp);

                //System.out.println(cp.toString());

                seatCount++;
                if (seatCount == room.getNoOfSeats()) {
                    break;
                }
            }

            c++;
        }

        //read exist Seats

        try {

            File myFile = new File("files/myFile2.txt");
            boolean isExist = myFile.exists();
            if (!isExist) {
                System.out.println("NO EXIST");
            } else {
                System.out.println("EXISR");
                FileReader fr = new FileReader(myFile);
                BufferedReader br = new BufferedReader(fr);
                String line = null;
                while ((line = br.readLine()) != null) {
                    String info[] = line.split("/");
                    System.out.println("INFO 0: " + info[0] + " ROOM: " + room.getRoomID());
                    if (info[0].equals(Integer.toString(room.getRoomID()))) {
                        System.out.println("OKIE");
                        System.out.println(room.getRoomID());
                        for (Seat mSeat : seats) {
                            for (int i = 0; i < info.length - 1; i++) {
                                if (info[i + 1].equals(mSeat.getSeatNo())) {
                                    mSeat.setCategory(5);
                                }
                            }
                        }

                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }


        final List<Seat> myBackupList = new ArrayList<Seat>();
        for (Seat seat : seats) {
            try {
                myBackupList.add((Seat) seat.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (final Seat seat : seats) {

            seat.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    if (seat.getSeatCategory().getCategoryID() == 4) {
                        for (Seat s : myBackupList) {
                            if (s.getSeatNo().equals(seat.getSeatNo())) {
                                seat.setCategory(s.getSeatCategory().getCategoryID());
                                chosenSeats.remove(seat);
                                System.out.println("Unchosen: " + seat.toString());
                            }
                        }
                    } else if (seat.getSeatCategory().getCategoryID() == 5) {
                    } else {
                        chosenSeats.add(seat);
                        seat.setCategory(4);
                        System.out.println("Chosen: " + seat.toString());
                    }

                    revalidate();
                    repaint();
                }
            });

        }

        seatPanel.add(centreSeat, BorderLayout.CENTER);
        //       seatPanel.add(coupleSeat, BorderLayout.SOUTH);



        //ADD COMPONENTS TO MAINPANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(seatPanel, BorderLayout.CENTER);
        mainPanel.add(leftLabelPanel, BorderLayout.WEST);
        mainPanel.add(rightLabelPanel, BorderLayout.EAST);

        //CREATE RIGHT PANEL
        JPanel rightPanel = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            rightPanel.setBackground(Color.BLACK);
            JPanel panel = new JPanel(new FlowLayout());
            panel.setBackground(Color.BLACK);
            Seat seat = new Seat("0", i + 1);
            JLabel label = new JLabel(seat.seatCategory.getCategoryName());
            label.setForeground(MyColor.lightGreen);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 17));
            panel.add(seat);
            panel.add(label);
            rightPanel.add(panel);
        }

        JButton nextBtn;
        if (movie != null) {
            nextBtn = new JButton("Book The Seat(s) for " + movie.getName() + " in " + sTime);
            nextBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String mySeats = "";
                    if (!chosenSeats.isEmpty()) {

                        for (Seat seat : chosenSeats) {
                            System.out.println("Final Choices: " + seat.toString());
                            for (Seat s : seats) {
                                if (s.getSeatNo().equals(seat.getSeatNo())) {
                                    s.setCategory(5);
                                    mySeats += s.getSeatNo() + "/";
                                }
                            }
                        }
                        setVisible(false);

                        TicketInfo ti = new TicketInfo(chosenSeats, movie, mv, period, sTime);
                        ti.init();
                        getParent().add(ti);
                        SwingUtilities.windowForComponent(getParent()).pack();

                        try {
                            String s = room.getRoomID() + "/" + mySeats;
                            File myFile = new File("files/myFile2.txt");
                            boolean isExist = myFile.exists();
                            if (!isExist) {
                                FileWriter out = new FileWriter(myFile);
                                out.write(s + "\n");
                                out.close();
                            } else {
                                FileReader fr = new FileReader(myFile);
                                BufferedReader br = new BufferedReader(fr);
                                FileWriter fw = new FileWriter(myFile, true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                bw.append(s);
                                bw.newLine();
                                br.close();
                                bw.close();
                            }

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                        }
                        tv.setMovie(new Movie());

                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Please choose at least 1 seat");
                    }


                }
            });
        } else {
            nextBtn = new JButton("Back to Rooms");
            nextBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    rv.setVisible(true);
                    rv.init();
                    getParent().add(rv);
                    getParent().remove(map);
                    SwingUtilities.windowForComponent(rv.getParent()).pack();
                }
            });
        }

        //JButton nextBtn = new JButton("Book The Seat(s)");
        JButton backBtn = new JButton("Go Back");
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);
        if(movie!=null)
        bottomPanel.add(backBtn);
        bottomPanel.add(nextBtn);

        //ADD COMPONENTS TO THE MAP
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);




        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                tv.setVisible(true);
                getParent().add(tv);
                SwingUtilities.windowForComponent(getParent()).pack();
            }
        });

        setVisible(true);
//        pack();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
