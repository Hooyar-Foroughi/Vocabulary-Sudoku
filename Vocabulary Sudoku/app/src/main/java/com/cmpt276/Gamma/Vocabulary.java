package com.cmpt276.Gamma;

import java.util.SplittableRandom;

public class Vocabulary {
    // Vocabulary (in the future, we will pull this information from a txt or similar randomly)
    private String[][] Vocabularylist = {
            {"Hello", "Hola"},
            {"Please", "Por favor"},
            {"Good", "Bien"},
            {"Red", "Rojo"},
            {"Green", "Verde"},
            {"Like", "Gusta"},
            {"Stop", "Para"},
            {"Happy", "Feliz"},
            {"Cold", "Frio"},
            {"Sorry", "Lo siento"},
            {"Time", "Tiempo"},
            {"Before", "Antes"},
            {"Absolve", "Absolver"},
            {"Sun", "elsol"},
            {"Table", "Mesa"},
            {"Accustom", "Acostumbrarse"},
            {"Rain", "Lluvia"},
            {"Chair", "Silla"},
            {"Phone","Teléfono"},
            {"Arrest","Arrestar"},
            //{"Bake","Hornear"},
           // {"Bar","Barrar"},
            {"Behave","Comportarse"},
            {"Betray","Traicionar"},
            {"Bewilder","Desconcertar"}
    };


    public String[][] getVocabulary() {
        return Vocabularylist;
    }

    public String[] getVocabulary(int i) {
        return Vocabularylist[i];
    }
}
