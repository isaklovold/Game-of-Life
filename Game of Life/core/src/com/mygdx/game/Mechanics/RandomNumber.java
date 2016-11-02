package com.mygdx.game.Mechanics;

import java.util.Random;

/**
 * Created by isakl on 30/10/2016.
 */
public class RandomNumber {

    private int randomNumber;
    private Random rand;

    public RandomNumber(int num){
        rand = new Random();
        randomNumber = randomGenerator(num);
    }

    public int randomGenerator(int num){
        int n = rand.nextInt(num);
        System.out.println(n);
        return n;
    }

}
