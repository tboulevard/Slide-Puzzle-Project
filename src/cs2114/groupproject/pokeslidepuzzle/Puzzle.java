package cs2114.groupproject.pokeslidepuzzle;

/**
 * This class is a representation of the puzzle board model.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.11.2013
 */
public class Puzzle
{
    private int      size;
    private int[][]  puzzleBoard;
    private int      blankSpace;

    /**
     * Class constructor. Instantiates the size, dimensions of the puzzle board,
     * and the blank space location.
     *
     * @param size
     *            , the size of the puzzle
     */
    public Puzzle(int size)
    {
        this.size = size;
        puzzleBoard = new int[size][size];
        int tileReference = 0;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                puzzleBoard[i][j] = tileReference;
                tileReference++;
            }
        }
        blankSpace = puzzleBoard[size - 1][size - 1];
    }


    /**
     * Accessor that gets the size of the puzzle board.
     *
     * @return size, the size of the puzzle board.
     */
    public int size()
    {
        return size;
    }


    /**
     * Gets the cell and returns it's reference. The reference is a number
     * (size^2 - 1). E.g.: A board with size 3 will have tile references ranging
     * from 0-8. getCell() returns -1 if the specified cell is not within the
     * bounds of the puzzle.
     *
     * @param loc
     *            , the location to get the reference from.
     * @return puzzleBoard[loc.x()][loc.y()], if the number reference of the
     *         tile. -1, if the cell is not within the bounds of the puzzle.
     */
    public int getCell(PLocation loc)
    {
        if (loc.x() >= 0 && loc.y() >= 0 && loc.x() < (size)
            && loc.y() < (size))
        {
            return puzzleBoard[loc.x()][loc.y()];
        }
        return -1;
    }


    /**
     * Determines whether a cell at a specified location is adjacent to a blank
     * space.
     *
     * @param loc
     *            , the location of the cell to check.
     * @return true, if it is adjacent to a blank space. false, if it is not
     *         adjacent to a blank space.
     */
    public boolean adjacentToBlankSpace(PLocation loc)
    {

        if (getCellEast(loc) == blankSpace || getCellWest(loc) == blankSpace
            || getCellNorth(loc) == blankSpace
            || getCellSouth(loc) == blankSpace)
        {
            return true;
        }
        return false;
    }


    /**
     * Sets a new blank space reference.
     *
     * @param newLoc
     *            , the new location for the blank space
     * @param prevLoc
     *            , the old location for the blank space
     */
    public void setBlankSpace(PLocation newLoc, PLocation prevLoc)
    {
        int tileReference = puzzleBoard[newLoc.x()][newLoc.y()];
        puzzleBoard[newLoc.x()][newLoc.y()] = blankSpace;
        puzzleBoard[prevLoc.x()][prevLoc.y()] = tileReference;
    }


    /**
     * Returns cell contents to the east of the specified cell.
     *
     * @param loc
     *            , the cell's location on the board.
     * @return getCell(loc.east()), the integer tile reference on the board 1
     *         cell east of the specified location.
     */
    public int getCellEast(PLocation loc)
    {
        return getCell(loc.east());
    }


    /**
     * Returns cell contents to the west of the specified cell.
     *
     * @param loc
     *            , the cell's location on the board.
     * @return getCell(loc.west()), the integer tile reference on the board 1
     *         cell west of the specified location.
     */
    public int getCellWest(PLocation loc)
    {
        return getCell(loc.west());
    }


    /**
     * Returns cell contents to the south of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     * @return getCell(loc.south()), the integer tile reference on the board 1
     *         cell south of the specified location.
     */
    public int getCellSouth(PLocation loc)
    {
        return getCell(loc.south());
    }


    /**
     * Returns cell contents to the north of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     * @return getCell(loc.north()), the integer tile reference on the board 1
     *         cell north of the specified location.
     */
    public int getCellNorth(PLocation loc)
    {
        return getCell(loc.north());
    }
}
