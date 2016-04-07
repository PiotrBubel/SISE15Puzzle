/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sisezad1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Piotrek
 */
public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
		
		
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCorrect method, of class Board.
     */
    @org.junit.Test
    public void testIsCorrect() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testIsCorrectFalse() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        boolean expResult = false;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testFindZero() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        int[] expResult = new int[2];
        expResult[0] = 3;
        expResult[1] = 2;
        int[] result = instance.findZero();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
    }

    /**
     * Test of countMisplaced method, of class Board.
     */
    @Test
    public void testCountMisplaced() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        int expResult = 2;
        int result = instance.countMisplaced();
        assertEquals(expResult, result);

        state = new int[][]{
            {2, 1, 4, 3},
            {6, 5, 8, 7},
            {10, 9, 12, 11},
            {0, 15, 14, 13}
        };

        instance = new Board(state);
        expResult = 16;
        result = instance.countMisplaced();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveRight method, of class Board.
     */
    @Test
    public void testCanMoveRight() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveRight();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveRight();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveLeft method, of class Board.
     */
    @Test
    public void testCanMoveLeft() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveLeft();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {0, 14, 15, 13}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveUp method, of class Board.
     */
    @Test
    public void testCanMoveUp() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveUp();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 0},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 4}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveDown method, of class Board.
     */
    @Test
    public void testCanMoveDown() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        boolean expResult = false;
        boolean result = instance.canMoveDown();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 0},
            {13, 14, 15, 12}
        };

        instance = new Board(state);
        expResult = true;
        result = instance.canMoveDown();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveDown method, of class Board.
     */
    @Test
    public void testPrint() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board instance = new Board(state);
        instance.print(System.out);
    }

    /**
     * Test of getState method, of class Board.
     */
    @Test
    public void testGetState() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };
        Board instance = new Board(state);
        int[][] result = instance.getState();
        assertArrayEquals(state, result);
    }

    /**
     * Test of moveRight method, of class Board.
     */
    @Test
    public void testMoveRight() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveRight();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveLeft method, of class Board.
     */
    @Test
    public void testMoveLeft() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 0, 14, 15}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveLeft();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveUp method, of class Board.
     */
    @Test
    public void testMoveUp() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 12},
            {13, 14, 11, 15}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveUp();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveDown method, of class Board.
     */
    @Test
    public void testMoveDown() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 12},
            {13, 14, 11, 15}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 0, 15}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveDown();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    //---------------------------------------------------------------------------
    /**
     * Test of isCorrect method, of class Board.
     */
    @org.junit.Test
    public void testIsCorrectUnsymmetrical() {

        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testIsCorrectFalseUnsymmetrical() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 11},};

        Board instance = new Board(state);
        boolean expResult = false;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
    }

    @org.junit.Test
    public void testFindZeroUnsymmetrical() {

        int[][] state = new int[][]{
            {1, 3, 2},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board instance = new Board(state);
        int[] expResult = new int[2];
        expResult[0] = 3;
        expResult[1] = 2;
        int[] result = instance.findZero();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
    }

    /**
     * Test of countMisplaced method, of class Board.
     */
    @Test
    public void testCountMisplacedUnsymmetrical() {

        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 11},};

        Board instance = new Board(state);
        int expResult = 2;
        int result = instance.countMisplaced();
        assertEquals(expResult, result);

        state = new int[][]{
            {2, 3, 1},
            {6, 4, 5},
            {8, 9, 7},
            {11, 0, 10}
        };

        instance = new Board(state);
        expResult = 12;
        result = instance.countMisplaced();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveRight method, of class Board.
     */
    @Test
    public void testCanMoveRightUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 0, 11}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveRight();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 0}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveRight();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveLeft method, of class Board.
     */
    @Test
    public void testCanMoveLeftUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveLeft();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {0, 9, 10, 11}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveLeft();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveUp method, of class Board.
     */
    @Test
    public void testCanMoveUpUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 0, 11}
        };

        Board instance = new Board(state);
        boolean expResult = true;
        boolean result = instance.canMoveUp();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 0},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 3}
        };

        instance = new Board(state);
        expResult = false;
        result = instance.canMoveUp();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveDown method, of class Board.
     */
    @Test
    public void testCanMoveDownUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board instance = new Board(state);
        boolean expResult = false;
        boolean result = instance.canMoveDown();
        assertEquals(expResult, result);

        state = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 0},
            {9, 10, 11, 8},};

        instance = new Board(state);
        expResult = true;
        result = instance.canMoveDown();
        assertEquals(expResult, result);
    }

    /**
     * Test of canMoveDown method, of class Board.
     */
    @Test
    public void testPrintUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board instance = new Board(state);
        instance.print(System.out);
    }

    /**
     * Test of getState method, of class Board.
     */
    @Test
    public void testGetStateUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };
        Board instance = new Board(state);
        int[][] result = instance.getState();
        assertArrayEquals(state, result);
    }

    /**
     * Test of moveRight method, of class Board.
     */
    @Test
    public void testMoveRightUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 0, 11}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveRight();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveLeft method, of class Board.
     */
    @Test
    public void testMoveLeftUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 0, 11}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveLeft();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveUp method, of class Board.
     */
    @Test
    public void testMoveUpUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0},
            {10, 11, 9}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveUp();

        assertArrayEquals(expResult.getState(), result.getState());
    }

    /**
     * Test of moveDown method, of class Board.
     */
    @Test
    public void testMoveDownUnsymmetrical() {
        int[][] state = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0},
            {10, 11, 9}
        };
        Board instance = new Board(state);
        int[][] state2 = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 0}
        };

        Board expResult = new Board(state2);
        Board result = instance.moveDown();

        assertArrayEquals(expResult.getState(), result.getState());
    }
}
