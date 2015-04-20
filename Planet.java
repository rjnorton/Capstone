import java.awt.geom.Ellipse2D;

public class Planet
{
    private double mass;
    private int radius;
    private boolean canMove;
    private Vector2D velocity;
    private Ellipse2D.Double planet;
    private final double MIN_START_MASS = 4.867E24;
    private final double MAX_START_MASS = 5.972E24;
    private final double MIN_START_VELOCITY = 4670;
    private final double MAX_START_VELOCITY = 47360;
    
    public Planet(int screenHeight, int screenWidth, boolean move)
    {
        radius = 5;
        canMove = move;
        double rand = Math.random() * 2;
        if(rand < .5)
        {
            mass = MIN_START_MASS;
        }
        else
        {
            mass = MIN_START_MASS + (Math.random() * (MAX_START_MASS - MIN_START_MASS));
        }
        
        
        
        int startX = (int) (Math.random() * (screenWidth - 30));
        int startY = (int) (Math.random() * (screenHeight - 30));
        
        velocity = new Vector2D();
        if(canMove)
        {
            double totalDist = Math.hypot(startX - screenWidth/2, startY - screenHeight/2);
            double ratio = 1 - (totalDist/(screenHeight/2));
            double vel = ((MAX_START_VELOCITY - MIN_START_VELOCITY) * ratio) + MIN_START_VELOCITY;
            velocity.x = vel;
            velocity.y = vel;
            if(startX > screenWidth/2 && startY > screenHeight/2)
            {
                velocity.x = -1 * velocity.x;
            }
            else if(startX < screenWidth/2 && startY < screenHeight/2)
            {
                velocity.y = -1 * velocity.y;
            }
            else if(startX < screenWidth/2 && startY > screenHeight/2)
            {
                velocity.x = -1 * velocity.x;
                velocity.y = -1 * velocity.y;
            }
        }
        else
        {
            velocity.x = 0;
            velocity.y = 0;
        }
        
        
        planet = new Ellipse2D.Double(startX, startY, radius*2, radius*2);
    }
    
    public double getMass()
    {
        return mass;
    }
    
    public void setMass(double newmass)
    {
        mass = newmass;
    }
    
    public int getRadius()
    {
        return radius;
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
        if(canMove){velocity = newVelocity;}
    }
    
    public Ellipse2D.Double getCircle()
    {
        return planet;
    }
}
