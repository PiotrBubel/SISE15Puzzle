package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.util.Comparator;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym, nie liczy zera
 *
 * Powinno byc uzywane do Best-first (podane do A* sprawia, ze A* dziala jak best-first)
 */
public class MisplacedComparator implements Comparator<Board> {

    @Override
    public int compare(Board b1, Board b2) {
        int b1Misplaced = this.countMisplacedWithout0(b1);
        int b2Misplaced = this.countMisplacedWithout0(b2);

        return b1Misplaced - b2Misplaced;
    }

    protected int countMisplacedWithout0(Board b) {

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