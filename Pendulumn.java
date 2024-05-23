import java.lang.Math;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The Pendulumn class represents a double pendulum system.
 * It calculates the positions and movements of the pendulum bobs based on given
 * parameters.
 * The class also provides methods to set and retrieve the positions and
 * velocities of the pendulum bobs.
 * The pendulum system can be updated by calling the `step` method to simulate
 * the passage of time.
 * The class also provides a method to draw the pendulum system on a graphics
 * object.
 */
public class Pendulumn {

    /*
     * Inner class to populate the trail queue
     * takes a hue and cooridinates
     */
    private class TrailPoint {
        int x;
        int y;
        float hue;
        int width = 2;

        public TrailPoint(int x, int y, float hue) {
            this.x = x;
            this.y = y;
            this.hue = hue;
        }
    }

    public Deque<TrailPoint> trail = new LinkedList<TrailPoint>();
    static int maxLength = 170;
    int trailLength = 300;
    boolean hasClicked = false;
    double origionalShade = 50;
    double shade = origionalShade;
    int m1;
    int m2;
    final int x0;
    final int y0;
    double x1;
    double y1;
    double x2;
    double y2;
    double l1;
    double l2;
    double angularVelocity1;
    double angularVelocity2;
    double theta1;
    double theta2;
    final double g = 9.8;
    final double dampening;
    float hue = 0f;
    double paintbobX;
    double paintbobY;
    double paintClickX;
    double paintClickY;
    double paintM;
    double paintC;

    public Pendulumn(int x0, int y0, double theta1, double theta2, double angularVelocity1, double angularVelocity2,
            double l1, double l2, int m1, int m2, double dampening) {
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

    /*
     * imagine a circle of radius l1 around (x0, y0)
     * and a circle of radius l2 around (the given point) the intersection of these
     * points is a potential position for bob1
     * 
     * equation for circle 1: (x-x0)^2 + (y-y0)^2 = l1^2
     * 
     * equation for circle 2: (x-xn)^2 + (y-yn)^2 = l2^2
     * 
     * found thier intesection with help from this video
     * 
     * https://www.youtube.com/watch?v=K8AfRtfwEdc
     * https://www.youtube.com/watch?v=PSlWb90JJx4
     */
    public boolean setBob2Potition(int xn, int yn) {
        // equation of line which cuts through the circles intersection

        double clickDistanceFrom0 = Math.sqrt(Math.pow(x0 - xn, 2) + Math.pow(y0 - yn, 2));
        if (clickDistanceFrom0 > l2 + l1)
            return false;
        if (clickDistanceFrom0 < Math.abs(l2 - l1))
            return false;
        hasClicked = true;
        shade = origionalShade;
        trail.clear();
        double m = (double) (xn - x0) / (y0 - yn);
        double c = (x0 * x0 + y0 * y0 + l2 * l2 - (xn * xn + yn * yn + l1 * l1)) / (2 * (y0 - yn));
        double A = 1 + m * m;
        double B = 2 * (m * (c - y0) - x0);
        double C = x0 * x0 + Math.pow(c - y0, 2) - l1 * l1;

        // bob one x position using quadratic formula
        // only need one solution so just take positive root
        double posOrNeg = Math.random() < 0.5 ? -1 : 1;
        double X = (-B + posOrNeg * Math.sqrt(B * B - 4 * A * C)) / (2 * A);
        double Y = m * X + c;
        paintbobX = X;
        paintbobY = Y;
        paintClickX = xn;
        paintClickY = yn;
        paintM = m;
        System.out.println("m: " + m);
        System.out.println("yn: " + yn);
        System.out.println("X: " + X);
        System.out.println("theta1: " + theta1);
        System.out.println("Oldtheta1: " + theta1);
        paintC = c;
        theta1 = Math.atan((X - x0) / (Y - y0));
        if (Y < y0)
            theta1 += Math.PI;

        theta2 = Math.atan((X - xn) / (Y - yn));
        if (yn < Y)
            theta2 += Math.PI;
        System.out.println("newTheta1: " + theta1);
        // theta2 = Math.atan((x2 - X) / (y2 - Y));
        return true;
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
        hue += 0.005f;
        double angularAcceleration1 = getAngularAcceleration1();
        double angularAcceleration2 = getAngularAcceleration2();
        angularVelocity1 += angularAcceleration1 * changeInTime * dampening;
        angularVelocity2 += angularAcceleration2 * changeInTime * dampening;
        theta1 += angularVelocity1 * changeInTime * dampening;
        theta2 += angularVelocity2 * changeInTime * dampening;
        AccelerationPanel.addValue1((int) (angularAcceleration1 * 150));
        AccelerationPanel.addValue2((int) (angularAcceleration2 * 150));
        DoublePendulumn.acceleration.repaint();
        refreshPositions();
        addToTrail((int) x2, (int) y2, hue);
    }

    private void addToTrail(int x, int y, float hue) {
        TrailPoint point = new TrailPoint(x, y, hue);
        trail.add(point);
        if (trail.size() > trailLength) {
            trail.remove();
        }
    }

    private void refreshPositions() {

        x1 = x0 + l1 * Math.sin(theta1);
        y1 = y0 + l1 * Math.cos(theta1);
        x2 = x1 + l2 * Math.sin(theta2);
        y2 = y1 + l2 * Math.cos(theta2);

        // throw error if y2>l1+l2

    }

    /*
     * method which draws the pendulumn
     * 
     * @param Graphics object
     * 
     */
    public void draw(Graphics g) {
        if (hasClicked) {
            // tangent to two large circles's intersection
            int xa = 0;
            int xb = 900;
            // grey
            g.setColor(new Color((int) shade, (int) shade, (int) shade));
            g.drawLine((int) xa, (int) (paintM * xa + paintC), (int) xb, (int) (paintM * xb + paintC));
            // two large circles
            g.drawOval((int) (paintClickX - l2), (int) (paintClickY - l2), (int) l2 * 2, (int) l2 * 2);
            g.drawOval((int) (x0 - l1), (int) (y0 - l1), (int) l1 * 2, (int) l1 * 2);
            // new bob1 prediction
            // g.fillOval((int) paintbobX, (int) paintbobY, 10, 10);
            shade -= .5;
            if (shade < 0) {
                shade = origionalShade;
                hasClicked = false;
            }

        }
        // trail
        float trailBrightness = 0f;
        float endBrightness = .6f;
        for (TrailPoint point : trail) {
            g.setColor(Color.getHSBColor(point.hue, .3f, trailBrightness));
            g.fillOval(point.x, point.y, point.width, point.width);
            trailBrightness += endBrightness / trail.size();
        }
        // pendulumn
        g.setColor(Color.getHSBColor(hue, 1f, 1f));
        // l1
        g.drawLine((int) x0, (int) y0, (int) x1, (int) y1);
        // bob1
        g.fillOval((int) x1 - m1 / 2, (int) y1 - m1 / 2, m1, m1);
        // l2
        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        // bob2
        g.fillOval((int) x2 - m2 / 2, (int) y2 - m2 / 2, (int) m2, (int) m2);
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