package com.example.puzzlesgalore;

import java.util.ArrayList;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
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
 * @version 12.08.2013
 */

public class MainScreen
    extends ShapeScreen
{
    private int               size           = 3;
    private Puzzle            puzzleBoard;
    private PuzzleTile[][]    tileCell;
    private PuzzleTile        currentTile;
    private float             puzzleDimension;
    private float             tileDimension;
    private boolean           easySelected   = true;
    private boolean           mediumSelected = false;
    private boolean           hardSelected   = false;
    private Location          currentLoc;
    private Location          blankSpaceLocation;
    private Image[][] imageArray;

    public void initialize()
    {
        puzzleBoard = new Puzzle(size);
        tileCell = new PuzzleTile[size][size];

        imageArray = new Image[size][size];
        Bitmap b =
            BitmapFactory.decodeResource(getResources(), R.drawable.pikachu);
        divideImage(b);

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
                tileCell[i][j].setImage(imageArray[i][j]);
                add(tileCell[i][j]);
            }
        }
        blankSpaceLocation = new Location(size - 1, size - 1);
        tileCell[blankSpaceLocation.x()][blankSpaceLocation.y()]
            .setFillColor(Color.black);

    }


    private void divideImage(Bitmap bmImage)
    {
        int width = bmImage.getWidth();
        int height = bmImage.getHeight();

        int pixelByCol = width / size;
        int pixelByRow = height / size;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int x = pixelByCol * j;
                int y = pixelByRow * i;

                Bitmap bitmapImage =
                    Bitmap.createBitmap(bmImage, x, y, pixelByCol, pixelByRow);
                Image image = new Image(bitmapImage);
                imageArray[j][i] = image;
            }
        }
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
        currentTile.setFillColor(Color.black);
        Image currentImage = imageArray[currentLoc.x()][currentLoc.y()];
        tileCell[newTile.x()][newTile.y()].setImage(currentImage);
        imageArray[currentLoc.x()][currentLoc.y()] = imageArray[newTile.x()][newTile.y()];
        imageArray[newTile.x()][newTile.y()] = currentImage;
        blankSpaceLocation = currentLoc;
    }

}
