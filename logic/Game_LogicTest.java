//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.logic;

import com.game.map.Game_Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Game_LogicTest {
    private static Game_Map Test = new Game_Map(100, 100, 10);

    Game_LogicTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void clearMap() {
        int[][] Cell = Test.gameMap;

        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                Assertions.assertEquals(0, Cell[i][j]);
            }
        }

    }

    @Test
    void randomMap() {
        int[][] Cell = Test.gameMap;

        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                Assertions.assertEquals(0.5D, (double)Cell[i][j], 0.5D);
            }
        }

    }

    @Test
    void gameCycle() {
        int[][] Cell = new int[100][100];
        int[][] nextCell = Test.gameMap;

        int i;
        for( i = 0; i < 100; ++i) {
            for(i = 0; i < 100; ++i) {
                Cell[i][i] = nextCell[i][i];
            }
        }

        int[][] Neighbor = new int[100][100];

        int j;
        for(i = 0; i < 100; ++i) {
            for(j = 1; j < 100; ++j) {
                Neighbor[i][j] = Test.getNeighborCount(i, j);
            }
        }

        Test.gameCycle();
        nextCell = Test.gameMap;

        for(i = 0; i < 100; ++i) {
            for(j = 1; j < 100; ++j) {
                if (Cell[i][j] == 1) {
                    if (Neighbor[i][j] != 2 && Neighbor[i][j] != 3) {
                        Assertions.assertEquals(0, nextCell[i][j]);
                    } else {
                        Assertions.assertEquals(1, nextCell[i][j]);
                    }
                } else if (Neighbor[i][j] == 3) {
                    Assertions.assertEquals(1, nextCell[i][j]);
                } else {
                    Assertions.assertEquals(0, nextCell[i][j]);
                }
            }
        }

    }
}
