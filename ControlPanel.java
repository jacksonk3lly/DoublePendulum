import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.*;

public class ControlPanel extends JPanel {
    public int maxMass = 30;

    public ControlPanel() {

        setBackground(new Color(20, 20, 20));
        setPreferredSize(new Dimension(150, DoublePendulumn.frame.getHeight()));
        // setBackground(Color.black);
        // Add the sliders and buttons here
        JSlider[] sliders = { new JSlider(JSlider.HORIZONTAL, 10, 200, 100),
                new JSlider(JSlider.HORIZONTAL, 10, 200, 100),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10) };
        for (JSlider slider : sliders) {
            slider.setBackground(Color.white);
        }

        sliders[0].addChangeListener(e -> {
            // Set the value of l1 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l1 = sliders[0].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();

        });
        add(sliders[0]);

        sliders[1].addChangeListener(e -> {
            // Set the value of l2 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l2 = sliders[1].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
        });

        add(sliders[1]);

        sliders[2].addChangeListener(e -> {
            // Set the value of m2 in the pendulumn object
            DoublePendulumn.panel.pendulumn.m2 = sliders[2].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
        });
        add(sliders[2]);

        sliders[3].addChangeListener(e -> {
            // Set the value of m1 in the pendulumn object
            DoublePendulumn.panel.pendulumn.m1 = sliders[3].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
        });
        add(sliders[3]);

    }

}
