import java.util.ArrayList;
import java.math.BigInteger;
import java.math.BigDecimal;

public class SolarSystem
{
    private ArrayList<Planet> system;
    private final double G = 6.67384E-11;
    private double PIXELS_TO_METERS;
    private double METERS_TO_PIXELS;
    private double deltaTime;
    
    public SolarSystem(int numPlanets, int screenWidth, int screenHeight, double dT)
    {
        double distanceToPluto = 5.9064E12;
        deltaTime = dT;
        PIXELS_TO_METERS = distanceToPluto/screenWidth; //Distance to pluto divided by width of screen
        METERS_TO_PIXELS = 1/PIXELS_TO_METERS;
        system = new ArrayList<Planet>();
        Planet sun = new Planet(screenHeight, screenWidth, false); //the sun
        sun.setCenter(new Vector2D(screenWidth/2,screenHeight/2));
        sun.setMass(1.989E30);
        system.add(sun);
        
        for(int i = 0; i < numPlanets; i++)
        {
            //add private final constant for min and max velocities
            system.add(new Planet(screenHeight, screenWidth, true));
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
            acceleration.x *= deltaTime;
            acceleration.y *= deltaTime;
            Vector2D currVel= p.getVelocity();
            Vector2D newVel = new Vector2D(currVel.x + acceleration.x, currVel.y + acceleration.y);
            p.setVelocity(newVel);
            Vector2D oldCenter = p.getCenter();
            double newX = oldCenter.x + (newVel.x * deltaTime * METERS_TO_PIXELS);
            double newY = oldCenter.y + (newVel.y * deltaTime * METERS_TO_PIXELS);
            Vector2D newCenter = new Vector2D(newX, newY);
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
                double xDist = (p.getCenter().x - p2.getCenter().x) * PIXELS_TO_METERS;
                double yDist = (p.getCenter().y - p2.getCenter().y) * PIXELS_TO_METERS;
                double totalDist = Math.hypot(xDist,yDist);
                double totalForce = (G * p.getMass() * p2.getMass())/(Math.pow(totalDist,2));
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
           //System.out.println("XAccel: " + xAccel + " YAccel: " + yAccel);
        //}
        return accel;
    }
}
