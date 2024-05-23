import javax.swing.*;
import java.awt.*;

public class DoublePendulumn {

    static AccelerationPanel acceleration = new AccelerationPanel();
    static JFrame frame = new JFrame();
    static MotionPanel panel = new MotionPanel();

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setBackground(Color.black);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(acceleration, BorderLayout.SOUTH);
        frame.add(new ControlPanel(), BorderLayout.EAST);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}