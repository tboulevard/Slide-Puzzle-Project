package cs2114.groupproject.pokeslidepuzzle;

import android.os.Bundle;
import android.app.Activity;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Tim
 * @version Dec 7, 2013
 */
public class DifficultyActivity
    extends Activity
{
    private int difficulty = 0;
    private MainScreen screen;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_activity);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void easyButtonClicked()
    {
        difficulty = 1;
        screen.initialize();
        this.finish();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void mediumButtonClicked()
    {
        difficulty = 2;
        this.finish();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void hardButtonClicked()
    {
        difficulty = 3;
        this.finish();
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public int getDifficulty()
    {
        return difficulty;
    }
}
