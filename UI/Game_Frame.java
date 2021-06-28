//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.UI;

import com.game.logic.Game_Logic;
import com.game.map.Game_Map;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Game_Frame extends JFrame {
    private final JToolBar toolBar = new JToolBar();
    private final JButton btnClear = new JButton("清空");
    private final JButton btnRandom = new JButton("随机生成");
    private final JButton btnStep = new JButton("单步演化");
    private final JButton btnStart = new JButton("开始演化");
    private final JButton btnStop = new JButton("结束演化");
    private final Game_Logic gameLogic = new Game_Logic();
    private int[][] gameMatrix;
    private JCheckBox[][] cellMatrix;
    public Game_Map gameMap = new Game_Map(35, 45, 2);
    private JPanel gamePanel = new JPanel();

    public Game_Frame() {
        this.setTitle("生命游戏");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("lifeGame.jpg");
        this.setIconImage(icon);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.initButtonLayout();
        this.initGameLayout();
    }

    private void initButtonLayout() {
        this.toolBar.add(this.btnClear);
        this.toolBar.add(this.btnRandom);
        this.toolBar.add(this.btnStep);
        this.toolBar.add(this.btnStart);
        this.toolBar.add(this.btnStop);
        this.getContentPane().add(this.toolBar, "North");
        this.btnClear.addActionListener((e) -> { this.onClear(); });
        this.btnRandom.addActionListener((e) -> { this.onRandom(); });
        this.btnStep.addActionListener((e) -> { this.onStep(); });
        this.btnStart.addActionListener((e) -> { this.onStart(); });
        this.btnStop.addActionListener((e) -> { this.onStop();  });
        this.btnStop.setEnabled(false);
    }

    private void initGameLayout() {
        int rows = this.gameMap.getHeight();
        int cols = this.gameMap.getWidth();
        this.gamePanel = new JPanel();
        this.gamePanel.setSize(400, 300);
        this.gamePanel.setLayout(new GridLayout(rows, cols));
        this.cellMatrix = new JCheckBox[rows][cols];
        this.gameMatrix = this.gameMap.getGameMap();

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSize(30, 30);
                checkBox.setSelected(false);
                this.cellMatrix[i][j] = checkBox;
                this.gamePanel.add(checkBox);
            }
        }

        this.getContentPane().add(this.gamePanel, "Center");
        this.updateMap();
    }

    public void getStatue() {
        for(int i = 0; i < this.gameMatrix.length; ++i) {
            for(int j = 0; j < this.gameMatrix[i].length; ++j) {
                this.gameMatrix[i][j] = this.cellMatrix[i][j].isSelected() ? 1 : 0;
            }
        }

    }

    public void updateMap() {
        this.gameMatrix = this.gameMap.getGameMap();

        for(int i = 0; i < this.gameMatrix.length; ++i) {
            for(int j = 0; j < this.gameMatrix[i].length; ++j) {
                this.cellMatrix[i][j].setSelected(this.gameMatrix[i][j] == 1);
            }
        }

        this.gamePanel.updateUI();
    }

    private void onClear() {
        this.gameLogic.clearMap(this.gameMap);
        this.updateMap();
    }

    private void onRandom() {
        this.gameLogic.randomMap(this.gameMap);
        this.updateMap();
    }

    private void onStep() {
        this.getStatue();
        this.gameMap.setStatue(this.gameMatrix);
        this.gameLogic.gameCycle(this.gameMap);
        this.updateMap();
    }

    private void onStart() {
        Game_Logic.gameStatue = true;
        (new Thread(new Game_Frame.GameTimer())).start();
        this.btnStart.setEnabled(false);
        this.btnStop.setEnabled(true);
    }

    private void onStop() {
        Game_Logic.gameStatue = false;
        this.btnStart.setEnabled(true);
        this.btnStop.setEnabled(false);
    }


    public static void main(String[] args) {
        new Game_Frame();
    }

    private class GameTimer implements Runnable {
        private GameTimer() {
        }

        public void run() {
            while(Game_Logic.gameStatue) {
                Game_Frame.this.onStep();

                try {
                    TimeUnit.MILLISECONDS.sleep((long)(1000 / Game_Frame.this.gameMap.getSpeed()));
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }

        }
    }
}
