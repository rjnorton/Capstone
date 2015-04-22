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
        system = new SolarSystem(500, frameWidth, frameHeight, 10);
    }
    
    public void paintComponent(Graphics g)
    {
        ArrayList<Planet> planets = system.getPlanets();
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < planets.size(); i++)
        {
            Planet p = planets.get(i);
            int red = 70 + (int)(p.getMass() * 2);
            int green = 120 + (int)(p.getMass() * 3);
            int blue = 200 - (int)(p.getMass() * 4);
            if(blue < 0)
            {
                blue = 0;
            }
            if(red > 255)
            {
                red = 255;
            }
            if(green > 255)
            {
                green = 255;
            }
            Color c = new Color(red,green,blue);
            g2.setColor(c); 
            g2.draw(p.getCircle());
            g2.fill(p.getCircle());
        }
    }
    
    public void updateSystem()
    {
        system.updateSystem();
    }
}