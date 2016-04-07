/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Piotrek
 */
public class SaveLoadFile {

    public static void saveData(String filePath, Board board) {
        try (PrintStream out = new PrintStream(new FileOutputStream(filePath))) {
            board.print(out);
            /*int[][] state = board.getState();
             for (int x = 0; x < state[0].length; x++) {
             for (int y = 0; y < state.length; y++) {
             out.print(state[x][y]);
             out.print(" ");
             }
             out.println("");
             }*/
        } catch (FileNotFoundException ex) {
            System.err.println("Wystapil blad przy zapisywaniu do pliku: " + ex.getMessage());
        }
    }

    public static Board loadData3(String filePath) {
        int[][] state;

        ArrayList<String> lines = new ArrayList<String>();

        try (Scanner in = new Scanner(new FileReader(filePath))) {
            while (in.hasNextLine()) {
                lines.add(in.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Wystapil blad przy zapisywaniu do pliku: " + ex.getMessage());
        }
        ArrayList<String[]> l = new ArrayList<>();
        for (String line : lines) {
            l.add(line.split("\t"));
        }

        state = new int[lines.size()][l.get(0).length];

        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                state[x][y] = Integer.parseInt(l.get(x)[y]);
            }
        }

        return new Board(state);
    }
}
