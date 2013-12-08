package cs2114.groupproject.pokeslidepuzzle;

import android.content.Intent;
import sofia.util.Random;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import sofia.graphics.Image;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;

/**
 * The main screen class. This class is a representation of the activity running
 * when a user is playing the slide puzzle game.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.08.2013
 */

public class MainScreen
    extends ShapeScreen
{
    private static final int SIZE        = 4;
    private Puzzle           puzzleBoard;
    private PuzzleTile[][]   tileCell;
    private PuzzleTile       currentTile;
    private float            puzzleDimension;
    private float            tileDimension;
    private Location         currentLoc;
    private Location         blankSpaceLocation;
    private Image[][]        randomizedImageArray;
    private Image[][]        imageArray;
    private Random           random      = new Random();
    private int              randomImage = 0;


    /**
     * Sets the initial state of the main screen activity. Calls all methods
     * necessary to placing and dividing the picture on the puzzle board.
     */
    public void initialize()
    {
        puzzleBoard = new Puzzle(SIZE);
        tileCell = new PuzzleTile[SIZE][SIZE];
        imageArray = new Image[SIZE][SIZE];
        randomizedImageArray = new Image[SIZE][SIZE];
        puzzleDimension = Math.min(getWidth(), getHeight());
        tileDimension = (puzzleDimension / SIZE);

        this.selectRandomPicture();
        this.createSolvablePuzzle();

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
                tileCell[i][j].setImage(randomizedImageArray[i][j]);
                add(tileCell[i][j]);
            }
        }

        this.setImageReference();
        blankSpaceLocation = new Location(SIZE - 1, SIZE - 1);

        tileCell[blankSpaceLocation.x()][blankSpaceLocation.y()]
            .setFillColor(Color.black);
    }


    /**
     * Generates a PuzzleTile with an undivided image below the Puzzle board to
     * use as reference.
     */
    private void setImageReference()
    {
        PuzzleTile imageRefTile =
            new PuzzleTile(140, 500, getWidth() - 140, getHeight() - 10);
        if (randomImage == 0)
        {
            imageRefTile.setImage("pikachu.png");
        }
        else if (randomImage == 1)
        {
            imageRefTile.setImage("charizard.png");
        }
        else if (randomImage == 2)
        {
            imageRefTile.setImage("snorlax.png");
        }
        else if (randomImage == 3)
        {
            imageRefTile.setImage("jigglypuff.png");
        }
        else
        {
            imageRefTile.setImage("gyarados.png");
        }
        add(imageRefTile);
    }


    /**
     * Divides the specified image so that it can be stored in an array of
     * Images. Each section is stored twice: The first time for the imageArray
     * that will be used as a reference to check if the puzzle is solved, the
     * second time is for the randomizedImaryArray that will be used to
     * randomize the tiles on the slide puzzle board.
     *
     * @param bmImage
     *            , a bitmap representation of the image to be divided.
     */
    private void divideImage(Bitmap bmImage)
    {
        int width = bmImage.getWidth();
        int height = bmImage.getHeight();

        int col = width / SIZE;
        int row = height / SIZE;

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                int x = col * j;
                int y = row * i;

                Bitmap bitmapImage =
                    Bitmap.createBitmap(bmImage, x, y, col, row);
                Image image = new Image(bitmapImage);
                imageArray[j][i] = image; // Kept constant for solution
                randomizedImageArray[j][i] = image; // Randomized later to
// generate random board
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
     * Handles behaviors when a tile has been clicked/touch down on on the
     * puzzle board. If the tile clicked is adjacent to a blank space, it
     * switches locations with blank space to simulate sliding. Otherwise, the
     * board remains unchanged. Each puzzle solution specified in this project
     * has its blank space in the (size - 1), (size - 1) location when the
     * puzzle has been properly solved. Therefore checking if the puzzle has
     * been solved is only made when this is true.
     *
     * @param x
     *            , the x coordinate of touch down.
     * @param y
     *            , the y coordinate of touch down.
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

        if (this.checkSolved())
        {
            Intent intent = new Intent(this, PuzzleSolvedActivity.class);
            startActivity(intent);
        }
    }


    /**
     * Helper method that selects a random picture from the drawable resource to
     * be divided for the puzzle.
     */
    public void selectRandomPicture()
    {
        int randomPicture = random.nextInt(0, 4);
        randomImage = randomPicture;
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


    /**
     * Handles the behaviors for setting a new blank space on the puzzle board.
     * (Simulates tiles sliding across the board.)
     */
    public void setNewBlankSpace()
    {
        puzzleBoard.setBlankSpace(currentLoc, blankSpaceLocation);
        Location newTile =
            new Location(blankSpaceLocation.x(), blankSpaceLocation.y());
        blankSpaceLocation = currentLoc;
        currentTile.setFillColor(Color.black);
        Image currentImage =
            randomizedImageArray[currentLoc.x()][currentLoc.y()];
        tileCell[newTile.x()][newTile.y()].setImage(currentImage);
        randomizedImageArray[currentLoc.x()][currentLoc.y()] =
            randomizedImageArray[newTile.x()][newTile.y()];
        randomizedImageArray[newTile.x()][newTile.y()] = currentImage;
        blankSpaceLocation = currentLoc;
    }


    /**
     * Checks if the puzzle has been solved by looking at the image references
     * on each tile. If the image references on the tiles match the ordering in
     * imageArray, the puzzle has successfully been solved.
     *
     * @return true, if the puzzle has been successfully solved; false, if the
     *         puzzle has not been solved.
     */
    public boolean checkSolved()
    {
        if (blankSpaceLocation.x() == (SIZE - 1)
            && blankSpaceLocation.y() == (SIZE - 1))
        {
            for (int i = 0; i < SIZE; i++)
            {
                for (int j = 0; j < SIZE; j++)
                {
                    if (randomizedImageArray[i][j] != imageArray[i][j])
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Helper method to create 'randomized' solvable slide puzzles.
     */
    public void createSolvablePuzzle()
    {
        Image tile1 = randomizedImageArray[0][0];
        Image tile2 = randomizedImageArray[1][0];
        Image tile3 = randomizedImageArray[2][0];
        Image tile4 = randomizedImageArray[3][0];
        Image tile5 = randomizedImageArray[0][1];
        Image tile6 = randomizedImageArray[1][1];
        Image tile7 = randomizedImageArray[2][1];
        Image tile8 = randomizedImageArray[3][1];
        Image tile9 = randomizedImageArray[0][2];
        Image tile10 = randomizedImageArray[1][2];
        Image tile11 = randomizedImageArray[2][2];
        Image tile12 = randomizedImageArray[3][2];
        Image tile13 = randomizedImageArray[0][3];
        Image tile14 = randomizedImageArray[1][3];
        Image tile15 = randomizedImageArray[2][3];

        int puzzleNum = random.nextInt(0, 1); // Chooses from 2 different
        // randomizations
        // -------Comment out for demo (to show that checkSolved works)--------
        if (puzzleNum == 0)
        {
            randomizedImageArray[0][0] = tile7;
            randomizedImageArray[1][0] = tile14;
            randomizedImageArray[2][0] = tile5;
            randomizedImageArray[3][0] = tile2;
            randomizedImageArray[0][1] = tile8;
            randomizedImageArray[1][1] = tile9;
            randomizedImageArray[2][1] = tile3;
            randomizedImageArray[3][1] = tile13;
            randomizedImageArray[0][2] = tile12;
            randomizedImageArray[1][2] = tile1;
            randomizedImageArray[2][2] = tile6;
            randomizedImageArray[3][2] = tile11;
            randomizedImageArray[0][3] = tile15;
            randomizedImageArray[1][3] = tile4;
            randomizedImageArray[2][3] = tile10;
        }
        else if (puzzleNum == 1)
        {
            randomizedImageArray[0][0] = tile7;
            randomizedImageArray[1][0] = tile9;
            randomizedImageArray[2][0] = tile11;
            randomizedImageArray[3][0] = tile2;
            randomizedImageArray[0][1] = tile1;
            randomizedImageArray[1][1] = tile15;
            randomizedImageArray[2][1] = tile10;
            randomizedImageArray[3][1] = tile4;
            randomizedImageArray[0][2] = tile3;
            randomizedImageArray[1][2] = tile12;
            randomizedImageArray[2][2] = tile6;
            randomizedImageArray[3][2] = tile13;
            randomizedImageArray[0][3] = tile8;
            randomizedImageArray[1][3] = tile5;
            randomizedImageArray[2][3] = tile14;
        }

        //---------------------------------------------------------------------
    }
}
