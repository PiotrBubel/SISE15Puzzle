package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;

/**
 *
 * @author Piotrek
 */
public abstract class PuzzleSolver {

    private long time = 0;

    private String order;

    public PuzzleSolver() {
        order = "RRRR";
    }

    public PuzzleSolver(String order) {
        if (order.length() == 4) {
            this.order = order;
        }
    }

    public long getTime() {
        return this.time;
    }

    public String getOrder() {
        return this.order;
    }

    /**
     *
     * @param unsolved board
     *
     * Method should count its execution time and save it to 'time' variable
     */
    public abstract void solve(Board unsolved);

}
