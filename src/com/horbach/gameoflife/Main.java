package com.horbach.gameoflife;

import com.horbach.gameoflife.models.GameOfLife;

public class Main {
    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife(25, 25);
        gameOfLife.start();
    }
}