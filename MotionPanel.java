import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MotionPanel extends JPanel {

    public Pendulumn pendulumn;
    public static int fps = 60;
    double prevTime;

    public MotionPanel() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(700, 700));
        pendulumn = new Pendulumn(350, 350, Math.PI, Math.PI / 2, 0, 0, 150, 150, 10, 10, 0.5);
        // one second is 1000 milliseconds
        Timer timer = null;
        int wantedDelay = 1000 / fps;
        timer = new Timer(wantedDelay - 2, e -> {
            double actualDelay = System.currentTimeMillis() - prevTime;
            System.out.println((actualDelay) / 1000.0);
            prevTime = System.currentTimeMillis();
            repaint();
            if (actualDelay > 0.01) {
                try {
                    pendulumn.step((wantedDelay * 7) / 1000.0);
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());

                }
            }
        });
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int y2 = e.getY();
                int x2 = e.getX();
                pendulumn.setThetas(x2, y2);
                pendulumn.angularVelocity1 = 0;
                pendulumn.angularVelocity2 = 0;
            }
        });

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pendulumn.draw(g);
    }

}
