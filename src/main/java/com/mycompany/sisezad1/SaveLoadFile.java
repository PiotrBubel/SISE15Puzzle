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
    public static Board loadData3(String filePath) {
        //String x;
        //String y;
        int[][] state;

        ArrayList<String> lines = new ArrayList<String>();
        int rowCount = 0;

        try (Scanner in = new Scanner(new FileReader(filePath))) {
            while (in.hasNextLine()) {
                lines.add(in.nextLine());
            }
            //System.out.println("rowCount: " + rowCount);

        } catch (FileNotFoundException ex) {
            System.err.println("Wystapil blad przy zapisywaniu do pliku: " + ex.getMessage());
        }
        ArrayList<String[]> l = new ArrayList<>();
        for (String line : lines) {
            l.add(line.split("\t"));
        }

        System.out.println(lines.size()); //x, w dół
        System.out.println(l.get(0).length); //y, wszerz

        state = new int[lines.size()][l.get(0).length];
        
        for (int x = 0; x < state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                state[x][y] = Integer.parseInt(l.get(x)[y]);
            }
        }

        return new Board(state);
        //System.out.println("rowCount: " + rowCount);
        //System.out.println(lines.toString());
        //System.out.println(l.get(0)[2]);
    }
}
