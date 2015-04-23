import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class SolarSystemComponent extends JComponent
{
    private SolarSystem system;
    private boolean up,left,down,right;
    public SolarSystemComponent(int frameWidth, int frameHeight)
    {
        system = new SolarSystem(1, frameWidth, frameHeight, 10);
        up = false;
        right = false;
        down = false;
        left = false;
        KeyEventListener key = new KeyEventListener();
        addKeyListener(key);
        setFocusable(true);
    }
    
    public void paintComponent(Graphics g)
    {
        ArrayList<Planet> planets = system.getPlanets();
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < planets.size(); i++)
        {
            Planet p = planets.get(i);
            Vector2D center = p.getCenter();
            System.out.println(left);
            if(left)
            {
                center.setX(center.x + 10);
                p.setCenter(center);
            }
            
            if(down)
            {
                center.setY(center.y + 10);
                p.setCenter(center);
            }
            
            if(up)
            {
                center.setY(center.y - 10);
                p.setCenter(center);
            }
            
            if(right)
            {
                center.setX(center.x - 10);
                p.setCenter(center);
            }
            
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
            if(blue == 0 && red == 255 && green == 255)
            {
                red = 0;
                green = 0;
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
    
    public class KeyEventListener extends KeyAdapter
    {
        public KeyEventListener()
        {
        }
        
        @Override
        public void keyPressed(KeyEvent event)
        {
            int e = event.getKeyCode();
            System.out.println("wut");
            if(e == KeyEvent.VK_LEFT)
            {
                right = false;
                left = true;
            }
            
            if(e == KeyEvent.VK_RIGHT)
            {
                right = true;
                left = false;
            }
            
            if(e == KeyEvent.VK_UP)
            {
                up = true;
                down = false;
            }
            
            if(e == KeyEvent.VK_DOWN)
            {
                down = true;
                up = false;
            }
            
            repaint();
        }
        
        @Override
        public void keyReleased(KeyEvent event)
        {
            int e = event.getKeyCode();
            if(e == KeyEvent.VK_LEFT)
            {
                left = false;
            }
            
            if(e == KeyEvent.VK_RIGHT)
            {
                right = false;
            }
            
            if(e == KeyEvent.VK_UP)
            {
                up = false;
            }
            
            if(e == KeyEvent.VK_DOWN)
            {
                down = false;
            }
        }
    }
}