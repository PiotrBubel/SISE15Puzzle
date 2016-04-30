package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.util.Stack;

import sun.misc.VM;

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
    public Board solve(Board unsolved) {
        this.time = System.nanoTime();

        Board correct = unsolved.findAnswerWithDFS(order, maxDepth); //rekurencyjnie, dlatego w klasie Board
        //TODO zapobieganie zapetleniom? nie wiem czy jest wg potrzebne przy tym sposobie
        this.time = time - System.nanoTime();
        return correct;
    }
}
