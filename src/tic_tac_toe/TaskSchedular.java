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
public class TaskSchedular extends Thread {
    private int playerId;
    private int gameId;
    private String symbol;
    private GameWindow gameWindow;
    TTTWebService proxy;
    
    
    public TaskSchedular(GameWindow gameScreen){
        proxy = new TTTWebService_Service().getTTTWebServicePort();
        this.gameWindow = gameScreen;
        playerId = gameScreen.getplayerId();
        gameId = gameScreen.getGameId();
        symbol = gameScreen.getSymbol();
    }
    
    @Override
    public void run() {
        boolean end = false;
        while (!Thread.currentThread().isInterrupted() && !end){
            try {
                Thread.sleep(1200);
            } catch(Exception e){
                
            }
            String board = proxy.getBoard(gameId);
            if (board.equals("ERROR-NOMOVES") || board.equals("ERROR-DB")) {
                gameWindow.setInfoText(board);
                continue;
            }
            int lastPid = -1;
            String[] board_arr = board.split("\\n");
            for (int i = 0; i < board_arr.length; i++) {
                String[] move = board_arr[i].split(",");
                String turnSymbol;
                int x = Integer.valueOf(move[1]); 
                int y = Integer.valueOf(move[2]);
                int pid =  Integer.valueOf(move[0]);
                if (playerId == pid) {
                    turnSymbol = symbol;
                }
                else {
                    if(symbol.equals("X"))
                        turnSymbol = "O";
                    else
                        turnSymbol = "X";
                }
                   
                gameWindow.takeSquare(turnSymbol, x, y);
                if (i == board_arr.length - 1) {
                    lastPid = pid;
                }
            }
            
            int endState = Integer.valueOf(proxy.checkWin(gameId));
            if (endState > 0) {
                gameWindow.block();
                end = true;
                proxy.setGameState(gameId, endState);
                if ((endState == 1 && symbol.equals("X")) || (endState == 2 && symbol.equals("O"))) 
                {
                    //gameWindow.setCurPlayerText("Game Over: You won!");
                    JOptionPane.showMessageDialog(gameWindow,"Game Over: You won!");
                }
                else if ((endState == 2 && symbol.equals("X")) || (endState == 1 && symbol.equals("O"))) 
                {
                    //gameWindow.setCurPlayerText("Game Over: You Lost!");
                    JOptionPane.showMessageDialog(gameWindow,"Game Over: You Lost!");
                }
                else if (endState == 3)
                {
                    //gameWindow.setCurPlayerText("Game Over: It's a tie!");
                    JOptionPane.showMessageDialog(gameWindow,"Game Over: It's a tie!");
                }
            } else if (lastPid != playerId){   
                gameWindow.unblock();
                gameWindow.setInfoText("It's your turn.");
            }
        }
    }
}
