package cs2114.groupproject.pokeslidepuzzle;

// -------------------------------------------------------------------------
/**
 * This class handles all tests for the Location class.
 *
 * @author Timothy Street (timvt)
 * @author Filip Gouglev (gfilip1)
 * @author Ryan Bishop (ryanb79)
 * @version 12.08.2013
 */
public class LocationTest
    extends student.TestCase
{
    private Location loc;


    /**
     * Sets the initial state of the LocationTest test cases.
     */
    public void setUp()
    {
        loc = new Location(4, 4);
    }


    /**
     * Tests all methods from the Location class.
     */
    public void testMethods()
    {
        assertEquals(4, loc.x());
        assertEquals(4, loc.y());
        assertEquals(5, loc.east().x());
        assertEquals(3, loc.north().y());
        assertEquals(5, loc.south().y());
        assertEquals(3, loc.west().x());
    }
}
