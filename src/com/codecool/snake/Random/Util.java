package com.codecool.snake.Random;

import java.util.Random;

public class Util {

    private static Random random=new Random();

    public static String getRandomBerryName() {
        String[] berryNames = {"ExtraBerry1", "ExtraBerry2", "ExtraBerry3", "ExtraBerry4", "5"};
        return berryNames[random.nextInt(berryNames.length-1)];
    }

}
