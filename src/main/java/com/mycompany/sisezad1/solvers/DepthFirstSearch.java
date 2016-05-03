package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;


/**
 * DFS Depth-first search - algorytm przeszukiwania wgłąb
 *
 * Przeszukiwanie w głąb polega na badaniu wszystkich krawędzi wychodzących z podanego wierzchołka.
 * Po zbadaniu wszystkich krawędzi wychodzących z danego wierzchołka algorytm powraca do
 * wierzchołka, z którego dany wierzchołek został odwiedzony
 */
public class DepthFirstSearch extends PuzzleSolver {

    int maxDepth;

    public DepthFirstSearch() {
        super();
        maxDepth = 10;
    }

    public DepthFirstSearch(String order) {
        super(order);
        maxDepth = 10;
    }

    public DepthFirstSearch(String order, int depth) {
        super(order);
        maxDepth = depth - 1;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        if(stream == null){
            stream = System.out;
        }

        this.time = System.nanoTime();

        Board correct = unsolved.findAnswerWithDFS(order, maxDepth, stream); //rekurencyjnie, dlatego w klasie Board
        this.time = time - System.nanoTime();
        return correct;
    }
}
