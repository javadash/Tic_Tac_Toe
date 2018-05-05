/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ttt.james.server.TTTWebService;
import ttt.james.server.TTTWebService_Service;

/**
 *
 * @author Johnson
 */
public class GameWindow extends javax.swing.JFrame {
    
    TTTWebService proxy;
    private int gameID;
    private int playerID;
    private String symbol;
    private String userName;
    private GridLayout gamePanel;
    private JButton[][] squares = new JButton[3][3];
    
    /**
     * Creates new form GameWindow
     */
    public GameWindow(int gid, int pid, String symbol, String playerName) {
        proxy = new TTTWebService_Service().getTTTWebServicePort();
        initComponents();
        gamePanel = new GridLayout(3, 3);
        gameGrid.setLayout(gamePanel);
        this.gameID = gid;
        this.playerID = pid;
        this.symbol = symbol;
        this.userName = playerName;
        this.setTitle("Tic Tac Toe");
        playerNameLabel.setText("Welcome: " + userName + " this game number " + gameID );
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new JButton();
                squares[i][j].setSize(150, 150);
                squares[i][j].putClientProperty("i", i);
                squares[i][j].putClientProperty("j", j);
                squares[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton)e.getSource();
                        buttonAction((int) button.getClientProperty("i"), (int) button.getClientProperty("j"));
                    }
                });    
                squares[i][j].setSize(200, 200);
                gameGrid.add(squares[i][j]);
            }            
        }
        if(symbol.equals("X")) {
            // if player is first player allow him to start
            infoLabel.setText(userName + ": It's your turn.");
            unblock();
        } else {
            // if not block the game until opponent took a square
            infoLabel.setText(userName + ": It's the opponents turn.");
            block();
        }
        TaskSchedular updThread = new TaskSchedular(this);
        updThread.start();
        setVisible(true);
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                String closeResult = proxy.deleteGame(gameID, playerID);
                if (closeResult.equals("ERROR-DB"))
                    JOptionPane.showMessageDialog(null, "Can't connect to Database!");
                else {
                    dispose();
                    GameOptions gameOptions = new GameOptions();
                    gameOptions.showOpenGames(playerID, userName);
                    gameOptions.setVisible(true);
                    gameOptions.setVisible(true);
                }
            }
    });
        
    }
    private void buttonAction(int i, int j) {
        String answer = proxy.takeSquare(i, j, gameID, playerID);
        if (answer.contains("ERROR")) {
            JOptionPane.showMessageDialog(null,"Sorry that square is taken!");
            dispose();
        }
        else if (answer.equals("ERROR-DB")) 
        {
            JOptionPane.showMessageDialog(null, "Can't connect to Database!");
            dispose();
        } else if(answer.equals("1")) {
            // if successfull mark the field
            takeSquare(symbol, i, j);
            infoLabel.setText(userName + ": It's the opponents turn.");
            block();
        }
    }

    public void takeSquare(String symbol, int x, int y) {
        // take square
        squares[x][y].setText(symbol);
    }
    public void block() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j].setEnabled(false);
            }
        }
    }
    public void unblock() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(squares[i][j].getText().equals(""))
                    squares[i][j].setEnabled(true);
            }
        }
    }
    public int getGameId(){
        return this.gameID;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public int getplayerId(){
        return this.playerID;
    }
     
    public String getUserName(){
        return this.userName;
    }
    public void setInfoText(String t) {
        infoLabel.setText(t);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameGrid = new javax.swing.JPanel();
        endButton = new javax.swing.JButton();
        playerNameLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        gameGrid.setPreferredSize(new java.awt.Dimension(500, 333));

        javax.swing.GroupLayout gameGridLayout = new javax.swing.GroupLayout(gameGrid);
        gameGrid.setLayout(gameGridLayout);
        gameGridLayout.setHorizontalGroup(
            gameGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        gameGridLayout.setVerticalGroup(
            gameGridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
        );

        endButton.setText("End Game");
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        playerNameLabel.setText("jLabel1");

        infoLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gameGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(88, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endButton)
                        .addGap(203, 203, 203))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameGrid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoLabel)
                    .addComponent(endButton))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed
        // TODO add your handling code here:
        String win = proxy.getGameState(gameID);
        System.out.println(win);
        if (win.equals("ERROR-NOGAME")) {
            JOptionPane.showMessageDialog(null, "Sorry, no game available!");
            dispose();
        }
        else if (win.equals("ERROR-DB"))
            JOptionPane.showMessageDialog(null, "Can't connect to Database!");
        else {
            int winInt = Integer.valueOf(win);
            if (winInt == -1)
                proxy.deleteGame(gameID, playerID);
            else if(winInt == 0 && symbol.equals("X"))
                proxy.setGameState(gameID, 2);
            else if(winInt == 0 && symbol.equals("O"))
                proxy.setGameState(gameID, 1);
        }
        dispose();
        GameOptions gameOptions = new GameOptions();
        gameOptions.showOpenGames(playerID, userName);
        gameOptions.setVisible(true);
    }//GEN-LAST:event_endButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton endButton;
    private javax.swing.JPanel gameGrid;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel playerNameLabel;
    // End of variables declaration//GEN-END:variables
}
