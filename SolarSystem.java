import java.util.ArrayList;

public class SolarSystem
{
    private ArrayList<Planet> system;
    private double deltaTime;
    private final double G = .01;
    public SolarSystem(int numPlanets, int screenWidth, int screenHeight, double dT)
    {
        deltaTime = dT;
        system = new ArrayList<Planet>();        
        for(int i = 0; i < numPlanets; i++)
        {
            //add private final constant for min and max velocities
            system.add(new Planet(screenHeight, screenWidth));
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
            double newX = oldCenter.x + (newVel.x * deltaTime);
            double newY = oldCenter.y + (newVel.y * deltaTime);
            Vector2D newCenter = new Vector2D(newX, newY);
            p.setCenter(newCenter);
            
            for(int j = 0; j < system.size(); j++)
            {
                if(j != i)
                {
                    Planet o = system.get(j);
                    if(p.collisions(o))
                    {
                        double initialMomentumX = (p.getMass() * p.getVelocity().x) + (o.getMass() * o.getVelocity().x);
                        double initialMomentumY = (p.getMass() * p.getVelocity().y) + (o.getMass() * o.getVelocity().y);
                        if(p.getMass() > o.getMass())
                        {
                            p.setMass(p.getMass() + o.getMass());
                            double newXVel = initialMomentumX/p.getMass();
                            double newYVel = initialMomentumY/p.getMass();
                            Vector2D v = new Vector2D(newXVel, newYVel);
                            p.setVelocity(v);
                            system.remove(o);
                            p.resize();
                        }
                        else
                        {
                            o.setMass(p.getMass() + o.getMass());
                            double newXVel = initialMomentumX/o.getMass();
                            double newYVel = initialMomentumY/o.getMass();
                            Vector2D v = new Vector2D(newXVel, newYVel);
                            o.setVelocity(v);
                            system.remove(p);
                            o.resize();
                            break;
                        }
                    }
                }
            }
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
                double xDist = p.getCenter().x - p2.getCenter().x;
                double yDist = p.getCenter().y - p2.getCenter().y;
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
