package com.mycompany.sisezad1.utils;

import com.mycompany.sisezad1.Board;
import com.mycompany.sisezad1.solvers.AStarSearch;
import com.mycompany.sisezad1.solvers.BestFirstSearch;
import com.mycompany.sisezad1.solvers.IterativeAStarSearch;
import com.mycompany.sisezad1.solvers.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

/**
 * Created by Piotrek on 04.05.2016.
 */
public class ReportsGenerator {

    public static void solveWithReport(PuzzleSolver solver, String reportFilePrefix, Board toSolve) {

        String className = solver.getClass().getSimpleName();

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
        }
        if (solver.getOrder() != null) {
            streamReport.println("Kolejnosc: " + solver.getOrder());
        }
        streamReport.println("Wielkosc planszy: " + toSolve.getState().length + "x" + toSolve.getState()[0].length);

        streamReport.println();

        streamReport.println("Czas rozwiazywania: " + solver.getTime() + "ms");
        streamReport.println("Stworzonych stanow: " + solver.getCreatedBoards());

        streamReport.println("Sprawdzonych stanow: " + countLinesInFile("_" + reportFilePrefix + className + "_Paths.txt"));


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
        streamReport.close();
    }


    public static void solveWithReport(PuzzleSolver solver, String reportFilePrefix, String boardFileName) {
        ReportsGenerator.solveWithReport(solver, reportFilePrefix, FileUtils.loadData(boardFileName));
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
}
