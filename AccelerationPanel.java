import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AccelerationPanel extends JPanel {

    final int WIDTH = DoublePendulum.frame.getWidth();
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

    private void plotList(ArrayList<Integer> list, Graphics g, float hue) {
        float finalBrighness = 1f;
        float brightness = 0f;
        for (int i = 1; i < MEASURMENTS; i++) {
            int step = WIDTH / MEASURMENTS;
            if (i < list.size()) {
                if (list.get(i) != null && list.get(i - 1) != null) {
                    g.setColor(Color.getHSBColor(hue, 1, brightness));
                    if (i < MEASURMENTS / 9) {
                        brightness += finalBrighness / (MEASURMENTS / 9);
                    }
                    if (i > MEASURMENTS - MEASURMENTS / 9) {
                        brightness -= finalBrighness / (MEASURMENTS / 9);
                    }
                    g.drawLine((i - 1) * step, 100 + list.get(i - 1), i * step, 100 + list.get(i));

                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        plotList(list1, g, 0.5f);
        g.setColor(Color.pink);
        plotList(list2, g, 0.8f);
    }

}
