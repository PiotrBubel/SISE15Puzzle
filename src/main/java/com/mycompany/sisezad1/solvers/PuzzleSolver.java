package com.mycompany.sisezad1.solvers;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.io.PrintStream;
import java.util.Comparator;


/**
 * @author Piotrek
 */
public abstract class PuzzleSolver {

    public static int DEFAULT_MAX_DEPTH = 25;
    protected static int CREATED_BOARDS = 0;
    protected int createdBoards;
    protected long time = 0;
    protected String order;            //nieuzywane w metodach heurystycznych
    protected Board firstBoard;
    protected Comparator heuristicFunction; //nieuzywane w metodach nie-heurystycznych


    /**
     * Wywoływany gdy kolejność ma być losowana
     */
    public PuzzleSolver() {
        this.order = BoardUtils.randomizeOrder();
        this.firstBoard = null;
        this.createdBoards = 0;
        this.heuristicFunction = null;
    }

    public PuzzleSolver(String order) {
        if (order.length() != 4
                || !(order.toLowerCase().contains(Board.DOWN_CHAR)
                && order.toLowerCase().contains(Board.LEFT_CHAR)
                && order.toLowerCase().contains(Board.RIGHT_CHAR)
                && order.toLowerCase().contains(Board.UP_CHAR)) ^ order.toLowerCase().contains("r")) {
            //jesli order nie ma 4 znakow lub jesli
            //      nie zawiera wszystkich liter-kierunkow xor zawiera r
            System.out.println("Podana kolejnosc jest nieprawidlowa. Losuje kolejnosc.");
            this.order = BoardUtils.randomizeOrder();
        } else {
            this.order = order;
        }
        this.firstBoard = null;
        this.createdBoards = 0;
        this.heuristicFunction = null;
    }

    /**
     * Method for counting created boards, to use only in Board class
     */
    public static void addCreated(int i) {
        PuzzleSolver.CREATED_BOARDS = PuzzleSolver.CREATED_BOARDS + i;
    }

    public Comparator getHeuristicFunction() {
        return this.heuristicFunction;
    }

    public long getTime() {
        return this.time;
    }

    public String getOrder() {
        return this.order;
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
