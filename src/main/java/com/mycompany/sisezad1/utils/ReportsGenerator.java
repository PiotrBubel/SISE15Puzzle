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
        } else {
            if (solver.getOrder() != null) {
                streamReport.println("Kolejnosc: " + solver.getOrder());
            } else{
                streamReport.println("--blad--");
            }
        }
        streamReport.println("Wielkosc planszy: " + toSolve.getState().length + "x" + toSolve.getState()[0].length);

        streamReport.println();

        double timeInMiliseconds = solver.getTime() / 1000000d;
        streamReport.println("Czas rozwiazywania: " + timeInMiliseconds + "ms");
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
