package MapAndTaxis;

import javax.swing.JFrame;
public class Car {
    public static void taxiJourney(){
        JFrame frame = new JFrame("Taxi Journey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CarPannel());
        frame.pack();
        frame.setVisible(true);
    }
}
