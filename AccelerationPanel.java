import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AccelerationPanel extends JPanel {

    final int WIDTH = 700;
    final int HEIGHT = 400;
    final int MEASURMENTS = 50;
    public static ArrayList<Integer> list = new ArrayList<Integer>();

    public AccelerationPanel() {
        setBackground(Color.blue);
        setPreferredSize(new Dimension(WIDTH, 500));
    }

    public void addValue(int value) {
        list.add(value);
        if (list.size() > MEASURMENTS) {
            list.remove(0);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < MEASURMENTS; i++) {
            g.setColor(Color.white);
            int step = WIDTH / MEASURMENTS;
            if (i < list.size()) {
                g.setColor(Color.white);
                g.fillOval(i * step, list.get(i), i, HEIGHT);
            } else {
                list.add(null); // Add null value to the ArrayList
            }
        }
    }

}
