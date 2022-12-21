package com.cmpt276.Gamma;
import java.util.HashMap;

public class sizeDefs {

    // Hashmaps to store references to size of subboxes
    public HashMap<Integer, Integer> sbX;
    public HashMap<Integer, Integer> sbY;

    sizeDefs()
    {
        sbX = new HashMap<Integer, Integer>();
        sbY = new HashMap<Integer, Integer>();

        // Initialize x subbox size
        sbX.put(4, 2);
        sbX.put(6, 3);
        sbX.put(9, 3);
        sbX.put(12, 4);

        // Initialize y subbox size
        sbY.put(4, 2);
        sbY.put(6, 2);
        sbY.put(9, 3);
        sbY.put(12, 3);
    }
}
