import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class MotionPanel extends JPanel {

    public Pendulumn pendulumn;
    public static int fps = 60;
    double prevTime;

    public MotionPanel() {
        JLabel label = new JLabel(
                "θ1'' =  \t−g (2 m1 + m2) sin θ1 − m2 g sin(θ1 − 2 θ2) − 2 sin(θ1 − θ2) m2 (θ2'2 L2 + θ1'2 L1 cos(θ1 − θ2))\n"
                        + //
                        "L1 (2 m1 + m2 − m2 cos(2 θ1 − 2 θ2))\n" + //
                        "θ2'' =  \t2 sin(θ1 − θ2) (θ1'2 L1 (m1 + m2) + g(m1 + m2) cos θ1 + θ2'2 L2 m2 cos(θ1 − θ2))\n" + //
                        "L2 (2 m1 + m2 − m2 cos(2 θ1 − 2 θ2))\n" + //
                        "");
        add(label);
        label.setVisible(true);
        label.setBackground(Color.white);
        setBackground(Color.black);
        pendulumn = new Pendulumn(2 * Pendulumn.maxLength, 2 * Pendulumn.maxLength, Math.PI, Math.PI / 2, 0, 0, 100,
                80, 10,
                10, 0.5);
        // one second is 1000 milliseconds
        Timer timer = null;
        int wantedDelay = 1000 / fps;
        timer = new Timer(wantedDelay - 2, e -> {
            double actualDelay = System.currentTimeMillis() - prevTime;
            prevTime = System.currentTimeMillis();
            repaint();
            if (actualDelay > 0.01) {
                try {
                    pendulumn.step((wantedDelay * 7) / 1000.0);
                } catch (Exception ex) {

                }
            }
        });
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int y2 = e.getY();
                int x2 = e.getX();

                if (pendulumn.setBob2Potition(x2, y2)) {
                    pendulumn.angularVelocity1 = 0;
                    pendulumn.angularVelocity2 = 0;
                }
            }
        });
        int panelDimension = (int) (4 * (Pendulumn.maxLength));
        setPreferredSize(new Dimension(panelDimension, panelDimension));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pendulumn.draw(g);
    }

}
