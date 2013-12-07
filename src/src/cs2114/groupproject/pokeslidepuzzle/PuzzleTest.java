package cs2114.groupproject.pokeslidepuzzle;

// -------------------------------------------------------------------------
/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author Tim
 *  @version Nov 14, 2013
 */
public class PuzzleTest
    extends student.TestCase
{
    private Puzzle puzzle;

    /**
     * Sets the initial state of the test class.
     */
    public void setUp()
    {
        puzzle = new Puzzle(3);

        /**
         * Board is laid out like this:
         *
         *     0    1    2
         *
         * 0  [0]  [1]  [2]
         *
         * 1  [3]  [4]  [5]
         *
         * 2  [6]  [7]  [8]
         *
         * Each tile has a number reference 1 through ((size^2) - 1)
         * [8] is automatically set to be the blank space in the constructor
         * of the Puzzle class.
         */
    }

    /**
     * Tests size() from the Puzzle class.
     */
    public void testSize()
    {
        assertEquals(puzzle.size(), 3);
    }

    /**
     * Tests getCell() from the Puzzle class.
     */
    public void testGetCell()
    {
        Location temp = new Location(0, 0);
        assertEquals(puzzle.getCell(temp), 0);

        Location temp2 = new Location(2, 2);
        assertEquals(puzzle.getCell(temp2), 8);
    }

    /**
     * Tests adjacentToBlankSpace() from the Puzzle class.
     */
    public void testAdjacentToBlankSpace()
    {
        PLocation temp = new Location(0, 0);
        assertFalse(puzzle.adjacentToBlankSpace(temp));

        PLocation temp2 = new Location(2, 2);
        assertFalse(puzzle.adjacentToBlankSpace(temp2));

        PLocation temp3 = new Location(1, 1);
        assertFalse(puzzle.adjacentToBlankSpace(temp3));

        PLocation temp4 = new Location(1, 2);
        assertTrue(puzzle.adjacentToBlankSpace(temp4));

        PLocation temp5 = new Location(2, 1);
        assertTrue(puzzle.adjacentToBlankSpace(temp5));

    }

}
