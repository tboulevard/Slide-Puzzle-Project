package com.example.puzzlesgalore;

import sofia.graphics.RectangleShape;

/**
 * The easy puzzle class.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.11.2013
 */

public class puzzleEasy extends RectangleShape
{
    public puzzleEasy(float left, float top, float right, float bottom)
    {
        //Have random number generator to choose between setting an image
        //for different pictures
        super(left, top, right, bottom);
        setImage("pikachu");
    }
}
