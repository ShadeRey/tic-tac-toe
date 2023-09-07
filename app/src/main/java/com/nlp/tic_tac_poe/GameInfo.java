package com.nlp.tic_tac_poe;

public class GameInfo {
    public static int [][] winCombination = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,4,8},{2,4,6},
            {0,3,6},{1,4,7},{2,5,8}
    };
    public static String firstPlayerSymbol = "X";
    public static String secondPlayerSymbol = "O";
    public static boolean whoIsNext = true;

}
