package com.mycompany.sisezad1.heuristics;

import com.mycompany.sisezad1.Board;

import java.util.Comparator;

/**
 * Ocena heurystyczna: suma dystansu kazdego elementu od wlasciwego miejsca (obliczane metryka
 * Manhattan) Dystans nie jest obliczany dla 0, plus ilosc ruchow potrzebnych do osiagniecia danego
 * stanu
 */
public class ManhattanDistanceComparator implements Comparator<Board> {

    @Override
    public int compare(Board b1, Board b2) {
        return (this.sumDistance(b1) + b1.getPath().length()) - (this.sumDistance(b2) + b2.getPath().length());
    }

    private int sumDistance(Board b) {
        int sum = 0;
        int[][] state = b.getState();
        for (int[] line : state) {
            for (int i : line) {
                sum = sum + getManhattanDistance(b, i);
            }
        }
        //System.out.println(sum);
        return sum;
    }

    private int getManhattanDistance(Board b, int val) {
        int x = b.findNumber(val)[0];
        int y = b.findNumber(val)[1];
        int distance = 0;

        if (val != 0) {
            //FIXME works only when board is symmetrical
            int properX = (int) Math.floor(((double) val - 1) / (double) (b.getState().length));
            int properY = (val - 1) % b.getState()[0].length;
            distance = Math.abs(properX - x) + Math.abs(properY - y);
        }

        return distance;
    }
}