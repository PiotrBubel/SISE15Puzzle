package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 * iDFS iterative Depth-first search - algorytm przeszukiwania wgłąb z pogłębianiem iteracyjnym
 *
 * Przeszukiwanie w głąb polega na badaniu wszystkich krawędzi wychodzących z podanego wierzchołka.
 * Po zbadaniu wszystkich krawędzi wychodzących z danego wierzchołka algorytm powraca do
 * wierzchołka, z którego dany wierzchołek został odwiedzony
 */
public class IterativeDepthFirstSearch extends PuzzleSolver {

    int maxDepth;

    public IterativeDepthFirstSearch(String order) {
        super(order);
        maxDepth = 10;
    }

    public IterativeDepthFirstSearch(String order, int depth) {
        super(order);
        maxDepth = depth;
    }

    public IterativeDepthFirstSearch(int depth) {
        super();
        maxDepth = depth;
    }

    //de facto to jest prawie wyszukiwanie wszerz (BFS), tylko nie trzyma wszystkiego na raz w pamieci
    @Override
    public Board solve(Board unsolved) {
        int depth = 1;
        PuzzleSolver depthFirstSolver;
        Board solved = null;

        this.time = System.nanoTime();

        while (depth <= maxDepth && solved == null) {
            depthFirstSolver = new DepthFirstSearch(this.order, depth);
            Board toSolve = new Board(unsolved);
            solved = depthFirstSolver.solve(toSolve);
            depth++;
        }
        this.time = time - System.nanoTime();
        return solved;
    }

}
