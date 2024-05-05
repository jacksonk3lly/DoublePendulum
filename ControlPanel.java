import javax.swing.JPanel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        // setBackground(Color.black);
        // Add the sliders and buttons here
        JSlider[] sliders = { new JSlider(JSlider.HORIZONTAL, 10, 200, 100),
                new JSlider(JSlider.HORIZONTAL, 10, 200, 100) };
        for (JSlider slider : sliders) {
            slider.setBackground(Color.white);
        }

        sliders[0].addChangeListener(e -> {
            // Set the value of l1 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l1 = sliders[0].getValue();
        });
        add(sliders[0]);

        sliders[1].addChangeListener(e -> {
            // Set the value of l2 in the pendulumn object
            DoublePendulumn.panel.pendulumn.l2 = sliders[1].getValue();
        });
        add(sliders[1]);

    }

}
