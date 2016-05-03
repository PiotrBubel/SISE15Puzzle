package com.mycompany.sisezad1;

import com.mycompany.sisezad1.solvers.BreadthFirstSearch;
import com.mycompany.sisezad1.solvers.DepthFirstSearch;
import com.mycompany.sisezad1.solvers.PuzzleSolver;
import com.mycompany.sisezad1.utils.BoardUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author Sebastian
 */
public class TestMain {

    /**
     * Klasa tylko dla sprawdzania algorytmow zeby nie psuć maina
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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

        Board instance = new Board(state666);
        PuzzleSolver solver;
        solver = new BreadthFirstSearch("wsad", 20);

        PrintStream stream = null;
        try {
            stream = new PrintStream(new FileOutputStream("path.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Blad podczas tworzenia pliku");
        }

        Board solved = solver.solve(instance, stream);

        if (solved != null) {
            BoardUtils.printBoard(solved);
        } else {
            System.out.println("algorytm nie znalazł rozwiązania");
        }
    }
}
