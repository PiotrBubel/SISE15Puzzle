package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 * A* - algorytm 'najpierw najlepszy'
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 */
public class AStarSearch extends PuzzleSolver {
    int maxDepth;

    public AStarSearch() {
        super();
        maxDepth = 10;
    }

    public AStarSearch(String order) {
        super(order);
        maxDepth = 10;
    }

    public AStarSearch(String order, int depth) {
        super(order);
        maxDepth = depth - 1;
    }

    @Override
    public Board solve(Board unsolved) {
        //TODO A* implementation
        //podobne do best first, korzysta z heurestyki
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
