import javax.swing.*;
import java.awt.*;

public class DoublePendulumn {

    static AccelerationPanel acceleration = new AccelerationPanel();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setBackground(Color.black);
        MotionPanel panel = new MotionPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(acceleration, BorderLayout.SOUTH);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}