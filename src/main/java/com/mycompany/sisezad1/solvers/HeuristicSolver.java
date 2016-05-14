package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.heuristics.Heuristic;
import com.mycompany.sisezad1.heuristics.MisplacedComparator;

/**
 * Created by Piotrek on 14.05.2016.
 */
public abstract class HeuristicSolver extends PuzzleSolver {
    protected Heuristic heuristicFunction; //nieuzywane w metodach nie-heurystycznych

    public HeuristicSolver() {
        super();
        this.heuristicFunction = new MisplacedComparator();
    }

    public HeuristicSolver(int depth) {
        super(depth);
        this.heuristicFunction = new MisplacedComparator();
    }

    public HeuristicSolver(Heuristic h) {
        super();
        this.heuristicFunction = h;
    }

    public HeuristicSolver(int depth, Heuristic h) {
        super(depth);
        this.heuristicFunction = h;
    }

    public Heuristic getHeuristicFunction() {
        return this.heuristicFunction;
    }

}
