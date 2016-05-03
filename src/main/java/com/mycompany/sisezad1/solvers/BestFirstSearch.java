package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Best-first search - algorytm 'najpierw najlepszy'
 *
 *
 * Rozwijany jest "najlepszy" węzeł spośród węzłów rozpatrywanych do tej pory, niezależnie od ich
 * położenia w grafie. Zakłada się, że węzeł najbardziej obiecujący ma najmniejszą wartość funkcji
 * heurystycznej.
 */
public class BestFirstSearch extends PuzzleSolver {

    //TODO zapobieganie zapętleniom?

    public int maxSteps;
    private List<Board> nextCombinations;
    private Comparator heuristicFunction;

    public BestFirstSearch(Comparator heuristicFunction) {
        super();
        nextCombinations = new ArrayList<>();
        this.heuristicFunction = heuristicFunction;
        this.maxSteps = 5000;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        this.firstBoard = new Board(unsolved);
        if(stream == null){
            stream = System.out;
        }
        this.time = System.nanoTime();
        int steps = 0;

        Board current = unsolved;
        this.time = System.nanoTime();
        while (!current.isCorrect()) {
            if (current.canMoveDown()) {
                nextCombinations.add(new Board(current).moveDown());
            }
            if (current.canMoveUp()) {
                nextCombinations.add(new Board(current).moveUp());
            }
            if (current.canMoveRight()) {
                nextCombinations.add(new Board(current).moveRight());
            }
            if (current.canMoveLeft()) {
                nextCombinations.add(new Board(current).moveLeft());
            }
            for (Board b : nextCombinations) {
                System.out.println(b.getPath());
            }
            current = chooseBest(nextCombinations, heuristicFunction);
            stream.println(current.getPath());
            nextCombinations.clear();
            steps++;
            //System.out.println("steps: " + steps);
            if (steps == maxSteps) {
                System.out.println("zbyt duza liczba kombinacji");
                return null;
            }
        }

        this.time = time - System.nanoTime();
        return current;
    }

    /**
     * Method chooses best Board from given list. Comparator acts here as heuristic function.
     *
     * @return heuristically best Board
     */
    private Board chooseBest(List<Board> combinations, Comparator heuristic) {
        Collections.sort(combinations, heuristic);
        return new Board(combinations.get(0));
    }
}