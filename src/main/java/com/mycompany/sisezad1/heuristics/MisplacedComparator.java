package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.util.Comparator;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym
 *
 * Powinno byc uzywane do Best-first (podane do A* sprawia, ze A* dziala jak best-first)
 */
public class MisplacedComparator implements Comparator<Board> {

    @Override
    public int compare(Board b1, Board b2) {
        int b1Misplaced = BoardUtils.countMisplaced(b1);
        int b2Misplaced = BoardUtils.countMisplaced(b2);

        return b1Misplaced - b2Misplaced;
    }
}