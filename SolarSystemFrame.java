import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Frame for the simulation
 * 
 * @author Rob Norton
 * @version 4/24/15
 */
public class SolarSystemFrame extends JFrame
{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;
    public SolarSystemComponent component;
    /**
     * Constructor for SolarSystemFrame, creates a SolarSystemComponent and a window listener to allow
     * keyboard events to happen in the frame
     *
     */
    public SolarSystemFrame()
    {       
        component = new SolarSystemComponent(FRAME_WIDTH, FRAME_HEIGHT);
        add(component);
        addWindowListener(new FrameWindowListener());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    /**
     * Used in the main loop of the simulation, this updates the position of every body in the simiulaton
     *
     */
    public void updateSystem()
    {
        component.updateSystem();
    }
    
    class FrameWindowListener extends WindowAdapter
    {
        public void windowOpened(WindowEvent event)
        {
            component.requestFocusInWindow();
        }
    }
}