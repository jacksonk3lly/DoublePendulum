import java.awt.*;
import javax.swing.*;

public class MotionPanel extends JPanel {

    public Pendulumn pendulumn;

    public MotionPanel() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(700, 700));
        pendulumn = new Pendulumn(350, 350, Math.PI, Math.PI / 2, 0, 0, 150, 150, 10, 10);
        Timer timer = new Timer(100, e -> {
            repaint();
            pendulumn.step();
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        pendulumn.draw(g);
    }

}