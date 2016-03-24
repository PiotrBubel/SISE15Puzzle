/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Piotrek
 */
public class ConsoleManualMode {

    public static Board manualMode(Board board) {
        Board current = board;
        boolean end = true;
        System.out.println("Program przeszedł w tryb manualny. Naciskaj w,a,s,d aby poruszać polem 0. Naciśnij 'p' aby wyjść.");
        current.print();

        do {
            try {
                switch (System.in.read()) {
                    case 'w':
                        if (board.canMoveUp()) {
                            current = board.moveUp();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        current.print();
                        break;
                    case 's':
                        if (board.canMoveDown()) {
                            current = board.moveDown();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        current.print();
                        break;
                    case 'a':
                        if (board.canMoveLeft()) {
                            current = board.moveLeft();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        current.print();
                        break;
                    case 'd':
                        if (board.canMoveRight()) {
                            current = board.moveRight();
                        } else {
                            System.out.println("Nie da sie.");
                        }
                        current.print();
                        break;
                    case 'p':
                        end = false;
                        current.print();
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
}