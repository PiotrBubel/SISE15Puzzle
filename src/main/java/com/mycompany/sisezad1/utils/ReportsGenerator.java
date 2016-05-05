package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.heuristics.*;
import com.mycompany.sisezad1.solvers.*;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Piotrek on 04.05.2016.
 */
public class ReportsGenerator {

    public static Board solveWithReport(PuzzleSolver solver, String reportFilePrefix, Board toSolve) {

        String className = solver.getClass().getSimpleName();

        FileUtils.saveBoard("_" + reportFilePrefix + className + "_FirstBoard.txt", toSolve);

        PrintStream streamPaths = null;
        try {
            streamPaths = new PrintStream(new FileOutputStream("_" + reportFilePrefix + className + "_Paths.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku paths");
        }


        Board solvedBoard = solver.solve(toSolve, streamPaths);
        streamPaths.close();

        PrintStream streamReport = null;
        try {
            streamReport = new PrintStream(new FileOutputStream("_" + reportFilePrefix + className + "_Report.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku raportu");
        }
        if (streamReport == null) {
            streamReport = System.out;
        }


        streamReport.println("Algorytm: " + className);
        if (solver.getHeuristicFunction() != null) {
            streamReport.println("Heurystyka: " + solver.getHeuristicFunction().getClass().getSimpleName());
        } else {
            if (solver.getOrder() != null) {
                streamReport.println("Kolejnosc: " + solver.getOrder());
            } else {
                streamReport.println("--blad--");
            }
        }
        streamReport.println("Ustawiona maksymalna glebokosc: " + solver.getMaxDepth());
        streamReport.println("Wielkosc planszy: " + toSolve.getState().length + "x" + toSolve.getState()[0].length);
        streamReport.println("Ochrona przed prostymi petlami: " + Board.SIMPLE_LOOP_CONTROL);
        streamReport.println("Ochrona przed zlozonymi petlami: " + Board.STRONG_LOOP_CONTROL);

        streamReport.println();
        DecimalFormat df = new DecimalFormat("0.00");

        double timeInMiliseconds = solver.getTimeInMilis();
        streamReport.println("Czas rozwiazywania: " + df.format(timeInMiliseconds) + "ms");
        streamReport.println("Stworzonych stanow: " + solver.getCreatedBoards());
        streamReport.println("Sprawdzonych stanow: " + countLinesInFile("_" + reportFilePrefix + className + "_Paths.txt"));

        //streamReport.println("Sredni czas rozwiazywania dla " + loopLength + " przypadkow: "
        //        + df.format(ReportsGenerator.countAvgTime(solver, toSolve, loopLength)) + "ms");

        streamReport.println();

        if (solvedBoard != null) {
            if (solvedBoard.getPath() != null && !solvedBoard.getPath().isEmpty()) {
                streamReport.println("Ilosc ruchow: " + solvedBoard.getPath().length());
                streamReport.println("Sciezka: " + solvedBoard.getPath());
            } else {
                streamReport.println("Brak sciezki");
            }
        } else {
            streamReport.println("Algorytm nie znalazl rozwiazania");
        }

        streamReport.println();
        streamReport.println("Plansza startowa: ");

        int[][] state = toSolve.getState();
        streamReport.println("---------");
        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                streamReport.print(state[x][y]);
                if (y < state[0].length - 1) {
                    streamReport.print("\t");
                }
            }
            if (x < state.length - 1) {
                streamReport.println();
            }
        }
        streamReport.println();
        streamReport.println("--end of report--");

        streamReport.close();

        System.out.println("Koniec tworzenia plikow raportu " + reportFilePrefix);

        return solvedBoard;
    }


    public static void solveWithReport(PuzzleSolver solver, String reportFilePrefix, String boardFileName, int loopLength) {
        ReportsGenerator.solveWithReport(solver, reportFilePrefix, FileUtils.loadBoard(boardFileName));
    }

    private static int countLinesInFile(String fileName) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> removeDuplicates(List<String> withDuplicates) {
        int deleted = withDuplicates.size();
        List<String> al = new ArrayList<>();
        al.addAll(withDuplicates);
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        deleted = deleted - al.size();
        if (deleted != 0) {
            System.out.println("Removed " + deleted);
        }
        return al;
    }

    /**
     * Method generates all states of 4x4 board in given depth
     *
     * @param fileName    name of file with paths, and prefix to state files if saveToFiles == true
     * @param saveToFiles true - saves boards to files
     */
    public static List<Board> generateAllStates(String fileName, int depth, boolean saveToFiles) {
        boolean tmp = Board.SIMPLE_LOOP_CONTROL;
        Board.SIMPLE_LOOP_CONTROL = true;
        int[][] unsolvableState = new int[][]{ //D
                {1, 3, 4, 8},
                {5, 2, 10, 7},
                {9, 6, 1, 12},
                {13, 14, 11, 0}
        };
        Board unsolvable = new Board(unsolvableState);
        PuzzleSolver bfs = new BreadthFirstSearch("rrrr", depth);

        PrintStream streamPaths = null;
        try {
            streamPaths = new PrintStream(new FileOutputStream("_" + fileName + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku paths");
        }

        bfs.solve(unsolvable, streamPaths);
        int generated = ReportsGenerator.countLinesInFile("_" + fileName + ".txt");

        List<String> paths = FileUtils.loadPaths("_" + fileName + ".txt");
        paths = ReportsGenerator.removeDuplicates(paths);

        List<Board> generatedBoards = new ArrayList<>();

        for (int i = 0; i < generated; i++) {
            if (paths.get(i).length() == depth) {
                Board toSave = BoardUtils.buildArrangedBoard(4, 4);
                toSave = toSave.allMoves(paths.get(i));
                if (toSave != null) {
                    generatedBoards.add(toSave);
                }
                if (saveToFiles) {
                    FileUtils.saveBoard(paths.get(i).length() + fileName + i + ".txt", toSave);
                }
            }
        }

        Board.SIMPLE_LOOP_CONTROL = tmp;
        System.out.println("Generated " + generatedBoards.size() + " boards");
        return generatedBoards;
    }


    public static void generateGeneralStatistics(String fileName, int depth, int algorithmsMaxDepth, String order) {
        List<Board> boards = ReportsGenerator.generateAllStates("path", depth, false);

        List<PuzzleSolver> solvers = ReportsGenerator.createAllSolvers(order, algorithmsMaxDepth);
        double[] avgCreated = new double[solvers.size()];
        double[] avgChecked = new double[solvers.size()];
        double[] avgTime = new double[solvers.size()];
        double[] avgPath = new double[solvers.size()];
        int[] notSolved = new int[solvers.size()];

        String tmpFilePath = "_generalReportTMP" + fileName + ".txt";
        for (int b = 0; b < boards.size(); b++) {
            solvers = ReportsGenerator.createAllSolvers(order, algorithmsMaxDepth);
            for (int s = 0; s < solvers.size(); s++) {
                PrintStream streamPaths = null;
                try {
                    streamPaths = new PrintStream(new FileOutputStream(tmpFilePath));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Blad podczas tworzenia pliku paths");
                }

                Board solved = solvers.get(s).solve(new Board(boards.get(b).getState()), streamPaths);

                avgChecked[s] = avgChecked[s] + (double) ReportsGenerator.countLinesInFile(tmpFilePath);
                avgCreated[s] = avgCreated[s] + (double) solvers.get(s).getCreatedBoards();
                avgTime[s] = avgTime[s] + solvers.get(s).getTimeInMilis();
                if (solved != null) {
                    avgPath[s] = avgPath[s] + solved.getPath().length();
                    //System.out.println(solved.getPath().length());
                } else {
                    notSolved[s]++;
                }
            }
        }

        ReportsGenerator.countAvg(avgCreated, boards.size());
        ReportsGenerator.countAvg(avgChecked, boards.size());
        ReportsGenerator.countAvg(avgTime, boards.size());

        PrintStream reportStream = null;
        try {
            reportStream = new PrintStream(new FileOutputStream("_" + fileName + ".txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku paths");
        }
        reportStream.println("maksymalna glebokosc algorytmow: " + algorithmsMaxDepth);
        reportStream.println("Ochrona przed prostymi petlami: " + Board.SIMPLE_LOOP_CONTROL);
        reportStream.println("Ochrona przed zlozonymi petlami: " + Board.STRONG_LOOP_CONTROL);

        DecimalFormat df = new DecimalFormat("0.00");

        for (int s = 0; s < solvers.size(); s++) {
            PuzzleSolver actual = solvers.get(s);
            avgPath[s] = avgPath[s] / (boards.size() - notSolved[s]);
            String additionalAlgData;
            if (actual.getHeuristicFunction() != null) {
                additionalAlgData = " heurystyka: " + actual.getHeuristicFunction().getClass().getSimpleName();
            } else {
                additionalAlgData = "kolejnosc: " + actual.getOrder();
            }

            reportStream.println(actual.getClass().getSimpleName() + "  " + additionalAlgData);
            reportStream.println("srednia dlugosc rozwiazania: \t" + df.format(avgPath[s]));
            reportStream.println("nie znaleziono rozwiazania dla: \t" + notSolved[s]);
            reportStream.println("sredni czas: \t\t\t" + df.format(avgTime[s]));
            reportStream.println("srednia ilosc sprawdzonych: \t" + df.format(avgChecked[s]));
            reportStream.println("srednia ilosc stworzonych: \t" + df.format(avgCreated[s]));

            reportStream.println();
        }
        reportStream.close();
        System.out.println("Stworzono plik z ogolnym raportem " + fileName + " dla glebokosci " + depth);
    }

    private static void countAvg(double[] avg, int size) {
        for (int i = 0; i < avg.length; i++) {
            avg[i] = avg[i] / (double) size;
        }
    }


    public static List<PuzzleSolver> createAllSolvers(String order, int maxDepth) {
        List<PuzzleSolver> solvers = new ArrayList<>();

        //A*
        solvers.add(new AStarSearch(new AManhattanDistanceComparator(), maxDepth));
        solvers.add(new AStarSearch(new AMisplacedComparator(), maxDepth));
        //Best-first
        solvers.add(new AStarSearch(new ManhattanDistanceComparator(), maxDepth));
        solvers.add(new AStarSearch(new MisplacedComparator(), maxDepth));
        //custom best-first
        solvers.add(new BestFirstSearch(new ManhattanDistanceComparator()));
        solvers.add(new BestFirstSearch(new MisplacedComparator()));
        //iterative a*
        solvers.add(new IterativeAStarSearch(new AManhattanDistanceComparator(), maxDepth));
        solvers.add(new IterativeAStarSearch(new AMisplacedComparator(), maxDepth));
        //DFS
        solvers.add(new DepthFirstSearch(order, maxDepth));
        //itarative DFS
        solvers.add(new IterativeDepthFirstSearch(order, maxDepth));
        //BFS
        solvers.add(new BreadthFirstSearch(order, maxDepth));

        return solvers;
    }

    /**
     * Avarage time is much smaller than time of first solving, this may be caused by processor
     * cache.
     *
     * @return algorithm execution time in ms
     */
    public static double countAvgTime(PuzzleSolver solver, Board notSolvedBoard, int ile) {
        double avgTime = 0;
        int notSolved1 = 0;
        Board solved = null;

        for (int x = 1; x <= ile; x++) {
            //TODO clear processor cache here - is it even possible in java?
            solved = solver.solve(notSolvedBoard, null);
            avgTime = avgTime + solver.getTimeInMilis();
            //System.out.println(x + ": " + solver.getTimeInMilis());
        }
        avgTime = avgTime / (double) ile;
        return avgTime;
    }
}
