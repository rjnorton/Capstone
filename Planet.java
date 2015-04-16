import java.awt.geom.Ellipse2D;

public class Planet
{
    private int mass;
    private int radius;
    private boolean canMove;
    private Vector2D velocity;
    private Ellipse2D.Double planet;
    
    public Planet(int screenHeight, int screenWidth, double minVel, double maxVel, boolean move)
    {
        radius = 5;
        mass = 1;
        canMove = move;
        
        int startX = (int) (Math.random() * (screenWidth - 30));
        int startY = (int) (Math.random() * (screenHeight - 30));
        
        velocity = new Vector2D();
        if(canMove)
        {
            //this has to be better... based on location relative to sun
            velocity.x = Math.random()*maxVel;
            velocity.y = Math.random()*maxVel;
            if(velocity.x < minVel){velocity.x = minVel;}//these could be going negative?
            if(velocity.y < minVel){velocity.y = minVel;}
            
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
    
    public int getMass()
    {
        return mass;
    }
    
    public void setMass(int newmass)
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
