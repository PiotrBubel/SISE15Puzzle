/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.util.List;
import java.util.Stack;

/**
 * BFS Breadth-first search - algorytm przeszukiwania wszerz
 *
 *
 * Przechodzenie grafu rozpoczyna się od zadanego wierzchołka s i polega na odwiedzeniu wszystkich
 * osiągalnych z niego wierzchołków. Wynikiem działania algorytmu jest drzewo przeszukiwania wszerz
 * o korzeniu w s, zawierające wszystkie wierzchołki osiągalne z s. Do każdego z tych wierzchołków
 * prowadzi dokładnie jedna ścieżka z s, która jest jednocześnie najkrótszą ścieżką w grafie
 * wejściowym.
 */
public class BreadthFirstSearch extends PuzzleSolver {

    private int maxDepth;

    public BreadthFirstSearch() {
        super();
    }

    public BreadthFirstSearch(String order) {
        super(order);
        this.maxDepth = 10;
    }

    public BreadthFirstSearch(String order, int maxDepth) {
        super(order);
        this.maxDepth = maxDepth;
    }

    //FIXME
    @Override
    public Board solve(Board unsolved) {
        this.time = System.nanoTime();
        Stack<Board> stack = new Stack<>();
        stack.push(unsolved);

        for (int i = 0; i < maxDepth; i++) {
            List<Board> list = stack.peek().getPossibleStates(this.order);
            for(Board b : list){
                stack.push(b);
            }
        }

        Board correct = unsolved.findAnswerWithBFS(order, maxDepth); //rekurencyjnie, dlatego w klasie Board
        //TODO zapobieganie zapetleniom? nie wiem czy jest wg potrzebne przy tym sposobie

        this.time = time - System.nanoTime();
        return correct;
    }
}