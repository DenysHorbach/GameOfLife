package com.horbach.gameoflife.models;

import com.horbach.gameoflife.services.UniverseService;

/**
 * Class to start game of life.
 */
public class GameOfLife {

    /**
     * The size of the universe where game will be simulated.
     */
    private final int universeSize;

    /**
     * The quantity of iterations of universe simulation.
     */
    private final int iterationsQuantity;

    private final UniverseService universeService = new UniverseService();


    public GameOfLife(int universeSize, int iterationsQuantity) {
        this.universeSize = universeSize;
        this.iterationsQuantity = iterationsQuantity;
    }

    /**
     * Starts the game.
     */
    public void start() {
        Universe universe = universeService.generateUniverseWithGlider(this.universeSize);

        for (int i = 0; i < this.iterationsQuantity; i++) {
            final Universe updatedUniverse = universeService.generateUniverseIteration(universe);
            universe = updatedUniverse;
            System.out.printf("Iteration №%s%n", i + 1);
            universeService.printUniverse(updatedUniverse);
        }
    }
}
