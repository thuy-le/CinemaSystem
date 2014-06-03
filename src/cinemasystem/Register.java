/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author PC
 */
public class Register extends JPanel {

    public JTextField username;
    public JTextField password;
    public JTextField address;
    public JTextField phoneNumber;
    public MovieView mView;

    public Register(MovieView mView) {
        this.mView = mView;
    }

    public void init() {
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(5, 1)) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 180);
            }
        };

        JPanel l1 = new JPanel(new GridLayout(1, 2));
        JLabel label1 = new JLabel("Username: ");
        username = new JTextField(20);
        l1.add(label1);
        l1.add(username);
        leftPanel.add(l1);

        JPanel l2 = new JPanel(new GridLayout(1, 2));
        JLabel label2 = new JLabel("Password: ");
        password = new JPasswordField(20);
        l2.add(label2);
        l2.add(password);
        leftPanel.add(l2);

        JPanel l3 = new JPanel(new GridLayout(1, 2));
        JLabel label3 = new JLabel("Address: ");
        address = new JTextField(20);
        l3.add(label3);
        l3.add(address);
        leftPanel.add(l3);

        JPanel l4 = new JPanel(new GridLayout(1, 2));
        JLabel label4 = new JLabel("Phone Number: ");
        phoneNumber = new JTextField(20);
        l4.add(label4);
        l4.add(phoneNumber);
        leftPanel.add(l4);

        JPanel l5 = new JPanel(new GridLayout(1, 2));
        JButton addBtn = new JButton("Register");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean userExist = false;
                    String name = username.getText();
                    String pass = password.getText();
                    String addr = address.getText();
                    String fone = phoneNumber.getText();
                    String s = name + "/" + pass + "/" + addr + "/" + fone;
                    File myFile = new File("files/myFile.txt");
                    boolean isExist = myFile.exists();
                    if (!isExist) {
                        FileWriter out = new FileWriter(myFile);
                        out.write(s + "\n");
                        out.close();
                        setVisible(false);
                        mView.role = 1;
                        mView.username = name;
                        mView.setVisible(true);
                        getParent().add(mView);
                        SwingUtilities.windowForComponent(getParent()).pack();
                    } else {
                        FileReader fr = new FileReader(myFile);
                        BufferedReader br = new BufferedReader(fr);
                        FileWriter fw = new FileWriter(myFile, true);
                        BufferedWriter bw = new BufferedWriter(fw);

                        String line = null;
                        while ((line = br.readLine()) != null) {
                            String info[] = line.split("/");
                            if (info[0].equals(name)) {
                                userExist = true;
                                System.out.println("Username: " + name + "Exists");
                                JOptionPane.showMessageDialog(getParent(), "This user cannot be added.\n " + name + " has already been registered. \n Please choose another username. Thank you!");
                                break;

                            }
                        }
                        if (!userExist) {
                            bw.append(s);
                            bw.newLine();
                            JOptionPane.showMessageDialog(getParent(), "Hi, " + name + "\n Thank you for registered!");
                            setVisible(false);
                            mView.role = 1;
                            mView.username = name;
                            mView.setVisible(true);
                            getParent().add(mView);
                            SwingUtilities.windowForComponent(getParent()).pack();
                        }
                        br.close();
                        bw.close();
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mView.setVisible(true);
                getParent().add(mView);
                SwingUtilities.windowForComponent(getParent()).pack();
            }
        });
        //addBtn.addActionListener();
        l5.add(cancelBtn);
        l5.add(addBtn);
        leftPanel.add(l5);

        add(leftPanel, BorderLayout.CENTER);

        setSize(leftPanel.getPreferredSize());
    }
}
