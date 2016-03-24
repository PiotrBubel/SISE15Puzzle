package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 *
 * iDFS iterative Depth-first search - algorytm przeszukiwania wgłąb z
 * pogłębianiem iteracyjnym
 *
 * Przeszukiwanie w głąb polega na badaniu wszystkich krawędzi wychodzących z
 * podanego wierzchołka. Po zbadaniu wszystkich krawędzi wychodzących z danego
 * wierzchołka algorytm powraca do wierzchołka, z którego dany wierzchołek
 * został odwiedzony
 */
public class IterativeDepthFirstSearch extends PuzzleSolver {

    public IterativeDepthFirstSearch(String order) {
        super(order);
    }

    public IterativeDepthFirstSearch() {
    }

    @Override
    public void solve(Board unsolved) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
