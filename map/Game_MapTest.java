//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.game.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Game_MapTest {
    private static Game_Map Test = new Game_Map(100, 100, 10);

    Game_MapTest() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void initMap() {
        int[][] Cell = Test.gameMap;

        for(int i = 0; i < 100; ++i) {
            for(int j = 0; j < 100; ++j) {
                Assertions.assertEquals(0, Cell[i][j]);
            }
        }

    }

    @Test
    void getNeighborCount() {
        int[][] Cell = Test.gameMap;

        for(int i = 1; i < 99; ++i) {
            for(int j = 1; j < 99; ++j) {
                int a = Cell[i][j - 1] + Cell[i - 1][j - 1] + Cell[i + 1][j - 1] + Cell[i - 1][j] + Cell[i + 1][j] + Cell[i][j + 1] + Cell[i + 1][j + 1] + Cell[i - 1][j + 1];
                Assertions.assertEquals(a, Test.getNeighborCount(i, j));
            }
        }

    }
}
