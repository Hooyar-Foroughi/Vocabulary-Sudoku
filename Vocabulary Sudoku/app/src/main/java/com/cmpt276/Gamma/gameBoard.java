package com.cmpt276.Gamma;

// The actual object for the gameboard

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.List;

public class gameBoard extends ViewModel
{
    private sizeDefs SD = new sizeDefs();

    // Definitions for subbox size
    public int sbX;
    public int sbY;
    public int msize;

    // The set of words to be used on this gameboard
    private wordPair[] wordset;
    public List<Integer> usedIndices;


    // The grid representing the game board and locked spaces
    private wordPair[][] board;
    private boolean[][] lockedCells;
    public boolean[][] isSelected;
    public boolean[][] correctPosition;

    // The nullPair is a placeholder for empty spaces on the grid
    public static wordPair nullPair = new wordPair(" ", " ", -1);
    private boardLayouts bl = new boardLayouts();


    // Constructor method for populating blank board, useful only for testing
    gameBoard()
    {
        msize = 9;
        sbY = 3;
        sbX = 3;

        wordset = new wordPair[9];

        board = new wordPair[9][9];
        lockedCells = new boolean[9][9];
        isSelected = new boolean[9][9];
        correctPosition = new boolean[9][9];

        for(int x = 0; x < 9; x++){
            wordset[x] = nullPair;
            for(int y = 0; y < 9; y++){
                board[x][y] = nullPair;
            }
        }
    }

    // Constructor method for populating with a word set
    gameBoard(wordPair[] wpL) throws Exception
    {
        msize = 9;
        sbY = 3;
        sbX = 3;

        wordset = new wordPair[9];

        board = new wordPair[9][9];
        lockedCells = new boolean[9][9];
        isSelected = new boolean[9][9];
        correctPosition = new boolean[9][9];

        setCorrectPosition();
        setWordset(wpL);
        for(int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board[x][y] = nullPair;
            }
        }
    }

    // Constructor method for populating with a word set
    gameBoard(wordPair[] wpL, int size) throws Exception
    {
        msize = size;

        wordset = new wordPair[size];

        board = new wordPair[size][size];
        lockedCells = new boolean[size][size];
        isSelected = new boolean[size][size];
        correctPosition = new boolean[size][size];

        sbX = getSBX();
        sbY = getSBY();

        setCorrectPosition();
        setWordset(wpL);
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board[x][y] = nullPair;
            }
        }
    }


    @Override
    protected void onCleared(){
        super.onCleared();
        Log.i("gameBoard","ViewModel is Destroyed");
    }

    // Boolean store the position of the cell correct or incorrect (true at starting)
    private void setCorrectPosition()
    {
        for (int i = 0; i < msize; i++) {
            for (int j = 0; j < msize; j++) {
                correctPosition[i][j] = true;
                isSelected[i][j]=false;
            }
        }
    }

    // Sets the wordset to entered array
    private void setWordset(wordPair[] wpL) throws Exception
    {
        if(wpL.length != msize) throw new Exception("Inserted word set not proper length");
        for(int i = 0; i < msize; i++) {
            wordset[i] = wpL[i];
            wordset[i].subInt = i;
        }
    }

    // Returns the set of all wordpairs
    public wordPair[] getWordSet()
    {
        return wordset;
    }

    // Method for inserting
    public void insertWord(int wpin, int x, int y) throws Exception
    {
        if(wpin < 0 || wpin > msize-1) throw new Exception("Invalid index for wordpair");
        for(int i = 0; i < msize; i++) if(wordset[i] == nullPair) throw new Exception("wordset not initialized");
        board[x][y] = wordset[wpin];
    }

    // Method for removing
    public void removeWord(int x, int y)
    {
        board[x][y] = nullPair;
    }

    // Method for returning board
    public wordPair[][] boardGet()
    {
        return board;
    }

    // Method for returning the locked-state of the board
    public boolean[][] lockGet()
    {
        return lockedCells;
    }

    // Method to check for win conditions
    // While technically a proper sudoku has one solution, its easier to work with algorithmically
    // It also provides some handy functions for giving players just the right amount of info
    public boolean checkWin()
    {
        int tot = 0;
        // Check each sub-box
        for(int x = 0; x < sbY; x++)
        {
            for(int y = 0; y < sbX; y++)
            {
                if(checkSubbox(x, y)) tot++;
            }
        }

        // Check each column
        for(int x = 0; x < msize; x++)
        {
            if(checkColumn(x)) tot++;
        }

        // Check each row
        for(int y = 0; y < msize; y++)
        {
            if(checkRow(y)) tot++;
        }

        return (tot == 3*msize);
    }

    // Checks a defined subbox area, denoted by the top left of the box, for a solved state
    public boolean checkSubbox(int ix, int iy)
    {
        int[] arrTmp = satSB(ix, iy);
        for(int i = 0; i < msize; i++) if(arrTmp[i] != 1) return false;
        System.out.println("True SB");
        return true;
    }

    // Checks a column for a solved state
    public boolean checkColumn(int ix)
    {
        int[] arrTmp = satCol(ix);
        for(int i = 0; i < msize; i++) if(arrTmp[i] != 1) return false;
        System.out.println("True Col");
        return true;
    }

    // Checks a row for a solved state
    public boolean checkRow(int iy)
    {
        int[] arrTmp = satRow(iy);
        for(int i = 0; i < msize; i++) if(arrTmp[i] != 1) return false;
        System.out.println("True Row");
        return true;
    }

    public boolean allSB(int ix, int iy)
    {
        int[] arrTmp = satSB(ix, iy);
        for(int i = 0; i < msize; i++) if(arrTmp[i] > 1) return false;
        return true;
    }

    public boolean allCol(int ix)
    {
        int[] arrTmp = satCol(ix);
        for(int i = 0; i < msize; i++) if(arrTmp[i] > 1) return false;
        return true;
    }

    public boolean allRow(int iy)
    {
        int[] arrTmp = satRow(iy);
        for(int i = 0; i < msize; i++) if(arrTmp[i] > 1) return false;
        return true;
    }

    public int[] satSB(int ix, int iy)
    {
        // Box's top left
        int tlx = sbX*ix;
        int tly = sbY*iy;

        // 0 initialized array to check occurrences of a number
        int arrTmp[] = new int[msize];
        Arrays.fill(arrTmp, 0);

        // For all cells in subbox, add count of number
        for(int x = tlx; x < tlx + sbX; x++){
            for(int y = tly; y < tly + sbY; y++){
                if(board[x][y].subInt > -1) arrTmp[board[x][y].subInt]++;
                System.out.print(board[x][y].subInt);
            }
            System.out.println();
        }

        System.out.println(Arrays.toString(arrTmp));

        return arrTmp;
    }

    public int[] satCol(int ix)
    {
        // Initialize 0 y space
        int cy = 0;

        // 0 initialized array to check occurences of a number
        int arrTmp[] = new int[msize];
        Arrays.fill(arrTmp, 0);

        // For all cells in column, check number
        for(int y = cy; y < msize; y++)
        {
            if(board[ix][y].subInt > -1) arrTmp[board[ix][y].subInt]++;
        }

        return arrTmp;
    }

    public int[] satRow(int iy)
    {
        // Initialize 0 x space
        int cx = 0;

        // 0 initialized array to check occurences of a number
        int arrTmp[] = new int[msize];
        Arrays.fill(arrTmp, 0);

        // For all cells in row, check number
        for(int x = cx; x < msize; x++)
        {
            if(board[x][iy].subInt > -1) arrTmp[board[x][iy].subInt]++;
        }

        return arrTmp;
    }

    private int getSBX()
    {
        return SD.sbX.get(msize);
    }

    private int getSBY()
    {
        return SD.sbY.get(msize);
    }

    // Initializes the gameboard
    public void initialInsert(int[][] ins)
    {
        for(int x = 0; x < msize; x++)
        {
            for(int y = 0; y < msize; y++)
            {
                // If the board at that point location is populated, add and lock
                if(ins[x][y] < msize && -1 < ins[x][y]){
                    board[x][y] =  wordset[ins[x][y]];
                    lockedCells[x][y] = true;
                }
                // If it is not, make it empty
                else{
                    board[x][y] = nullPair;
                }
            }
        }
    }
}
