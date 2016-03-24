/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 *
 * BFS Breadth-first search - algorytm przeszukiwania wszerz
 *
 *
 * Przechodzenie grafu rozpoczyna się od zadanego wierzchołka s i polega na
 * odwiedzeniu wszystkich osiągalnych z niego wierzchołków. Wynikiem działania
 * algorytmu jest drzewo przeszukiwania wszerz o korzeniu w s, zawierające
 * wszystkie wierzchołki osiągalne z s. Do każdego z tych wierzchołków prowadzi
 * dokładnie jedna ścieżka z s, która jest jednocześnie najkrótszą ścieżką w
 * grafie wejściowym.
 */
public class BreadthFirstSearch extends PuzzleSolver {

    public BreadthFirstSearch() {
    }

    public BreadthFirstSearch(String order) {
        super(order);
    }

    @Override
    public void solve(Board unsolved) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
