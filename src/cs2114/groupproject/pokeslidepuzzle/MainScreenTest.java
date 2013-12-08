package cs2114.groupproject.pokeslidepuzzle;

import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * This class tests the user's interaction with the MainScreen GUI.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.08.2013
 */
public class MainScreenTest
    extends student.AndroidTestCase<MainScreen>
{
    private static final int SIZE = 4;
    private ShapeView        shapeView;
    private MainScreen       puzzleScreen;
    private int              cellSize;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MainScreenTest()
    {
        super(MainScreen.class);
    }


    /**
     * Initializes test fixtures.
     */
    public void setUp()
    {
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());
        puzzleScreen = this.getScreen();
        cellSize = (int)viewSize / SIZE;
    }


    /**
     * Simulates touching down on the middle of the specified cell on the board.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    /**
     * Simulates clicking the middle of the specified cell in the puzzle. This
     * is equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }


    private void testProcessTouch()
    {

    }

}
