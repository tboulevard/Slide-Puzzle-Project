package cs2114.groupproject.pokeslidepuzzle;

import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

/**
 * The main screen class.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.11.2013
 */

public class PuzzleTile extends RectangleShape
{
    // ----------------------------------------------------------
    /**
     * Create a new PuzzleTile object.
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public PuzzleTile(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setColor(Color.black);
    }
}
