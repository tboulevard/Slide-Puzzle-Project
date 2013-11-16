package com.example.puzzlesgalore;

import java.awt.*;
import java.io.File;
import sofia.graphics.Image;
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
    private int            size           = 5;
    private Puzzle         puzzleBoard;
    private PuzzleTile[][] tileCell;
    private PuzzleTile     currentTile;
    private float          puzzleDimension;
    private float          tileDimension;
    private float          widthSpace;
    private float          heightSpace;
    private boolean        easySelected   = true;
    private boolean        mediumSelected = false;
    private boolean        hardSelected   = false;
    private Location       currentLoc;
    private Location       blankSpaceLocation;
    private Image[][]      imageArray;


    public void initialize()
    {
        puzzleBoard = new Puzzle(size);
        tileCell = new PuzzleTile[size][size];

        /** Uncomment when dividePicture works
        if (easySelected)
        {
            Image file = new Image("pikachu.png");
            dividePicture(file);
        }
        */

        puzzleDimension = Math.min(getWidth(), getHeight());
        tileDimension = (puzzleDimension / size);

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                float left = (i * tileDimension);
                float top = (j * tileDimension);
                float right = ((i + 1) * tileDimension);
                float bottom = ((j + 1) * tileDimension);
                PuzzleTile tile = new PuzzleTile(left, top, right, bottom);
                tileCell[i][j] = tile;
                tileCell[i][j].setFillColor(Color.blue);
                //tileCell[i][j].setImage(imageArray[i][j]); Uncomment once divide image is set up
                add(tileCell[i][j]);
            }
        }
        blankSpaceLocation = new Location(size - 1, size - 1);
        tileCell[blankSpaceLocation.x()][blankSpaceLocation.y()]
            .setFillColor(Color.white);

    }


    /**
     * Determines the action taken when the screen is touched at a specific
     * point.
     *
     * @param x
     *            , the x coordinate of touch down.
     * @param y
     *            , the y coordinate of touch down.
     */
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
                this.setNewBlankSpace();
            }
        }

    }


    public void setNewBlankSpace()
    {
        puzzleBoard.setBlankSpace(currentLoc, blankSpaceLocation);
        Location newTile =
            new Location(blankSpaceLocation.x(), blankSpaceLocation.y());
        blankSpaceLocation = currentLoc;
        currentTile.setFillColor(Color.white);
        tileCell[newTile.x()][newTile.y()].setFillColor(Color.blue);
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
    public void dividePicture(Image image)
    {
        int numDivisions = (size ^ 2);
        int subImageWidth = image.getWidth() / numDivisions;
        int subImageHeight = image.getHeight() / numDivisions;
        imageArray = new Image[size][size];

        for (int i = 0; i < numDivisions; i++)
        {
            for (int j = 0; j < numDivisions; j++)
            {
                //imageArray[i][j] = (subImageWidth, subImageHeight);
                //subImageWidth += subImageWidth;
                //subImageHeight += subImageHeight;
            }
        }

    }

}
