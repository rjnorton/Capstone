import java.util.ArrayList;

public class SolarSystem
{
    private ArrayList<Planet> system;
    
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
            system.add(new Planet(screenHeight, screenWidth, 2, 10, true));
        }
    }
    
    public ArrayList<Planet> getPlanets()
    {
        return system;
    }
    
    public void update()
    {
        for(int i = 0; i < system.size(); i++)
        {
            Vector2D acceleration = getAccel(system.get(i), i);
            
        }
    }
    
    public Vector2D getAccel(Planet p, int ind)
    {
        double totalXForce = 0;
        double totalYForce = 0;
        for(int i = 0; i < system.size(); i++)
        {
            if(!(i == ind))
            {
                Planet p2 = system.get(i);
                //The planet can be on either side, force needs to be negative in some cases
                double xDist = Math.abs(p.getCenter().x - p2.getCenter().x);
                double yDist = Math.abs(p.getCenter().y - p2.getCenter().y);
                double xForce = (p.getMass() * p2.getMass())/(Math.pow(xDist,2));
                double yForce = (p.getMass() * p2.getMass())/(Math.pow(yDist,2));
                totalXForce += xForce;
                totalYForce += yForce;
            }
        }
    }
}
