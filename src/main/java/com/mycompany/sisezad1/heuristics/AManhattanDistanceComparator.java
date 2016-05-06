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
        return (this.heuristicValue(b1)) - (this.heuristicValue(b2));
    }

    public int heuristicValue(Board b){
        return super.heuristicValue(b) + b.getPath().length();
    }
}
