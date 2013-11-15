package com.example.puzzlesgalore;

import sofia.app.ShapeScreen;
import sofia.graphics.Color;

/**
 * The main screen class.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.11.2013
 */

public class MainScreen
    extends ShapeScreen
{
    private static final int SIZE           = 4;
    private Puzzle           puzzleBoard;
    private PuzzleTile[][]   tileCell;
    private PuzzleTile       currentTile;
    private float            puzzleDimension;
    private float            tileDimension;
    private float            widthSpace;
    private float            heightSpace;
    private boolean          easySelected   = true;
    private boolean          mediumSelected = false;
    private boolean          hardSelected   = false;
    private Location         currentLoc;
    private Location         blankSpaceLocation;


    public void initialize()
    {
        puzzleBoard = new Puzzle(SIZE);
        tileCell = new PuzzleTile[SIZE][SIZE];

        puzzleDimension = Math.min(getWidth(), getHeight());
        // Code for centering the puzzle on the app screen
        widthSpace = (getWidth() - puzzleDimension) / 2;
        heightSpace = (getHeight() - puzzleDimension) / 2;
        // -----------------------------------------------

        /**
         * if (easySelected) { puzzleEasy easyPuzzle = new puzzleEasy(
         * widthSpace, heightSpace, getWidth() - widthSpace, getHeight() -
         * heightSpace); add(easyPuzzle); }
         */
        tileDimension = (puzzleDimension / SIZE);

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                float left = (i * tileDimension);
                float top = (j * tileDimension);
                float right = ((i + 1) * tileDimension);
                float bottom = ((j + 1) * tileDimension);
                PuzzleTile tile = new PuzzleTile(left, top, right, bottom);
                tileCell[i][j] = tile;
                tileCell[i][j].setFillColor(Color.blue);
                add(tileCell[i][j]);
            }
        }
        blankSpaceLocation = new Location(SIZE - 1, SIZE - 1);
        tileCell[blankSpaceLocation.x()][blankSpaceLocation.y()]
            .setFillColor(Color.white);

    }


    public void onTouchDown(float x, float y)
    {
        processTouch(x, y);
    }


    public void processTouch(float x, float y)
    {
        // Prevents ArrayIndexOutOfBounds Exceptions
        if ((int)x > 0 && (int)x < puzzleDimension && (int)y > 0
            && (int)y < puzzleDimension)
        {
            int xCoord = (int)(x / tileDimension);
            int yCoord = (int)(y / tileDimension);

            currentTile = tileCell[xCoord][yCoord];
            currentLoc = new Location(xCoord, yCoord);

            if (puzzleBoard.adjacentToBlankSpace(currentLoc))
            {
                setNewBlankSpace();
            }
        }

    }


    public void setNewBlankSpace()
    {
        int newX = currentLoc.x();
        int newY = currentLoc.y();
        int oldX = blankSpaceLocation.x();
        int oldY = blankSpaceLocation.y();
        Location newLocation = new Location(newX, newY);
        blankSpaceLocation = currentLoc;
        currentLoc = newLocation;
        tileCell[oldX][oldY].setFillColor(Color.blue);
        puzzleBoard.setBlankSpace(new Location(newX, newY));
        currentTile.setFillColor(Color.white);
    }

}
