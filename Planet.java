import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Planet
{
    private double mass;
    private int radius;
    private Vector2D velocity;
    private Ellipse2D.Double planet;
    
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
    
    public double getMass()
    {
        return mass;
    }
    
    public int getRadius()
    {
        radius = 2 + ((int) (Math.sqrt(mass)/2));
        return radius;
    }
    
    public void resize()
    {
        Vector2D center = getCenter();
        planet = new Ellipse2D.Double(center.x - getRadius(), center.y - getRadius(), getRadius() * 2, getRadius() * 2);
    }
    
    public void setMass(double newmass)
    {
        mass = newmass;
    }
    
    public void setRadius(int newradius)
    {
        radius = newradius;
    }
    
    public Vector2D getCenter()
    {
        Vector2D center = new Vector2D();
        center.x = planet.getCenterX();
        center.y = planet.getCenterY();
        return center;
    }
    
    public void setCenter(Vector2D center)
    {
        planet.setFrameFromCenter(center.x, center.y, center.x - radius, center.y - radius);
    }
    
    public Vector2D getVelocity()
    {
        return velocity;
    }
   
    public void setVelocity(Vector2D newVelocity)
    {
        velocity = newVelocity;
    }
    
    public Ellipse2D.Double getCircle()
    {
        return planet;
    }
}
