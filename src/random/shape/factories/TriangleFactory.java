package random.shape.factories;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

/**
 * Created by Azal on 4/14/2017.
 */
public class TriangleFactory extends JPanel {
    private static class Point {
        final int x;
        final int y;
        final Color color;
        private Point (int x, int y , Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    private static final int SIZE = 940;
    private static final Point topPoint = new Point (SIZE / 2, 0, Color.RED);
    private static final Point leftPoint = new Point (0, SIZE-1, Color.GREEN);
    private static final Point rightPoint = new Point (SIZE-1, SIZE-1, Color.BLUE);
    private static Point sourcePoint = new Point (SIZE-1, SIZE-1, Color.BLACK);


    private static TriangleFactory instance;
    private static BufferedImage bi;
    private static JLabel jLabel;
    static {
        try {
            instance = new TriangleFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private TriangleFactory() throws Exception {
        if(instance!=null){
            throw  new Exception("No way to create more objects!");
        }
        prepareBufferedImage();

    }

    private void prepareBufferedImage() {
        bi = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        ImageIcon icon = new ImageIcon( bi );
        jLabel = new JLabel(icon);
        add(jLabel);
        for (int y = 0; y < SIZE; y += 1) {
            for (int x = 0; x < SIZE; x++) {
                bi.setRGB(x, y, Color.BLACK.getRGB());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        createAndShowShape();
    }

    private static void createAndShowShape() throws InterruptedException {
        createAndShowFrame();
        drawShape();
    }

    private static void createAndShowFrame() {
        JFrame frame = new JFrame("Rawan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(instance);
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    private static void drawShape() throws InterruptedException {
        drawPixelDependingOnGravity(topPoint);
        drawPixelDependingOnGravity(leftPoint);
        drawPixelDependingOnGravity(rightPoint);

        for (int i = 0; i < SIZE*44; i += 1) {
            sleep(0,4);
 //           int x = (int) (Math.random() * SIZE);
 //           int y = (int) (Math.random() * SIZE);
 //           sourcePoint = new Point(x, y, Color.BLACK);
            drawPixelDependingOnGravity(sourcePoint);
            jLabel.repaint();
        }
        Toolkit.getDefaultToolkit().beep();
    }

    private static void drawPixelDependingOnGravity(Point point) {
        int chosenPoint = (int) (Math.random() * 3);
        int x = 0, y = 0;

//        if(distanceTop < distanceLeft && distanceTop < distanceRight) {
        if(chosenPoint == 0) {
            x = (point.x + topPoint.x)/2;
            y = (point.y + topPoint.y)/2;
        }
//      if (distanceLeft < distanceRight && distanceLeft < distanceTop) {
        if(chosenPoint == 1) {
            x = (point.x + leftPoint.x)/2;
            y = (point.y + leftPoint.y)/2;
        }
//        if (distanceRight < distanceTop && distanceRight < distanceLeft) {
        if(chosenPoint == 2) {
            x = (point.x + rightPoint.x)/2;
            y = (point.y + rightPoint.y)/2;
        }
        sourcePoint = new Point(x, y, Color.BLACK);
        final double base = SIZE*2;
        double distanceTop = calculeteDistance(topPoint, sourcePoint);
        double distanceLeft = calculeteDistance(leftPoint, sourcePoint);
        double distanceRight = calculeteDistance(rightPoint, sourcePoint);
        int R = (int)(255 - ((distanceTop/base)*256));
        int G = (int)(255 - ((distanceLeft/base)*256));
        int B = (int)(255 - ((distanceRight/base)*256));
        Color color = new Color(R, G, B);

        bi.setRGB(x, y, color.getRGB());
    }

    private static double calculeteDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) +Math.pow(p2.y - p1. y, 2));
    }
}
