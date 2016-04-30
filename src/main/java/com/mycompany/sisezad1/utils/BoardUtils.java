package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class BoardUtils {

    public static void printBoard(Board b) {
        int[][] state = b.getState();
        System.out.println("");
        System.out.println("---------");
        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                System.out.print(state[x][y]);
                if (y < state[0].length - 1) {
                    System.out.print("\t");
                }
            }
            if (x < state.length - 1) {
                System.out.println();
            }
        }
    }

    /**
     * Method checks if state is correct for N-Puzzle It checks if there is every number from 0to N,
     * with no duplicates or negative numbers
     *
     * @param state table with state
     * @return true if state id correct, false otherwise
     */
    public static boolean correctState(int[][] state) {

        List<Integer> intList = new ArrayList<>();
        for (int[] line : state) {
            for (int i : line) {
                intList.add(i);
            }
        }
        Collections.sort(intList);
        for (int i = 0; i < intList.size(); i++) {
            if (i != intList.get(i)) return false;
        }

        return true;
    }

    /**
     * Method randomize order for solving puzzle
     *
     * @return string (w,s,a,d) in random order
     */
    public static String randomizeOrder() {
        List<String> ord = new ArrayList<>();
        ord.add("w");
        ord.add("s");
        ord.add("a");
        ord.add("d");
        String randOrd = new String();
        while (ord.size() > 0) {
            Random r = new Random();
            int i = r.nextInt(ord.size());
            randOrd = randOrd + ord.get(i);
            ord.remove(i);
        }
        return randOrd;
    }

    public static int countMisplaced(Board b) {
        int[][] state = b.getState();
        int misplaced = 0;
        int correctValue = 1;

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if (x == state.length - 1 && y == state[0].length - 1) {
                    correctValue = 0;
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