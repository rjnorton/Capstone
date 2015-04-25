import javax.swing.JFrame;
import java.awt.Graphics;
/**
 * The main viewer class
 * 
 * @author Rob Norton
 * @version 4/24/15
 */
public class SolarSystemViewer
{
    /**
     * The main method for the simulation, creates a new frame then runs through the mainloop
     * with a 10 millisecond delay
     *
     * @param  args The parameters passed into the method (useless)
     */
    public static void main(String[] args) throws InterruptedException
    {
        JFrame frame = new SolarSystemFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        SolarSystemFrame frame2 = (SolarSystemFrame) frame;
        while(true)
        {
            frame2.updateSystem();
            Thread.sleep(10);
            frame.repaint();
        }
    }
}
