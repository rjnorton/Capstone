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
import java.util.TimerTask;
import java.util.Timer;

/**
 * The component for the simulation, handles graphics and keyboard/mouse events
 * 
 * @author Rob Norton
 * @version 4/24/15
 * @see SolarSystem
 */
public class SolarSystemComponent extends JComponent
{
    private SolarSystem system;
    private boolean up,left,down,right,add;
    private int width, height;
    private MousePressListener mListener;
    private int totalFrameCount = 0;
    private int FPS = 0;
    private final int NUM_PLANETS = 1000;
    
    /**
     * Constructor for SolarSystemComponent, initializes variables for keyboard and mouse events and FPS
     * and creates keyboard/mouse listeners from anonymous subclasses in this class
     *
     * @param  frameWidth The width of the frame
     * @param  frameHeight The height of the frame
     */
    public SolarSystemComponent(int frameWidth, int frameHeight)
    {
        system = new SolarSystem(NUM_PLANETS, frameWidth, frameHeight, 10);
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
        TimerTask updateFPS = new TimerTask()
        {
            public void run()
            {
                FPS = totalFrameCount;
                totalFrameCount = 0;
            }
        };
        
        Timer t = new Timer();
        t.scheduleAtFixedRate(updateFPS, 1000, 1000);
    }
    
    /**
     * Renders all of the bodies in the simulation, and prints FPS, highest mass, and number of bodies left 
     * int the simulation
     *
     * @param  g The graphics object used in the rendering
     */
    public void paintComponent(Graphics g)
    {
        totalFrameCount++;
        ArrayList<Planet> planets = system.getPlanets();
        Graphics2D g2 = (Graphics2D) g;
        double highestMass = 0;
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
            
            int red = 70 + (int)(p.getMass() * 1.5);
            int green = 100 + (int)(p.getMass() * 2);
            int blue = 200 - (int)(p.getMass() * 3);
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
            if(p.getMass() > highestMass)
            {
                highestMass = p.getMass();
            }
            Color c = new Color(red,green,blue);
            g2.setColor(Color.BLACK);
            g2.drawString("Number of bodies: " + planets.size(), 30, 30);
            g2.setColor(c); 
            g2.draw(p.getCircle());
            g2.fill(p.getCircle());
         }
        g2.setColor(Color.BLACK);
        g2.drawString("Highest Mass: " + Math.floor(100 * highestMass)/100, 30, 50);
        g2.drawString("FPS: " + FPS, 30, 70);
    }
    
    /**
     * Used in the main loop of the simulation, this updates the position of every body in the simiulaton
     *
     */
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
        
        public void mouseReleased(MouseEvent e)
        {
            add = false;
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