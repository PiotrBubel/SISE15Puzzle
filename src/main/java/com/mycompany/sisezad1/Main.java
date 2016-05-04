package com.mycompany.sisezad1;

import com.mycompany.sisezad1.solvers.AStarSearch;
import com.mycompany.sisezad1.solvers.BreadthFirstSearch;
import com.mycompany.sisezad1.solvers.PuzzleSolver;
import com.mycompany.sisezad1.utils.BoardUtils;
import com.mycompany.sisezad1.utils.ConsoleManualMode;


/**
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ConsoleManualMode.mainLoop(args);
        PuzzleSolver pz = new BreadthFirstSearch("dupa");
        System.out.println(pz.getOrder());

        int[][] state3 = new int[][]{ //3 ruchy
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 0, 10, 12},
                {13, 14, 11, 15}
        };

        Board solved = pz.solve(new Board(state3), null);

        BoardUtils.printBoard(solved);

        System.exit(0);
    }
}
