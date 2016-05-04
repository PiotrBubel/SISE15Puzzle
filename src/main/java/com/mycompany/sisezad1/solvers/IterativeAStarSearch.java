package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;
import java.util.Comparator;

/**
 * A* - algorytm 'najpierw najlepszy' z poglebieniem iteracyjnym
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 */
public class IterativeAStarSearch extends PuzzleSolver {

    int maxDepth;

    public IterativeAStarSearch(Comparator heuristicFunction, int depth) {
        maxDepth = depth;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    public IterativeAStarSearch(Comparator heuristicFunction) {
        maxDepth = DEFAULT_MAX_DEPTH;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        int depth = 1;
        PuzzleSolver aStarSolver;
        Board solved = null;
        if (stream == null) {
            stream = System.out;
        }
        this.time = System.nanoTime();
        PuzzleSolver.CREATED_BOARDS = 0;
        this.createdBoards = 0;
        while (depth <= maxDepth && solved == null) {
            aStarSolver = new AStarSearch(this.heuristicFunction, depth);
            Board toSolve = new Board(unsolved);
            solved = aStarSolver.solve(toSolve, stream);
            this.createdBoards = +aStarSolver.getCreatedBoards();
            depth++;
        }
        this.time = System.nanoTime() - time;
        return solved;
    }
}