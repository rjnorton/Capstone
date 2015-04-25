/**
 * A class to store public x and y values for ease of use.
 * 
 * @author Rob Norton
 * @version 4/24/15
 */
public class Vector2D
{
    public double x;
    public double y;
    /**
     * Creates a Vector2D with specified x and y values
     *
     * @param  x1 The x value
     * @param  y1 The y value
     * 
     */
    public Vector2D(double x1, double y1)
    {
        x = x1;
        y = y1;
    }
    
    /**
     * Defualt constructor for Vector2D
     *
     * 
     */
    public Vector2D()
    {
    }
    
    /**
     * Sets the x value of this Vector2D
     *
     * @param  x1 The new x value
     * 
     */
    public void setX(double x1)
    {
        x = x1;
    }
    
    /**
     * Sets the y value of this Vector2D
     *
     * @param  y1 The new y value
     * 
     */
    public void setY(double y1)
    {
        y = y1;
    }
}
