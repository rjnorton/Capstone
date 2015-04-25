import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * The representation of a planet, defined by its mass, radius, velocity, and Ellipse
 * 
 * @author Rob Norton
 * @version 4/24/15
 * @see SolarSystem
 * @see Vector2D
 */
public class Planet
{
    private double mass;
    private int radius;
    private Vector2D velocity;
    private Ellipse2D.Double planet;
    /**
     * Constructor for planet, creates a random starting x and y position
     * and a random velocity based on the x and y distance from the sun
     *
     * @param screenHeight The height of the screen
     * @param screenWidth The width of the screen
     * 
     */
    public Planet(int screenHeight, int screenWidth)
    {
        mass = (Math.random() * .5) +.5;
        radius = getRadius();
        
        double startX = (int) (Math.random() * (screenWidth - 30));
        double startY = (int) (Math.random() * (screenHeight - 30));
        double xDiff = ((screenWidth/2) - startX)/(screenWidth/2);
        double yDiff = ((screenHeight/2) - startY)/(screenHeight/2);
        double ratioX = 1 - Math.abs(xDiff);
        double ratioY = 1 - Math.abs(yDiff);
        if(yDiff < 0)
        {
            ratioX = -1 * ratioX;
        }
        if(xDiff > 0)
        {
            ratioY = -1 * ratioY;
        }
       velocity = new Vector2D(((Math.random() * .06) + .02) * ratioX, ((Math.random() * .04) + .01) * ratioY);
       //velocity = new Vector2D(1/(startX - screenWidth/2), 1/(startY - screenHeight/2));
        planet = new Ellipse2D.Double(startX, startY, radius*2, radius*2);
    }
    
    /**
     * Returns the mass of the planet
     * 
     * @return The mass of the planet 
     */
    public double getMass()
    {
        return mass;
    }
    
    /**
     * Calculates the radius based on the mass
     *
     * @return The radius of the planet
     * 
     */
    public int getRadius()
    {
        radius = 2 + ((int) (Math.sqrt(mass)/2));
        return radius;
    }
    
    /**
     * Creates a new ellipse to represent the increased radius size
     * 
     */
    public void resize()
    {
        Vector2D center = getCenter();
        planet = new Ellipse2D.Double(center.x - getRadius(), center.y - getRadius(), getRadius() * 2, getRadius() * 2);
    }
    
    /**
     * Sets the mass of the planet
     *
     * @param  newmass The new mass of the planet
     * 
     */
    public void setMass(double newmass)
    {
        mass = newmass;
    }
    
    /**
     * Returns the center of the planet
     *
     * @return The Vector2D representation of the center
     * @see Vector2D
     * 
     */
    public Vector2D getCenter()
    {
        Vector2D center = new Vector2D();
        center.x = planet.getCenterX();
        center.y = planet.getCenterY();
        return center;
    }
    
    /**
     * Sets the center of the planet
     *
     * @param  center The Vector2D representation of the center
     * @see Vector2D
     * 
     */
    public void setCenter(Vector2D center)
    {
        planet.setFrameFromCenter(center.x, center.y, center.x - radius, center.y - radius);
    }
    
    /**
     * Returns the Vector2D representation of the velocity of the planet
     *
     * @return The velocity represented as a Vector2D
     * @see Vector2D
     */
    public Vector2D getVelocity()
    {
        return velocity;
    }
   
    /**
     * Sets the velocity of the planet
     *
     * @param  newVelocity The Vector2D velocity
     * @see Vector2D
     * 
     */
    public void setVelocity(Vector2D newVelocity)
    {
        velocity = newVelocity;
    }
    
    /**
     * Returns the Ellipse2D of the planet
     *
     * @return The Ellipse2D of the planet
     * 
     */
    public Ellipse2D.Double getCircle()
    {
        return planet;
    }
}
