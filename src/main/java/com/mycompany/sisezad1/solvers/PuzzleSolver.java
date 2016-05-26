package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

import java.io.PrintStream;


/**
 * @author Piotrek
 */
public abstract class PuzzleSolver {

    public static int DEFAULT_MAX_DEPTH = 25;
    protected static int CREATED_BOARDS = 0;

    protected int createdBoards;
    protected long time = 0;
    protected Board firstBoard;
    protected int maxDepth;

    public PuzzleSolver() {
        this.createdBoards = 0;
        this.maxDepth = DEFAULT_MAX_DEPTH;
    }

    public PuzzleSolver(int maxDepth) {
        this.createdBoards = 0;
        this.maxDepth = maxDepth;
    }


    /**
     * Method for counting created boards, to use only in Board class
     */
    public static void addCreated(int i) {
        PuzzleSolver.CREATED_BOARDS = PuzzleSolver.CREATED_BOARDS + i;
    }

    public long getTimeInNanos() {
        return this.time;
    }

    public double getTimeInMilis() {
        return this.time / 1000000d;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public int getCreatedBoards() {
        return this.createdBoards;
    }

    /**
     * Method should count its execution time and save it to 'time' variable
     *
     * @param unsolved board
     * @return solved board or null when algorithm cannot find solution
     */
    public abstract Board solve(Board unsolved, PrintStream pStream);

}
