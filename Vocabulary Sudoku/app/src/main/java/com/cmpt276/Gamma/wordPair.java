package com.cmpt276.Gamma;

import java.io.Serializable;

public class wordPair implements Serializable
{
    public String Eword;
    public String Sword;
    public int subInt;

    wordPair(String e, String s, int i)
    {
        Eword = e;
        Sword = s;
        subInt = i;
    }
}

