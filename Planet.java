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
        mass = 5;
        radius = getRadius();
        
        int startX = (int) (Math.random() * (screenWidth - 30));
        int startY = (int) (Math.random() * (screenHeight - 30));
        
        velocity = new Vector2D(0,0);       
        
        planet = new Ellipse2D.Double(startX, startY, radius*2, radius*2);
    }
    
    public double getMass()
    {
        return mass;
    }
    
    public int getRadius()
    {
        int radius = 2 + ((int) (mass/10));
        return radius;
    }
    
    public void resize()
    {
        Vector2D center = getCenter();
        planet = new Ellipse2D.Double(center.x, center.y, getRadius() * 2, getRadius() * 2);
    }
    
    public boolean collisions(Planet other)
    {
        Rectangle2D thisRect = planet.getBounds2D();
        Rectangle2D otherRect = other.getCircle().getBounds2D();
        if(thisRect.intersects(otherRect.getX(), otherRect.getY(), otherRect.getWidth(), otherRect.getHeight()))
        {
            return true;
        }
        return false;
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
