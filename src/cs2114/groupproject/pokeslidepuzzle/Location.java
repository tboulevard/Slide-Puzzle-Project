package cs2114.groupproject.pokeslidepuzzle;

/**
 * This class is an abstraction that represents an (x, y) coordinate pair. It
 * will be used to specify the position of objects within the puzzle class.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.08.2013
 */
public class Location
    implements PLocation
{
    private int x;
    private int y;


    /**
     * Class constructor. Sets the x and y coordinates for the location. These
     * coordinates are immutable.
     *
     * @param x
     *            , the x coordinate of the location.
     * @param y
     *            , the y coordinate of the location.
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * Gets the y coordinate of the location.
     *
     * @return x, an object representing the x coordinate of the location.
     */
    public int x()
    {
        return x;
    }


    /**
     * Gets the x-coordinate of the location.
     *
     * @return y, an object representing the y coordinate of the location.
     */
    public int y()
    {
        return y;
    }


    /**
     * Returns a new location one cell north of the location this method is
     * called on.
     *
     * @return a new location one cell to the north of the Location set by the
     *         constructor.
     */
    public PLocation north()
    {
        int newY = this.y() - 1;
        return new Location(x, newY);
    }


    /**
     * Returns a new location one cell south of the location this method is
     * called on.
     *
     * @return a new location one cell to the south of the Location set by the
     *         constructor.
     */
    public PLocation south()
    {
        int newY = this.y() + 1;
        return new Location(x, newY);
    }


    /**
     * Returns a new location one cell east of the location this method is
     * called on.
     *
     * @return a new location one cell to the east of the Location set by the
     *         constructor.
     */
    public PLocation east()
    {
        int newX = this.x() + 1;
        return new Location(newX, y);
    }


    /**
     * Returns a new location one cell west of the location this method is
     * called on.
     *
     * @return a new location one cell to the west of the Location set by the
     *         constructor.
     */
    public PLocation west()
    {
        int newX = this.x() - 1;
        return new Location(newX, y);
    }
}
