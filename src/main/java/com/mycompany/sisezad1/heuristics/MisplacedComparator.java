package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.util.Comparator;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym
 */
public class MisplacedComparator implements Comparator<Board> {

    @Override
    public int compare(Board b1, Board b2) {
        int b1Misplaced = BoardUtils.countMisplaced(b1);  //5
        int b2Misplaced = BoardUtils.countMisplaced(b2);  //0

        return b1Misplaced - b2Misplaced;
    }
}