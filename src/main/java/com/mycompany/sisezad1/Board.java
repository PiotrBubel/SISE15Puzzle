package com.mycompany.sisezad1;

import com.mycompany.sisezad1.solvers.PuzzleSolver;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Piotrek
 */
public class Board {

    public static final String RIGHT_CHAR_CAP = "D";
    public static final String LEFT_CHAR_CAP = "A";
    public static final String UP_CHAR_CAP = "W";
    public static final String DOWN_CHAR_CAP = "S";
    public static final String RIGHT_CHAR = "d";
    public static final String LEFT_CHAR = "a";
    public static final String UP_CHAR = "w";
    public static final String DOWN_CHAR = "s";
    public static boolean LOOP_CONTROL = false;
    private int[][] state;
    private List<Board> nextNodes;
    private Board parentNode;
    private String path;

    /**
     * Creates board with given state, other fields are default
     *
     * @param state given state
     */
    public Board(int[][] state) {
        this.state = state.clone();
        parentNode = null;
        path = "";
    }

    /**
     * Creates copy of given board, use this when you want to copy state and path. Does not copy
     * parentNode
     *
     * @param original Board
     */
    public Board(Board original) {
        state = new int[original.state.length][original.state[0].length];

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                state[x][y] = original.state[x][y];
            }
        }
        parentNode = null;
        path = original.getPath();
    }

    public void setNextStepInPath(String step) {
        this.path = path + step;
    }

    public String getPath() {
        return this.path;
    }

    public Board getParentNode() {
        return this.parentNode;
    }

    public void setParentNode(Board parent) {
        this.parentNode = parent;
    }

    public List<Board> getNextNodes() {
        return this.nextNodes;
    }

    public int[][] getState() {
        return this.state;
    }

    public boolean isCorrect() {
        return BoardUtils.countMisplaced(this) == 0;
    }

    /**
     * @return x, y coordinates of value, x and y are counted from 0, (0,0) is in upper left corner,
     * x goes down, y goes right
     */
    public int[] findNumber(int value) {
        int[] coordinates = new int[2];
        coordinates[0] = coordinates[1] = -1;

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if (state[x][y] == value) {
                    coordinates[0] = x;
                    coordinates[1] = y;
                    return coordinates;
                }
            }
        }
        return coordinates;
    }

    /**
     * @return x, y coordinates of Zero, x and y are counted from 0, (0,0) is in upper left corner,
     * x goes down, y goes right
     */
    public int[] findZero() {
        return findNumber(0);
    }

    public boolean canMoveRight() {
        if (LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Board.LEFT_CHAR)
                    // || this.path.endsWith("DWAS") //FIXME sometimes causes deadlocks
                    // || this.path.endsWith("DSAW")
                        ) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return !(zeroCoord[1] == state[0].length - 1);
    }

    public boolean canMoveLeft() {
        if (LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Board.RIGHT_CHAR)
                    //|| this.path.endsWith("AWDS") //FIXME sometimes causes deadlocks
                    //|| this.path.endsWith("ASDW")
                        ) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[1] > 0);
    }

    public boolean canMoveUp() {
        if (LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Board.DOWN_CHAR)
                    //|| this.path.endsWith("WDSA")
                    // || this.path.endsWith("WASD") //FIXME sometimes causes deadlocks
                        ) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[0] > 0);
    }

    public boolean canMoveDown() {
        if (LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Board.UP_CHAR)
                    // || this.path.endsWith("SDWA")
                    // || this.path.endsWith("SAWD")    //FIXME sometimes causes deadlocks
                        ) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return !(zeroCoord[0] == state.length - 1);
    }

    public Board moveRight() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] + 1];
        newState[zeroCoord[0]][zeroCoord[1] + 1] = 0;
        //return new Board(newState);
        newB.path = new String(this.path);
        newB.setNextStepInPath(Board.RIGHT_CHAR);
        newB.setParentNode(this);
        return newB;
    }

    public Board moveLeft() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] - 1];
        newState[zeroCoord[0]][zeroCoord[1] - 1] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Board.LEFT_CHAR);
        newB.setParentNode(this);
        return newB;
    }

    public Board moveUp() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] - 1][zeroCoord[1]];
        newState[zeroCoord[0] - 1][zeroCoord[1]] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Board.UP_CHAR);
        newB.setParentNode(this);
        return newB;
    }

    public Board moveDown() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] + 1][zeroCoord[1]];
        newState[zeroCoord[0] + 1][zeroCoord[1]] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Board.DOWN_CHAR);
        newB.setParentNode(this);
        return newB;
    }

    /**
     * Method returns possible states from this Board in given order
     */
    public List<Board> getPossibleStates(String ord) {
        String order = new String(ord);
        if (order.startsWith("r") || order.startsWith("R")) {
            order = BoardUtils.randomizeOrder();
        }
        List<Board> possibleStates = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Board toAdd = this.move(order.charAt(i));
            if (toAdd != null) {
                possibleStates.add(toAdd);
                PuzzleSolver.createdBoard();
            }
        }
        if (possibleStates.isEmpty()) {
            System.out.println("DEADLOCK - brak mozliwosci ruchu bez zapetlenia.");
        }
        return possibleStates;
    }

    /**
     * Method returns possible states from this Board in heuristic order
     */
    public List<Board> getPossibleStates(Comparator heuristics) {
        String order = BoardUtils.randomizeOrder();
        List<Board> possibleStates = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Board toAdd = this.move(order.charAt(i));
            if (toAdd != null) {
                possibleStates.add(toAdd);
                PuzzleSolver.createdBoard();
            }
        }
        Collections.sort(possibleStates, heuristics);
        return possibleStates;
    }

    public Board findAnswerWithDFS(String order, int depth, PrintStream stream) {
        if (depth >= 0) {
            this.nextNodes = getPossibleStates(order);
            for (Board nextNode : nextNodes) {
                stream.println(nextNode.path);
                if (nextNode.isCorrect()) {
                    return nextNode;
                } else {
                    Board possibleAnswer = nextNode.findAnswerWithDFS(order, depth - 1, stream);
                    if (possibleAnswer != null) {
                        return possibleAnswer;
                    }
                }
            }
        } else {
            this.nextNodes = null;
            return null;
        }
        return null;
    }

    public Board findAnswerWithAStar(Comparator heuristics, int depth, PrintStream stream) {
        if (depth >= 0) {
            this.nextNodes = getPossibleStates(heuristics);
            for (Board nextNode : nextNodes) {
                stream.println(nextNode.path);
                if (nextNode.isCorrect()) {
                    return nextNode;
                } else {
                    Board possibleAnswer = nextNode.findAnswerWithAStar(heuristics, depth - 1, stream);
                    if (possibleAnswer != null) {
                        return possibleAnswer;
                    }
                }
            }
        } else {
            this.nextNodes = null;
            return null;
        }
        return null;
    }

    /**
     * @param direction [w|s|a|d]
     * @return changed Board, or null if wrong direction given or can't move in given direction
     */
    public Board move(char direction) {
        String directionString = new String(new char[]{direction});
        switch (directionString) {
            case Board.UP_CHAR_CAP:
            case Board.UP_CHAR:
                if (canMoveUp()) {
                    return moveUp();
                } else {
                    return null;
                }
            case Board.DOWN_CHAR_CAP:
            case Board.DOWN_CHAR:
                if (canMoveDown()) {
                    return moveDown();
                } else {
                    return null;
                }
            case Board.LEFT_CHAR_CAP:
            case Board.LEFT_CHAR:
                if (canMoveLeft()) {
                    return moveLeft();
                } else {
                    return null;
                }
            case Board.RIGHT_CHAR_CAP:
            case Board.RIGHT_CHAR:
                if (canMoveRight()) {
                    return moveRight();
                } else {
                    return null;
                }
            default:
                return null;
        }
    }


    @Override
    public boolean equals(Object other) {
        Board otherB = (Board) other;
        try {
            for (int i = 0; i < this.state.length; i++) {
                for (int j = 0; j < this.state[0].length; j++) {
                    if (this.state[i][j] != otherB.getState()[i][j]) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
