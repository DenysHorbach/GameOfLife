package com.horbach.gameoflife.models;

/**
 * Class that represents game of life universe with alive(stored as 1) and dead(stored as 0) cells.
 */
public class Universe {

    private final int[][] cells;

    public Universe(int size) {
        cells = new int[size][size];
    }

    public Universe(int[][] cells) {
        this.cells = cells;
    }

    public int[][] getCells() {
        return cells;
    }
}
