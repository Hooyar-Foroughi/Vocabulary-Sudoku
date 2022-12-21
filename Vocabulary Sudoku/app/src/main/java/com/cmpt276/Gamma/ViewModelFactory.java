package com.cmpt276.Gamma;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class ViewModelFactory implements ViewModelProvider.Factory {
    int gridSize;
    ArrayList<wordPair> Vocabulary;

    public ViewModelFactory(int size,ArrayList<wordPair> finalWords){
        this.Vocabulary = finalWords;
        this.gridSize = size;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        gameBoardInitial gbI = new gameBoardInitial(gridSize,Vocabulary);
        return (T) gbI;
    }
}
