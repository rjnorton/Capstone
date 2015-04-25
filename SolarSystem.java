import java.util.ArrayList;

/**
 * The Solar System itself, it holds all the planets and calculates force to
 * determine the new velocity and position of each planet
 * 
 * @author Rob Norton
 * @version 4/24/15
 * @see Planet
 * @see Vector2D
 */
public class SolarSystem
{
    private ArrayList<Planet> system;
    private double deltaTime;
    private final double G = .01;
    
    /**
     * Constructor for SolarSystem, initializes an ArrayList with a specified number of planets
     *
     * @param  numPlanets The number of planets to be created
     * @param  screenWidth The width of the frame
     * @param  screenHeight The height of the frame
     * @param  dT The time step for the simulation
     */
    public SolarSystem(int numPlanets, int screenWidth, int screenHeight, double dT)
    {
        deltaTime = dT;
        system = new ArrayList<Planet>();    
        Planet p = new Planet(screenHeight, screenWidth);
        p.setVelocity(new Vector2D(0,0));
        p.setMass(50);
        p.setCenter(new Vector2D(screenWidth/2, screenHeight/2));
        //system.add(p);
        for(int i = 0; i < numPlanets; i++)
        {
            //add private final constant for min and max velocities
            system.add(new Planet(screenHeight, screenWidth));
        }
    }
    
    /**
     * Returns the ArrayList of the planets
     *
     * @return The ArrayList of planets contained within this SolarSystem object
     */
    public ArrayList<Planet> getPlanets()
    {
        return system;
    }
    
    /**
     * Updates the velocity and position of every body in this SolarSystem with Euler integration
     */
    public void updateSystem()
    {
        for(int i = 0; i < system.size(); i++)
        {
            Planet p = system.get(i);
            Vector2D acceleration = getAccel(p, i);
            if(acceleration != null)
            {
                acceleration.x *= deltaTime;
                acceleration.y *= deltaTime;
                Vector2D currVel= p.getVelocity();
                Vector2D newVel = new Vector2D(currVel.x + acceleration.x, currVel.y + acceleration.y);
                p.setVelocity(newVel);
                Vector2D oldCenter = p.getCenter();
                double newX = oldCenter.x + (newVel.x * deltaTime);
                double newY = oldCenter.y + (newVel.y * deltaTime);
                Vector2D newCenter = new Vector2D(newX, newY);
                p.setCenter(newCenter);
            }
        }
    }
    
    /**
     * Calculates the acceleration of a planet by using the law of
     * universal gravitation on every other planet in the solar system.
     * It also checks if two planets are close enough to collide, and if they are
     * then an elastic collision occurs.
     *
     * @param  p The planet to calculate the acceleration of
     * @param  ind Where Planet p is in the list of planets, so that  it won't calculate the force on itself
     * @return The acceleration represented in Vector2D form
     * @see Vector2D
     */
    public Vector2D getAccel(Planet p, int ind)
    {
        double totalXForce = 0;
        double totalYForce = 0;
        
        for(int i = 0; i < system.size(); i++)
        {
            if(!(i == ind))
            {
                Planet p2 = system.get(i);
                double xDist = (p.getCenter().x - p2.getCenter().x) * 2;
                double yDist = (p.getCenter().y - p2.getCenter().y) * 2;
                double totalDist = Math.hypot(xDist,yDist);
                
                if(totalDist < p.getRadius() + p2.getRadius())
                {
                    double momentumX = (p.getMass() * p.getVelocity().x) + (p2.getMass() * p2.getVelocity().x);
                    double momentumY = (p.getMass() * p.getVelocity().y) + (p2.getMass() * p2.getVelocity().y);
                    if(p.getMass() > p2.getMass())
                    {
                        p.setMass(p.getMass() + p2.getMass());
                        double newXVel = momentumX/p.getMass();
                        double newYVel = momentumY/p.getMass();
                        Vector2D v = new Vector2D(newXVel, newYVel);
                        p.setVelocity(v);
                        system.remove(p2);
                        p.resize();
                        if(ind > i)
                        {
                            ind--;
                        }
                    }
                    else
                    {
                        p2.setMass(p.getMass() + p2.getMass());
                        double newXVel = momentumX/p2.getMass();
                        double newYVel = momentumY/p2.getMass();
                        Vector2D v = new Vector2D(newXVel, newYVel);
                        p2.setVelocity(v);
                        system.remove(p);
                        p2.resize();
                        return null;
                    }
                }
                
                else
                {    
                    double totalForce = (G * p.getMass() * p2.getMass())/(Math.pow(totalDist,2));
                    double angle = Math.atan2(yDist, xDist);
                    double xForce = totalForce * Math.cos(angle);
                    double yForce = totalForce * Math.sin(angle);
                    totalXForce -= xForce;
                    totalYForce -= yForce;
                }
            }
        }
        
        double xAccel = totalXForce/p.getMass();
        double yAccel = totalYForce/p.getMass();
        Vector2D accel = new Vector2D(xAccel, yAccel);
        return accel;
    }
    
    /**
     * Adds a planet to the solar system
     *
     * @param  p The planet to be added
     * 
     */
    public void addPlanet(Planet p)
    {
        system.add(p);
    }
}
