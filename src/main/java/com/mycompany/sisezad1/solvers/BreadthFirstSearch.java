/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
public class BreadthFirstSearch extends NonHeuristicSolver {

    // Robie na listach ale mozna tez na kolejce FIFO
    private List<Board> uncheckedNodes;
    private List<Board> checkedNodes;
    private List<Board> newNodes;

    public BreadthFirstSearch() {
        super();
    }

    public BreadthFirstSearch(String order) {
        super(order);
    }

    public BreadthFirstSearch(String order, int maxDepth) {
        super(order, maxDepth);
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        uncheckedNodes = new ArrayList<>(); //roman
        checkedNodes = new ArrayList<>();   //andrzej
        newNodes = new ArrayList<>();
        Board current;
        PuzzleSolver.CREATED_BOARDS = 0;
        this.createdBoards = 0;

        this.time = System.nanoTime();

        // Pierwszy wierzchołek do sprawdzenia 
        uncheckedNodes.add(new Board(unsolved.getState()));

        while (!uncheckedNodes.isEmpty()) {
            //pobieranie pierwszego wierzcholka z listy niesprawdzonych
            current = uncheckedNodes.get(0);
            // Sprawdzenie mozliwych stanow wg kolejnosci
            newNodes = current.getPossibleStates(this.order);
            this.createdBoards = this.createdBoards + newNodes.size();

            // usuniecie powtarzajacych sie wierzcholkow (zeby nie zapetlilo)
            newNodes = removeChecked();
            uncheckedNodes.addAll(newNodes);
            // Sprawdzenie aktualnego wiercholka 
            if (stream != null && !current.getPath().isEmpty() && current.getPath() != null) {
                stream.println(current.getPath());
            }
            if (current.isCorrect()) {
                this.time = System.nanoTime() - time;
                return current;
            }
            // Dodanie do listy sprawdzonych, usuniecie z niesprawdzonych
            addToChecked(current);

            if (current.getPath().length() > maxDepth) {
                //System.out.println("Za duza głebokosc");
                // To raczej nie powinno sie zdarzyc i pewnie mozna to usunac
                // Bo jak nie znajdzie to petla while sie skonczy wiec mozliwe
                // ze glebokosc tu jest wgl niepotrzebna.
                break;
            }
        }
        //System.out.println("Nie znaleziono rozwiazania");

        this.time = System.nanoTime() - time;
        return null;
    }

    private List<Board> removeChecked() {
        List<Board> tmp = new ArrayList();
        boolean isChecked = false;
        for (Board newNode : newNodes) {
            for (Board checkedNode : checkedNodes) {
                if (newNode.equals(checkedNode)) {
                    isChecked = true;
                }
            }
            if (isChecked) {
                isChecked = false;
            } else {
                tmp.add(newNode);
            }
        }
        return tmp;
    }

    private void addToChecked(Board current) {
        checkedNodes.add(current);
        uncheckedNodes.remove(current);
    }
}
