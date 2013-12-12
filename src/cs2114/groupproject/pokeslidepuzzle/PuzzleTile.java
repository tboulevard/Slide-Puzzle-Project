package cs2114.groupproject.pokeslidepuzzle;

import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * This class generates PuzzleTiles that represent each grid on the MainScreen.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.08.2013
 */

public class PuzzleTile
    extends RectangleShape
{

    /**
     * Creates a new PuzzleTile object.
     *
     * @param left
     *            , the bound for the left side of the RectangleShape.
     * @param top
     *            , the bound for the top side of the RectangleShape.
     * @param right
     *            , the bound for the right side of the RectangleShape.
     * @param bottom
     *            , the bound for the bottom side of the RectangleShape.
     */
    public PuzzleTile(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setFillColor(Color.black);
    }
}
