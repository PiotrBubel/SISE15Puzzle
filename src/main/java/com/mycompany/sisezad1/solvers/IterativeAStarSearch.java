package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 * A* - algorytm 'najpierw najlepszy' z poglebieniem iteracyjnym
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 */
public class IterativeAStarSearch extends PuzzleSolver {

    int maxDepth;

    public IterativeAStarSearch() {
        super();
        maxDepth = 10;
    }

    public IterativeAStarSearch(String order, int depth) {
        super(order);
        maxDepth = depth;
    }

    public IterativeAStarSearch(String order) {
        super(order);
        maxDepth = 10;
    }

    //TODO test after implementation of AStar
    @Override
    public Board solve(Board unsolved) {
        int depth = 1;
        PuzzleSolver aStarSolver;
        Board solved = null;

        this.time = System.nanoTime();

        while (depth != maxDepth || solved == null) {
            aStarSolver = new AStarSearch(this.order, depth);
            Board toSolve = new Board(unsolved);
            solved = aStarSolver.solve(toSolve);
            depth++;
        }
        this.time = time - System.nanoTime();
        return solved;
    }
}