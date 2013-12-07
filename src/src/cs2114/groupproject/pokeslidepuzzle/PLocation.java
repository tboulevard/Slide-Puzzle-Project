package cs2114.groupproject.pokeslidepuzzle;

public interface PLocation
{
    public int x();
    public int y();
    public PLocation north();
    public PLocation south();
    public PLocation east();
    public PLocation west();
}
