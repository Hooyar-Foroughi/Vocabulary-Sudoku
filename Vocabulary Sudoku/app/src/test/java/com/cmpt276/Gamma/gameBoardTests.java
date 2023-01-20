package com.cmpt276.Gamma;

import org.junit.Test;

import static org.junit.Assert.*;

public class gameBoardTests {

    // wordPair.java class tests

    @Test
    public void wpBaseTest() throws Exception
    {
        wordPair tmp = new wordPair("English", "Spanish", 0);

        assertEquals(tmp.Eword, "English");
        assertEquals(tmp.Sword, "Spanish");
        assertEquals(tmp.subInt, 0);
    }

    // gameBoard.java class tests

    // Test for a standard 9x9 game board
    @Test
    public void wp_test() throws Exception
    {
        // Tests the constructor method of gameBoard.java

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);
        wordPair[] tmp = gb.getWordSet();

        assertArrayEquals(tmp, wpLS);
    }

    @Test
    public void insertion_test() throws Exception
    {
        // Tests the insertion method of gameBoard.java

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);
        wordPair[] tmp = gb.getWordSet();

        assertArrayEquals(tmp, wpLS);

        gb.insertWord(1, 0, 0);
        gb.insertWord(3, 6, 2);

        wordPair[][] tmpB = gb.boardGet();

        assertEquals(tmpB[0][0], wp1);
        assertEquals(tmpB[6][2], wp3);
    }

    @Test
    public void remove_test() throws Exception
    {
        // Tests the removal method of gameBoard.java

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);
        wordPair[] tmp = gb.getWordSet();

        assertArrayEquals(tmp, wpLS);

        gb.insertWord(1, 0, 0);
        gb.insertWord(3, 6, 2);

        gb.removeWord(1, 0);

        wordPair[][] tmpB = gb.boardGet();

        assertEquals(tmpB[6][2], wp3);
        assertEquals(tmpB[1][0], gb.nullPair);
    }

    @Test
    public void solve_testTrue1() throws Exception
    {
        // Tests the solve function as true

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);
        int[][] cor = {
                {4, 5, 3, 8, 2, 6, 1, 0, 7},
                {8, 0, 2, 5, 7, 1, 6, 3, 4},
                {1, 6, 7, 4, 0, 3, 5, 2, 8},
                {7, 1, 4, 0, 5, 2, 8, 6, 3},
                {5, 8, 6, 1, 3, 7, 2, 4, 0},
                {3, 2, 0, 6, 8, 4, 7, 5, 1},
                {0, 3, 5, 2, 1, 8, 4, 7, 6},
                {6, 7, 1, 3, 4, 5, 0, 8, 2},
                {2, 4, 8, 7, 6, 0, 3, 1, 5}
        };
        gb.initialInsert(cor);

        assertEquals(gb.boardGet()[0][0].Eword, wp4.Eword);
        assertEquals(gb.boardGet()[0][8].Eword, wp7.Eword);
        assertEquals(gb.boardGet()[8][0].Eword, wp2.Eword);
        assertEquals(gb.boardGet()[8][8].Eword, wp5.Eword);
        assertEquals(gb.checkWin(), true);
    }

    @Test
    public void solve_testTrue2() throws Exception
    {
        // Tests the solve function as true
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);
        int[][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {8, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };
        gb.initialInsert(cor);

        assertEquals(gb.boardGet()[0][0].Eword, wp4.Eword);
        assertEquals(gb.boardGet()[0][8].Eword, wp7.Eword);
        assertEquals(gb.boardGet()[8][0].Eword, wp2.Eword);
        assertEquals(gb.boardGet()[8][8].Eword, wp5.Eword);
        assertEquals(gb.checkWin(), true);

    }

    @Test
    public void solve_testFalse1() throws Exception
    {
        // Tests the solve function as False
        // Filled but incorrect
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wpLS = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};

        gameBoard gb = new gameBoard(wpLS);

        int[][] incorrect =
                {
                        {4, 5, 3, 8, 2, 6, 1, 0, 7},
                        {8, 0, 2, 5, 7, 1, 6, 3, 4},
                        {1, 6, 7, 4, 0, 3, 5, 2, 8},
                        {7, 1, 4, 0, 5, 2, 8, 6, 3},
                        {5, 8, 6, 1, 3, 7, 2, 4, 0},
                        {3, 2, 0, 6, 8, 4, 7, 5, 1},
                        {0, 3, 5, 2, 1, 8, 4, 7, 6},
                        {6, 7, 1, 3, 4, 5, 0, 8, 2},
                        {2, 4, 8, 7, 6, 0, 3, 1, 2}
                };

        gb.initialInsert(incorrect);
        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void solve_testFalse2() throws Exception
    {
        // Tests the solve function as False
        boardLayouts tmp = new boardLayouts();

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void solve_testFalse3() throws Exception
    {
        // Tests the solve function as False
        boardLayouts tmp = new boardLayouts();

        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);

        gb.initialInsert(tmp.retBoard9x9(0));

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void checkrow_true1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {8, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkRow(0), true);
    }

    @Test
    public void checkrow_true2() throws Exception
    {
        // Another column incorrect, this one is correct
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 2, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {8, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkRow(0), true);
    }

    @Test
    public void checkrow_false1() throws Exception
    {
        // Column incorrect
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkRow(0), false);
    }

    @Test
    public void checkrow_false2() throws Exception
    {
        // Column incorrect multiple times
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {2, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {3, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkRow(0), false);
    }

    @Test
    public void checkcolumn_true1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkColumn(0), true);
    }

    @Test
    public void checkcolumn_true2() throws Exception
    {
        // Other column incorrect, checked one correct
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 3, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkColumn(0), true);
    }

    @Test
    public void checkcolumn_false1() throws Exception
    {
        // Column incorrect
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 3, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkColumn(2), false);
    }

    @Test
    public void checkcolumn_false2() throws Exception
    {
        // Column incorrect multiple times
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 3, 5, 0, 5, 4, 5},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkColumn(2), false);
    }

    @Test
    public void checksubbox_true1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
    }

    @Test
    public void checksubbox_true2() throws Exception
    {
        // Other subbox incorrect, checked is correct
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 2, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
    }

    @Test
    public void checksubbox_false1() throws Exception
    {
        // CHecked subbox incorrect
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {4, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 2, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 1, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), false);
    }

    @Test
    public void checksubbox_false2() throws Exception
    {
        // Multiple incorrect boxes
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);

        int [][] cor = {
                {5, 2, 0, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 0, 3, 8},
                {7, 8, 3, 6, 5, 0, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 0},
                {3, 0, 5, 2, 8, 6, 2, 7, 4},
                {2, 4, 1, 7, 0, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 0, 2},
                {0, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 0, 4, 1, 3, 8, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8};
        gameBoard gb = new gameBoard(wp);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), false);
        assertEquals(gb.checkSubbox(2, 1), false);
    }

    // Test for a 4x4 game board

    @Test
    public void checkSB4x4_True1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {0, 1, 2, 3}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
    }

    @Test
    public void checkSB4x4_True2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {-1, 1, 2, 2}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
    }

    @Test
    public void checkSB4x4_False1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {2, 1, 2, 3}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 1), false);
    }

    @Test
    public void solve4x4_testTrue1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {0, 1, 2, 3}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), true);
    }

    @Test
    public void solve4x4_testFalse1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {0, 1, 2, 2}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void solve4x4_testFalse2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);

        int[][] cor = {
                {1, 2, 3, 0},
                {3, 0, 1, 2},
                {2, 3, 0, 1},
                {0, 1, 2, -1}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3};
        gameBoard gb = new gameBoard(wp, 4);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), false);
    }

    // Test for a 6x6 game board

    @Test
    public void solve6x6_testTrue1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 5, 3, 2, 0, 4},
                {2, 0, 4, 3, 1, 5},
                {3, 1, 5, 4, 2, 0},
                {4, 2, 0, 5, 3, 1},
                {5, 3, 1, 0, 4, 2},
                {0, 4, 2, 1, 5, 3}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), true);
    }

    @Test
    public void solve6x6_testFalse1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 2, 3, 4, 5, 0},
                {3, 4, 5, 0, 1, 2},
                {5, 0, 1, 2, 3, 4},
                {2, 3, 4, 2, 0, 1},
                {4, 5, 0, 1, 2, 3},
                {0, 1, 2, 3, 4, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void checkSB6x6_True1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 2, 3, 4, 5, 0},
                {3, 4, 5, 0, 1, 2},
                {5, 0, 1, 2, 3, 4},
                {2, 3, 4, 5, 0, 1},
                {4, 5, 0, 1, 2, 3},
                {0, 1, 2, 3, 4, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 1), true);
    }

    @Test
    public void checkSB6x6_True2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 2, 5, 4, 5, 0},
                {3, 4, 0, 0, -1, 2},
                {5, 0, 1, 2, 3, 4},
                {1, 1, 1, 5, 0, 1},
                {4, -1, 0, 1, -1, 3},
                {0, 1, 2, 3, 4, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
    }

    @Test
    public void checkSB6x6_False1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 2, 3, 4, 5, 0},
                {3, 4, 5, 0, 1, 2},
                {5, 0, 1, 2, 3, 4},
                {2, 3, 4, 5, 0, 1},
                {1, 5, 0, 1, 2, 3},
                {0, 1, 2, 3, 4, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(1, 0), false);
    }

    @Test
    public void checkSB6x6_False2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);

        int[][] cor = {
                {1, 2, 3, 4, 5, 0},
                {3, 4, 5, 0, 1, 2},
                {5, 0, 1, 2, 3, 4},
                {2, 3, 4, 5, 0, 1},
                {-1, 5, 0, 1, 2, 3},
                {0, 1, 2, 3, 4, 5}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5};
        gameBoard gb = new gameBoard(wp, 6);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(1, 0), false);
    }

    @Test
    public void solve12x12_True1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), true);
    }

    @Test
    public void solve12x12_True2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), true);
    }

    @Test
    public void solve12x12_False1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 5, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        // Counts for correct rows and columns are 11, ie 1 incorrect for each, which is what we wanted here

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void solve12x12_False2() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 1, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 0, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkWin(), false);
    }

    @Test
    public void checkSB12x12_True1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), true);
        assertEquals(gb.checkSubbox(2, 2), true);
        assertEquals(gb.checkSubbox(1, 3), true);
    }

    @Test
    public void checkSB12x12_TF1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 3, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 4, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 0), false);
        assertEquals(gb.checkSubbox(2, 2), true);
        assertEquals(gb.checkSubbox(1, 3), true);
    }

    @Test
    public void checkSB12x12_False1() throws Exception
    {
        wordPair wp0 = new wordPair("E0", "S0", 0);
        wordPair wp1 = new wordPair("E1", "S1", 1);
        wordPair wp2 = new wordPair("E2", "S2", 2);
        wordPair wp3 = new wordPair("E3", "S3", 3);
        wordPair wp4 = new wordPair("E4", "S4", 4);
        wordPair wp5 = new wordPair("E5", "S5", 5);
        wordPair wp6 = new wordPair("E6", "S6", 6);
        wordPair wp7 = new wordPair("E7", "S7", 7);
        wordPair wp8 = new wordPair("E8", "S8", 8);
        wordPair wp9 = new wordPair("E9", "S9", 9);
        wordPair wp10 = new wordPair("E10", "S10", 10);
        wordPair wp11 = new wordPair("E11", "S11", 11);

        int[][] cor = {
                {8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6, 7},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                {4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0},
                {5, 6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4},
                {9, 10, 11, 1, 1, 2, 3, 4, 5, 6, 7, 8},
                {6, 7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1},
                {10, 11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {11, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 1, 2},
                {7, 8, 9, 10, 11, 0, 1, 2, 3, 4, 5, 6}
        };

        wordPair[] wp = {wp0, wp1, wp2, wp3, wp4, wp5, wp6, wp7, wp8, wp9, wp10, wp11};
        gameBoard gb = new gameBoard(wp, 12);
        gb.initialInsert(cor);

        assertEquals(gb.checkSubbox(0, 1), false);
    }
}