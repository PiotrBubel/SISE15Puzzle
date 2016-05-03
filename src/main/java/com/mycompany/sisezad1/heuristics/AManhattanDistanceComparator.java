package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;

/**
 * Ocena heurystyczna: suma dystansu kazdego elementu od wlasciwego miejsca (obliczane metryka
 * Manhattan) Dystans nie jest obliczany dla 0, plus ilosc ruchow potrzebnych do osiagniecia danego
 * stanu
 *
 * Powinno byc uzywane do A* i IDA*
 */
public class AManhattanDistanceComparator extends ManhattanDistanceComparator {
    @Override
    public int compare(Board b1, Board b2) {
        return (this.sumDistance(b1) + b1.getPath().length()) - (this.sumDistance(b2) + b2.getPath().length());
    }
}
