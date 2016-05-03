package com.mycompany.sisezad1;

import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;
import com.mycompany.sisezad1.utils.BoardUtils;
import com.mycompany.sisezad1.utils.FileUtils;

import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * @author Piotrek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length > 1) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }

            PuzzleSolver solver;

            switch (args[0]) {
                //pierwszy argument to algorytm, drugi kiedy potrzebny to glebokosc
                case "-b":
                    solver = new BreadthFirstSearch();
                    break;
                case "-d":
                    solver = new DepthFirstSearch();
                    break;
                case "-i":
                    solver = new IterativeDepthFirstSearch(Integer.parseInt(args[1]));
                    break;

                //pierwszy argument to algorytm, drugi to kolejnosc, trzeci jesli potrzebny to glebokosc
                case "-bfs":
                    solver = new BreadthFirstSearch(args[1]);
                    break;
                case "-dfs":
                    solver = new DepthFirstSearch(args[1]);
                    break;
                case "-idfs":
                    if (args.length > 2) {
                        solver = new IterativeDepthFirstSearch(args[1], Integer.parseInt(args[2]));
                    } else {
                        solver = new IterativeDepthFirstSearch(args[1], 8); //default depth
                    }
                    break;
                //TODO ogarnac wlaczanie algorytmu best first i wybieranie heurystyki

                default:
                    System.out.println("Podano bledny argument");
                    solver = new DepthFirstSearch();
                    break;
            }
            System.out.println("Uruchomi sie algorytm: " + solver.getClass().toString());
        } else {
            System.out.println("Program uruchomiony bez poprawnych argumentow");
        }

        int[][] state1 = new int[][]{ //1 ruch
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 15}
        };
        int[][] state2 = new int[][]{ //2 ruchy
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 0, 12},
                {13, 14, 11, 15}
        };
        int[][] state3 = new int[][]{ //3 ruchy
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 0, 10, 12},
                {13, 14, 11, 15}
        };

        int[][] state666 = new int[][]{ //da sie rozwiazac
                {0, 1, 2, 3},
                {5, 6, 8, 4},
                {9, 10, 7, 12},
                {13, 14, 11, 15}
        };

        int[][] state6 = new int[][]{ //da sie latwo rozwiazac - 6 ruchow
                {0, 1, 2, 4},
                {5, 6, 3, 8},
                {9, 10, 7, 12},
                {13, 14, 11, 15}
        };
        int[][] state7 = new int[][]{ //da sie latwo rozwiazac - 7 ruchow
                {5, 1, 2, 4},
                {9, 6, 3, 8},
                {0, 10, 7, 12},
                {13, 14, 11, 15}
        };

        FileUtils.saveData("plik3.txt", new Board(state666));
        Board instance = FileUtils.loadData("plik3.txt");

        //instance = BoardUtils.randomizeBoard(4, 4, 3);

        PuzzleSolver solver = new IterativeDepthFirstSearch("wsad", 20);
        //solver = new DepthFirstSearch("wsad", 20);
        //solver = new BestFirstSearch(new MisplacedComparator());
        //solver = new BestFirstSearch(new ManhattanDistanceComparator());
        //solver = new AStarSearch(new MisplacedComparator(), 10);
        //solver = new IterativeAStarSearch(new MisplacedComparator(), 10);
        solver = new BreadthFirstSearch("wsad", 20);

        PrintStream stream = null;
        try {
            String className = solver.getClass().getName();
            stream = new PrintStream(new FileOutputStream("path_" + className + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku");
        }

        Board solved = solver.solve(instance, stream);

        if (solved != null) {
            BoardUtils.printBoard(solved);
        } else {
            System.out.println("Algorytm nie znalazł rozwiązania");
        }

        System.exit(0);
    }
}
