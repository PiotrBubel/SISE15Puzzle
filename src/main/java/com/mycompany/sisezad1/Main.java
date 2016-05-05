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


        ReportsGenerator.generateAllStates("allStates", 6);
        //List<String> paths = FileUtils.loadPaths("_" + "allStates" + ".txt");
        //ReportsGenerator.removeDuplicates(paths);


        Board instance = new Board(state5);
        instance = BoardUtils.randomizeBoard(6, 5,15);
        Board solved = null;// = solver.solve(instance, null);
        PuzzleSolver.DEFAULT_MAX_DEPTH = 20;
        Board.SIMPLE_LOOP_CONTROL = false;
        Board.STRONG_LOOP_CONTROL = false;

        PuzzleSolver solver1 = new IterativeAStarSearch(new AManhattanDistanceComparator());
        //solver1 = new IterativeDepthFirstSearch(20);
        //solver1 = new DepthFirstSearch("pdlg");
        //solver1 = new BreadthFirstSearch();


        //solved = solver1.solve(instance, null);
        //System.out.println("time: " + solver1.getTimeInMilis() + "ms");

        //solved = ReportsGenerator.solveWithReport(solver1, "test", instance);
        if (solved != null) {
            BoardUtils.printBoard(solved);
            System.out.println("time: " + solver1.getTimeInMilis() + "ms");
        }


        if (false) {

            PuzzleSolver.DEFAULT_MAX_DEPTH = 30;
            int maxMovesToSolve = 10;
            int xy = 3;
            PrintStream streamPaths = null;
            try {
                streamPaths = new PrintStream(new FileOutputStream("_" + "test" + "_Paths.txt"));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Blad podczas tworzenia pliku paths");
            }
            int notSolved = 0;

            for (int i = 0; i < 100; i++) {
                instance = BoardUtils.randomizeBoard(xy, xy, maxMovesToSolve);
                AStarSearch solver = new AStarSearch(new AMisplacedComparator());
                //solver = new BreadthFirstSearch();

                solved = solver.solve(instance, streamPaths);
                if (solved == null) {
                    notSolved++;
                }
            }
            System.out.println("AMisplaced - not solved with given max depth: " + notSolved);


            notSolved = 0;
            for (int i = 0; i < 100; i++) {
                instance = BoardUtils.randomizeBoard(xy, xy, maxMovesToSolve);
                AStarSearch solver = new AStarSearch(new MisplacedComparator());
                //solver = new BreadthFirstSearch();

                solved = solver.solve(instance, streamPaths);
                if (solved == null) {
                    notSolved++;
                }
            }
            System.out.println("Misplaced - not solved with given max depth: " + notSolved);


            notSolved = 0;
            for (int i = 0; i < 100; i++) {
                instance = BoardUtils.randomizeBoard(xy, xy, maxMovesToSolve);
                AStarSearch solver = new AStarSearch(new AManhattanDistanceComparator());
                //solver = new BreadthFirstSearch();

                solved = solver.solve(instance, streamPaths);
                if (solved == null) {
                    notSolved++;
                }
            }
            System.out.println("AManhattan - not solved with given max depth: " + notSolved);


            notSolved = 0;
            for (int i = 0; i < 100; i++) {
                instance = BoardUtils.randomizeBoard(xy, xy, maxMovesToSolve);
                AStarSearch solver = new AStarSearch(new ManhattanDistanceComparator());
                //solver = new BreadthFirstSearch();

                solved = solver.solve(instance, streamPaths);
                if (solved == null) {
                    notSolved++;
                }
            }
            System.out.println("Manhattan - not solved with given max depth: " + notSolved);
            //solved = ReportsGenerator.solveWithReport(solver, "firstReport", instance);
        }
        System.exit(0);
    }
}
