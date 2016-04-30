/*
 * Makes swipes!
 */
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.geom.Line2D;

class Swiper
{
    private Robot rob;

    public Swiper(Robot rob)
    {
        this.rob = rob;
    }

    /*
     * TODO Needs to work for swipes in any direction, horizontal, and vertical swipes
     */
    public void swipe(Line2D.Double line, int speed) throws InterruptedException
    {
        this.rob.mouseMove((int)line.getX1(), (int)line.getY1());
        this.rob.mousePress(InputEvent.BUTTON1_MASK);
        this.rob.mouseMove((int)line.getX2(), (int)line.getY2());
        Thread.sleep(speed);
        this.rob.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
