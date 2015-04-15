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
}
