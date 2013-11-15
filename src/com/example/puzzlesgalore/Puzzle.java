package com.example.puzzlesgalore;

import sofia.graphics.Image;
import java.awt.*;

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
    private int       size;
    private int[][]   puzzleBoard;
    private Image[][] dividedPicture;
    private int       blankSpace;
    private Location blankSpaceLocation;


    /**
     * Class constructor. Instantiates the size and dimensions of the puzzle
     * board.
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
        blankSpaceLocation = new Location(size - 1, size - 1);
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
     * Returns an array of subdivisions of a specified picture. The number of
     * divisions is dependent on the size of the puzzle.
     *
     * @param image
     *            , the image to be divided
     * @param size
     *            , the size of the puzzle.
     * @return dividedPicture, an array of image divisions.
     */
    public Image[][] dividePicture(Image image, int size)
    {
        return dividedPicture;
    }


    /**
     * Gets the cell and returns it's reference. The reference is a number
     * 1-(size^2). E.g.: A board with size 3 will have tile references ranging
     * from 1-9.
     *
     * @param loc
     *            , the location to get the reference from.
     * @return int, the number reference of the tile.
     */
    public Location getCell(PLocation loc)
    {
        if (loc.x() >= 0 && loc.y() >= 0 && loc.x() <= (size - 1)
            && loc.y() <= (size - 1))
        {
            return new Location(loc.x(), loc.y());
        }
        return null;
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

        if (getCellEast(loc) == blankSpaceLocation || getCellWest(loc) == blankSpaceLocation
            || getCellNorth(loc) == blankSpaceLocation
            || getCellSouth(loc) == blankSpaceLocation)
        {
            return true;
        }
        return false;
    }


    public void setBlankSpace(PLocation loc)
    {
        if (adjacentToBlankSpace(loc))
        {
            blankSpaceLocation = new Location(loc.x(), loc.y());
        }
    }


    /**
     * Returns cell contents to the east of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     */
    public Location getCellEast(PLocation loc)
    {
        return getCell(loc.east());
    }


    /**
     * Returns cell contents to the west of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     */
    public Location getCellWest(PLocation loc)
    {
        return getCell(loc.west());
    }


    /**
     * Returns cell contents to the south of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     */
    public Location getCellSouth(PLocation loc)
    {
        return getCell(loc.south());
    }


    /**
     * Returns cell contents to the north of the specified cell.
     *
     * @param loc
     *            , the cell's location.
     */
    public Location getCellNorth(PLocation loc)
    {
        return getCell(loc.north());
    }
}
