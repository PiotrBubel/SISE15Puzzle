package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.Heuristic;

import java.io.PrintStream;
import java.util.List;

/**
 * A* - algorytm 'najpierw najlepszy' z poglebieniem iteracyjnym
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 */
public class IterativeAStarSearch extends PuzzleSolver {

    public IterativeAStarSearch(Heuristic heuristicFunction, int depth) {
        super();
        maxDepth = depth;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    public IterativeAStarSearch(Heuristic heuristicFunction) {
        super();
        maxDepth = DEFAULT_MAX_DEPTH * 200;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {    //FIXME

        int depth = 1;
        Board solved = null;

        this.time = System.nanoTime();
        this.createdBoards = 0;
        Board toSolve = new Board(unsolved);

        while (depth <= maxDepth && solved == null) {
            PuzzleSolver.CREATED_BOARDS = 0;
            solved = toSolve.findAnswerWithIDA(heuristicFunction, depth, stream);
            this.createdBoards = this.createdBoards + CREATED_BOARDS;
            depth++;
        }
        this.time = System.nanoTime() - time;
        return solved;
    }
}