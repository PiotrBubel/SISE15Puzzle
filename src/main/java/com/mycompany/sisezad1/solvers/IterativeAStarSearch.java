package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.util.Comparator;

/**
 * A* - algorytm 'najpierw najlepszy' z poglebieniem iteracyjnym
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 */
public class IterativeAStarSearch extends PuzzleSolver {

    int maxDepth;
    private Comparator heuristicFunction;

    public IterativeAStarSearch(Comparator heuristicFunction, int depth) {
        maxDepth = depth;
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public Board solve(Board unsolved) {
        int depth = 1;
        PuzzleSolver aStarSolver;
        Board solved = null;

        this.time = System.nanoTime();

        while (depth <= maxDepth && solved == null) {
            aStarSolver = new AStarSearch(this.heuristicFunction, depth);
            Board toSolve = new Board(unsolved);
            solved = aStarSolver.solve(toSolve);
            depth++;
        }
        this.time = time - System.nanoTime();
        return solved;
    }
}