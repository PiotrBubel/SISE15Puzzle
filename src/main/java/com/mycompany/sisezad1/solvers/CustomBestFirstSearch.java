package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.Heuristic;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Best-first search - algorytm 'najpierw najlepszy'
 *
 *
 * Rozwijany jest "najlepszy" węzeł spośród węzłów rozpatrywanych do tej pory. Zakłada się, że węzeł
 * najbardziej obiecujący ma najmniejszą wartość funkcji heurystycznej.
 *
 * Brak zapamietywania calego grafu, nie przechodzi przez graf, do optymalnego dzialania wymaga
 * wlaczonego STRONG_LOOP_CONTROL
 */
public class CustomBestFirstSearch extends HeuristicSolver {

    public CustomBestFirstSearch(Heuristic heuristicFunction) {
        super(heuristicFunction);
        this.maxDepth = DEFAULT_MAX_DEPTH * 20;
    }

    public CustomBestFirstSearch(Heuristic heuristicFunction, int maxDepth) {
        super(heuristicFunction);
        this.maxDepth = maxDepth * 20;
    }

    @Override
    public Board solve(Board unsolved, PrintStream stream) {
        boolean tmp = Board.STRONG_LOOP_CONTROL;
        Board.STRONG_LOOP_CONTROL = true;
        this.firstBoard = new Board(unsolved);
        int steps = 0;
        PuzzleSolver.CREATED_BOARDS = 0;
        Board current = new Board(unsolved.getState());
        Board last = null;
        String deadlockCause = "x";
        List<Board> nextCombinations;

        this.createdBoards = 0;

        this.time = System.nanoTime();
        while (!current.isCorrect()) {

            nextCombinations = getNextCombinations(current, deadlockCause);

            if (nextCombinations.isEmpty()) {
                //undo last move, then try again, but now without going same direction
                deadlockCause = current.getPath()
                        .substring(current.getPath().length() - 1)
                        .toLowerCase();
                //System.out.println("CBF: deadlock caused by: " + deadlockCause + " going back one move");
                current = new Board(last);
                nextCombinations = getNextCombinations(current, deadlockCause);
                deadlockCause = "x";
            }

            last = new Board(current);
            current = chooseBest(nextCombinations, heuristicFunction);
            if (stream != null && !current.getPath().isEmpty() && current.getPath() != null) {
                stream.println(current.getPath());
            }
            nextCombinations.clear();
            steps++;
            //System.out.println("steps: " + steps);
            if (steps == maxDepth) {
                this.createdBoards = maxDepth;
                Board.STRONG_LOOP_CONTROL = tmp;
                return null;
            }
        }
        this.createdBoards = current.getPath().length();
        this.time = System.nanoTime() - time;
        Board.STRONG_LOOP_CONTROL = tmp;
        return current;
    }

    private List<Board> getNextCombinations(Board current, String deadlockCause) {
        List<Board> nextCombinations = new ArrayList<>();
        if (deadlockCause != Board.DOWN_CHAR && current.canMoveDown()) {
            nextCombinations.add(new Board(current).moveDown());
            this.createdBoards++;
        }
        if (deadlockCause != Board.UP_CHAR && current.canMoveUp()) {
            nextCombinations.add(new Board(current).moveUp());
            this.createdBoards++;
        }
        if (deadlockCause != Board.RIGHT_CHAR && current.canMoveRight()) {
            nextCombinations.add(new Board(current).moveRight());
            this.createdBoards++;
        }
        if (deadlockCause != Board.LEFT_CHAR && current.canMoveLeft()) {
            nextCombinations.add(new Board(current).moveLeft());
            this.createdBoards++;
        }

        return nextCombinations;
    }

    /**
     * Method chooses best Board from given list. Comparator acts here as heuristic function.
     *
     * @return heuristically best Board
     */
    private Board chooseBest(List<Board> combinations, Comparator<Board> heuristic) {
        Collections.sort(combinations, heuristic);
        return new Board(combinations.get(0));
    }
}