package com.mycompany.sisezad1;

import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;
import com.mycompany.sisezad1.utils.*;



/**
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //TODO uncomment this
        //String[] argsTmp = new String[]{"-a", "cbf"};
        //ConsoleMode.mainLoop(argsTmp);

        int[][] state6 = new int[][]{ //da sie latwo rozwiazac - 6 ruchow
                {0, 1, 2, 4},
                {5, 6, 3, 8},
                {9, 10, 7, 12},
                {13, 14, 11, 15}
        };

        Board instance = new Board(state6);
        //instance = BoardUtils.randomizeBoard(4, 4, 20);

        PuzzleSolver solver = new BestFirstSearch(new MisplacedComparator());

        Board solved;// = solver.solve(instance, null);

        solved = ReportsGenerator.solveWithReport(solver, "firstReport", instance);

        if (solved != null) {
            BoardUtils.printBoard(solved);
        } else {
            System.out.println("nie udalo sie znalzc rozwiazania");
        }

        System.exit(0);
    }
}
