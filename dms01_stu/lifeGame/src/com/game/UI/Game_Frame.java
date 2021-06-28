package com.game.UI;

import com.game.logic.Game_Logic;
import com.game.map.Game_Map;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Game_Frame extends JFrame {

    private final JToolBar toolBar = new JToolBar();
    private final JButton btnClear = new JButton("清空");
    private final JButton btnRandom = new JButton("随机生成");
    private final JButton btnStep = new JButton("单步演化");
    private final JButton btnStart = new JButton("开始演化");
    private final JButton btnStop = new JButton("结束演化");

    private final Game_Logic gameLogic = new Game_Logic();

    private int[][] gameMatrix;                 // 状态矩阵
    private JCheckBox[][] cellMatrix;        // 细胞矩阵


    public Game_Map gameMap = new Game_Map(35,45,2);
    private JPanel gamePanel = new JPanel();

    public Game_Frame() {

        this.setTitle("生命游戏");
       // this.setJMenuBar(menuBar);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("lifeGame.jpg");
        this.setIconImage(icon);
        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 初始化布局
        initButtonLayout();
        initGameLayout();
    }

    // 初始化按钮布局
    private void initButtonLayout() {
        toolBar.add(btnClear);
        toolBar.add(btnRandom);
        toolBar.add(btnStep);
        toolBar.add(btnStart);
        toolBar.add(btnStop);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        btnClear.addActionListener(e -> onClear());
        btnRandom.addActionListener(e -> onRandom());
        btnStep.addActionListener(e -> onStep());
        btnStart.addActionListener(e -> onStart());
        btnStop.addActionListener(e -> onStop());

        // 设置按钮状态
        //stop.setEnabled(false);
        btnStop.setEnabled(false);
    }

    // 初始化游戏界面布局
    private void initGameLayout() {
        int rows = gameMap.getHeight();
        int cols = gameMap.getWidth();
        gamePanel = new JPanel();

        gamePanel.setSize(400,300);
        gamePanel.setLayout(new GridLayout(rows, cols));

        cellMatrix = new JCheckBox[rows][cols];
        gameMatrix = gameMap.getGameMap();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSize(30, 30);
                checkBox.setSelected(false);
                cellMatrix[i][j] = checkBox;
                gamePanel.add(checkBox);
            }
        }
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        updateMap();
    }

    // 获取游戏状态
    public void getStatue() {
        for (int i = 0; i < gameMatrix.length; i++)
            for (int j = 0; j < gameMatrix[i].length; j++)
                gameMatrix[i][j] = cellMatrix[i][j].isSelected() ? 1 : 0;
    }

    // 更新游戏地图
    public void updateMap() {
        gameMatrix = gameMap.getGameMap();
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < gameMatrix[i].length; j++) {
                cellMatrix[i][j].setSelected(gameMatrix[i][j] == 1);
            }
        }
        gamePanel.updateUI();
    }

    // 清空画面
    private void onClear() {
        gameLogic.clearMap(gameMap);
        updateMap();
    }

    // 随机生成画面
    private void onRandom() {
        gameLogic.randomMap(gameMap);
        updateMap();
    }

    // 单步演化
    private void onStep() {
        getStatue();
        gameMap.setStatue(gameMatrix);
        gameLogic.gameCycle(gameMap);
        updateMap();
    }

    // 开始演化
    private void onStart() {
        Game_Logic.gameStatue = Game_Logic.START;
        new Thread(new GameTimer()).start();
        // 设置按钮状态
        //start.setEnabled(false);
        btnStart.setEnabled(false);
        //stop.setEnabled(true);
        btnStop.setEnabled(true);
    }

    // 停止演化
    private void onStop() {
        Game_Logic.gameStatue = Game_Logic.STOP;
        // 设置按钮状态
      //  start.setEnabled(true);
        btnStart.setEnabled(true);
      //  stop.setEnabled(false);
        btnStop.setEnabled(false);
    }



    private void onSetWidth() {
        String str = JOptionPane.showInputDialog("请输入需要设置的宽度");
        gameMap.setWidth(Integer.parseInt(str));
        remove(gamePanel);
        initGameLayout();
    }

    // 设置地图高度
    private void onSetHeight() {
        String str = JOptionPane.showInputDialog("请输入需要设置的高度");
        gameMap.setHeight(Integer.parseInt(str));
        remove(gamePanel);
        initGameLayout();
    }



    // 程序入口
    public static void main(String[] args) {
        new Game_Frame();
    }

    // 游戏线程类
    private class GameTimer implements Runnable {
        // 一个生命周期
        @Override
        public void run() {
            while (Game_Logic.gameStatue) {
                onStep();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 / gameMap.getSpeed());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
