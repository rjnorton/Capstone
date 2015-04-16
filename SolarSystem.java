import java.util.ArrayList;

public class SolarSystem
{
    private ArrayList<Planet> system;
    //use G and actual planet masses for more realistic motion?
    public SolarSystem(int numPlanets, int screenWidth, int screenHeight)
    {
        system = new ArrayList<Planet>();
        Planet sun = new Planet(screenHeight, screenWidth, 0, 0, false); //the sun
        sun.setCenter(new Vector2D(screenWidth/2,screenHeight/2));
        sun.setMass(4000);
        system.add(sun);
        
        for(int i = 0; i < numPlanets; i++)
        {
            //add private final constant for min and max velocities
            system.add(new Planet(screenHeight, screenWidth, 1, 2, true));
        }
    }
    
    public ArrayList<Planet> getPlanets()
    {
        return system;
    }
    
    public void updateSystem()
    {
        for(int i = 0; i < system.size(); i++)
        {
            Planet p = system.get(i);
            Vector2D acceleration = getAccel(p, i);
            Vector2D currVel= p.getVelocity();
            Vector2D newVel = new Vector2D(currVel.x + acceleration.x, currVel.y + acceleration.y);
            p.setVelocity(newVel);
            Vector2D oldCenter = p.getCenter();
            Vector2D newCenter = new Vector2D(oldCenter.x + newVel.x, oldCenter.y + newVel.y);
            p.setCenter(newCenter);
            //if(i%2 == 1)
            //{
            //    System.out.println("X: " + newVel.x + " Y: " + newVel.y);
            //}
        }
    }
    
    public Vector2D getAccel(Planet p, int ind)
    {
        //Calculate force as a whole, then break up into x and y vectors using trig
        double totalXForce = 0;
        double totalYForce = 0;
        
        for(int i = 0; i < system.size(); i++)
        {
            if(!(i == ind))
            {
                Planet p2 = system.get(i);
                //The planet can be on either side, force needs to be negative in some cases
                double xDist = p.getCenter().x - p2.getCenter().x;
                double yDist = p.getCenter().y - p2.getCenter().y;
                double totalForce = (p.getMass() * p2.getMass())/(Math.pow(Math.hypot(xDist,yDist),2));
                double angle = Math.atan2(yDist, xDist);
                double xForce = totalForce * Math.cos(angle);
                double yForce = totalForce * Math.sin(angle);
                totalXForce -= xForce;
                totalYForce -= yForce;
            }
        }
        
        double xAccel = totalXForce/p.getMass();
        double yAccel = totalYForce/p.getMass();
        Vector2D accel = new Vector2D(xAccel, yAccel);
        //if(ind%2 == 1)
        //{
          //  System.out.println("Xaccel: " + xAccel + " Yaccel: " + yAccel);
        //}
        return accel;
    }
}
