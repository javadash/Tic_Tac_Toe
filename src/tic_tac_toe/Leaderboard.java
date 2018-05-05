/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import ttt.james.server.TTTWebService;
import ttt.james.server.TTTWebService_Service;

/**
 *
 * @author Johnson
 */
public class Leaderboard extends javax.swing.JFrame {
    TTTWebService proxy;
    
    public Leaderboard() {
        proxy = new TTTWebService_Service().getTTTWebServicePort();
        initComponents();
        this.setTitle("Leaderboard");

//        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(leaderboardTable.getModel());
//        leaderboardTable.setRowSorter(sorter);
//
//        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
//        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
//        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
//        sorter.setSortKeys(sortKeys);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        leaderboardTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        leaderboardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Player ID", "Wins", "Draws", "Losses"
            }
        ));
        jScrollPane1.setViewportView(leaderboardTable);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Top Players in World by Wins");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(286, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeButton)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    public void showLeaderboard() {
        String leagueTableResults = proxy.leagueTable();
        if (leagueTableResults.equals("ERROR-NOGAMES")) {
            JOptionPane.showMessageDialog(null, "No games played");
        } else if (leagueTableResults.equals("ERROR-DB")) {
            JOptionPane.showMessageDialog(null, "Can't connect to Database!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] league_arr = leagueTableResults.split("\\n");
            String[][] league = new String[league_arr.length][];
            for (int i = 0; i < league_arr.length; i++){
                league[i] = league_arr[i].split(",");
            }
            // tally wins and show table
            String[][] stats = leaderboardCalcs(league);
            String[] columns = {"Username", "Wins", "Draws",  "Losses"};
            DefaultTableModel dataSet = new DefaultTableModel(stats, columns);
            leaderboardTable.setModel(dataSet);
            leaderboardTable.repaint();
        }
    }
    
    private String[][] leaderboardCalcs(String[][] games) {
        HashMap<String, Integer[]> stats = new HashMap<>();
        for (String match[] : games) {
            String u1 = match[1]; // user 1
            String u2 = match[2]; // usee 2
            int gameState = Integer.valueOf(match[3]);
            // create entries if the don't exist
            if (!stats.containsKey(u1)) {
                Integer[] tmp = {0,0,0};
                stats.put(u1,tmp);
            }
            if (!stats.containsKey(u2)){
                Integer[] tmp = {0,0,0};
                stats.put(u2,tmp);
            }
            
            if (gameState == 1) { //increments win
                Integer[] tmp = stats.get(u1);
                ++tmp[0];
                stats.put(u1,tmp);
                tmp = stats.get(u2);
                ++tmp[2];
                stats.put(u2,tmp);
            } else if (gameState == 2) { //player2 won
                Integer[] tmp = stats.get(u1);
                ++tmp[2];
                stats.put(u1,tmp);
                tmp = stats.get(u2);
                ++tmp[0];
                stats.put(u2,tmp);
            } else if (gameState == 3) { //tie
                Integer[] tmp = stats.get(u1);
                ++tmp[1];
                stats.put(u1,tmp);
                tmp = stats.get(u2);
                ++tmp[1];
                stats.put(u2,tmp);
            }
        }
        
        String[][] result = new String[stats.size()][4];
        int i = 0;
        for (String user : stats.keySet()){
            Integer[] tmp = stats.get(user);
            String[] tmp_str = {user, String.valueOf(tmp[0]), String.valueOf(tmp[1]),
                                String.valueOf(tmp[2])};
            result[i] = tmp_str;
            i++;
        }
        return result;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable leaderboardTable;
    // End of variables declaration//GEN-END:variables
}
