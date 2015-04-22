import javax.swing.JFrame;
import java.awt.Graphics;

public class SolarSystemViewer
{
    public static void main(String[] args) throws InterruptedException
    {
        JFrame frame = new SolarSystemFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        SolarSystemFrame frame2 = (SolarSystemFrame) frame;
        while(true)
        {
            frame2.updateSystem();
            Thread.sleep(2);
            frame.repaint();
        }
    }
}
