package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym, nie liczy zera
 *
 * Powinno byc uzywane do Best-first (podane do A* sprawia, ze A* dziala jak best-first)
 */
public class MisplacedComparator implements Heuristic {

    @Override
    public int compare(Board b1, Board b2) {
        return (this.heuristicValue(b1)) - (this.heuristicValue(b2));
    }

    public int heuristicValue(Board b) {

        int[][] state = b.getState();
        int misplaced = 0;
        int correctValue = 1;

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if (x == state.length - 1 && y == state[0].length - 1) {
                    break;
                }
                if (state[x][y] != correctValue) {
                    misplaced++;
                }
                correctValue++;
            }
        }
        return misplaced;
    }
}