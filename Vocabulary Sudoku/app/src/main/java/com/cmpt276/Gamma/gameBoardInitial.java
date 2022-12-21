package com.cmpt276.Gamma;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class gameBoardInitial extends ViewModel {
    public gameBoard gb;
    public int msize;
    public int msize2;
    private boardLayouts bl = new boardLayouts();
    public ArrayList<wordPair> Vocabulary;


    public gameBoardInitial(int size,ArrayList<wordPair> finalWords){
        msize = size;
        msize2 = size*size;
        Vocabulary = finalWords;


        // Create gameboard
        wordPair[] ws = new wordPair[size];
        for (int i = 0; i < size; i++) ws[i] = new wordPair(Vocabulary.get(i).Eword, Vocabulary.get(i).Sword, i);
        try {
            gb = new gameBoard(ws,size);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gets the initial board layout for the gameboard (easy to import new layouts)
        int[][] tmp;

        // Gets the initial board layout for the gameboard (easy to import new layouts)
        if (size == 4){
            int randomBoard = new Random().nextInt(bl.retBoards4x4().length);
            tmp = bl.retBoard4x4(randomBoard);
        }
        else if(size == 6){
            int randomBoard = new Random().nextInt(bl.retBoards6x6().length);
            tmp = bl.retBoard6x6(randomBoard);
        }
        else if(size == 12){
            int randomBoard = new Random().nextInt(bl.retBoards12x12().length);
            tmp = bl.retBoard12x12(randomBoard);
        }
        else{
            int randomBoard = new Random().nextInt(bl.retBoards9x9().length);
            tmp = bl.retBoard9x9(randomBoard);
        }

        gb.initialInsert(tmp);

    }

    }


