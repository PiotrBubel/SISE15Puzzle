/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Piotrek
 */
public class ConsoleMode {

    public static Board manualMode(Board board) {
        Board current = board;
        boolean end = true;
        Board.STRONG_LOOP_CONTROL = false;

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
                Logger.getLogger(ConsoleMode.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (current.isCorrect() && end) {
                System.out.println("Zagadka zostala ulozona.");
            }

        } while (!current.isCorrect() && end);

        return current;
    }

    public static void mainLoop(String[] args) {
        PuzzleSolver solver = null;

        PuzzleSolver.DEFAULT_MAX_DEPTH = 25; //uzywane gdy nie podajemy maksymalnej glebokosci w konstruktorze

        if (args.length >= 1) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
            }

            switch (args[0]) {
                case "--bfs":
                case "-b":
                    if (args.length >= 2) {
                        solver = new BreadthFirstSearch(args[1]);
                    } else {
                        solver = new BreadthFirstSearch();
                    }
                    break;
                case "--dfs":
                case "-d":
                    if (args.length >= 2) {
                        solver = new DepthFirstSearch(args[1]);
                    } else {
                        solver = new DepthFirstSearch();
                    }
                    break;
                case "--idfs":
                case "-i":
                    if (args.length >= 3) {
                        solver = new IterativeDepthFirstSearch(args[1], Integer.parseInt(args[2]));
                    } else if (args.length == 2) {
                        solver = new IterativeDepthFirstSearch(args[1]); //default depth
                    } else {
                        solver = new IterativeDepthFirstSearch();
                    }
                    break;
                case "--a":
                case "-a":
                    if (args.length >= 3) {
                        switch (args[1]) {
                            case "a":
                                if (args[2] == "1") {
                                    solver = new BestFirstSearch(new AMisplacedComparator());
                                } else if (args[2] == "2") {
                                    solver = new BestFirstSearch(new AManhattanDistanceComparator());
                                } else {
                                    solver = new BestFirstSearch(new AMisplacedComparator());
                                }
                                break;
                            case "ida":
                                if (args[2] == "1") {
                                    solver = new IterativeAStarSearch(new AMisplacedComparator());
                                } else if (args[2] == "2") {
                                    solver = new IterativeAStarSearch(new AManhattanDistanceComparator());
                                } else {
                                    solver = new IterativeAStarSearch(new AMisplacedComparator());
                                }
                                break;
                            case "bf":
                                if (args[2] == "1") {
                                    solver = new BestFirstSearch(new MisplacedComparator());
                                } else if (args[2] == "2") {
                                    solver = new BestFirstSearch(new ManhattanDistanceComparator());
                                } else {
                                    solver = new BestFirstSearch(new MisplacedComparator());
                                }
                                break;
                            case "cbf":
                                if (args[2] == "1") {
                                    solver = new CustomBestFirstSearch(new MisplacedComparator());
                                } else if (args[2] == "2") {
                                    solver = new CustomBestFirstSearch(new ManhattanDistanceComparator());
                                } else {
                                    solver = new CustomBestFirstSearch(new MisplacedComparator());
                                }
                                break;
                            default:
                                System.out.println("Podano bledny argument");
                                break;
                        }
                    } else if (args.length == 2) {
                        switch (args[1]) {
                            case "a":
                                solver = new BestFirstSearch(new AMisplacedComparator());
                                break;
                            case "ida":
                                solver = new IterativeAStarSearch(new AMisplacedComparator());
                                break;
                            case "bf":
                                solver = new IterativeAStarSearch(new MisplacedComparator());
                                break;
                            case "cbf":
                                solver = new CustomBestFirstSearch(new MisplacedComparator());
                                break;
                            default:
                                System.out.println("Podano bledny argument");
                                break;
                        }
                    }
                    break;

                default:
                    System.out.println("Podano bledny argument");
                    break;
            }
        } else {
            System.out.println("Program uruchomiony bez poprawnych argumentow");
        }

        if (solver == null) {
            solver = new BreadthFirstSearch("rrrr", 10);
        }

        System.out.println("Uruchomi sie algorytm: " + solver.getClass().getSimpleName());
        if (solver.getHeuristicFunction() == null) {
            System.out.println("Kolejnosc: " + solver.getOrder());
        } else {
            System.out.println("Heurystyka: " + solver.getHeuristicFunction().getClass().getSimpleName());
        }

        Board instance = null;
        while (instance == null) {
            try {
                instance = ConsoleMode.loadBoardFromUser();
            } catch (Exception e) {
                System.out.println("Nastapil blad, sprobuj ponownie");
            }
        }

        PrintStream stream = null;
        try {
            String className = solver.getClass().getSimpleName();
            stream = new PrintStream(new FileOutputStream("path_" + className + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku path");
        }

        Board solved = solver.solve(instance, stream);

        if (solved != null) {
            System.out.println(solved.getPath().length());
            System.out.println(solved.getPath());
        } else {
            System.out.println("-1");
        }
    }

    public static Board loadBoardFromUser() throws IOException {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Wprowadz wymiary planszy (wiersze kolumny)");
        String line = br.readLine();
        String[] splittedLine = line.split(" ");
        int rows = Integer.parseInt(splittedLine[0]);
        int columns = Integer.parseInt(splittedLine[1]);
        int[][] state = new int[columns][rows];

        System.out.println("Wprowadz stan planszy (wiersze po kolei, ze spacja miedzy liczbami)");
        for (int i = 0; i < columns; i++) {
            line = br.readLine();
            splittedLine = line.split(" ");
            for (int j = 0; j < rows; j++) {
                state[i][j] = Integer.parseInt(splittedLine[j]);
            }
        }
        System.out.println("Poprawnie wprowadzono plansze");

        return new Board(state);
    }

    public static void oldMain() {

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

        FileUtils.saveBoard("zzz.txt", new Board(state6));
        Board instance = FileUtils.loadBoard("zzz.txt");

        //instance = BoardUtils.randomizeBoard(4, 4, 10);

        //FileUtils.saveBoard("plikTestowy.txt", instance);

        Board.STRONG_LOOP_CONTROL = false;


        PuzzleSolver solver;// = new IterativeDepthFirstSearch("wsad", 20);
        //solver = new DepthFirstSearch("wsad", 15);
        //solver = new CustomBestFirstSearch(new MisplacedComparator());
        //solver = new CustomBestFirstSearch(new ManhattanDistanceComparator());
        //solver = new BestFirstSearch(new AMisplacedComparator(), 20);             //A* with not-A comparator acts as regular best-first search
        solver = new IterativeAStarSearch(new AMisplacedComparator(), 20);    //A* with not-A comparator acts as regular best-first search
        //solver = new BreadthFirstSearch("wsad", 10);

        //ReportsGenerator.solveWithReport(solver, "report1", instance);

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

    }
}
