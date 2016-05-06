package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;

import java.util.Comparator;

/**
 * Created by Piotrek on 06.05.2016.
 */
public interface Heuristic extends Comparator<Board>{
    int heuristicValue(Board board);
    int compare(Board b1, Board b2);
}
