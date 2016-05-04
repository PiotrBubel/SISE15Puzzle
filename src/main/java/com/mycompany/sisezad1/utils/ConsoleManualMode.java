/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Piotrek
 */
public class ConsoleManualMode {

    public static Board manualMode(Board board) {
        Board current = board;
        boolean end = true;
        Board.LOOP_CONTROL = false;

        System.out.println("Program przeszedł w tryb manualny. Naciskaj w,a,s,d aby poruszać polem 0. Naciśnij 'p' aby wyjść.");
        BoardUtils.printBoard(current);
        do {
            try {
                switch (System.in.read()) {
                    case 'w':
                        if (board.canMoveUp()) {
                            current = board.moveUp();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        BoardUtils.printBoard(current);
                        break;
                    case 's':
                        if (board.canMoveDown()) {
                            current = board.moveDown();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        BoardUtils.printBoard(current);
                        break;
                    case 'a':
                        if (board.canMoveLeft()) {
                            current = board.moveLeft();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        BoardUtils.printBoard(current);
                        break;
                    case 'd':
                        if (board.canMoveRight()) {
                            current = board.moveRight();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        BoardUtils.printBoard(current);
                        break;
                    case 'p':
                        end = false;
                        BoardUtils.printBoard(current);
                        System.out.println("Zakończono tryb manualny");
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ConsoleManualMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (current.isCorrect() && end) {
                System.out.println("Zagadka zostala ulozona.");
            }

        } while (!current.isCorrect() && end);

        return current;
    }

    public static void mainLoop(String[] args) {
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
                    if (args.length > 1) {
                        solver = new IterativeDepthFirstSearch(args[1], Integer.parseInt(args[2]));
                    } else {
                        solver = new IterativeDepthFirstSearch(args[1], 8); //default depth
                    }
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
                //TODO ogarnac wlaczanie algorytmu best first, a* i wybieranie heurystyki

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

        FileUtils.saveData("plik3.txt", new Board(state7));
        Board instance = FileUtils.loadData("plik3.txt");

        instance = BoardUtils.randomizeBoard(4, 4, 10);

        //FileUtils.saveData("plikTestowy.txt", instance);

        Board.LOOP_CONTROL = false;


        PuzzleSolver solver = new IterativeDepthFirstSearch("wsad", 20);
        //solver = new DepthFirstSearch("wsad", 15);
        Board.LOOP_CONTROL = true;
        //solver = new BestFirstSearch(new MisplacedComparator());  //FIXME requires loop control enabled
        //solver = new BestFirstSearch(new ManhattanDistanceComparator()); //FIXME requires loop control enabled
        Board.LOOP_CONTROL = false;
        //solver = new AStarSearch(new AMisplacedComparator(), 20);             //A* with not-A comparator acts as regular best-first search
        //solver = new IterativeAStarSearch(new AMisplacedComparator(), 20);    //A* with not-A comparator acts as regular best-first search
        solver = new BreadthFirstSearch("wsad", 10);

        ReportsGenerator.solveWithReport(solver, "report1", instance);

        //TODO ilość odwiedzonych stanów (sprawdzonych) - DONE
        //TODO ilość stanów przetworzonych (dodanych do kolejki itp.) (stworzonych Boardow)

        PrintStream stream = null;
        try {
            String className = solver.getClass().getSimpleName();
            stream = new PrintStream(new FileOutputStream("path_" + className + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku");
        }

        Board solved = solver.solve(instance, null);

        if (solved != null) {
            BoardUtils.printBoard(solved);
        } else {
            System.out.println("Algorytm nie znalazł rozwiązania");
        }

        System.exit(0);
    }
}
