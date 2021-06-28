//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.map;

import java.util.Arrays;
import java.util.Scanner;

public class Game_Map {
    private int nRows;
    private int nCols;
    private int speed;
    public int[][] gameMap;

    public Game_Map(int rows, int cols, int speed) {
        this.nRows = rows;
        this.nCols = cols;
        this.speed = speed;
        this.updateMap();
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"DLS_DEAD_LOCAL_STORE", "DM_DEFAULT_ENCODING"})
    public void initMap() {
        int[][] map = new int[this.nRows][this.nCols];
        Scanner scanner = new Scanner(System.in);
        int numPoint = scanner.nextInt();
        scanner.close();
        this.gameMap = map;
    }

    public void updateMap() {
        this.gameMap = new int[this.nRows][this.nCols];
        int[][] var1 = this.gameMap;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            int[] map = var1[var3];
            Arrays.fill(map, 0);
        }

    }

    public int getNeighborCount(int row, int col) {
        int count = 0;
        if (row != 0) {
            count += this.gameMap[row - 1][col];
        }

        if (row != this.nRows - 1) {
            count += this.gameMap[row + 1][col];
        }

        if (col != 0) {
            count += this.gameMap[row][col - 1];
        }

        if (col != this.nCols - 1) {
            count += this.gameMap[row][col + 1];
        }

        if (row != 0 && col != 0) {
            count += this.gameMap[row - 1][col - 1];
        }

        if (row != 0 && col != this.nCols - 1) {
            count += this.gameMap[row - 1][col + 1];
        }

        if (row != this.nRows - 1 && col != 0) {
            count += this.gameMap[row + 1][col - 1];
        }

        if (row != this.nRows - 1 && col != this.nCols - 1) {
            count += this.gameMap[row + 1][col + 1];
        }

        return count;
    }

    public void setHeight(int rows) {
        this.nRows = rows;
        this.updateMap();
    }

    public void setWidth(int cols) {
        this.nCols = cols;
        this.updateMap();
    }

    public int getHeight() {
        return this.nRows;
    }

    public int getWidth() {
        return this.nCols;
    }

    public int[][] getGameMap() {
        return this.gameMap;
    }

    public int getStatue(int row, int col) {
        return this.gameMap[row][col];
    }

    public void setStatue(int[][] map) {
        this.gameMap = map;
    }

    public static void main(String[] args) {
        Game_Map map = new Game_Map(10, 10, 5);
        map.initMap();

        for (int i = 0; i < map.gameMap.length; ++i) {
            for (int j = 0; j < map.gameMap[i].length; ++j) {
                System.out.print(map.gameMap[i][j]);
            }

            System.out.println();
        }

    }


    public int getSpeed() {
        return speed;
    }

    public void gameCycle() { }
}
