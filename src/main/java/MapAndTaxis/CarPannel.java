package MapAndTaxis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
@SuppressWarnings("serial")

public class CarPannel extends JPanel {
    Color SKYBLUE = new Color (153, 204, 255);
    Color LIGHTS = new Color (255, 255, 153);
    private final int DELAY = 20;
    private final int WIDTH = 1550, HEIGHT = 100;
    private int x,moveX;
    private Timer timer;

    public CarPannel(){
        timer = new Timer(DELAY, new CarMove());
        x = 0;
        moveX = 4;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(SKYBLUE);
        timer.start();
    }


    int [] xBody = {20, 20, 40, 50, 90, 100, 120, 120};
    int [] yBody = {70, 40, 40, 20, 20, 40, 40, 70};
    int [] xLwindow = {45, 55, 67, 67};
    int [] yLwindow = {40, 25, 25, 40};
    int [] xRwindow = {72, 72, 85, 95};
    int [] yRwindow = {40, 25, 25, 40};
    int [] xHeadlight = {118, 170, 170};
    int [] yHeadlight = {50, 32, 60};

    public void paintComponent(Graphics page)
    {

        super.paintComponent(page);
        //car body
        page.setColor(Color.RED);
        page.fillPolygon(xBody, yBody, 8);

        //wheels
        page.setColor(Color.black);
        page.fillOval(25+x, 65, 20, 20);
        page.setColor(Color.black);
        page.fillOval(95+x, 65, 20, 20);

        //window
        page.setColor(Color.GRAY);
        page.fillPolygon(xLwindow, yLwindow, 4);
        page.fillPolygon(xRwindow, yRwindow, 4);

        //headlights
        page.setColor(LIGHTS);
        page.fillPolygon(xHeadlight, yHeadlight, 3);
    }

    private class CarMove implements ActionListener{
        public void actionPerformed(ActionEvent event){
            for (int k=0; k<xBody.length; k++ )
                xBody[k]+=moveX;
            for (int k=0; k<xLwindow.length; k++)
                xLwindow[k]+=moveX;
            for (int k=0; k<xRwindow.length; k++)
                xRwindow[k]+=moveX;
            for (int k=0; k<xHeadlight.length; k++)
                xHeadlight[k]+=moveX;
            x +=moveX;
            repaint();
        }
    }

}
