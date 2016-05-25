package com.mycompany.sisezad1;

import com.mycompany.sisezad1.heuristics.Heuristic;
import com.mycompany.sisezad1.solvers.PuzzleSolver;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Piotrek
 */
public class Board {

    /**
     * Designed to work with custom best-first, not safe to use outside this algorithm, may cause
     * deadlocks (in only 4 cases, but still)
     */
    public static boolean STRONG_LOOP_CONTROL = false;

    /**
     * Safe to use, prevents board to create new states witch undo last move
     */
    public static boolean SIMPLE_LOOP_CONTROL = false;

    private int[][] state;
    private List<Board> nextNodes;
    //private Board parentNode;
    private String path;
    private int pathValue; //used in IDA*

    /**
     * Creates board with given state, other fields are default
     *
     * @param state given state
     */
    public Board(int[][] state) {
        this.state = state.clone();
        //parentNode = null;
        nextNodes = null;
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
        //parentNode = null;
        nextNodes = null;
        path = original.getPath();
        pathValue = original.pathValue;
    }

    public void setNextStepInPath(String step) {
        this.path = path + step;
    }

    public String getPath() {
        return this.path;
    }

    //public Board getParentNode() {
    //    return this.parentNode;
    //}

    //public void setParentNode(Board parent) {
    //    this.parentNode = parent;
    //}

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
        if (SIMPLE_LOOP_CONTROL || STRONG_LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Moves.LEFT_CHAR)) {
                    return false;
                }
                if (STRONG_LOOP_CONTROL && (
                        this.path.toLowerCase().endsWith(loopCauseRight1())
                                || this.path.toLowerCase().endsWith(loopCauseRight2())
                )) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return !(zeroCoord[1] == state[0].length - 1);
    }

    private String loopCauseRight1() {  //DWAS
        return Moves.RIGHT_CHAR + Moves.UP_CHAR + Moves.LEFT_CHAR + Moves.DOWN_CHAR;
    }

    private String loopCauseRight2() {   //DSAW
        return Moves.RIGHT_CHAR + Moves.DOWN_CHAR + Moves.LEFT_CHAR + Moves.UP_CHAR;
    }

    public boolean canMoveLeft() {
        if (SIMPLE_LOOP_CONTROL || STRONG_LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Moves.RIGHT_CHAR)) {
                    return false;
                }
                if (STRONG_LOOP_CONTROL && (
                        this.path.toLowerCase().endsWith(loopCauseLeft1())
                                || this.path.toLowerCase().endsWith(loopCauseLeft2())
                )) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[1] > 0);
    }

    private String loopCauseLeft1() {  //AWDS
        return Moves.LEFT_CHAR + Moves.UP_CHAR + Moves.RIGHT_CHAR + Moves.DOWN_CHAR;
    }

    private String loopCauseLeft2() {   //ASDW
        return Moves.LEFT_CHAR + Moves.DOWN_CHAR + Moves.RIGHT_CHAR + Moves.UP_CHAR;
    }

    public boolean canMoveUp() {
        if (SIMPLE_LOOP_CONTROL || STRONG_LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Moves.DOWN_CHAR)) {
                    return false;
                }
                if (STRONG_LOOP_CONTROL && (
                        this.path.toLowerCase().endsWith(loopCauseUp1())
                                || this.path.toLowerCase().endsWith(loopCauseUp2())
                )) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[0] > 0);
    }

    private String loopCauseUp1() {  //WDSA
        return Moves.UP_CHAR + Moves.RIGHT_CHAR + Moves.DOWN_CHAR + Moves.LEFT_CHAR;
    }

    private String loopCauseUp2() {   //WASD
        return Moves.UP_CHAR + Moves.LEFT_CHAR + Moves.DOWN_CHAR + Moves.RIGHT_CHAR;
    }

    public boolean canMoveDown() {
        if (SIMPLE_LOOP_CONTROL || STRONG_LOOP_CONTROL) {
            if (!this.path.isEmpty() && this.path != null) {
                if (this.path.toLowerCase().endsWith(Moves.UP_CHAR)) {
                    return false;
                }
                if (STRONG_LOOP_CONTROL && (
                        this.path.toLowerCase().endsWith(loopCauseDown1())
                                || this.path.toLowerCase().endsWith(loopCauseDown2())
                )) {
                    return false;
                }
            }
        }
        int[] zeroCoord = findZero();
        return !(zeroCoord[0] == state.length - 1);
    }

    private String loopCauseDown1() {  //SDWA
        return Moves.DOWN_CHAR + Moves.RIGHT_CHAR + Moves.UP_CHAR + Moves.LEFT_CHAR;
    }

    private String loopCauseDown2() {   //SAWD
        return Moves.DOWN_CHAR + Moves.LEFT_CHAR + Moves.UP_CHAR + Moves.RIGHT_CHAR;
    }

    public Board moveRight() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] + 1];
        newState[zeroCoord[0]][zeroCoord[1] + 1] = 0;
        //return new Board(newState);
        newB.path = new String(this.path);
        newB.setNextStepInPath(Moves.RIGHT_CHAR);
        //newB.setParentNode(this);
        return newB;
    }

    public Board moveLeft() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0]][zeroCoord[1] - 1];
        newState[zeroCoord[0]][zeroCoord[1] - 1] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Moves.LEFT_CHAR);
        //newB.setParentNode(this);
        return newB;
    }

    public Board moveUp() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] - 1][zeroCoord[1]];
        newState[zeroCoord[0] - 1][zeroCoord[1]] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Moves.UP_CHAR);
        //newB.setParentNode(this);
        return newB;
    }

    public Board moveDown() {
        Board newB = new Board(this);
        int[][] newState = newB.state;
        int[] zeroCoord = findZero();

        newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] + 1][zeroCoord[1]];
        newState[zeroCoord[0] + 1][zeroCoord[1]] = 0;
        newB.path = new String(this.path);
        newB.setNextStepInPath(Moves.DOWN_CHAR);
        //newB.setParentNode(this);
        return newB;
    }

    /**
     * Method returns possible states from this Board in given order
     */
    public List<Board> getPossibleStates(String ord) {
        String order = new String(ord);
        if (order.contains("r") || order.contains("R")) {
            order = BoardUtils.randomizeOrder();
        }
        List<Board> possibleStates = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Board toAdd = this.move(order.charAt(i));
            if (toAdd != null) {
                possibleStates.add(toAdd);
            }
        }
        if (possibleStates.isEmpty()) {
            System.out.println("DEADLOCK - brak mozliwosci ruchu bez zapetlenia.");
        }
        return possibleStates;
    }

    /**
     * Method returns possible states from this Board in given order
     */
    public List<Board> getPossibleStates(Heuristic heuristics) {
        List<Board> possibleStates = this.getPossibleStates("rrrr");
        Collections.sort(possibleStates, heuristics);
        return possibleStates;
    }

    /**
     * Method returns only best states from this Board in heuristic order If more than one state
     * have same, lowest heuristics value, then returns all of them
     */
    public List<Board> getBestStates(Heuristic heuristics) {
        List<Board> bestStates = BoardUtils.getOnlyBestBoards(this.getPossibleStates("rrrr"), heuristics);
        return bestStates;
    }

    public Board findAnswerWithDFS(String order, int depth, PrintStream stream) {
        if (depth >= 0) {
            this.nextNodes = getPossibleStates(order);
            PuzzleSolver.addCreated(this.nextNodes.size());
            for (Board nextNode : nextNodes) {
                if (stream != null && !nextNode.getPath().isEmpty() && nextNode.getPath() != null) {
                    stream.println(nextNode.getPath());
                }
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

    public Board findAnswerWithIDA(Heuristic heuristics, int maxCost, PrintStream stream) { //FIXME

        if (maxCost >= 0) {
            this.nextNodes = getPossibleStates(heuristics);
            this.nextNodes = BoardUtils.deleteBoardsAboveMaxHeuristicCost(this.nextNodes, maxCost, heuristics);

            PuzzleSolver.addCreated(this.nextNodes.size());
            for (Board nextNode : nextNodes) {
                if (stream != null && !nextNode.getPath().isEmpty() && nextNode.getPath() != null) {
                    stream.println(nextNode.getPath());
                }
                if (nextNode.isCorrect()) {
                    return nextNode;
                } else {
                    Board possibleAnswer = nextNode.findAnswerWithIDA(heuristics, maxCost - 1, stream);
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

    public void updatePathCost(Heuristic heuristic) {
        this.pathValue = 0;
        char[] directions = BoardUtils.reverseMoves(this.path).toCharArray();
        Board stepBefore = this;
        int costSum = 0;

        for (char s : directions) {
            stepBefore = this.move(s);
            costSum = +heuristic.heuristicValue(stepBefore);
        }
        this.pathValue = costSum;
    }

    /**
     * @param moves - String wih moves to make
     * @return changed Board, or null if wrong direction given or can't move in given direction
     */
    public Board allMoves(String moves) {
        Board afterMoves = this;
        for (char c : moves.toCharArray()) {
            afterMoves = afterMoves.move(c);
        }
        return afterMoves;
    }

    /**
     * @param direction [w|s|a|d]
     * @return changed Board, or null if wrong direction given or can't move in given direction
     */
    public Board move(char direction) {
        String directionString = new String(new char[]{direction});
        switch (directionString) {
            case Moves.UP_CHAR:
                if (canMoveUp()) {
                    return moveUp();
                } else {
                    return null;
                }
            case Moves.DOWN_CHAR:
                if (canMoveDown()) {
                    return moveDown();
                } else {
                    return null;
                }
            case Moves.LEFT_CHAR:
                if (canMoveLeft()) {
                    return moveLeft();
                } else {
                    return null;
                }
            case Moves.RIGHT_CHAR:
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
