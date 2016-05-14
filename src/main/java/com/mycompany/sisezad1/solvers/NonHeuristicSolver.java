package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

/**
 * Created by Piotrek on 14.05.2016.
 */
public abstract class NonHeuristicSolver extends PuzzleSolver {
    protected String order;            //nieuzywane w metodach heurystycznych

    public NonHeuristicSolver(String order) {
        super();
        if (order.length() != 4
                || !(order.toLowerCase().contains(Board.DOWN_CHAR)
                && order.toLowerCase().contains(Board.LEFT_CHAR)
                && order.toLowerCase().contains(Board.RIGHT_CHAR)
                && order.toLowerCase().contains(Board.UP_CHAR)) ^ order.toLowerCase().contains("r")) {
            //jesli order nie ma 4 znakow lub jesli
            //      nie zawiera wszystkich liter-kierunkow xor zawiera r
            this.order = BoardUtils.randomizeOrder();
        } else {
            this.order = order;
        }
        this.firstBoard = null;
        this.createdBoards = 0;
    }

    public NonHeuristicSolver(String order, int depth) {
        super(depth);
        if (order.length() != 4
                || !(order.toLowerCase().contains(Board.DOWN_CHAR)
                && order.toLowerCase().contains(Board.LEFT_CHAR)
                && order.toLowerCase().contains(Board.RIGHT_CHAR)
                && order.toLowerCase().contains(Board.UP_CHAR)) ^ order.toLowerCase().contains("r")) {
            //jesli order nie ma 4 znakow lub jesli
            //      nie zawiera wszystkich liter-kierunkow xor zawiera r
            this.order = BoardUtils.randomizeOrder();
        } else {
            this.order = order;
        }
        this.firstBoard = null;
        this.createdBoards = 0;
    }

    public NonHeuristicSolver() {
        super();
        this.order = BoardUtils.randomizeOrder();
    }

    public NonHeuristicSolver(int depth) {
        super(depth);
        this.order = BoardUtils.randomizeOrder();
    }

    public String getOrder() {
        return this.order;
    }

}
