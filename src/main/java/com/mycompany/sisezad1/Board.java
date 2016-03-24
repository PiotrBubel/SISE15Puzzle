package com.mycompany.sisezad1;

import java.io.PrintStream;

/**
 *
 * @author Piotrek
 */
public class Board {

    private int[][] state;

    public Board(int[][] state) {
        this.state = state;
    }

    public int[][] getState() {
        return this.state;
    }

    public boolean isCorrect() {
        return countMisplaced() == 0;
    }

    public int countMisplaced() {
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
     *
     * @return x,y coordinates of Zero, x and y are counted from 0, (0,0) is in
     * upper left corner, x goes down, y goes right
     */
    public int[] findZero() {
        int[] coordinates = new int[2];
        coordinates[0] = coordinates[1] = -1;

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if (state[x][y] == 0) {
                    coordinates[0] = x;
                    coordinates[1] = y;
                    return coordinates;
                }
            }
        }
        return coordinates;
    }

    public void print(PrintStream out) {
        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                out.print(state[x][y]);
                if (y < state[0].length - 1) {
                    out.print("\t");
                }
            }
            if (x < state.length - 1) {
                out.println();
            }
        }
    }

    public void print() {
        print(System.out);
    }

    public boolean canMoveRight() {
        int[] zeroCoord = findZero();
        return !(zeroCoord[1] == state[0].length - 1);
    }

    public boolean canMoveLeft() {
        int[] zeroCoord = findZero();
        return (zeroCoord[1] > 0);
    }

    public boolean canMoveUp() {
        int[] zeroCoord = findZero();
        return (zeroCoord[0] > 0);
    }

    public boolean canMoveDown() {
        int[] zeroCoord = findZero();
        return !(zeroCoord[0] == state.length - 1);
    }

    public Board moveRight() {
        int[][] newState = state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] + 1];
        newState[zeroCoord[0]][zeroCoord[1] + 1] = 0;
        return new Board(newState);
    }

    public Board moveLeft() {
        int[][] newState = state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] - 1];
        newState[zeroCoord[0]][zeroCoord[1] - 1] = 0;
        return new Board(newState);
    }

    public Board moveUp() {
        int[][] newState = state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] - 1][zeroCoord[1]];
        newState[zeroCoord[0] - 1][zeroCoord[1]] = 0;
        return new Board(newState);
    }

    public Board moveDown() {
        int[][] newState = state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] + 1][zeroCoord[1]];
        newState[zeroCoord[0] + 1][zeroCoord[1]] = 0;
        return new Board(newState);
    }

    /**
     *
     * @param direction [w|s|a|d]
     * @return changed Board, or this Board if wrong direction given
     */
    public Board move(char direction) {
        switch (direction) {
            case 'w':
                if (canMoveUp()) {
                    return moveUp();
                } else {
                    return this;
                }
            case 's':
                if (canMoveDown()) {
                    return moveDown();
                } else {
                    return this;
                }
            case 'a':
                if (canMoveLeft()) {
                    return moveLeft();
                } else {
                    return this;
                }
            case 'd':
                if (canMoveRight()) {
                    return moveRight();
                } else {
                    return this;
                }
            default:
                return this;
        }
    }
}
