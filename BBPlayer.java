/*
 * Representing the initial BBPlayer class
 * TODO not sure whether to have 1 robot or more than one
 */

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.Line2D;

class BBPlayer 
{
    private BufferedImage screen;
    private Robot rob;
    private Color col;

    private Finder finder;
    private Swiper swiper;
    
    public BBPlayer()
    {
        try {
            this.rob = new Robot();
            this.finder = new Finder(new Rectangle(0,0,1000,1000), rob);
            this.swiper = new Swiper(rob);
            try {
                this.swiper.swipe(new Line2D.Double(30,160,500,700),200);
            }catch(Exception e){
            }
        } catch(Exception e) { 
        }
    }

    public Point getMidPoint(Point one, Point two)
    {
        double x = (one.getX() + two.getX()) / 2;
        double y = (one.getY() + two.getY()) / 2;
        return new Point((int)x, (int)y);
    }

    public void play() throws InterruptedException
    {
        while(true)
        {
            Point p1 = this.finder.findBall();
            System.out.println(" x1 " + p1.getX() + ", y1" + p1.getY());
            Point p2 = this.finder.findNet();
            Point end = this.getMidPoint(p1,this.getMidPoint(p1,p2)); //1/4 original length
            System.out.println(" x.end " + end.getX() + ", y.end" + end.getY());
            Line2D.Double swipeLine = new Line2D.Double(p1.getX(), p1.getY(), end.getX(), end.getY());

            this.swiper.swipe(swipeLine,200);
            Thread.sleep(1000);
        }
    }
}
