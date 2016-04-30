package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.util.Comparator;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym + liczba ruchow od startu
 * (tym lepsze czym wiecej plytek na swoim miejscu, tym gorsze czym wiecej ruchow potrzebuje)
 *
 * A* ?? podobne
 */
public class MisplacedDepthComparator implements Comparator<Board> {

    @Override
    public int compare(Board b1, Board b2) {
        int b1HeuristicValue = BoardUtils.countMisplaced(b1) + b1.getPath().length();  //5
        int b2HeuristicValue = BoardUtils.countMisplaced(b2) + b2.getPath().length();  //0

        return b1HeuristicValue - b2HeuristicValue;
    }
}