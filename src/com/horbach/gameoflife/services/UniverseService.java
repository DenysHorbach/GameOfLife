package com.horbach.gameoflife.services;

import com.horbach.gameoflife.models.Universe;

import java.util.Arrays;

/**
 * Service to operate with universe by game rules.
 */
public class UniverseService {

    /**
     * Generates init state of universe with Glider pattern.
     * @param size - size of the universe.
     * @return - generated universe.
     */
    public Universe generateUniverseWithGlider(final int size) {
        final Universe universe = new Universe(size);
        final int middle = (universe.getCells().length - 1) / 2;
        universe.getCells()[middle - 1][middle] = 1;
        universe.getCells()[middle][middle + 1] = 1;
        universe.getCells()[middle + 1][middle - 1] = 1;
        universe.getCells()[middle + 1][middle] = 1;
        universe.getCells()[middle + 1][middle + 1] = 1;
        return universe;
    }

    /**
     * Simulates one universe generation.
     * @param universe - initial state of universe.
     * @return - returns copy of universe with applied changes according to rules.
     */
    public Universe generateUniverseIteration(final Universe universe) {
        final Universe universeCopy = copyUniverse(universe);

        for (int i = 0; i < universe.getCells().length; i++) {
            for (int j = 0; j < universe.getCells().length; j++) {
                final int population = calculatePopulationForCell(i, j, universe.getCells());
                final int cellLiveness = identifyCellLiveness(population, i, j, universe.getCells());
                universeCopy.getCells()[i][j] = cellLiveness;
            }
        }
        System.out.println("Initialized universe with Glider pattern:");
        printUniverse(universe);
        return universeCopy;
    }

    /**
     * Prints the state of universe.
     * @param universe - universe to be printed.
     */
    public void printUniverse(final Universe universe) {
        for (int[] ints : universe.getCells()) {
            for (int j = 0; j < universe.getCells().length; j++) {
                System.out.print(prettierCell(ints[j]) + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * Identifies the state of the cell, if it is dead or alive.
     * @param population - quantity of alive cells around current cell.
     * @param i - line cell coordinate.
     * @param j - column cell coordinate.
     * @param universe - universe, which contains current cell.
     * @return - state of the current cell, alive or dead (1 or 0).
     */
    private int identifyCellLiveness(final int population, final int i, final int j, final int[][] universe) {
        int liveness = universe[i][j];
        if (universe[i][j] == 1) {
            if (population == 2 || population == 3) {
                liveness = 1;
            }
            if (population > 3 || population < 2) {
                liveness = 0;
            }
        } else {
            if (population == 3) {
                liveness = 1;
            }
        }
        return liveness;
    }

    /**
     * Calculates quantity of alive cells around current cell.
     * @param i - line cell coordinate.
     * @param j - column cell coordinate.
     * @param universe - universe, which contains current cell.
     * @return - quantity of alive cells around current cell.
     */
    private int calculatePopulationForCell(final int i, final int j, final int[][] universe) {
        final int maxSize = universe.length;
        final int up = i - 1;
        final int down = i + 1;
        final int left = j - 1;
        final int right = j + 1;
        int population = 0;

        if (up >= 0) {
            population += universe[up][j];
            if (left >= 0) {
                population += universe[up][left];
            }
            if (right < maxSize) {
                population += universe[up][right];
            }
        }

        if (down < maxSize) {
            population += universe[down][j];
            if (left >= 0) {
                population += universe[down][left];
            }
            if (right < maxSize) {
                population += universe[down][right];
            }
        }

        if (left >= 0) {
            population += universe[i][left];
        }

        if (right < maxSize) {
            population += universe[i][right];
        }

        return population;

    }

    /**
     * Copies universe.
     * @param universe - universe to be copied.
     * @return - the copy of passed universe.
     */
    private Universe copyUniverse(final Universe universe) {
        final int[][] cells = Arrays.stream(universe.getCells()).map(int[]::clone).toArray(int[][]::new);
        return new Universe(cells);
    }

    /**
     * Replaces cell states 1 and 0 to * and . accordingly when universe is printed.
     * @param cell - value of cell to be displayed.
     * @return - replaced char for cell state.
     */
    private char prettierCell(final int cell) {
        return cell == 0 ? '.' : '*';
    }
}
