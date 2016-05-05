package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.solvers.BreadthFirstSearch;
import com.mycompany.sisezad1.solvers.DepthFirstSearch;
import com.mycompany.sisezad1.solvers.IterativeDepthFirstSearch;
import com.mycompany.sisezad1.solvers.PuzzleSolver;
import com.sun.xml.internal.fastinfoset.Decoder;

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
        System.out.println("Removed " + deleted);

        return al;
    }

    /**
     * Mathod generates all states of 4x4 board in given depth, then saves them to files Mathod also
     * creates file with all paths
     */
    public static void generateAllStates(String fileName, int depth) {
        boolean tmp = Board.SIMPLE_LOOP_CONTROL;
        Board.SIMPLE_LOOP_CONTROL = true;
        int[][] unsolvableState = new int[][]{ //D
                {1, 3, 4, 8},
                {5, 2, 10, 7},
                {9, 6, 1, 12},
                {13, 14, 11, 0}
        };
        Board unsolvable = new Board(unsolvableState);
        PuzzleSolver bfs = new BreadthFirstSearch("rrrr", 6);

        PrintStream streamPaths = null;
        try {
            streamPaths = new PrintStream(new FileOutputStream("_" + fileName + "_Paths.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad podczas tworzenia pliku paths");
        }


        bfs.solve(unsolvable, streamPaths);
        int generated = ReportsGenerator.countLinesInFile("_" + fileName + "_Paths.txt");
        System.out.println("Generated " + (generated - 1) + " paths");

        List<String> paths = FileUtils.loadPaths("_" + fileName + "_Paths.txt");


        for (int i = 0; i < generated; i++) {
            Board toSave = BoardUtils.buildArrangedBoard(4, 4);
            toSave = toSave.allMoves(paths.get(i));
            FileUtils.saveBoard(paths.get(i).length() + fileName + i + ".txt", toSave);
        }


        Board.SIMPLE_LOOP_CONTROL = tmp;
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
