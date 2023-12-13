package MapAndTaxis;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Car {
    public static void taxiJourney() {
        System.out.println("Your taxi journey has begun");
        JFrame frame = new JFrame("Taxi Journey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CarPannel());
        frame.pack();
        frame.setVisible(true);

        // Schedule the frame to close after 11.5 seconds (11,500 milliseconds)
        Timer timer = new Timer(11500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the frame
            }
        });
        timer.setRepeats(false); // Set to execute only once
        timer.start(); // Start the timer

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
