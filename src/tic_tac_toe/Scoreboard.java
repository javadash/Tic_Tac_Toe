/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import javax.swing.JOptionPane;
import ttt.james.server.TTTWebService;
import ttt.james.server.TTTWebService_Service;

/**
 *
 * @author Johnson
 */
public class Scoreboard extends javax.swing.JFrame {
    private TTTWebService proxy;

    public Scoreboard() {
        proxy = new TTTWebService_Service().getTTTWebServicePort();
        initComponents();
        this.setTitle("Scoreboard");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        winsLabel = new javax.swing.JLabel();
        noOfWIns = new javax.swing.JLabel();
        lossLabel = new javax.swing.JLabel();
        noOfLosses = new javax.swing.JLabel();
        drawLabel = new javax.swing.JLabel();
        noOfDraws = new javax.swing.JLabel();
        playerNameLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(640, 480));

        winsLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        winsLabel.setText("Wins");

        noOfWIns.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        noOfWIns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noOfWIns.setText("0");

        lossLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lossLabel.setText("Losses");

        noOfLosses.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        noOfLosses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noOfLosses.setText("0");

        drawLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        drawLabel.setText("Draws");

        noOfDraws.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        noOfDraws.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noOfDraws.setText("0");

        playerNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        playerNameLabel.setText("jLabel7");

        closeButton.setText("Close");
        closeButton.setToolTipText("");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(noOfWIns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(winsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lossLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(noOfLosses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(drawLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(noOfDraws, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(closeButton))
                        .addGap(60, 60, 60))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(playerNameLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(winsLabel)
                    .addComponent(lossLabel)
                    .addComponent(drawLabel))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noOfWIns)
                    .addComponent(noOfLosses)
                    .addComponent(noOfDraws))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    void showscore(int playerID, String playerName) {
        playerNameLabel.setText("Current Game Score for " + playerName);
        String allGames = proxy.showAllMyGames(playerID);
        if (allGames.equals("ERROR-NOGAMES")) {
            JOptionPane.showMessageDialog(null, "No games played");
        } else if (allGames.equals("ERROR-DB")) {
            JOptionPane.showMessageDialog(null, "Can't connect to Database!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] games_arr = allGames.split("\\n");
            String[][] games = new String[games_arr.length][];
            int i = 0;
            for (String match : games_arr){
                games[i] = match.split(",");
                i++;
            }
            int[] stats = scoreBoardTally(games, playerName);
            noOfWIns.setText(String.valueOf(stats[0]));
            noOfLosses.setText(String.valueOf(stats[1]));
            noOfDraws.setText(String.valueOf(stats[2]));
            setVisible(true);
        }
    }
    
    private int[] scoreBoardTally(String[][] games, String playerName) {
        int[] result = {0, 0, 0}; // wins, losses, draws
        for (String[] game : games){
            String state_str = proxy.getGameState(Integer.valueOf(game[0]));
            String p1 = game[1];
            String p2 = game[2];
            if (state_str.equals("ERROR-NOGAME") ||
                state_str.equals("ERROR-DB")){
                System.err.println(state_str);
                continue;
            }
            int state = Integer.valueOf(state_str);
            switch(state){
                case 1:  // if first player won
                    if (p1.equals(playerName)) // and user is first player
                        ++result[0]; // increment wins
                    else if (p2.equals(playerName)) // user is second player
                        ++result[1]; // increment loss
                    break;
                case 2:
                    if (p1.equals(playerName))
                        ++result[1];
                    else if (p2.equals(playerName))
                        ++result[0];
                    break;
                case 3: //
                    ++result[2];
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel drawLabel;
    private javax.swing.JLabel lossLabel;
    private javax.swing.JLabel noOfDraws;
    private javax.swing.JLabel noOfLosses;
    private javax.swing.JLabel noOfWIns;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JLabel winsLabel;
    // End of variables declaration//GEN-END:variables
}
