import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AccelerationPanel extends JPanel {

    final int WIDTH = 700;
    final int HEIGHT = 200;
    final static int MEASURMENTS = 150;
    private static ArrayList<Integer> list1 = new ArrayList<Integer>();
    private static ArrayList<Integer> list2 = new ArrayList<Integer>();

    public AccelerationPanel() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public static void addValue1(int value) {
        list1.add(value);
        if (list1.size() > MEASURMENTS) {
            list1.remove(0);
        }
    }

    public static void addValue2(int value) {
        list2.add(value);
        if (list2.size() > MEASURMENTS) {
            list2.remove(0);
        }
    }

    private void plotList(ArrayList<Integer> list, Graphics g) {
        for (int i = 1; i < MEASURMENTS; i++) {
            int step = WIDTH / MEASURMENTS;
            if (i < list.size()) {
                if (list.get(i) != null && list.get(i - 1) != null) {
                    g.drawLine((i - 1) * step, 100 + list.get(i - 1), i * step, 100 + list.get(i));

                }
            } else {
                list.add(null); // Add null value to the ArrayList
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        plotList(list1, g);
        g.setColor(Color.pink);
        plotList(list2, g);
    }

}
