package com.mycompany.sisezad1;

import com.mycompany.sisezad1.solvers.*;

/**
 *
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length > 0) {

            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }

            PuzzleSolver solver;

            switch (args[0]) {
                case "-b":
                    solver = new BreadthFirstSearch();
                    break;
                case "-d":
                    solver = new DepthFirstSearch();
                    break;
                case "-i":
                    solver = new IterativeDepthFirstSearch();
                    break;

                case "-bfs":
                    solver = new BreadthFirstSearch(args[1]);
                    break;
                case "-dfs":
                    solver = new DepthFirstSearch(args[1]);
                    break;
                case "-idfs":
                    solver = new IterativeDepthFirstSearch(args[1]);
                    break;
                //case "a":
                //    solver = new BestFirstSearch(args[1], args[2]);
                //    break;
                default:
                    solver = new IterativeDepthFirstSearch();
                    break;
            }
            System.out.println("Uruchomi sie algorytm: " + solver.getClass().toString());
        } else {
            System.out.println("Program uruchomiony bez argumentow");
        }

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);

        instance.print();

        SaveLoadFile.saveData("plik.txt", instance);
        instance = SaveLoadFile.loadData3("plik.txt");

        //instance = ConsoleManualMode.manualMode(instance);
        instance.print();

        System.exit(0);
    }
}
