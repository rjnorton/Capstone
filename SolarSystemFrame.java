import javax.swing.JFrame;

public class SolarSystemFrame extends JFrame
{
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;
    public SolarSystemFrame()
    {       
        SolarSystemComponent component = new SolarSystemComponent(FRAME_WIDTH, FRAME_HEIGHT);
        add(component);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
}