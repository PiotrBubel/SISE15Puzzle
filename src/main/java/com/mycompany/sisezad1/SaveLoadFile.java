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
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    //TODO
    public static void loadData(String filePath) {
        String x;
        String y;
        int[][] state;

        ArrayList<Integer> values = new ArrayList<Integer>();
        int rowCount = 0;
        boolean flaga = true;
        try (Scanner in = new Scanner(new FileReader(filePath))) {
            while (in.hasNext()) {
                in.useDelimiter(" ");
                values.add(Integer.parseInt(in.next()));
                if (flaga) {
                    flaga = false;
                    rowCount++;
                }
                in.useDelimiter(" ");
                if (in.hasNext()) {
                    in.useDelimiter("\n");
                    values.add(Integer.parseInt(in.next()));
                }
                System.out.println("rowCount: " + rowCount);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Wystapil blad przy zapisywaniu do pliku: " + ex.getMessage());
        }
        //return new Board();
    }
    
    //TODO
    public static void loadData3(String filePath) {
        String x;
        String y;
        int[][] state;

        ArrayList<Integer> values = new ArrayList<Integer>();
        int rowCount = 0;
        try (Scanner in = new Scanner(new FileReader(filePath))) {
            while (in.hasNextInt()) {
                in.useDelimiter(" ");
                values.add(in.nextInt());
                in.useDelimiter(" ");
                if (in.hasNextInt()) {
                    in.useDelimiter("\n");
                    //rowCount++;
                    values.add(in.nextInt());
                }
                //System.out.println("rowCount: " + rowCount);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Wystapil blad przy zapisywaniu do pliku: " + ex.getMessage());
        }
        //return new Board();
        //System.out.println("rowCount: " + rowCount);
        System.out.println(values.toString());
    }
}
