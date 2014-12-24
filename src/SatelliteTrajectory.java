import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class SatelliteTrajectory {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        int width = 900;
        int height = width / 2;
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Graphics g = frame.getGraphics();
        g.setColor(Color.BLACK);
        g.drawLine(0, width / 4, width, width / 4);
        g.drawLine(width / 2, 0, width / 2, width / 2);

        double x = 0;
        double y = 0;
        double z = 7000000;

        double v = 12000;
        double aa = 45;
        aa = aa / 180 * Math.PI;
        double vx = v * Math.cos(aa);
        double vy = v * Math.sin(aa);
        double vz = 0;

        double M = 5.972e24;
        double G = 6.673e-11;
        double dt = 0.1;
        double ww = 360. / 24. / 3600.;
        double dw = ww * dt;

        while (true) {
            double r2 = x * x + y * y + z * z;
            double F = G * M / r2;
            double ax = -F * x / Math.sqrt(r2);
            double ay = -F * y / Math.sqrt(r2);
            double az = -F * z / Math.sqrt(r2);

            vx += ax * dt;
            vy += ay * dt;
            vz += az * dt;

            x += vx * dt;
            y += vy * dt;
            z += vz * dt;

            double w = Math.asin(x / Math.sqrt(x * x + z * z));
            w = w / Math.PI * 180;
            w = z < 0 ? 180 - w : w;
            w = w - dw;
            w = w > 180 ? w - 360 : w;
            double f = Math.asin(y / Math.sqrt(x * x + y * y + z * z));
            f = f / Math.PI * 180;

            g.drawRect((int) (width / 2 + w / 180 * width / 2), (int) (width / 4 - f / 90 * width / 4), 1, 1);
        }
    }
}
