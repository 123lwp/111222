package com.game.map;

import java.util.Arrays;
import java.util.Scanner;

public class Game_Map {
    private int nRows;	        // 行数
    private int nCols;  	    // 列数
    private int mortality;      // 死亡率
    private int speed;  	    // 运行速度

    private int[][] gameMap;	// 游戏地图

    // 构造函数
    public Game_Map(int rows, int cols, int speed) {
        this.nRows = rows;
        this.nCols = cols;
        this.speed = speed;
        updateMap();
    }

    // 初始化地图
    public void initMap() {
        int[][] map = new int[nRows][nCols];

        Scanner scanner = new Scanner(System.in);
        int numPoint = scanner.nextInt();

        scanner.close();

        gameMap = map;
    }

    // 初始化游戏地图
    public void updateMap() {
        gameMap = new int[nRows][nCols];
        for (int[] map : gameMap) Arrays.fill(map, 0);
    }

    // 获取一个方格周围的活细胞数
    public int getNeighborCount(int row, int col) {
        // 活细胞数
        int count = 0;
        // 依此判断该方格周围的八个方格
        if(row != 0)							//上
            count += gameMap[row - 1][col];
        if(row != nRows - 1) 					// 下
            count += gameMap[row + 1][col];
        if(col != 0) 							// 左
            count += gameMap[row][col - 1];
        if(col != nCols - 1)						// 右
            count += gameMap[row][col + 1];
        if(row != 0 && col != 0) 				// 左上
            count += gameMap[row - 1][col - 1];
        if(row != 0 && col != nCols - 1) 		// 右上
            count += gameMap[row - 1][col + 1];
        if(row != nRows - 1 && col != 0) 		// 左下
            count += gameMap[row + 1][col - 1];
        if(row != nRows - 1 && col != nCols - 1) 	// 右下
            count += gameMap[row + 1][col + 1];

        return count;
    }

    // 更改地图行数
    public void setHeight(int rows) {
        this.nRows = rows;
        updateMap();
    }
    // 更改地图列数
    public void setWidth(int cols) {
        this.nCols = cols;
        updateMap();
    }
    public int getHeight() {
        return nRows;
    }

    // 获取地图列数
    public int getWidth() {
        return nCols;
    }

    // 获取死亡率
    public int getMortality() {
        return mortality;
    }

    // 获取速度
    public int getSpeed() {
        return speed;
    }

    // 更改速度
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // 获取游戏地图
    public int[][] getGameMap() {
        return gameMap;
    }

    // 获取地图上方格的状态
    public int getStatue(int row, int col) {
        return gameMap[row][col];
    }

    // 更新地图上方格的状态
    public void setStatue(int[][] map) {
        this.gameMap = map;
    }

    // 测试
    public static void main(String[] args){
        Game_Map map = new Game_Map(10, 10, 5);
        map.initMap();
        for(int i = 0; i < map.gameMap.length; i++) {
            for(int j = 0; j< map.gameMap[i].length; j++)
            {
                System.out.print(map.gameMap[i][j]);
            }
            System.out.println();
        }
    }

    public void setMortality(int parseInt) {
    }
}
