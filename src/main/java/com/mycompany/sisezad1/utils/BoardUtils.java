package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.Heuristic;

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
        System.out.println();

        if(b.getPath() != null && !b.getPath().isEmpty()){
            System.out.println("Moves: " + b.getPath().length());
            System.out.println("Path: " + b.getPath());
        }else{
            System.out.println("No path attached");
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
        ord.add(Board.DOWN_CHAR);
        ord.add(Board.LEFT_CHAR);
        ord.add(Board.RIGHT_CHAR);
        ord.add(Board.UP_CHAR);
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

    /**
     * @return arranged (correct) board of given size
     */
    public static Board buildArrangedBoard(int xState, int yState) {
        Board randomizedBoard = null;
        int[][] state = new int[xState][yState];
        int correctValue = 1;

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if (x == state.length - 1 && y == state[0].length - 1) {
                    correctValue = 0;
                }
                state[x][y] = correctValue;
                correctValue++;
            }
        }
        randomizedBoard = new Board(state);
        return randomizedBoard;
    }

    /**
     * @param board           - arranged board to randomize
     * @param maxMovesToSolve - moves to perform on given board
     * @return board after random moves, board path is cleared
     */
    public static Board randomizeBoard(Board board, int maxMovesToSolve) {
        boolean temp_lc = Board.STRONG_LOOP_CONTROL;
        Board.STRONG_LOOP_CONTROL = false;    //FIXME dont work with strong loop control enabled
        Board randomizedBoard = new Board(board);
        int moves = 0;

        while (moves < maxMovesToSolve) {
            Random rand = new Random();
            int direction = rand.nextInt(4);// % 4;

            switch (direction) {
                case 0:
                    if (randomizedBoard.canMoveUp()) {
                        randomizedBoard = randomizedBoard.moveUp();
                        moves++;
                    }
                    break;
                case 1:
                    if (randomizedBoard.canMoveDown()) {
                        randomizedBoard = randomizedBoard.moveDown();
                        moves++;
                    }
                    break;
                case 2:
                    if (randomizedBoard.canMoveLeft()) {
                        randomizedBoard = randomizedBoard.moveLeft();
                        moves++;
                    }
                    break;
                case 3:
                    if (randomizedBoard.canMoveRight()) {
                        randomizedBoard = randomizedBoard.moveRight();
                        moves++;
                    }
                    break;
            }
        }
        Board.STRONG_LOOP_CONTROL = temp_lc;
        return new Board(randomizedBoard.getState());
    }

    /**
     * @return board of given size, after random moves
     */
    public static Board randomizeBoard(int xState, int yState, int maxMovesToSolve) {
        return BoardUtils.randomizeBoard(BoardUtils.buildArrangedBoard(xState, yState), maxMovesToSolve);
    }

    /**
     * Method returns only best states from given list in heuristic order
     * If more than one state have same, lowest heuristics value, then returns all of them
     */
    public static List<Board> getOnlyBestBoards(List<Board> list, Heuristic heuristics){
        List<Board> possibleStates = new ArrayList<>();
        List<Board> bestStates = new ArrayList<>();

        possibleStates.addAll(list);

        Collections.sort(possibleStates, heuristics);

        bestStates.add(possibleStates.get(0));

        for (int i = 1; i < possibleStates.size(); i++) {
            if(heuristics.compare(bestStates.get(0), possibleStates.get(i)) == 0){
                bestStates.add(possibleStates.get(i));
            }
        }

        return bestStates;
    }
}