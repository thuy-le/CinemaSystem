/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class TicketInfo extends JPanel {

    private List<Seat> chosenSeats;
    private Movie movie;
    private MovieView mView;
    private String period;
    private ShowingTime sTime;

    public TicketInfo(final List<Seat> chosenSeats, final Movie movie, final MovieView mView, final String period, final ShowingTime sTime) {
        this.chosenSeats = chosenSeats;
        this.movie = movie;
        this.mView = mView;
        this.period = period;
        this.sTime = sTime;
    }

    public void init() {
        System.out.println("CALLED TICKET INFO");
        String address = null;
        String phoneNumber = null;
        Boolean isUser = false;
        
        

        try {

            File myFile = new File("files/myFile.txt");
            boolean isExist = myFile.exists();
            if (!isExist) {
                JOptionPane.showMessageDialog(getParent(), "No Username In The System!");
            } else {
                FileReader fr = new FileReader(myFile);
                BufferedReader br = new BufferedReader(fr);

                String line;
                while ((line = br.readLine()) != null) {
                    String info[] = line.split("/");
                    if (info[0].equals(mView.username)) {
                        address = info[2];
                        phoneNumber = info[3];
                        isUser = true;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        String uName = null;
        String uAddr = null;
        String uPhone = null;
        
        if(mView.role == 2){
            uName = "Admin";
            uAddr = "HCMC";
            uPhone = "0905310427";
        }
        
        if(isUser){
            uName = mView.username;
            uAddr = address;
            uPhone = phoneNumber;
        }
        
        JLabel nameLabel = new JLabel("Hi, " + uName);
        JLabel addressLabel = new JLabel("Address: " + uAddr);
        JLabel phoneNumberLabel = new JLabel("Phone Number: " + uPhone);
        
        String seats = "";
        
            for (Seat seat : chosenSeats) {
                seats += "No." + seat.getSeatNo() + " ";
            }
        
        JLabel ticketInfo = new JLabel("You've booked: " + seats);
        JLabel dateInfo = new JLabel("Date: " + period);
        JLabel movieInfo = new JLabel("Movie: " + movie.getName());
        
        JButton bt = new JButton("Back to Movie");
        bt.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    mView.setVisible(true);
                    getParent().add(mView);
                    SwingUtilities.windowForComponent(getParent()).pack();
                }
            });
        
        JPanel panel = new JPanel(new GridLayout(4,2)){    
            public Dimension getPreferredSize(){
                return new Dimension(430,200);
            }
        };
        panel.add(nameLabel);
        panel.add(addressLabel);
        panel.add(phoneNumberLabel);
        panel.add(ticketInfo);
        panel.add(dateInfo);
        panel.add(movieInfo);
        panel.add(sTime);
        
        JPanel bottom = new JPanel();
        bottom.add(bt);
        
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        
    }
}
