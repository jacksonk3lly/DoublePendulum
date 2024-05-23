import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class ControlPanel extends JPanel {
    public int maxMass = 30;

    public ControlPanel() {

        int backShade = 15;
        setBackground(new Color(backShade, backShade, backShade));
        // setBackground(Color.black);
        setPreferredSize(new Dimension(200, DoublePendulum.frame.getHeight()));
        // setBackground(Color.black);
        // Add the sliders and buttons here
        JSlider[] sliders = {
                new JSlider(JSlider.HORIZONTAL, 10, Pendulum.maxLength, (int) DoublePendulum.panel.pendulum.l1),
                new JSlider(JSlider.HORIZONTAL, 10, Pendulum.maxLength, (int) DoublePendulum.panel.pendulum.l2),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10) };
        for (JSlider slider : sliders) {
            slider.setBackground(Color.white);
            slider.setForeground(Color.white);
        }

        sliders[0].addChangeListener(e -> {
            // Set the value of l1 in the pendulum object
            DoublePendulum.panel.pendulum.l1 = sliders[0].getValue();
            DoublePendulum.panel.pendulum.trail.clear();

        });
        addLabel("Length 1");
        add(sliders[0]);

        sliders[1].addChangeListener(e -> {
            // Set the value of l2 in the pendulum object
            DoublePendulum.panel.pendulum.l2 = sliders[1].getValue();
            DoublePendulum.panel.pendulum.trail.clear();
        });
        addLabel("Length 2");
        add(sliders[1]);

        sliders[2].addChangeListener(e -> {
            // Set the value of m2 in the pendulum object
            DoublePendulum.panel.pendulum.m2 = sliders[2].getValue();
            DoublePendulum.panel.pendulum.trail.clear();
        });
        addLabel("Mass 1");
        add(sliders[2]);

        sliders[3].addChangeListener(e -> {
            // Set the value of m1 in the pendulum object
            DoublePendulum.panel.pendulum.m1 = sliders[3].getValue();
            DoublePendulum.panel.pendulum.trail.clear();
        });
        addLabel("Mass 2");
        add(sliders[3]);

    }

    private void addLabel(String text) {
        JLabel label = new JLabel(text);
        int textShade = 200;
        label.setForeground(new Color(textShade, textShade, textShade));
        label.setFont(label.getFont().deriveFont(16f)); // Increase the font size to 16
        add(label);
        add(label);
    }

}
