package com.cmpt276.Gamma;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class fileReader extends Activity
{

    private final String fname;
    private final Context ctx;

    fileReader()
    {
        fname = "Words1";
        ctx = getApplicationContext();
    }

    fileReader(String name,Context context)
    {
        fname = name;
        ctx = context;
    }

    public void writeWords(ArrayList<wordPair> words)
    {
        // Get all words necessary to write to a file into string format
        StringBuilder ret = new StringBuilder();
        for (wordPair word : words) {
            ret.append(word.Eword).append(",").append(word.Sword).append("\n");
            Log.v("result of words","writing"+word.Eword);
        }

        File file = new File(ctx.getFilesDir(), fname + ".txt");
        // Attempt to write string into given file
        try {
            FileOutputStream s = new FileOutputStream(file);
            s.write(ret.toString().getBytes(StandardCharsets.UTF_8));
            s.close();
        }
        // Catch any errors
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   public ArrayList<wordPair> parseWords()
    {
        // Open the file, read into string
        StringBuilder txt = new StringBuilder();
        File file = new File(ctx.getFilesDir(), fname + ".txt");
        if (!file.exists()){
            return new ArrayList<>();
        }
        try{
            // Create new buffer
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            // While the file is valid, read into StringBuilder
            while ((line = br.readLine()) != null)
            {
                txt.append(line);
                txt.append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Split the string into a set of wordPairs
        if (txt.length()==0){
            return new ArrayList<>();
        }
        else {
            return parseString(txt.toString());
        }
    }

    private ArrayList<wordPair> parseString(String s)
    {
        // Creates ret Arraylist of wordPairs
        ArrayList<wordPair> ret = new ArrayList<wordPair>();

        // Split the string into component pairs
        String[] stringSet = s.split("\n");
        String[] tmpL;
        String tmp;

        // Split each component pair into parts
        for(int i = 0; i < stringSet.length; i++)
        {
            tmp = stringSet[i];
            tmpL = tmp.split(",");
            // Add new wordPair to ret
            ret.add(i,new wordPair(tmpL[0], tmpL[1], i));
        }
        return ret;
    }
}
