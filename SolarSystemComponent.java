import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class SolarSystemComponent extends JComponent
{
    private SolarSystem system;
    private boolean up,left,down,right,add;
    private int width, height;
    private MousePressListener mListener;
    public SolarSystemComponent(int frameWidth, int frameHeight)
    {
        system = new SolarSystem(1000, frameWidth, frameHeight, 10);
        width = frameWidth;
        height = frameHeight;
        up = false;
        right = false;
        down = false;
        left = false;
        add = false;
        KeyEventListener key = new KeyEventListener();
        addKeyListener(key);
        mListener = new MousePressListener();
        addMouseListener(mListener);
        setFocusable(true);
    }
    
    public void paintComponent(Graphics g)
    {
        ArrayList<Planet> planets = system.getPlanets();
        Graphics2D g2 = (Graphics2D) g;
        if(add)
        {
            Planet p2 = new Planet(width, height);
            Scanner s = new Scanner(System.in);
            System.out.println("Enter mass: ");
            double mass = s.nextDouble();
            System.out.println("Enter x velocity: ");
            double xVel = s.nextDouble();
            System.out.println("Enter y velocity: ");
            double yVel = s.nextDouble();
            p2.setMass(mass);
            p2.setVelocity(new Vector2D(xVel,yVel));
            p2.setCenter(new Vector2D(mListener.getX(), mListener.getY()));
            system.addPlanet(p2);
            add = false;
        }
        
        for(int i = 0; i < planets.size(); i++)
        {
            Planet p = planets.get(i);
            Vector2D center = p.getCenter();
            if(left)
            {
                center.setX(center.x + 5);
                p.setCenter(center);
            }
                
            if(down)
            {
                center.setY(center.y - 5);
                p.setCenter(center);
            }
            
            if(up)
            {
                center.setY(center.y + 5);
                p.setCenter(center);
            }
                
            if(right)
            {
                center.setX(center.x - 5);
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
            if(p.getMass() > 1000)
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
        
        public void keyPressed(KeyEvent event)
        {
            int e = event.getKeyCode();
            
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
    
    public class MousePressListener extends MouseAdapter
    {
        private int x;
        private int y;
        public MousePressListener()
        {
            x = 0;
            y = 0;
        }
        
        public void mousePressed(MouseEvent e)
        {
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                add = true;
                x = e.getX();
                y = e.getY();
            }
        }
        
        public int getX()
        {
            return x;
        }
        
        public int getY()
        {
            return y;
        }
    }
}