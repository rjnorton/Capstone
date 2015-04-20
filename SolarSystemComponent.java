import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class SolarSystemComponent extends JComponent
{
    SolarSystem system;
    
    public SolarSystemComponent(int frameWidth, int frameHeight)
    {
        system = new SolarSystem(20, frameWidth, frameHeight, 86400);
    }
    
    public void paintComponent(Graphics g)
    {
        ArrayList<Planet> planets = system.getPlanets();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN); //(Mass / 100)%26 or something to get color 
        for(int i = 0; i < planets.size(); i++)
        {
            g2.draw(planets.get(i).getCircle());
            g2.fill(planets.get(i).getCircle());
        }
    }
    
    public void updateSystem()
    {
        system.updateSystem();
    }
}