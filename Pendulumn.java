import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Pendulumn {
    private double m1;
    private double m2;
    private double x0;
    private double y0;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double l1;
    private double l2;
    private double angularVelocity1;
    private double angularVelocity2;
    private double theta1;
    private double theta2;
    private final double g = 9.8;
    private float hue = 0f;

    public Pendulumn(int x0, int y0, double theta1, double theta2, double angularVelocity1, double angularVelocity2,
            double l1, double l2, double m1, double m2) {
        this.x0 = x0;
        this.y0 = y0;
        this.theta1 = theta1;
        this.theta2 = theta2;
        this.l1 = l1;
        this.l2 = l2;
        this.m1 = m1;
        this.m2 = m2;
        refreshPositions();
    }

    public Point get1() {
        Point point = new Point((int) x1, (int) y1);
        return point;
    }

    public Point get2() {
        Point point = new Point((int) x2, (int) y2);
        return point;
    }

    public void step() {
        double angularAcceleration1 = getAngularAcceleration1();
        double angularAcceleration2 = getAngularAcceleration2();
        angularVelocity1 += angularAcceleration1;
        angularVelocity2 += angularAcceleration2;
        theta1 += angularVelocity1;
        theta2 += angularVelocity2;
        refreshPositions();
        hue += 0.01;
    }

    private void refreshPositions() {
        x1 = x0 + l1 * Math.sin(theta1);
        y1 = y0 + l1 * Math.cos(theta1);
        x2 = x1 + l2 * Math.sin(theta2);
        y2 = y1 + l2 * Math.cos(theta2);

    }

    public void draw(Graphics g) {
        g.setColor(Color.getHSBColor(hue, 1f, 1f));
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        g.fillOval((int) x1 - 5, (int) y1 - 5, 10, 10);
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        g.fillOval((int) x2 - 5, (int) y2 - 5, 10, 10);
    }

    public double getAngularAcceleration1() {
        double num1 = -g * (2 * m1 + m2) * Math.sin(theta1);
        double num2 = -m2 * g * Math.sin(theta1 - 2 * theta2);
        double num3 = -2 * Math.sin(theta1 - theta2) * m2
                * (Math.pow(angularVelocity2, 2) * l2 + Math.pow(angularVelocity1, 2) * l1 * Math.cos(theta1 - theta2));
        double denominator = l1 * (2 * m1 + m2 - m2 * Math.cos(2 * theta1 - 2 * theta2));
        return (num1 + num2 + num3) / denominator;
    }

    public double getAngularAcceleration2() {
        double num1 = 2 * Math.sin(theta1 - theta2);
        double num2 = (Math.pow(angularVelocity1, 2) * l1 * (m1 + m2));
        double num3 = g * (m1 + m2) * Math.cos(theta1);
        double num4 = Math.pow(angularVelocity2, 2) * l2 * m2 * Math.cos(theta1 - theta2);
        double denominator = l2 * (2 * m1 + m2 - m2 * Math.cos(2 * theta1 - 2 * theta2));
        return (num1 * (num2 + num3 + num4)) / denominator;
    }
}
