package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class AStarSearch extends PuzzleSolver {

    private List<Board> uncheckedNodes;
    private List<Board> checkedNodes;
    private List<Board> newNodes;

    public AStarSearch() {
        super();
        maxDepth = DEFAULT_MAX_DEPTH;
        this.heuristicFunction = new MisplacedComparator();
        this.createdBoards = 0;
    }

    public AStarSearch(Comparator<Board> heuristicFunction, int depth) {
        //maxDepth = depth - 1;//jak rekurencyjnie
        maxDepth = depth;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    public AStarSearch(Comparator<Board> heuristicFunction) {
        maxDepth = DEFAULT_MAX_DEPTH;
        this.heuristicFunction = heuristicFunction;
        this.createdBoards = 0;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {

        uncheckedNodes = new ArrayList<>();
        checkedNodes = new ArrayList<>();
        newNodes = new ArrayList<>();
        Board current;
        PuzzleSolver.CREATED_BOARDS = 0;
        this.createdBoards = 0;

        this.time = System.nanoTime();

        // Pierwszy wierzchołek do sprawdzenia
        uncheckedNodes.add(new Board(unsolved));

        while (!uncheckedNodes.isEmpty()) {
            //pobieranie pierwszego wierzcholka z listy niesprawdzonych
            current = uncheckedNodes.get(0);
            // Sprawdzenie tylko najlepszych heurystycznie stanow
            newNodes = current.getBestStates(heuristicFunction);
            this.createdBoards = this.createdBoards + newNodes.size();
            // usuniecie powtarzajacych sie wierzcholkow (zeby nie zapetlilo)
            newNodes = newNodesWithoutChecked();

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

            if (countActualDepth(current) > maxDepth) {
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

    private List<Board> newNodesWithoutChecked() {
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

    private int countActualDepth(Board current) {
        int depth = 0;
        Board parent = current.getParentNode();
        while (parent != null) {
            depth++;
            parent = parent.getParentNode();
        }
        return depth;
    }

    /*
    public Board solveRe(Board unsolved, PrintStream stream) {
        //połączenie depth first z heurestyką, rozwijany jest węzeł o najlepszej heurestyce,
        //rozni sie od best first, ze zapisuje wyniki w grafie
        //rozni sie od dfs tym, ze kolejnosc nie jest podana ani losowa, ale heurystyczna

        if (stream == null) {
            stream = System.out;
        }

        this.time = System.nanoTime();
        PuzzleSolver.CREATED_BOARDS = 0;
        Board correct = unsolved.findAnswerWithAStar(heuristicFunction, maxDepth, stream); //rekurencyjnie, dlatego w klasie Board
        this.createdBoards = PuzzleSolver.CREATED_BOARDS;
        this.time = System.nanoTime() - time;
        return correct;
    }
    */
}

