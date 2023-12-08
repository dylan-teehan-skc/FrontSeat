package MapAndTaxis;
import OisinPackage.Review;

import javax.swing.JFrame;

public class Car {
    public static void taxiJourney() {
        System.out.println("Your taxi journey has begun");
        JFrame frame = new JFrame("Taxi Journey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CarPannel());
        frame.pack();
        frame.setVisible(true);
        try {
            Thread.sleep(11500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        frame.dispose();
    }

}

