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
        setPreferredSize(new Dimension(200, DoublePendulumn.frame.getHeight()));
        // setBackground(Color.black);
        // Add the sliders and buttons here
        JSlider[] sliders = {
                new JSlider(JSlider.HORIZONTAL, 10, Pendulumn.maxLength, (int) DoublePendulumn.panel.pendulumn.l1),
                new JSlider(JSlider.HORIZONTAL, 10, Pendulumn.maxLength, (int) DoublePendulumn.panel.pendulumn.l2),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10),
                new JSlider(JSlider.HORIZONTAL, 10, maxMass, 10) };
        for (JSlider slider : sliders) {
            slider.setBackground(Color.white);
            slider.setForeground(Color.white);
        }

        sliders[0].addChangeListener(e -> {
            // Set the value of l1 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l1 = sliders[0].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();

        });
        addLabel("Length 1");
        add(sliders[0]);

        sliders[1].addChangeListener(e -> {
            // Set the value of l2 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l2 = sliders[1].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
        });
        addLabel("Length 2");
        add(sliders[1]);

        sliders[2].addChangeListener(e -> {
            // Set the value of m2 in the pendulumn object
            DoublePendulumn.panel.pendulumn.m2 = sliders[2].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
        });
        addLabel("Mass 1");
        add(sliders[2]);

        sliders[3].addChangeListener(e -> {
            // Set the value of m1 in the pendulumn object
            DoublePendulumn.panel.pendulumn.m1 = sliders[3].getValue();
            DoublePendulumn.panel.pendulumn.trail.clear();
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
