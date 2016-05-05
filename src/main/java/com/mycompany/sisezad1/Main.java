package com.mycompany.sisezad1;

import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;
import com.mycompany.sisezad1.utils.*;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //TODO uncomment this
        //String[] argsTmp = new String[]{"-a", "a", "2"};
        //ConsoleMode.mainLoop(argsTmp);

        int[][] state6 = new int[][]{ //da sie latwo rozwiazac - 6 ruchow
                {0, 1, 2, 4},
                {5, 6, 3, 8},
                {9, 10, 7, 12},
                {13, 14, 11, 15}
        };

        int[][] state1 = new int[][]{ //D
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 15}
        };

        int[][] state5 = new int[][]{ //D
                {1, 0, 3, 4},
                {5, 2, 7, 8},
                {9, 6, 10, 12},
                {13, 14, 11, 15}
        };
        int[][] state10 = new int[][]{ //D
                {1, 3, 4, 8},
                {5, 2, 10, 7},
                {9, 6, 0, 12},
                {13, 14, 11, 15}
        };


        //ReportsGenerator.generateAllStates("allStates", 6, false);
        //List<String> paths = FileUtils.loadPaths("_" + "allStates" + ".txt");
        //ReportsGenerator.removeDuplicates(paths);
        int depth = 5;
        int algorithmMaxDepth = 25;
        String order = "pgld";

        Board.SIMPLE_LOOP_CONTROL = false;
        Board.STRONG_LOOP_CONTROL = false;
        for(int i = 1; i <= 6; i++){
            ReportsGenerator.generateGeneralStatistics("generalReport" + i, i, algorithmMaxDepth, order);
        }
        //ReportsGenerator.generateGeneralStatistics("generalReport" + 7, 7, algorithmMaxDepth, order);

        algorithmMaxDepth = 25;
        Board.SIMPLE_LOOP_CONTROL = true;
        Board.STRONG_LOOP_CONTROL = false;
        for(int i = 1; i < 6; i++){
            //ReportsGenerator.generateGeneralStatistics("generalReportLoopControl" + i, i, algorithmMaxDepth, order);
        }

        //ReportsGenerator.generateGeneralStatistics("generalReport" + depth, depth, algorithmMaxDepth, order);


        Board instance = new Board(state5);
        instance = BoardUtils.randomizeBoard(6, 5, 15);
        Board solved = null;// = solver.solve(instance, null);
        Board.SIMPLE_LOOP_CONTROL = false;
        Board.STRONG_LOOP_CONTROL = false;

        PuzzleSolver solver1 = new IterativeAStarSearch(new AManhattanDistanceComparator());
        //solver1 = new IterativeDepthFirstSearch(20);
        //solver1 = new DepthFirstSearch("pdlg");
        //solver1 = new BreadthFirstSearch();


        //solved = solver1.solve(instance, null);

        //solved = ReportsGenerator.solveWithReport(solver1, "test", instance);

        if (solved != null) {
            BoardUtils.printBoard(solved);
            System.out.println("time: " + solver1.getTimeInMilis() + "ms");
        }

        System.exit(0);
    }
}
