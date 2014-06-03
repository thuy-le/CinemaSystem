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
public class Login extends JPanel {

    public JTextField username;
    public JTextField password;
    public MovieView mView;

    public Login(MovieView mView) {
        this.mView = mView;
    }

    public MovieView getmView() {
        return mView;
    }

    public void setmView(MovieView mView) {
        this.mView = mView;
    }

    public JTextField getPassword() {
        return password;
    }

    public void setPassword(JTextField password) {
        this.password = password;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }
    
    

    public void init() {
        setVisible(true);
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new GridLayout(3, 1)) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 120);
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

        JPanel l5 = new JPanel(new GridLayout(1, 2));
        JButton addBtn = new JButton("Login");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isUser = false;
                String name = username.getText();
                String pass = password.getText();

                if (name.equals("admin") && pass.equals("admin")) {

                    JOptionPane.showMessageDialog(getParent(), "Hi, " + name + "\n Thank you for logged in");
                    setVisible(false);
                    mView.role = 2;
                    mView.username = name;
                    mView.setVisible(true);
                    getParent().add(mView);
                    SwingUtilities.windowForComponent(getParent()).pack();

                } else {
                    try {

                        File myFile = new File("files/myFile.txt");
                        boolean isExist = myFile.exists();
                        if (!isExist) {
                            JOptionPane.showMessageDialog(getParent(), "No Username In The System!");
                        } else {
                            FileReader fr = new FileReader(myFile);
                            BufferedReader br = new BufferedReader(fr);

                            String line = null;
                            while ((line = br.readLine()) != null) {
                                String info[] = line.split("/");
                                if (info[0].equals(name) && info[1].equals(pass)) {
                                    isUser = true;
                                    break;

                                }
                            }
                            if (isUser) {
                                JOptionPane.showMessageDialog(getParent(), "Hi, " + name + "\n Thank you for logged in");
                                setVisible(false);
                                mView.role = 1;
                                mView.username = name;
                                mView.setVisible(true);
                                getParent().add(mView);
                                SwingUtilities.windowForComponent(getParent()).pack();
                            } else {
                                JOptionPane.showMessageDialog(getParent(), "Wrong username or password");
                            }

                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                    }
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
