import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Pendulumn {
    double m1;
    double m2;
    final int x0;
    final int y0;
    double x1;
    double y1;
    double x2;
    double y2;
    final double l1;
    final double l2;
    double angularVelocity1;
    double angularVelocity2;
    double theta1;
    double theta2;
    final double g = 9.8;
    final double dampening;
    float hue = 0f;

    public Pendulumn(int x0, int y0, double theta1, double theta2, double angularVelocity1, double angularVelocity2,
            double l1, double l2, double m1, double m2, double dampening) {
        this.x0 = x0;
        this.y0 = y0;
        this.theta1 = theta1;
        this.theta2 = theta2;
        this.l1 = l1;
        this.l2 = l2;
        this.m1 = m1;
        this.m2 = m2;
        this.dampening = dampening;
        try {
            refreshPositions();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Point get1() {
        Point point = new Point((int) x1, (int) y1);
        return point;
    }

    public Point get2() {
        Point point = new Point((int) x2, (int) y2);
        return point;
    }

    public void setTheta1(double theta1) {
        this.theta1 = theta1;
        refreshPositions();
    }

    public void setTheta2(double theta2) {
        this.theta2 = theta2;
        refreshPositions();
    }

    public void setAngularVelocity1(double angularVelocity1) {
        this.angularVelocity1 = angularVelocity1;
    }

    public void setAngularVelocity2(double angularVelocity2) {
        this.angularVelocity2 = angularVelocity2;
    }

    public void step(double changeInTime) {
        double angularAcceleration1 = getAngularAcceleration1() * dampening;
        double angularAcceleration2 = getAngularAcceleration2() * dampening;
        angularVelocity1 += angularAcceleration1 * changeInTime;
        angularVelocity2 += angularAcceleration2 * changeInTime;
        System.out.println("angularVelocity1 " + angularVelocity1 + " angularVelocity2 " + angularVelocity2);
        theta1 += angularVelocity1 * changeInTime;
        theta2 += angularVelocity2 * changeInTime;
        AccelerationPanel.addValue1((int) (angularAcceleration1 * 150));
        AccelerationPanel.addValue2((int) (angularAcceleration2 * 150));
        DoublePendulumn.acceleration.repaint();
        refreshPositions();
        hue += 0.01;
    }

    private void refreshPositions() {

        x1 = x0 + l1 * Math.sin(theta1);
        y1 = y0 + l1 * Math.cos(theta1);
        x2 = x1 + l2 * Math.sin(theta2);
        y2 = y1 + l2 * Math.cos(theta2);

        System.out.println("theta1 " + theta1 / Math.PI + " PI" + " theta2 " + theta2 / Math.PI + " PI");
        // throw error if y2>l1+l2
        if (Math.abs(y2 - y0) > l1 + l2) {
            throw new IllegalArgumentException("y2 is " + (Math.abs(y2 - y0) - (l1 + l2)) + " greater than l1+l2");
        } else if (Math.abs(y2 - y0) < l1 + l2) {
            throw new IllegalArgumentException("y2 is " + ((l1 + l2) - Math.abs(y2 - y0)) + " greater than l1+l2");
        }

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
