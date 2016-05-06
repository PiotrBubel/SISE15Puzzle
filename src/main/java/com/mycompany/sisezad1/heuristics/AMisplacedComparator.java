package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.util.Comparator;

/**
 * Ocena heurystyczna: liczba elementow poza swoim polozeniem docelowym + liczba ruchow od startu
 * (tym lepsze czym wiecej plytek na swoim miejscu, tym gorsze czym wiecej ruchow potrzebuje), nie
 * liczy zera
 *
 * Powinno byc uzywane do A* i IDA*
 */
public class AMisplacedComparator extends MisplacedComparator {

    @Override
    public int compare(Board b1, Board b2) {
        return (this.heuristicValue(b1)) - (this.heuristicValue(b2));
    }

    public int heuristicValue(Board b) {
        return super.heuristicValue(b) + b.getPath().length();
    }
}