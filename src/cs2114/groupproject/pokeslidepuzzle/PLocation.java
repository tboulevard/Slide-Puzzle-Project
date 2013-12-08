package cs2114.groupproject.pokeslidepuzzle;

// -------------------------------------------------------------------------
/**
 *  This interface outlines all methods for the Location class.
 *
 *  @author Timothy Street (timvt)
 *  @author Filip Gouglev (gfilip1)
 *  @author Ryan Bishop (ryanb79)
 *  @version 12.08.2013
 */
public interface PLocation
{
    // ----------------------------------------------------------
    /**
     * Gets the y coordinate of the location.
     *
     * @return x, an object representing the x coordinate of the location.
     */
    public int x();

    /**
     * Gets the x-coordinate of the location.
     *
     * @return y, an object representing the y coordinate of the location.
     */
    public int y();

    /**
     * Returns a new location one cell north of the location this method is
     * called on.
     *
     * @return a new location one cell to the north of the Location set by the
     *         constructor.
     */
    public PLocation north();

    /**
     * Returns a new location one cell south of the location this method is
     * called on.
     *
     * @return a new location one cell to the south of the Location set by the
     *         constructor.
     */
    public PLocation south();

    /**
     * Returns a new location one cell east of the location this method is
     * called on.
     *
     * @return a new location one cell to the east of the Location set by the
     *         constructor.
     */
    public PLocation east();

    /**
     * Returns a new location one cell west of the location this method is
     * called on.
     *
     * @return a new location one cell to the west of the Location set by the
     *         constructor.
     */
    public PLocation west();
}
