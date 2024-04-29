import javax.swing.*;
import java.awt.*;

public class DoublePendulumn {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setBackground(Color.black);
        MotionPanel panel = new MotionPanel();
        frame.add(panel, BorderLayout.CENTER);
        AccelerationPanel panel2 = new AccelerationPanel();
        frame.add(panel2, BorderLayout.SOUTH);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}