package com.mycompany.sisezad1;

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
        int[] zeroCoord = findZero();
        if (!this.path.isEmpty() && this.path != null) {
            if (this.path.endsWith("A") ||
                    this.path.endsWith("DWAS") ||
                    this.path.endsWith("DSAW")
                    ) { //TODO more loop patterns
                //dwas
                //dsaw
                return false;
            }
        }
        return !(zeroCoord[1] == state[0].length - 1);
    }

    public boolean canMoveLeft() {
        if (!this.path.isEmpty() && this.path != null) {
            if (this.path.endsWith("D") ||
                    this.path.endsWith("AWDS") ||
                    this.path.endsWith("ASDW")
                    ) { //TODO more loop patterns
                return false;
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[1] > 0);
    }

    public boolean canMoveUp() {
        if (!this.path.isEmpty() && this.path != null) {
            if (this.path.endsWith("S") ||
                    this.path.endsWith("WDSA") ||
                    this.path.endsWith("WASD")
                    ) {  //TODO more loop patterns
                return false;
            }
        }
        int[] zeroCoord = findZero();
        return (zeroCoord[0] > 0);
    }

    public boolean canMoveDown() {
        if (!this.path.isEmpty() && this.path != null) {
            if (this.path.endsWith("W") ||
                    this.path.endsWith("SDWA") ||
                    this.path.endsWith("SAWD")
                    ) {  //TODO more loop patterns
                return false;
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
        newB.setNextStepInPath("D");
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
        newB.setNextStepInPath("A");
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
        newB.setNextStepInPath("W");
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
        newB.setNextStepInPath("S");
        newB.setParentNode(this);
        return newB;
        /*
         //Z jakiegoś powodu jak było tak, to zmieniało stan tego obiektu. .clone() nie działa tak jak myślę?
         int[][] newState = state.clone();
         int[] zeroCoord = findZero();

         newState[zeroCoord[0]][zeroCoord[1]] = newState[zeroCoord[0] + 1][zeroCoord[1]];
         newState[zeroCoord[0] + 1][zeroCoord[1]] = 0;
         return new Board(newState);
         */

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
            }
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
        switch (direction) {
            case 'w':
                if (canMoveUp()) {
                    return moveUp();
                } else {
                    return null;
                }
            case 's':
                if (canMoveDown()) {
                    return moveDown();
                } else {
                    return null;
                }
            case 'a':
                if (canMoveLeft()) {
                    return moveLeft();
                } else {
                    return null;
                }
            case 'd':
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
