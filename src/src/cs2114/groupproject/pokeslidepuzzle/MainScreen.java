package cs2114.groupproject.pokeslidepuzzle;

import sofia.util.Random;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
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
    private int            size              = 3;
    private Puzzle         puzzleBoard;
    private PuzzleTile[][] tileCell;
    private PuzzleTile     currentTile;
    private float          puzzleDimension;
    private float          tileDimension;
    private boolean        easySelected      = true;
    private boolean        mediumSelected    = false;
    private boolean        hardSelected      = false;
    private Location       currentLoc;
    private Location       blankSpaceLocation;
    private Image[][]      randomizedImageArray;
    private Image[][]      imageArray;
    private int            numSectionsPlaced = 0;
    private Random         random            = new Random();
    int                    row               = 0;
    int                    col               = 0;


    public void initialize()
    {
        puzzleBoard = new Puzzle(size);
        tileCell = new PuzzleTile[size][size];
        imageArray = new Image[size][size];

        selectRandomPicture();

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

        int col = width / size;
        int row = height / size;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int x = col * j;
                int y = row * i;

                Bitmap bitmapImage =
                    Bitmap.createBitmap(bmImage, x, y, col, row);
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


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param x
     * @param y
     */
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


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void selectRandomPicture()
    {
        int randomPicture = random.nextInt(0, 4);
        if (randomPicture == 0)
        {
            Bitmap b1 =
                BitmapFactory
                    .decodeResource(getResources(), R.drawable.pikachu);
            divideImage(b1);
        }
        else if (randomPicture == 1)
        {
            Bitmap b2 =
                BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.charizard);
            divideImage(b2);
        }
        else if (randomPicture == 2)
        {
            Bitmap b3 =
                BitmapFactory
                    .decodeResource(getResources(), R.drawable.snorlax);
            divideImage(b3);
        }
        else if (randomPicture == 3)
        {
            Bitmap b4 =
                BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.jigglypuff);
            divideImage(b4);
        }
        else
        {
            Bitmap b5 =
                BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.gyarados);
            divideImage(b5);
        }
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void setNewBlankSpace()
    {
        puzzleBoard.setBlankSpace(currentLoc, blankSpaceLocation);
        Location newTile =
            new Location(blankSpaceLocation.x(), blankSpaceLocation.y());
        blankSpaceLocation = currentLoc;
        currentTile.setFillColor(Color.black);
        Image currentImage = imageArray[currentLoc.x()][currentLoc.y()];
        tileCell[newTile.x()][newTile.y()].setImage(currentImage);
        imageArray[currentLoc.x()][currentLoc.y()] =
            imageArray[newTile.x()][newTile.y()];
        imageArray[newTile.x()][newTile.y()] = currentImage;
        blankSpaceLocation = currentLoc;
    }
}
