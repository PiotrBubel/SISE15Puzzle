package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.heuristics.Heuristic;

/**
 * Created by Piotrek on 14.05.2016.
 */
public abstract class HeuristicSolver extends PuzzleSolver {
    protected Heuristic heuristicFunction; //nieuzywane w metodach nie-heurystycznych

    public HeuristicSolver() {
        super();
    }

    public HeuristicSolver(int depth) {
        super(depth);
    }

    public Heuristic getHeuristicFunction() {
        return this.heuristicFunction;
    }

}
