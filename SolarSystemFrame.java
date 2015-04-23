import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SolarSystemFrame extends JFrame
{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;
    public SolarSystemComponent component;
    public SolarSystemFrame()
    {       
        component = new SolarSystemComponent(FRAME_WIDTH, FRAME_HEIGHT);
        add(component);
        addWindowListener(new FrameWindowListener());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
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