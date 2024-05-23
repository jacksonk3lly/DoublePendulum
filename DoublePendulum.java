import javax.swing.*;
import java.awt.*;

public class DoublePendulum {

    static AccelerationPanel acceleration;
    static JFrame frame = new JFrame();
    static MotionPanel panel = new MotionPanel();

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setBackground(Color.black);
        frame.add(new ControlPanel(), BorderLayout.EAST);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        acceleration = new AccelerationPanel();
        frame.add(acceleration, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}