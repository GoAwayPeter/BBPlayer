
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * This class finds things in the capture area
 * very primitively, but well enough for our
 * purposes
 *
 * TODO ScreenCapture must only update screen after findColor has run
 * TODO Other thread synchronization
 */
class Finder
{
    private BufferedImage screen;
    private int netColor;
    private int ballColor;
    private ScreenCapture capt;
    private Rectangle capture;

    private int captureWidth;
    private int captureHeight;


    private int roughNetX;
    private int roughNetY;

    public Finder(Rectangle capture, Robot rob)
    {
        this.capture = capture;
        this.captureWidth = (int)this.capture.getWidth();
        this.captureHeight = (int)this.capture.getHeight();
        this.capt = new ScreenCapture(this.capture, this, rob);
        this.capt.start();
        this.netColor = 0xFFFF260F;
        this.ballColor = 0xFFFF7800;
    }

    /*
     * Primitive function to find the box containing 
     * the specified color.
     * Won't work if the color is found elsewhere on the screen.
     */
    private synchronized Rectangle findColor(int color)
    {
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        int increment = 5;

        for(int x = 0;x < this.captureWidth; x+=increment)
        {
            for(int y = 0; y < this.captureHeight; y++)
            {
                if(color == this.screen.getRGB(x,y)) 
                    x1 = x;
            }
        }

        for(int y = 0;y < this.captureWidth; y+=increment)
        {
            for(int x = 0; x < this.captureHeight; x++)
            {
                if(color == this.screen.getRGB(x,y)) 
                    y1 = y;
            }
        }

        for(int x = this.captureWidth;x > 0; x-=increment)
        {
            for(int y = 0; y < this.captureHeight; y++)
            {
                if(color == this.screen.getRGB(x,y)) 
                    x2 = x;
            }
        }

        for(int y = this.captureWidth;y > 0; y-=increment)
        {
            for(int x = 0; x < this.captureHeight; x++)
            {
                if(color == this.screen.getRGB(x,y)) 
                    y2 = y;
            }
        }
        return new Rectangle(x1,y1,x2-x1,y2-y1);
    }


    private Point getRectMidPoint(Rectangle p)
    {
        int xMid = (int)p.getX() + (int)p.getWidth()/2; 
        int yMid = (int)p.getY() + (int)p.getHeight()/2;

        return new Point(xMid,yMid);
    }

    //TODO findNet or findBall must only be called after setScreen();
    public Point findNet()
    {
        return this.getRectMidPoint(this.findColor(this.netColor));
    }

    public Point findBall()
    {
        return this.getRectMidPoint(this.findColor(this.ballColor));
    }

    public synchronized BufferedImage setScreen(BufferedImage screen)
    {
        return this.screen = screen;
    }
}
