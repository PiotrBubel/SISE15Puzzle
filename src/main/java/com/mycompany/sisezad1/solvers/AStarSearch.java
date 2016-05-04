package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.*;

import java.io.PrintStream;
import java.util.Comparator;

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
    int maxDepth;
    private Comparator heuristicFunction;

    public AStarSearch() {
        super();
        maxDepth = 10;
        this.heuristicFunction = new MisplacedComparator();
    }

    public AStarSearch(Comparator heuristicFunction, int depth) {
        maxDepth = depth - 1;
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        //połączenie depth first z heurestyką, rozwijany jest węzeł o najlepszej heurestyce,
        //rozni sie od best first, ze zapisuje wyniki w grafie
        //rozni sie od dfs tym, ze kolejnosc nie jest podana ani losowa, ale heurystyczna

        if (stream == null) {
            stream = System.out;
        }

        this.time = System.nanoTime();

        Board correct = unsolved.findAnswerWithAStar(heuristicFunction, maxDepth, stream); //rekurencyjnie, dlatego w klasie Board

        this.time = time - System.nanoTime();
        return correct;
    }

}
