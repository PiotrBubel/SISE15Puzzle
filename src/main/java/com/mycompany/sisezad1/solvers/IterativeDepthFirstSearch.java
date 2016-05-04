package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;

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
        this.createdBoards = 0;
    }

    public IterativeDepthFirstSearch(String order, int depth) {
        super(order);
        maxDepth = depth;
        this.createdBoards = 0;
    }

    public IterativeDepthFirstSearch(int depth) {
        super();
        maxDepth = depth;
        this.createdBoards = 0;
    }

    //de facto to jest prawie wyszukiwanie wszerz (BFS), tylko nie trzyma wszystkiego na raz w pamieci
    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        int depth = 1;
        PuzzleSolver depthFirstSolver;
        Board solved = null;
        if (stream == null) {
            stream = System.out;
        }
        this.time = System.nanoTime();
        PuzzleSolver.CREATED_BOARDS = 0;
        this.createdBoards = 0;

        while (depth <= maxDepth && solved == null) {
            depthFirstSolver = new DepthFirstSearch(this.order, depth);
            Board toSolve = new Board(unsolved);
            solved = depthFirstSolver.solve(toSolve, stream);
            this.createdBoards = +depthFirstSolver.getCreatedBoards();
            depth++;
        }
        this.time = System.nanoTime() - time;
        return solved;
    }

}
