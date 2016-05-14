package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.Heuristic;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A* - algorytm 'najpierw najlepszy'
 *
 *
 * Celem tej strategii jest wyznaczenie najtańszej drogi w grafie.
 *
 * A* do dzialania wymaga komparatora A, gdy podany zostanie komparator nie-A dziala jak zwykle
 * best-first
 */
public class BestFirstSearch extends HeuristicSolver {

    List<Board> uncheckedNodes;
    List<Board> checkedNodes;

    public BestFirstSearch() {
        super();
    }

    public BestFirstSearch(Heuristic heuristicFunction, int depth) {
        super(depth, heuristicFunction);
    }

    public BestFirstSearch(Heuristic heuristicFunction) {
        super(heuristicFunction);
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {

        uncheckedNodes = new ArrayList<>();
        checkedNodes = new ArrayList<>();
        List<Board> newNodes = new ArrayList<>();
        Board current;
        PuzzleSolver.CREATED_BOARDS = 0;
        this.createdBoards = 0;
        this.time = System.nanoTime();

        // Pierwszy wierzchołek do sprawdzenia
        uncheckedNodes.add(new Board(unsolved.getState()));

        while (!uncheckedNodes.isEmpty()) {
            //wstawianie najlepszego heurystycznie wierzcholka na pierwsze miejsce
            Collections.sort(uncheckedNodes, heuristicFunction);
            //pobieranie pierwszego wierzcholka z listy niesprawdzonych
            current = uncheckedNodes.get(0);
            //sprawdzenie czy aktualna glebokosc jest wieksza niz maksymalna, nie musi tego byc
            if (current.getPath().length() > maxDepth) {
                this.time = System.nanoTime() - time;
                return null;
            }
            // Sprawdzenie aktualnego wierzcholka
            if (stream != null && !current.getPath().isEmpty() && current.getPath() != null) {
                stream.println(current.getPath());
            }
            if (current.isCorrect()) {
                this.time = System.nanoTime() - time;
                return current;
            }
            // Dodanie do listy sprawdzonych, usuniecie z niesprawdzonych
            addToChecked(current);
            //dodanie kolejnych mozliwych stanow
            newNodes = current.getPossibleStates(heuristicFunction);
            this.createdBoards = this.createdBoards + newNodes.size();
            //usuniecie powtarzajacych sie wierzcholkow (zeby nie zapetlilo)
            newNodes = newNodesWithoutChecked(newNodes);

            uncheckedNodes.addAll(newNodes);
        }

        this.time = System.nanoTime() - time;
        return null;
    }

    private List<Board> newNodesWithoutChecked(List<Board> newNodes) {
        List<Board> tmp = new ArrayList<>();
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

