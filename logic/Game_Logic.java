//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.logic;

import com.game.map.Game_Map;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.Random;

public class Game_Logic {
    public static final boolean STOP = false;
    public static final boolean START = true;
    @SuppressFBWarnings("MS_CANNOT_BE_FINAL")
    public static boolean gameStatue;

    public Game_Logic() {
    }

    public void clearMap(Game_Map gameMap) {
        int rows = gameMap.getHeight();
        int cols = gameMap.getWidth();
        int[][] nextMap = new int[rows][cols];
        int[][] var5 = nextMap;
        int var6 = nextMap.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            int[] map = var5[var7];
            Arrays.fill(map, 0);
        }

        gameMap.setStatue(nextMap);
    }

    public void randomMap(Game_Map gameMap) {
        int rows = gameMap.getHeight();
        int cols = gameMap.getWidth();
        int[][] nextMap = new int[rows][cols];

        for(int i = 0; i < nextMap.length; ++i) {
            for(int j = 0; j < nextMap[i].length; ++j) {
                nextMap[i][j] = (new Random()).nextInt(2);
            }
        }

        gameMap.setStatue(nextMap);
    }

    public void gameCycle(Game_Map gameMap) {
        int rows = gameMap.getHeight();
        int cols = gameMap.getWidth();
        int[][] nextMap = new int[rows][cols];

        for(int i = 0; i < nextMap.length; ++i) {
            for(int j = 0; j < nextMap[i].length; ++j) {
                nextMap[i][j] = 0;
                int neighborCount = gameMap.getNeighborCount(i, j);
                if (neighborCount == 3) {
                    nextMap[i][j] = 1;
                } else if (neighborCount == 2) {
                    nextMap[i][j] = gameMap.getStatue(i, j);
                }
            }
        }

        gameMap.setStatue(nextMap);
    }
}
