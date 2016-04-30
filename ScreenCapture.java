

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Thread;

/*
 * Separate thread to do screen capturing things
 */
class ScreenCapture extends Thread
{
    private BufferedImage screen;
    private Color net;
    private Rectangle capture;
    private ScreenCapture capt;
    private Robot rob;
    private Finder finder;

    public ScreenCapture(Rectangle capture, Finder finder, Robot rob)
    {
        try {
            this.finder = finder;
            this.capture = capture;
            this.rob = rob;
        } catch(Exception e) {
            //oh no
        }
    }

    public void run()
    {
        while(true) 
        {
            this.finder.setScreen(this.rob.createScreenCapture(this.capture));
            this.rob.delay(20);
        }
    }
}
