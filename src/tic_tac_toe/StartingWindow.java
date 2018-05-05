/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

/**
 *
 * @author Johnson
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartingWindow extends JFrame {
    private JPanel panel;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel labelMain, loginLabel, registerLabel ;
    
    
    //constructor for LandingPage
    public StartingWindow(){
        GridBagLayout grid = new GridBagLayout();
        
        GridBagConstraints c = new GridBagConstraints();
        this.setMinimumSize(new Dimension(640,480));
        
        panel = new JPanel(grid);
        this.getContentPane().add(panel);
        this.setTitle("Welcome!");

        labelMain = new JLabel("Welcome");
        loginLabel = new JLabel("Login");
        registerLabel = new JLabel("Register");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 3 ;
        panel.add(labelMain,c);
        
        
        c.gridx = 1 ;
        c.gridy = 1 ;
        c.gridwidth = 1 ;
        panel.add(registerLabel,c);

        
        c.gridx = 3 ;
        c.gridy = 1 ;
        c.gridwidth = 1 ;
        panel.add(registerButton,c);

        
        c.gridx = 1 ;
        c.gridy = 3 ;
        c.gridwidth = 1 ;
        panel.add(loginLabel,c);

        
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(loginButton,c);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                registerActionPerformed(evt);
            }
        });
    }
    public void loginActionPerformed (ActionEvent e){
        dispose();
        LoginWindow login = new LoginWindow();
    	login.setVisible(true);
    	login.setTitle("Login Window");
    }
    
    public void registerActionPerformed (ActionEvent e){
        dispose();
        RegistrationWindow register = new RegistrationWindow();
    	register.setVisible(true);
    	register.setTitle("Registration Window");	
    }

    public static void main (String[] args) throws Exception {
        StartingWindow frame = new StartingWindow();
        frame.setTitle("Welcome! Let's Play Tic Tac Toe");
    }
}
